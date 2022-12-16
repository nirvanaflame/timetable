package com.lizziputt.timetable.teacher;

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
@Entity(name = "teacher")
public class Teacher implements Persistable<Integer>, Printable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teacherId;

    private String firstName;

    private String secondName;

    private String middleName;

    @ManyToMany(mappedBy = "teachers", fetch = FetchType.LAZY)
    private List<Timesheet> timesheets = new ArrayList<>();

    @Override
    public Integer getId() {
        return teacherId;
    }

    public void setId(Integer id) {
        teacherId = id;
    }

    public Teacher(String firstName, String secondName, String middleName) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
    }

    public Teacher() {
    }

    public String getFullName() {
        return String.format("%s %s %s", secondName, firstName, middleName);
    }

    @Override
    public String print() {
        return "{" + "teacherId=" + teacherId + ", firstName='" + firstName + '\'' + ", secondName='" + secondName + '\'' + ", middleName='" + middleName + '\'' + '}';

    }
}