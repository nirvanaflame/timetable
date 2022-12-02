package com.lizziputt.timetable.student;

import com.lizziputt.timetable.SimpleMenuService;
import com.lizziputt.timetable.jpa.CrudRepository;

import java.util.Optional;
import java.util.Scanner;

public class StudentService extends SimpleMenuService<StudentBatch> {

    public StudentService(CrudRepository<StudentBatch, Integer> crudRepository) {
        super(crudRepository);
    }

    public Optional<StudentBatch> findByName(String name) {
        return crudRepository.findAll().stream().filter(entity -> entity.getName().equals(name)).findFirst();
    }

    public void create() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter name of a new student group: \n> ");
        String name = scan.nextLine();
        StudentBatch saved = super.save(new StudentBatch(name));
        System.out.println("Student Group with a name: " + saved.getName() + " is created");
    }

    public void update() {
        Scanner scan = new Scanner(System.in);
        System.out.printf("Enter %s id:\n> %n", getEntityName());
        int id = Integer.parseInt(scan.nextLine());

        findById(id).ifPresentOrElse(student -> {
            System.out.println("Enter a new student group name:\n>");
            String name = scan.nextLine();
            student.setName(name);
            StudentBatch updated = super.update(student);
            System.out.println("Student group with a name: " + updated.getName() + " is updated");
        }, recordNotFound(id));
    }

    @Override
    protected String getEntityName() {
        return "Student";
    }
}