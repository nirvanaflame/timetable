package com.lizziputt.consoleapp;

import com.lizziputt.timetable.classroom.Classroom;
import com.lizziputt.timetable.classroom.ClassroomDao;
import com.lizziputt.timetable.classroom.ClassroomService;
import com.lizziputt.timetable.jpa.CrudService;
import com.lizziputt.timetable.student.StudentBatch;
import com.lizziputt.timetable.student.StudentBatchDao;
import com.lizziputt.timetable.student.StudentService;
import com.lizziputt.timetable.subject.Subject;
import com.lizziputt.timetable.subject.SubjectDao;
import com.lizziputt.timetable.subject.SubjectService;
import com.lizziputt.timetable.teacher.Teacher;
import com.lizziputt.timetable.teacher.TeacherDao;
import com.lizziputt.timetable.teacher.TeacherService;
import com.lizziputt.timetable.timesheet.Timesheet;
import com.lizziputt.timetable.timesheet.TimesheetDao;
import com.lizziputt.timetable.timesheet.TimetableService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static com.lizziputt.consoleapp.TimetableBuilder.EXIT_CODE;
import static com.lizziputt.consoleapp.TimetableBuilder.EXIT_TXT;

public class Application {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = emf.createEntityManager();

        ClassroomDao classroomDao = new ClassroomDao(em, Classroom.class);
        StudentBatchDao studentBatchDao = new StudentBatchDao(em, StudentBatch.class);
        SubjectDao subjectDao = new SubjectDao(em, Subject.class);
        TeacherDao teacherDao = new TeacherDao(em, Teacher.class);
        TimesheetDao timesheetDao = new TimesheetDao(em, Timesheet.class);

        ClassroomService classroomService = new ClassroomService(classroomDao);
        StudentService studentService = new StudentService(studentBatchDao);
        SubjectService subjectService = new SubjectService(subjectDao);
        TeacherService teacherService = new TeacherService(teacherDao);
        TimetableService timetableService = new TimetableService(timesheetDao, subjectService, classroomService, studentService, teacherService);


        Menu timetableMenu = TimetableBuilder.buildMenu();
        timetableMenu.printMenu();

        Scanner scan = new Scanner(new InputStreamReader(System.in));
        while (true) {
            String choice = scan.nextLine();
            if (choice.equals(EXIT_CODE) || choice.equals(EXIT_TXT)) break;

            Menu submenu = timetableMenu.select(Integer.parseInt(choice));

            while (true) {
                submenu.printMenuItems();
                Menu.MenuItem menuItem = submenu.selectItem(Integer.parseInt(scan.nextLine()));

                boolean isReturn = false;
                switch (menuItem.strategy()) {
                    case RETURN -> isReturn = true;

                    // Timetable Menu Items
                    case TIMETABLE_FIND_ALL -> printAll(timetableService);
                    case TIMETABLE_FIND_BY_ID -> printRecordById(classroomService, scan, "Timetable");
                    case TIMETABLE_CREATE -> timetableService.createTimetable(scan);
                    case TIMETABLE_UPDATE ->
                            findById(timetableService, scan, "Timetable").ifPresentOrElse(timetableService::updateTimetable, recordNotFound());
                    case TIMETABLE_REMOVE -> removeById(timetableService, scan, "Timetable");

                    // Classroom Menu Items
                    case CLASSROOM_FIND_ALL -> printAll(classroomService);
                    case CLASSROOM_FIND_BY_ID -> printRecordById(classroomService, scan, "Classroom");
                    case CLASSROOM_CREATE -> {
                        System.out.println("Enter name of a new classroom: \n> ");
                        String roomName = scan.nextLine();
                        Classroom cl = classroomService.save(new Classroom(roomName));
                        System.out.println("Classroom with a name: " + cl.getName().toUpperCase() + " is created");
                    }
                    case CLASSROOM_UPDATE ->
                            findById(classroomService, scan, "Classroom").ifPresentOrElse(classroom -> {
                                System.out.println("Enter a new classroom name:\n>");
                                String roomName = scan.nextLine();
                                classroom.setName(roomName);
                                Classroom update = classroomService.update(classroom);
                                System.out.println("Classroom with a name: " + update.getName().toUpperCase() + " is updated");
                            }, recordNotFound());
                    case CLASSROOM_REMOVE -> removeById(classroomService, scan, "Classroom");

                    // Subject Menu Items
                    case SUBJECT_FIND_ALL -> printAll(subjectService);
                    case SUBJECT_FIND_BY_ID -> printRecordById(subjectService, scan, "Subject");
                    case SUBJECT_CREATE -> {
                        System.out.println("Enter name of a new subject: \n> ");
                        String subjectName = scan.nextLine();
                        Subject save = subjectService.save(new Subject(subjectName));
                        System.out.println("Subject with a name: " + save.getName().toUpperCase() + " is created");
                    }
                    case SUBJECT_UPDATE -> findById(subjectService, scan, "Subject").ifPresentOrElse(subject -> {
                        System.out.println("Enter a new classroom name:\n>");
                        String roomName = scan.nextLine();
                        subject.setName(roomName);
                        Subject update = subjectService.update(subject);
                        System.out.println("Subject with a name: " + update.getName().toUpperCase() + " is updated");
                    }, recordNotFound());
                    case SUBJECT_REMOVE -> removeById(subjectService, scan, "Subject");

                    // Teacher Menu Items
                    case TEACHER_FIND_ALL -> printAll(teacherService);
                    case TEACHER_FIND_BY_ID -> printRecordById(teacherService, scan, "Teacher");
                    case TEACHER_CREATE -> {
                        System.out.println("Enter First name of a new teacher: \n> ");
                        String fName = scan.nextLine();
                        System.out.println("Enter Second name of a new teacher: \n> ");
                        String sName = scan.nextLine();
                        System.out.println("Enter Middle name (optional) of a new teacher: \n> ");
                        String mName = scan.nextLine();

                        Teacher teacher = new Teacher(fName, sName, mName);
                        Teacher save = teacherService.save(teacher);
                        System.out.println("Teacher with full name: " + save.getFullName() + " is created");
                    }
                    case TEACHER_UPDATE -> findById(teacherService, scan, "Teacher").ifPresentOrElse(teacher -> {
                        System.out.println("Enter new Full name in order [Surname Firstname Middle-name]: \n> ");
                        String name = scan.nextLine();
                        String[] n = name.split(" ");
                        teacher.setSecondName(n[0]);
                        teacher.setFirstName(n[1]);
                        teacher.setMiddleName(n[2]);
                        Teacher update = teacherService.update(teacher);
                        System.out.println("Teacher with a name: " + update.getFullName().toUpperCase() + " is updated");
                    }, recordNotFound());
                    case TEACHER_REMOVE -> removeById(teacherService, scan, "Teacher");


                    // Student Groups Menu Items
                    case STUDENT_FIND_ALL -> printAll(studentService);
                    case STUDENT_FIND_BY_ID -> printRecordById(studentService, scan, "Student Group");
                    case STUDENT_CREATE -> {
                        System.out.println("Enter name of a new student group: \n> ");
                        String sgName = scan.nextLine();
                        StudentBatch save = studentService.save(new StudentBatch(sgName));
                        System.out.println("Student group with a name: " + save.getName().toUpperCase() + " is created");
                    }
                    case STUDENT_UPDATE -> findById(studentService, scan, "Student Group").ifPresentOrElse(student -> {
                        System.out.println("Enter a new student group name:\n>");
                        String sgName = scan.nextLine();
                        student.setName(sgName);
                        StudentBatch update = studentService.update(student);
                        System.out.println("Student group with a name: " + update.getName().toUpperCase() + " is updated");
                    }, recordNotFound());
                    case STUDENT_REMOVE -> removeById(studentService, scan, "Student Group");
                }
                if (isReturn) break;
            }
            timetableMenu.printMenu();
        }
        System.out.print("Bye!!!");
    }


    private static <T> void printAll(CrudService<T> service) {
        List<T> result = service.findAll();
        if (result.isEmpty()) System.out.println("Nothing to show");
        else result.forEach(System.out::println);
    }

    private static <T> void printRecordById(CrudService<T> service, Scanner scanner, String className) {
        findById(service, scanner, className).ifPresentOrElse(System.out::println, recordNotFound());
    }

    private static <T> Optional<T> findById(CrudService<T> service, Scanner scanner, String className) {
        System.out.printf("Enter %s id:\n> %n", className);
        int id = Integer.parseInt(scanner.nextLine());
        return service.findById(id);
    }

    private static <T> void removeById(CrudService<T> service, Scanner scanner, String className) {
        System.out.printf("Enter %s id for removal: \n> %n", className);
        int id = Integer.parseInt(scanner.nextLine());
        service.delete(id);
        System.out.printf("Record with %s from %s table is deleted%n", id, className);
    }

    private static Runnable recordNotFound() {
        return () -> System.out.println("Record is not found");
    }

}