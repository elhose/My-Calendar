package com.js.calendar.service;

import com.js.calendar.entities.BaseEntity;

import java.util.Optional;

public interface BaseService<T extends BaseEntity> {

    Iterable<T> getEntities();
    Optional<T> getEntity(Long id);
    void addEntity(T entity);
    void updateEntity(Long id, T entity);
    void deleteEntity(Long id);

}
