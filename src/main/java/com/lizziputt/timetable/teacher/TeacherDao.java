package com.lizziputt.timetable.teacher;

import com.lizziputt.timetable.jpa.SimpleJpaRepository;
import jakarta.persistence.EntityManager;

public class TeacherDao extends SimpleJpaRepository<Teacher, Integer> {
    public TeacherDao(EntityManager em, Class<Teacher> domainType) {
        super(em, domainType);
    }
}
