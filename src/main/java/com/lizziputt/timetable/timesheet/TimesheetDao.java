package com.lizziputt.timetable.timesheet;

import com.lizziputt.timetable.jpa.SimpleJpaRepository;
import jakarta.persistence.EntityManager;

public class TimesheetDao extends SimpleJpaRepository<Timesheet, Integer> {

    public TimesheetDao(EntityManager em, Class<Timesheet> domainType) {
        super(em, domainType);
    }
}
