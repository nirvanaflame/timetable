package com.lizziputt.timetable.subject;

import com.lizziputt.timetable.jpa.SimpleJpaRepository;
import jakarta.persistence.EntityManager;

public class SubjectDao extends SimpleJpaRepository<Subject, Integer> {
    public SubjectDao(EntityManager em, Class<Subject> domainType) {
        super(em, domainType);
    }
}
