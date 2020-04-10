package com.js.calendar.controller;

import com.js.calendar.dto.BaseDTO;
import com.js.calendar.dto.ShortDTO;
import com.js.calendar.dto.UpdateDTO;
import com.js.calendar.dto.job.JobDTO;
import com.js.calendar.entities.BaseEntity;
import com.js.calendar.mappers.BaseMapper;
import com.js.calendar.service.BaseService;
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
        entities.forEach(entity -> dtos.add(mapper.mapEntityToDto(entity)));
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getEntityById(@PathVariable("id") Long id) {
        Optional<V> entity = service.getEntity(id);
        return entity.map(value -> new ResponseEntity<>(mapper.mapEntityToDto(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity addEntity(@RequestBody U updateDto) {
        V day = mapper.mapUpdateDtoToEntity(updateDto);
        service.addEntity(day);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEntity(@PathVariable("id") Long id, @RequestBody U updateDto) {
        V day = mapper.mapUpdateDtoToEntity(updateDto);
        service.updateEntity(id, day);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEntity(@PathVariable("id") Long id) {
        service.deleteEntity(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("hello")
    public T test() {

        JobDTO jobDTO = new JobDTO();
        jobDTO.setTestString("HELLO");

        return (T) jobDTO;
    }

}

