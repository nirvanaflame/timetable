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
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

    public void create() {
        Scanner scan = new Scanner(System.in);
        LocalDateTime dateTime = createDateTime(scan);

        System.out.println("If you don't wanna add subjects/classrooms/students_groups/teachers to the timetable now " + "press enter to skip section or type `skip` to fill timesheet later on");

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
        super.executeAction(this::updateTimetable);
    }

    private void updateTimetable(Timesheet timesheet) {
        Scanner scan = new Scanner(System.in);

        System.out.println("What you wanna change?\nValid options: time|subject|classroom|group|teacher");
        String option = scan.nextLine();
        switch (option) {
            case "time" -> {
                LocalDateTime newTime = createDateTime(scan);
                timesheet.setTime(newTime);
                crudRepository.update(timesheet);
            }
            case "subject" -> {
                List<Subject> currentSubjects = timesheet.getSubjects();
                List<Subject> newSubjects = addSubject();
                System.out.println("What you wanna do with subjects?\n" + "Valid options: ext(extend)|rep(replace all)|rem(remove all)");
                switch (scan.nextLine()) {
                    case "ext" -> currentSubjects.addAll(newSubjects);
                    case "rep" -> timesheet.setSubjects(newSubjects);
                    case "rem" -> currentSubjects.removeAll(newSubjects);
                    default -> System.out.println("Operation is not supported");
                }
                crudRepository.update(timesheet);
            }
            case "classroom" -> {
                List<Classroom> currentClassrooms = timesheet.getClassrooms();
                List<Classroom> newClassrooms = addClassrooms();
                System.out.println("What you wanna do with classrooms?\n" + "Valid options: ext(extend)|rep(replace all)|rem(remove all)");
                switch (scan.nextLine()) {
                    case "ext" -> currentClassrooms.addAll(newClassrooms);
                    case "rep" -> timesheet.setClassrooms(newClassrooms);
                    case "rem" -> currentClassrooms.removeAll(newClassrooms);
                    default -> System.out.println("Operation is not supported");
                }
                crudRepository.update(timesheet);
            }
            case "teacher" -> {
                List<Teacher> currentTeachers = timesheet.getTeachers();
                List<Teacher> newTeachers = addTeachers();
                System.out.println("What you wanna do with teachers?\n" + "Valid options: ext(extend)|rep(replace all)|rem(remove all)");
                switch (scan.nextLine()) {
                    case "ext" -> currentTeachers.addAll(newTeachers);
                    case "rep" -> timesheet.setTeachers(newTeachers);
                    case "rem" -> currentTeachers.removeAll(newTeachers);
                    default -> System.out.println("Operation is not supported");
                }
                crudRepository.update(timesheet);
            }
            case "group" -> {
                List<StudentBatch> currentTeachers = timesheet.getStudentBatches();
                List<StudentBatch> newTeachers = addGroups();
                System.out.println("What you wanna do with student groups?\n" + "Valid options: ext(extend)|rep(replace all)|rem(remove all)");
                switch (scan.nextLine()) {
                    case "ext" -> currentTeachers.addAll(newTeachers);
                    case "rep" -> timesheet.setStudentBatches(newTeachers);
                    case "rem" -> currentTeachers.removeAll(newTeachers);
                    default -> System.out.println("Operation is not supported");
                }
                crudRepository.update(timesheet);
            }
        }
    }

    private List<Teacher> addTeachers() {
        Scanner scan = new Scanner(System.in);
        ArrayList<Teacher> teachers = new ArrayList<>();
        while (true) {
            System.out.print("Which student group?\n> ");
            String name = scan.nextLine();
            if (name.equals("\n")) break;
            Teacher teacher = teacherService.findByName(name).orElseThrow(() -> new EntityNotFoundException("Classroom with name: " + name + " is not found"));
            teachers.add(teacher);
        }
        return teachers;
    }

    private List<StudentBatch> addGroups() {
        Scanner scan = new Scanner(System.in);
        ArrayList<StudentBatch> studentBatches = new ArrayList<>();
        while (true) {
            System.out.print("Which student group?\n> ");
            String name = scan.nextLine();
            if (name.equals("\n")) break;
            StudentBatch studentBatch = studentService.findByName(name).orElseThrow(() -> new EntityNotFoundException("Student group with name: " + name + " is not found"));
            studentBatches.add(studentBatch);
        }
        return studentBatches;
    }

    private List<Classroom> addClassrooms() {
        Scanner scan = new Scanner(System.in);
        ArrayList<Classroom> classrooms = new ArrayList<>();
        while (true) {
            System.out.print("Which classroom?\n> ");
            String name = scan.nextLine();
            if (name.equals("\n")) break;
            Classroom classroom = classroomService.findByName(name).orElseThrow(() -> new EntityNotFoundException("Classroom with name: " + name + " is not found"));
            classrooms.add(classroom);
        }
        return classrooms;
    }

    private List<Subject> addSubject() {
        Scanner scan = new Scanner(System.in);
        ArrayList<Subject> subjects = new ArrayList<>();
        while (true) {
            System.out.print("Which subject?\n> ");
            String name = scan.nextLine();
            if (name.equals("\n")) break;
            Subject subject = subjectService.findByName(name).orElseThrow(() -> new EntityNotFoundException("Subject with name: " + name + " is not found"));
            subjects.add(subject);
        }

        return subjects;
    }

    private static LocalDateTime createDateTime(Scanner scan) {
        System.out.print("Enter time of the class in format: hh:mm:ss\n> ");
        int[] t = Arrays.stream(scan.nextLine().split(":")).mapToInt(Integer::parseInt).toArray();

        System.out.print("Enter date of the class in format: day.month.year\n> ");
        int[] d = Arrays.stream(scan.nextLine().split("\\.")).mapToInt(Integer::parseInt).toArray();

        return LocalDateTime.of(LocalDate.of(d[2], d[1], d[0]), LocalTime.of(t[0], t[1], t[2]));
    }

    @Override
    protected String getEntityName() {
        return "Timesheet";
    }
}