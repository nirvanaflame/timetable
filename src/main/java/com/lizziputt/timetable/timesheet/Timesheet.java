package com.lizziputt.timetable.timesheet;

import com.lizziputt.timetable.classroom.Classroom;
import com.lizziputt.timetable.jpa.Persistable;
import com.lizziputt.timetable.student.StudentBatch;
import com.lizziputt.timetable.subject.Subject;
import com.lizziputt.timetable.teacher.Teacher;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity(name = "Timesheet")
@Table(name = "timesheet")
public class Timesheet implements Persistable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timesheet_id")
    private Integer timesheetId;

    @Column(name = "timestamp")
    private LocalDateTime time;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinTable(name = "time_subject", joinColumns = { @JoinColumn(name = "timesheet_id") }, inverseJoinColumns = { @JoinColumn(name = "subject_id") })
    private List<Subject> subjects = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinTable(name = "time_classroom", joinColumns = { @JoinColumn(name = "timesheet_id") }, inverseJoinColumns = { @JoinColumn(name = "classroom_id") })
    private List<Classroom> classrooms = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinTable(name = "time_student_batch", joinColumns = { @JoinColumn(name = "timesheet_id") }, inverseJoinColumns = { @JoinColumn(name = "student_batch_id") })
    private List<StudentBatch> studentBatches = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinTable(name = "time_teacher", joinColumns = { @JoinColumn(name = "timesheet_id") }, inverseJoinColumns = { @JoinColumn(name = "teacher_id") })
    private List<Teacher> teachers = new ArrayList<>();

    @Override
    public Integer getId() {
        return timesheetId;
    }

    public void setId(Integer id) {
        timesheetId = id;
    }

    public Timesheet(LocalDateTime time, List<Subject> subjects, List<Classroom> classrooms, List<StudentBatch> studentBatches, List<Teacher> teachers) {
        this.time = time;
        this.subjects = subjects;
        this.classrooms = classrooms;
        this.studentBatches = studentBatches;
        this.teachers = teachers;
    }

    public Timesheet() {
    }
}
