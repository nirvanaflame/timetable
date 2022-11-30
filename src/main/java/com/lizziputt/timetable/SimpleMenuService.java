package com.lizziputt.timetable;

import com.lizziputt.timetable.jpa.CrudRepository;
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

    protected  <S extends T> S save(S o) {
        return crudRepository.save(o);
    }

    protected <S extends T> S update(S o) {
        return crudRepository.update(o);
    }

    private List<T> findAll() {
        return crudRepository.findAll();
    }

    protected Optional<T> findById(Integer id) {
        return crudRepository.findById(id);
    }

    public void printAll() {
        List<T> result = findAll();
        if (result.isEmpty()) System.out.println("Nothing to show");
        else result.forEach(System.out::println);
    }

    public void delete() {
        int id = getIdInput();
        delete(id);
        System.out.printf("Record with %s from %s table is deleted%n", id, getEntityName());
    }

    public void printRecordById() {
        executeAction(System.out::println);
    }

    protected void executeAction(Consumer<T> action) {
        int id = getIdInput();
        findById(id).ifPresentOrElse(action, recordNotFound());
    }

    protected String getEntityName() {
        return "";
    }

    protected static Runnable recordNotFound() {
        return () -> System.out.println("Record is not found");
    }

    protected int getIdInput() {
        Scanner scan = new Scanner(System.in);
        System.out.printf("Enter %s id:\n> %n", getEntityName());
        return Integer.parseInt(scan.nextLine());
    }
}