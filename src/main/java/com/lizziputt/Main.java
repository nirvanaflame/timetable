package com.lizziputt;

import com.lizziputt.timetable.teacher.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("time");
        EntityManager em = emf.createEntityManager();
        Teacher teacher = em.find(Teacher.class, Integer.valueOf(1));
        System.out.println(teacher);
    }
}
