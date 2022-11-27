package com.lizziputt.timetable.student;

import com.lizziputt.timetable.jpa.SimpleJpaRepository;
import jakarta.persistence.EntityManager;

public class StudentBatchDao extends SimpleJpaRepository<StudentBatch, Integer> {

    public StudentBatchDao(EntityManager em, Class<StudentBatch> domainType) {
        super(em, domainType);
    }
}
