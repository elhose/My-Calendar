package com.js.calendar.service;

import com.js.calendar.entities.BaseEntity;
import com.js.calendar.repository.BaseRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    private BaseRepository<T> repository;

    public BaseServiceImpl(BaseRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<T> getEntities() {
        return repository.findAll();
    }

    @Override
    public Optional<T> getEntity(Long id) {
        return repository.findById(id);
    }

    @Override
    public void addEntity(T entity) {
        repository.save(entity);
    }

    @Override
    public void updateEntity(Long id, T entity) {
        Optional<T> foundEntity = repository.findById(id);
        if (foundEntity.isPresent()) {
            entity.setId(id);
            repository.save(entity);
        } else throw new EmptyResultDataAccessException(Math.toIntExact(id));
    }

    @Override
    public void deleteEntity(Long id) {
        repository.deleteById(id);
    }
}
