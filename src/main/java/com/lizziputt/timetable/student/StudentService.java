package com.lizziputt.timetable.student;

import com.lizziputt.timetable.classroom.Classroom;
import com.lizziputt.timetable.jpa.CrudService;
import com.lizziputt.timetable.jpa.CrudRepository;

import java.util.Optional;

public class StudentService extends CrudService<StudentBatch> {

    public StudentService(CrudRepository<StudentBatch, Integer> crudRepository) {
        super(crudRepository);
    }

    public Optional<StudentBatch> findByName(String name) {
        return crudRepository.findAll().stream().filter(entity -> entity.getName().equals(name)).findFirst();
    }
}
