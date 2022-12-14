package com.lizziputt.consoleapp.service;

import com.lizziputt.timetable.jpa.CrudRepository;
import com.lizziputt.timetable.student.Student;

import java.util.Optional;
import java.util.Scanner;

public class StudentService extends SimpleMenuService<Student> {

    public StudentService(CrudRepository<Student, Integer> crudRepository) {
        super(crudRepository);
    }

    public Optional<Student> findByName(String name) {
        return crudRepository.findAll().stream().filter(entity -> entity.getName().equals(name)).findFirst();
    }

    public void create() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter name of a new student group: \n> ");
        String name = scan.nextLine();
        Student saved = super.save(new Student(name));
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
            Student updated = super.update(student);
            System.out.println("Student group with a name: " + updated.getName() + " is updated");
        }, recordNotFound(id));
    }

    @Override
    protected String getEntityName() {
        return "Student";
    }
}