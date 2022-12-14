package com.lizziputt.consoleapp.service;

import com.lizziputt.timetable.jpa.CrudRepository;
import com.lizziputt.util.InputValidator;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;

@RequiredArgsConstructor
public abstract class SimpleMenuService<T> {

    protected final CrudRepository<T, Integer> crudRepository;

    private void delete(Integer id) {
        crudRepository.findById(id).ifPresent(crudRepository::delete);
    }

    protected <S extends T> S save(S o) {
        return crudRepository.save(o);
    }

    protected <S extends T> S update(S o) {
        return crudRepository.update(o);
    }

    public List<T> findAll() {
        return crudRepository.findAll();
    }

    public Optional<T> findById(Integer id) {
        return crudRepository.findById(id);
    }

    public void printAll() {
        List<T> result = findAll();
        if (result.isEmpty()) System.out.println("Nothing to show!");
        else result.forEach(System.out::println);
    }

    public void delete() {
        int id = getIdInput();
        if (id != 0) {
            delete(id);
            System.out.printf("delete from %s where id=%d%n", getEntityName(), id);
        }
    }

    public void printRecordById() {
        executeAction(System.out::println);
    }

    protected void executeAction(Consumer<T> action) {
        int id = getIdInput();
        if (id != 0) findById(id).ifPresentOrElse(action, recordNotFound(id));
    }

    protected String getEntityName() {
        return "";
    }

    protected static Runnable recordNotFound(int id) {
        return () -> System.out.printf("Record with id:%s is not found%n", id);
    }

    protected static Runnable recordNotFound(String name) {
        return () -> System.out.printf("Record with name:%s is not found%n", name);
    }

    protected int getIdInput() {
        System.out.printf("Enter %s id:\n> ", getEntityName());
        return InputValidator.validateId();
    }
}