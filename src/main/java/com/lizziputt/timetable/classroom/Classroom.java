package com.lizziputt.timetable.classroom;

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
@Entity(name = "Classroom")
@Table(name = "classroom")
public class Classroom implements Persistable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classroom_id")
    private Integer classroomId;

    @Column(name = "classroom_name")
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
}
