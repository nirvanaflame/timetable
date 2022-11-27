package com.lizziputt.timetable.subject;

import com.lizziputt.timetable.jpa.CrudService;
import com.lizziputt.timetable.jpa.CrudRepository;

import java.util.Optional;

public class SubjectService extends CrudService<Subject> {

    public SubjectService(CrudRepository<Subject, Integer> crudRepository) {
        super(crudRepository);
    }

    public Optional<Subject> findByName(String name) {
        return crudRepository.findAll().stream().filter(entity -> entity.getName().equals(name)).findFirst();
    }
}
