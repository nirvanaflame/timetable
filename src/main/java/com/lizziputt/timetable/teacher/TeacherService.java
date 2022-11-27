package com.lizziputt.timetable.teacher;

import com.lizziputt.timetable.classroom.Classroom;
import com.lizziputt.timetable.jpa.CrudService;
import com.lizziputt.timetable.jpa.CrudRepository;

import java.util.Optional;

public class TeacherService extends CrudService<Teacher> {

    public TeacherService(CrudRepository<Teacher, Integer> crudRepository) {
        super(crudRepository);
    }

    public Optional<Teacher> findByName(String name) {
        return crudRepository.findAll().stream().filter(entity -> entity.getSecondName().equals(name)).findFirst();
    }
}
