package com.lizziputt.consoleapp;


import java.util.List;

import static com.lizziputt.consoleapp.ExecutionStrategy.*;

public class TimetableBuilder {

    public static final String EXIT_TXT = "exit";
    public static final String EXIT_CODE = "6";

    public static Menu buildMenu() {
        return Menu.builder()
            .name("# Timetable app #")
            .menus(subMenu())
            .build();
    }

    private static List<Menu> subMenu() {
        return List.of(
            timetable(),
            classroom(),
            student(),
            subject(),
            teacher(),
            exit()
        );
    }

    private static Menu timetable() {
        return Menu.builder()
            .name("Timetables")
            .menuItems(List.of(
                new Menu.MenuItem("Show all timetables", TIMETABLE_FIND_ALL),
                new Menu.MenuItem("Show timetable with id", TIMETABLE_FIND_BY_ID),
                new Menu.MenuItem("Create timetable", TIMETABLE_CREATE),
                new Menu.MenuItem("Update timetable with id", TIMETABLE_UPDATE),
                new Menu.MenuItem("Remove timetable with id", TIMETABLE_REMOVE),
                new Menu.MenuItem("Return to previous menu", RETURN)
            )).build();
    }

    private static Menu classroom() {
        return Menu.builder()
            .name("Classrooms")
            .menuItems(List.of(
                new Menu.MenuItem("Show all classrooms", CLASSROOM_FIND_ALL),
                new Menu.MenuItem("Show classroom with id", CLASSROOM_FIND_BY_ID),
                new Menu.MenuItem("Create classroom", CLASSROOM_CREATE ),
                new Menu.MenuItem("Update classroom with id", CLASSROOM_UPDATE),
                new Menu.MenuItem("Remove classroom with id", CLASSROOM_REMOVE),
                new Menu.MenuItem("Return to previous menu", RETURN)
            )).build();
    }

    private static Menu student() {
        return Menu.builder()
            .name("Students")
            .menuItems(List.of(
                new Menu.MenuItem("Show all students", STUDENT_FIND_ALL),
                new Menu.MenuItem("Show student with id", STUDENT_FIND_BY_ID),
                new Menu.MenuItem("Create student", STUDENT_CREATE),
                new Menu.MenuItem("Update student with id", STUDENT_UPDATE),
                new Menu.MenuItem("Remove student with id", STUDENT_REMOVE),
                new Menu.MenuItem("Return to previous menu", RETURN)
            )).build();
    }

    private static Menu subject() {
        return Menu.builder()
            .name("Subjects")
            .menuItems(List.of(
                new Menu.MenuItem("Show all subjects", SUBJECT_FIND_ALL),
                new Menu.MenuItem("Show subject with id", SUBJECT_FIND_BY_ID),
                new Menu.MenuItem("Create subject", SUBJECT_CREATE),
                new Menu.MenuItem("Update subject with id", SUBJECT_UPDATE),
                new Menu.MenuItem("Remove subject with id", SUBJECT_REMOVE),
                new Menu.MenuItem("Return to previous menu", RETURN)
            )).build();
    }

    private static Menu teacher() {
        return Menu.builder()
            .name("Teachers")
            .menuItems(List.of(
                new Menu.MenuItem("Show all teachers", TEACHER_FIND_ALL),
                new Menu.MenuItem("Show teacher with id", TEACHER_FIND_BY_ID),
                new Menu.MenuItem("Create teacher", TEACHER_CREATE),
                new Menu.MenuItem("Update teacher with id", TEACHER_UPDATE),
                new Menu.MenuItem("Remove teacher with id", TEACHER_REMOVE),
                new Menu.MenuItem("Return to previous menu", RETURN)
            )).build();
    }

    private static Menu exit() {
        return Menu.builder().name("Exit").build();
    }
}