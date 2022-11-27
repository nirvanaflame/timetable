package com.lizziputt.timetable.student;

import com.lizziputt.timetable.jpa.Persistable;
import com.lizziputt.timetable.timesheet.Timesheet;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

// Группа Студенотов
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity(name = "StudentBatch")
@Table(name = "sudent_batch")
public class StudentBatch implements Persistable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_batch_id")
    private Integer studentBatchId;

    @Column(name = "student_batch_name")
    private String name;

    @ManyToMany(mappedBy = "studentBatches", fetch = FetchType.LAZY)
    private List<Timesheet> timesheets = new ArrayList<>();

    @Override
    public Integer getId() {
        return studentBatchId;
    }

    public void setId(Integer id) {
        studentBatchId = id;
    }

    public StudentBatch(String name) {
        this.name = name;
    }

    public StudentBatch() {
    }
}
