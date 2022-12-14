package com.lizziputt.consoleapp.service;

import com.lizziputt.consoleapp.service.SimpleMenuService;
import com.lizziputt.timetable.jpa.CrudRepository;
import com.lizziputt.timetable.teacher.Teacher;

import java.util.Optional;
import java.util.Scanner;

public class TeacherService extends SimpleMenuService<Teacher> {

    public TeacherService(CrudRepository<Teacher, Integer> crudRepository) {
        super(crudRepository);
    }

    public Optional<Teacher> findByName(String name) {
        return crudRepository.findAll().stream().filter(entity -> entity.getSecondName().equals(name)).findFirst();
    }

    public void create() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter First name of a new teacher: \n> ");
        String fName = scan.nextLine();
        System.out.print("Enter Second name of a new teacher: \n> ");
        String sName = scan.nextLine();
        System.out.print("Enter Middle name (optional) of a new teacher: \n> ");
        String mName = scan.nextLine();

        Teacher teacher = new Teacher(fName, sName, mName);
        Teacher saved = super.save(teacher);
        System.out.println("Teacher with full name: " + saved.getFullName() + " is created");
    }

    public void update() {
        Scanner scan = new Scanner(System.in);
        System.out.printf("Enter %s id:\n> ", getEntityName());
        int id = Integer.parseInt(scan.nextLine());

        findById(id).ifPresentOrElse(teacher -> {
            System.out.print("Enter new Full name in order [Surname Firstname Middle-name]: \n> ");
            String name = scan.nextLine();
            String[] n = name.split(" ");
            teacher.setSecondName(n[0]);
            teacher.setFirstName(n[1]);
            teacher.setMiddleName(n[2]);
            Teacher updated = super.update(teacher);
            System.out.println("Teacher with a name: " + updated.getFullName() + " is updated");
        }, recordNotFound(id));
    }

    @Override
    protected String getEntityName() {
        return "Teacher";
    }
}