package com.lizziputt.timetable.timesheet;

import com.lizziputt.timetable.classroom.Classroom;
import com.lizziputt.timetable.classroom.ClassroomService;
import com.lizziputt.timetable.jpa.CrudRepository;
import com.lizziputt.timetable.SimpleMenuService;
import com.lizziputt.timetable.student.StudentBatch;
import com.lizziputt.timetable.student.StudentService;
import com.lizziputt.timetable.subject.Subject;
import com.lizziputt.timetable.subject.SubjectService;
import com.lizziputt.timetable.teacher.Teacher;
import com.lizziputt.timetable.teacher.TeacherService;
import com.lizziputt.util.InputValidator;
import com.lizziputt.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class TimesheetService extends SimpleMenuService<Timesheet> {

    private final SubjectService subjectService;

    private final ClassroomService classroomService;

    private final StudentService studentService;

    private final TeacherService teacherService;

    public TimesheetService(CrudRepository<Timesheet, Integer> crudRepository, SubjectService subjectService, ClassroomService classroomService, StudentService studentService, TeacherService teacherService) {
        super(crudRepository);
        this.subjectService = subjectService;
        this.classroomService = classroomService;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @Override
    public void printAll() {
        List<Timesheet> result = crudRepository.findAll();
        if (result.isEmpty()) System.out.println("Nothing to show!");
        else System.out.println(result.stream().map(Timesheet::timeTableView).collect(Collectors.joining(",\n")));
    }

    @Override
    public void printRecordById() {
        int id = getIdInput();
        if (id != 0) crudRepository.findById(id).ifPresentOrElse(timesheet -> {
            System.out.println(timesheet.timeTableView());
        }, recordNotFound(id));
    }

    public void create() {
        Scanner scan = new Scanner(System.in);
        LocalDateTime dateTime = createDateTime();
        if (dateTime.equals(InputValidator.EXIT_TIME)) return;

        System.out.print("If you don't wanna add subjects/classrooms/students_groups/teachers to the timetable now \nplease type `skip` to do it later or press ENTER to continue...\n> ");

        List<Subject> subjects = new ArrayList<>();
        List<Classroom> classrooms = new ArrayList<>();
        List<StudentBatch> studentBatches = new ArrayList<>();
        List<Teacher> teachers = new ArrayList<>();
        if (!"skip".equals(scan.nextLine())) {
            subjects = addSubject();

            classrooms = addClassrooms();

            studentBatches = addGroups();

            teachers = addTeachers();
        }

        Timesheet timesheet = new Timesheet(dateTime, subjects, classrooms, studentBatches, teachers);

        Timesheet saved = crudRepository.save(timesheet);
        System.out.println(saved + " is saved!");
    }

    public void update() {
        String valid = crudRepository.findAll().stream().map(Timesheet::getTimesheetId).map(String::valueOf).collect(Collectors.joining("|", "[", "]"));
        System.out.printf("Valid options:%s or type EXIT to return%n> ", valid);
        super.executeAction(this::updateTimetable);
    }

    private void updateTimetable(Timesheet timesheet) {
        Scanner scan = new Scanner(System.in);

        System.out.println("What you wanna change?\nValid options: time|subject|classroom|group|teacher");
        String option = scan.nextLine();
        switch (option) {
            case "time" -> {
                LocalDateTime newTime = createDateTime();
                if (newTime.equals(InputValidator.EXIT_TIME)) return;
                timesheet.setTime(newTime);
                crudRepository.update(timesheet);
            }
            case "subject" -> {
                List<Subject> currentSubjects = timesheet.getSubjects();
                System.out.println("What you wanna do with subjects?\n" + "Valid options: ext(extend)|rep(replace all)|rem(remove all)");
                switch (scan.nextLine()) {
                    case "ext" -> currentSubjects.addAll(addSubject());
                    case "rep" -> timesheet.setSubjects(addSubject());
                    case "rem" -> currentSubjects.removeAll(addSubject());
                    default -> failOperation();
                }
                crudRepository.update(timesheet);
            }
            case "classroom" -> {
                List<Classroom> currentClassrooms = timesheet.getClassrooms();
                System.out.println("What you wanna do with classrooms?\n" + "Valid options: ext(extend)|rep(replace all)|rem(remove all)");
                switch (scan.nextLine()) {
                    case "ext" -> currentClassrooms.addAll(addClassrooms());
                    case "rep" -> timesheet.setClassrooms(addClassrooms());
                    case "rem" -> currentClassrooms.removeAll(addClassrooms());
                    default -> failOperation();
                }
                crudRepository.update(timesheet);
            }
            case "teacher" -> {
                List<Teacher> currentTeachers = timesheet.getTeachers();
                List<Teacher> all = teacherService.findAll();

                System.out.println("What you wanna do with teachers?");
                System.out.println("Available teachers:" + Arrays.toString(all.toArray()));
                System.out.print("Valid options: ext(extend)|rep(replace all)|rem(remove all)\n> ");


                switch (scan.nextLine()) {
                    case "ext" -> {
                        System.out.println("Type IDs with space to add.(e.g: 1 2 3)\n> ");
                        List<Integer> ids = InputValidator.validateIds();
                        if (!ids.isEmpty()) {
                            List<Teacher> toAdd = all.stream().filter(it -> ids.contains(it.getId())).filter(it -> currentTeachers.stream().noneMatch(cit -> it.getTeacherId().equals(cit.getTeacherId()))).toList();
                            currentTeachers.addAll(toAdd);
                        }
                    }
                    case "rep" -> timesheet.setTeachers(addTeachers());
                    case "rem" -> currentTeachers.removeAll(addTeachers());
                    default -> failOperation();
                }
                crudRepository.update(timesheet);
            }
            case "group" -> {
                List<StudentBatch> currentTeachers = timesheet.getStudentBatches();
                System.out.println("What you wanna do with student groups?\n" + "Valid options: ext(extend)|rep(replace all)|rem(remove all)");
                switch (scan.nextLine()) {
                    case "ext" -> currentTeachers.addAll(addGroups());
                    case "rep" -> timesheet.setStudentBatches(addGroups());
                    case "rem" -> currentTeachers.removeAll(addGroups());
                    default -> failOperation();
                }
                crudRepository.update(timesheet);
            }
        }
    }

    private List<Teacher> addTeachers() {
        Scanner scan = new Scanner(System.in);
        List<Teacher> all = teacherService.findAll();

        ArrayList<Teacher> teachers = new ArrayList<>();
        while (true) {
            System.out.println("Which teacher?");
            String valid = all.stream().map(teacher -> teacher.getId() + "." + teacher.getFullName()).collect(Collectors.joining("|", "[", "]"));

            System.out.printf("Valid options:%s or type EXIT to return%n> ", valid);
            String in = scan.nextLine();

            if (in.isBlank()) break;
            int id = Integer.parseInt(in);

            List<Integer> allIds = all.stream().map(Teacher::getId).toList();
            if (allIds.contains(id)) {
                Teacher teacher = all.get(id - 1);
                teachers.add(teacher);
                System.out.printf("%s is added!%nPress ENTER to continue, or you can add more teacher to this timeslot.%n", teacher.getFullName());
            } else recordNotFound(id).run();
        }
        return teachers;
    }

    private List<StudentBatch> addGroups() {
        Scanner scan = new Scanner(System.in);
        ArrayList<StudentBatch> studentBatches = new ArrayList<>();
        while (true) {
            System.out.println("Which student group?");
            String valid = studentService.findAll().stream().map(StudentBatch::getName).collect(Collectors.joining("|", "[", "]"));
            System.out.printf("Valid options:%s%n> ", valid);
            String name = scan.nextLine();
            if (name.isBlank()) break;
            studentService.findByName(name).ifPresentOrElse(it -> {
                studentBatches.add(it);
                System.out.printf("%s is added!%nPress ENTER to continue, or you can add more student group to this timeslot.%n", name);
            }, recordNotFound(name));
        }
        return studentBatches;
    }

    private List<Classroom> addClassrooms() {
        Scanner scan = new Scanner(System.in);
        ArrayList<Classroom> classrooms = new ArrayList<>();
        while (true) {
            System.out.println("Which classroom?");
            String valid = classroomService.findAll().stream().map(Classroom::getName).collect(Collectors.joining("|", "[", "]"));
            System.out.printf("Valid options:%s%n> ", valid);
            String name = scan.nextLine();
            if (name.isBlank()) break;
            classroomService.findByName(name).ifPresentOrElse(it -> {
                classrooms.add(it);
                System.out.printf("%s is added!%nPress ENTER to continue, or you can add more subjects to this timeslot.%n", name);
            }, recordNotFound(name));
        }
        return classrooms;
    }

    private List<Subject> addSubject() {
        Scanner scan = new Scanner(System.in);
        ArrayList<Subject> subjects = new ArrayList<>();
        while (true) {
            System.out.println("Which subject?");
            String valid = subjectService.findAll().stream().map(Subject::getName).collect(Collectors.joining("|", "[", "]"));
            System.out.printf("Valid options:%s%n> ", valid);
            String name = scan.nextLine();
            if (name.isBlank()) break;
            String capName = StringUtils.capitalize(name);
            subjectService.findByName(capName).ifPresentOrElse(it -> {
                subjects.add(it);
                System.out.printf("%s is added!%nPress ENTER to continue, or you can add more subjects to this timeslot.%n", capName);
            }, recordNotFound(capName));
        }

        return subjects;
    }

    private static LocalDateTime createDateTime() {
        return InputValidator.validateTime();
    }

    private static void failOperation() {
        System.out.println("Operation is not supported!");
    }

    @Override
    protected String getEntityName() {
        return "Timesheet";
    }
}