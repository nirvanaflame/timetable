package com.lizziputt.timetable.teacher;

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
@Entity(name = "Teacher")
@Table(name = "teacher")
public class Teacher implements Persistable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Integer teacherId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "middle_name")
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
}
