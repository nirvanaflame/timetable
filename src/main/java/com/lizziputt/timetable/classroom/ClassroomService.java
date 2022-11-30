package com.lizziputt.timetable.classroom;

import com.lizziputt.timetable.SimpleMenuService;
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
        System.out.println("Classroom with a name: " + saved.getName().toUpperCase() + " is created");
    }

    public void update() {
        Scanner scan = new Scanner(System.in);
        System.out.printf("Enter %s id:\n> %n", getEntityName());
        int id = Integer.parseInt(scan.nextLine());

        super.findById(id).ifPresentOrElse(classroom -> {
            System.out.println("Enter a new classroom name:\n>");
            String name = getEntityName();
            classroom.setName(name);
            Classroom updated = super.update(classroom);
            System.out.println("Classroom with a name: " + updated.getName().toUpperCase() + " is updated");
        }, recordNotFound());
    }

    @Override
    protected String getEntityName() {
        return "Classroom";
    }
}