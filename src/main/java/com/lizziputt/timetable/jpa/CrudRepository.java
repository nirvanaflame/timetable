package com.lizziputt.timetable.jpa;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {

    void delete(T entity);

    // обобщение с ограничением, означает, что метод возвращает тип T, либо его наследников.
    <S extends T> S save(S entity);

    <S extends T> S update(S entity);

    List<T> findAll();

    Optional<T> findById(ID id);
}
