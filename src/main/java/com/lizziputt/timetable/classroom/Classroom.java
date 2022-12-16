package com.lizziputt.timetable.classroom;

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

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity(name = "classroom")
public class Classroom implements Persistable<Integer>, Printable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer classroomId;

    private String name;

    @ManyToMany(mappedBy = "classrooms", fetch = FetchType.LAZY)
    private List<Timesheet> timesheets = new ArrayList<>();

    @Override
    public Integer getId() {
        return classroomId;
    }

    public void setId(Integer id) {
        classroomId = id;
    }

    public Classroom(String name) {
        this.name = name;
    }

    public Classroom() {
    }

    @Override
    public String print() {
        return "{" + "classroomId=" + classroomId + ", name='" + name + '\'' + '}';

    }
}