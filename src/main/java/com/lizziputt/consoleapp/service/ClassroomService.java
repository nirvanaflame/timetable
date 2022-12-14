package com.lizziputt.consoleapp.service;

import com.lizziputt.timetable.classroom.Classroom;
import com.lizziputt.timetable.jpa.CrudRepository;

import java.util.Optional;
import java.util.Scanner;

public class ClassroomService extends SimpleMenuService<Classroom> {

    public ClassroomService(CrudRepository<Classroom, Integer> crudRepository) {
        super(crudRepository);
    }

    public Optional<Classroom> findByName(String name) {
        return crudRepository.findAll().stream().filter(entity -> entity.getName().equals(name)).findFirst();
    }

    public void create() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter name of a new classroom: \n> ");
        String name = scan.nextLine();
        Classroom saved = super.save(new Classroom(name));
        System.out.println("Classroom with a name: " + saved.getName() + " is created");
    }

    public void update() {
        Scanner scan = new Scanner(System.in);
        System.out.printf("Enter %s id:\n> ", getEntityName());
        int id = Integer.parseInt(scan.nextLine());

        super.findById(id).ifPresentOrElse(classroom -> {
            System.out.print("Enter a new classroom name:\n> ");
            String name = scan.nextLine();
            classroom.setName(name);
            Classroom updated = super.update(classroom);
            System.out.println("Classroom with a name: " + updated.getName() + " is updated");
        }, recordNotFound(id));
    }

    @Override
    protected String getEntityName() {
        return "Classroom";
    }
}