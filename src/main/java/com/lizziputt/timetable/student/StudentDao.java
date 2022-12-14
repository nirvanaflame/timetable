package com.lizziputt.timetable.student;

import com.lizziputt.timetable.jpa.SimpleJpaRepository;
import jakarta.persistence.EntityManager;

public class StudentDao extends SimpleJpaRepository<Student, Integer> {

    public StudentDao(EntityManager em, Class<Student> domainType) {
        super(em, domainType);
    }
}