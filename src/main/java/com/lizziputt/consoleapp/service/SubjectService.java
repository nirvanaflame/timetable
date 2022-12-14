package com.lizziputt.consoleapp.service;

import com.lizziputt.timetable.jpa.CrudRepository;
import com.lizziputt.timetable.subject.Subject;
import com.lizziputt.util.StringUtils;

import java.util.Optional;
import java.util.Scanner;

public class SubjectService extends SimpleMenuService<Subject> {

    public SubjectService(CrudRepository<Subject, Integer> crudRepository) {
        super(crudRepository);
    }

    public Optional<Subject> findByName(String name) {
        return crudRepository.findAll().stream().filter(entity -> entity.getName().equals(name)).findFirst();
    }

    public void create() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter name of a new subject: \n> ");
        String name = scan.nextLine();
        Subject saved = super.save(new Subject(StringUtils.capitalize(name)));
        System.out.println("Subject with a name: " + saved.getName() + " is created");
    }

    public void update() {
        Scanner scan = new Scanner(System.in);
        System.out.printf("Enter %s id:\n> %n", getEntityName());
        int id = Integer.parseInt(scan.nextLine());

        findById(id).ifPresentOrElse(subject -> {
            System.out.println("Enter a new subject name:\n>");
            String name = scan.nextLine();
            subject.setName(name);
            Subject updated = super.update(subject);
            System.out.println("Subject with a name: " + updated.getName() + " is updated");
        }, recordNotFound(id));
    }

    @Override
    protected String getEntityName() {
        return "Subject";
    }
}