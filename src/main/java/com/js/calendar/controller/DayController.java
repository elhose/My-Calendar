package com.js.calendar.controller;

import com.js.calendar.dto.DayDTO;
import com.js.calendar.dto.DayUpdateDTO;
import com.js.calendar.entities.Day;
import com.js.calendar.mappers.DayMapper;
import com.js.calendar.service.DayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("days")
public class DayController {

    private DayMapper mapper;
    private DayService service;

    public DayController(DayMapper mapper, DayService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Iterable<DayDTO>> getDays() {
        Iterable<Day> days = service.getEntities();
        List<DayDTO> dtos = new ArrayList<>();

        days.forEach(day -> dtos.add(mapper.mapDayToDto(day)));

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{dayId}")
    public ResponseEntity<DayDTO> getDayById(@PathVariable("dayId") Long dayId) {
        Optional<Day> day = service.getEntity(dayId);

        return day.map(value -> new ResponseEntity<>(mapper.mapDayToDto(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new DayDTO(), HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity addNewDay(@RequestBody DayUpdateDTO dayUpdateDTO) {
        Day day = mapper.mapDayUpdateDtoToDay(dayUpdateDTO);
        service.addEntity(day);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{dayId}")
    public ResponseEntity updateUser(@PathVariable("dayId") Long dayId, @RequestBody DayUpdateDTO dayUpdateDTO) {
        Day day = mapper.mapDayUpdateDtoToDay(dayUpdateDTO);
        service.updateEntity(dayId, day);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{dayId}")
    public ResponseEntity deleteUser(@PathVariable("dayId") Long dayId) {
        service.deleteEntity(dayId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
