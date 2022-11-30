package com.lizziputt.consoleapp;

import com.lizziputt.timetable.classroom.ClassroomService;
import com.lizziputt.timetable.student.StudentService;
import com.lizziputt.timetable.subject.SubjectService;
import com.lizziputt.timetable.teacher.TeacherService;
import com.lizziputt.timetable.timesheet.TimesheetService;

import java.io.InputStreamReader;
import java.util.Scanner;

import static com.lizziputt.consoleapp.TimetableBuilder.EXIT_CODE;
import static com.lizziputt.consoleapp.TimetableBuilder.EXIT_TXT;

public class Application {

    public static void main(String[] args) {
        ConsoleApplicationContext context = new ConsoleApplicationContext();
        TimesheetService timesheetService = context.getTimesheetService();
        ClassroomService classroomService = context.getClassroomService();
        SubjectService subjectService = context.getSubjectService();
        TeacherService teacherService = context.getTeacherService();
        StudentService studentService = context.getStudentService();


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
                    case TIMETABLE_FIND_ALL -> timesheetService.printAll();
                    case TIMETABLE_FIND_BY_ID -> timesheetService.printRecordById();
                    case TIMETABLE_CREATE -> timesheetService.create();
                    case TIMETABLE_UPDATE -> timesheetService.update();
                    case TIMETABLE_REMOVE -> timesheetService.delete();

                    // Classroom Menu Items
                    case CLASSROOM_FIND_ALL -> classroomService.printAll();
                    case CLASSROOM_FIND_BY_ID -> classroomService.printRecordById();
                    case CLASSROOM_CREATE -> classroomService.create();
                    case CLASSROOM_UPDATE -> classroomService.update();
                    case CLASSROOM_REMOVE -> classroomService.delete();

                    // Subject Menu Items
                    case SUBJECT_FIND_ALL -> subjectService.printAll();
                    case SUBJECT_FIND_BY_ID -> subjectService.printRecordById();
                    case SUBJECT_CREATE -> subjectService.create();
                    case SUBJECT_UPDATE -> subjectService.update();
                    case SUBJECT_REMOVE -> subjectService.delete();

                    // Teacher Menu Items
                    case TEACHER_FIND_ALL -> teacherService.printAll();
                    case TEACHER_FIND_BY_ID -> teacherService.printRecordById();
                    case TEACHER_CREATE -> teacherService.create();
                    case TEACHER_UPDATE -> teacherService.update();
                    case TEACHER_REMOVE -> teacherService.delete();

                    // Student Groups Menu Items
                    case STUDENT_FIND_ALL -> studentService.printAll();
                    case STUDENT_FIND_BY_ID -> studentService.printRecordById();
                    case STUDENT_CREATE -> studentService.create();
                    case STUDENT_UPDATE -> studentService.update();
                    case STUDENT_REMOVE -> studentService.delete();
                }
                if (isReturn) break;
            }
            timetableMenu.printMenu();
        }
        System.out.print("Bye!!!");
    }
}