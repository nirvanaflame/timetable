package com.lizziputt.timetable.classroom;

import com.lizziputt.timetable.jpa.SimpleJpaRepository;
import jakarta.persistence.EntityManager;

public class ClassroomDao extends SimpleJpaRepository<Classroom, Integer> {
    public ClassroomDao(EntityManager em, Class<Classroom> domainType) {
        super(em, domainType);
    }
}
