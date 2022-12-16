package com.lizziputt.timetable.timesheet;

import com.lizziputt.consoleapp.service.Printable;
import com.lizziputt.timetable.classroom.Classroom;
import com.lizziputt.timetable.jpa.Persistable;
import com.lizziputt.timetable.student.Student;
import com.lizziputt.timetable.subject.Subject;
import com.lizziputt.timetable.teacher.Teacher;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity(name = "timesheet")
public class Timesheet implements Persistable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer timesheetId;

    private LocalDateTime time;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "time_subject", joinColumns = {@JoinColumn(name = "timesheet_id")}, inverseJoinColumns = {@JoinColumn(name = "subject_id")})
    private List<Subject> subjects = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "time_classroom", joinColumns = {@JoinColumn(name = "timesheet_id")}, inverseJoinColumns = {@JoinColumn(name = "classroom_id")})
    private List<Classroom> classrooms = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "time_student", joinColumns = {@JoinColumn(name = "timesheet_id")}, inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private List<Student> students = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "time_teacher", joinColumns = {@JoinColumn(name = "timesheet_id")}, inverseJoinColumns = {@JoinColumn(name = "teacher_id")})
    private List<Teacher> teachers = new ArrayList<>();

    @Override
    public Integer getId() {
        return timesheetId;
    }

    public void setId(Integer id) {
        timesheetId = id;
    }

    public Timesheet(LocalDateTime time, List<Subject> subjects, List<Classroom> classrooms, List<Student> students, List<Teacher> teachers) {
        this.time = time;
        this.subjects = subjects;
        this.classrooms = classrooms;
        this.students = students;
        this.teachers = teachers;
    }

    public Timesheet() {
    }

    @Override
    public String toString() {
        return "\n{\n" + "\ttimesheetId:" + timesheetId + ",\n\ttime:" + time + "\n}";
    }

    public String timeTableView() {
        return "{\n" + "\ttimesheetId:" + timesheetId + ",\n\ttime:" + time
                + ",\n\tsubjects:[" + listView(subjects)
                + "],\n\tclassrooms:[" + listView(classrooms)
                + "],\n\tstudentBatches:[" + listView(students)
                + "],\n\tteachers:[" + listView(teachers) + "]\n}";

    }

    private <T extends Printable> String listView(List<T> list) {
        return list.stream().map(Printable::print).collect(Collectors.joining("\n"));
    }
}