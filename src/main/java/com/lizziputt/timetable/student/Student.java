package com.lizziputt.timetable.student;

import com.lizziputt.consoleapp.service.Printable;
import com.lizziputt.timetable.jpa.Persistable;
import com.lizziputt.timetable.timesheet.Timesheet;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

// Группа Студентов
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity(name = "student")
public class Student implements Persistable<Integer>, Printable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentBatchId;

    private String name;

    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    private List<Timesheet> timesheets = new ArrayList<>();

    @Override
    public Integer getId() {
        return studentBatchId;
    }

    public void setId(Integer id) {
        studentBatchId = id;
    }

    public Student(String name) {
        this.name = name;
    }

    public Student() {
    }

    @Override
    public String print() {
        return "{" + "studentBatchId=" + studentBatchId + ", name='" + name + '\'' + '}';

    }
}