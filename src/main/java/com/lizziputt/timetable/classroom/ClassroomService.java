package com.lizziputt.timetable.classroom;

import com.lizziputt.timetable.jpa.CrudService;
import com.lizziputt.timetable.jpa.CrudRepository;

import java.util.Optional;

public class ClassroomService extends CrudService<Classroom> {

    public ClassroomService(CrudRepository<Classroom, Integer> crudRepository) {
        super(crudRepository);
    }

    public Optional<Classroom> findByName(String name) {
        return crudRepository.findAll().stream().filter(entity -> entity.getName().equals(name)).findFirst();
    }
}

