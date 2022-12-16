package com.lizziputt.timetable.subject;

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
@Entity(name = "subject")
public class Subject implements Persistable<Integer>, Printable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subjectId;

    private String name;

    @ManyToMany(mappedBy = "subjects", fetch = FetchType.LAZY)
    private List<Timesheet> timesheets = new ArrayList<>();

    @Override
    public Integer getId() {
        return subjectId;
    }

    public void setId(Integer id) {
        subjectId = id;
    }

    public Subject(String name) {
        this.name = name;
    }

    public Subject() {
    }

    @Override
    public String print() {
        return "{" + "subjectId=" + subjectId + ", name='" + name + '\'' + '}';

    }
}