package com.lizziputt.timetable.jpa;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class CrudService<T> {

    protected final CrudRepository<T, Integer> crudRepository;

    public void delete(Integer id) {
        crudRepository.findById(id).ifPresent(crudRepository::delete);
    }

    public <S extends T> S save(S o) {
        return crudRepository.save(o);
    }

    public <S extends T> S update(S o) {
        return crudRepository.update(o);
    }

    public List<T> findAll() {
        return crudRepository.findAll();
    }

    public Optional<T> findById(Integer id) {
        return crudRepository.findById(id);
    }
}
