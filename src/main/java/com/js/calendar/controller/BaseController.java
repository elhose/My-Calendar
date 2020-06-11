package com.js.calendar.controller;

import com.js.calendar.dto.BaseDTO;
import com.js.calendar.dto.ShortDTO;
import com.js.calendar.dto.UpdateDTO;
import com.js.calendar.entities.BaseEntity;
import com.js.calendar.exceptions.NoEntityException;
import com.js.calendar.mappers.BaseMapper;
import com.js.calendar.service.BaseService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BaseController<T extends BaseDTO, S extends ShortDTO, U extends UpdateDTO, V extends BaseEntity> {

    private BaseMapper<T, S, U, V> mapper;
    private BaseService<V> service;

    public BaseController(BaseMapper<T, S, U, V> mapper, BaseService<V> service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Iterable<T>> getEntities() {
        Iterable<V> entities = service.getEntities();
        List<T> dtos = new ArrayList<>();

        if (entities.iterator().hasNext()) {
            entities.forEach(entity -> dtos.add(mapper.mapEntityToDto(entity)));
        } else {
            throw new NoEntityException("No such entities in DataBase");
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getEntityById(@PathVariable("id") Long id) {
        Optional<V> entity = service.getEntity(id);
        if (entity.isPresent()) {
            T baseDTO = mapper.mapEntityToDto(entity.get());
            return new ResponseEntity<>(baseDTO, HttpStatus.OK);
        } else {
            throw new NoEntityException("No such entity with ID: " + id + " in DataBase");
        }
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addEntity(@RequestBody U updateDto) {
        V day = mapper.mapUpdateDtoToEntity(updateDto);
        service.addEntity(day);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateEntity(@PathVariable("id") Long id, @RequestBody U updateDto) {
        V day = mapper.mapUpdateDtoToEntity(updateDto);
        try {
            service.updateEntity(id, day);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            throw new NoEntityException("No such entity with ID: " + id + " in DataBase");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEntity(@PathVariable("id") Long id) {
        try {
            service.deleteEntity(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            throw new NoEntityException("No such entity with ID: " + id + " in DataBase");
        }
    }
}