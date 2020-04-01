package com.js.calendar.controller;

import com.js.calendar.entities.Day;
import com.js.calendar.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("days")
public class DayController {

    private DayService dayService;

    @Autowired
    public DayController(DayService dayService) {
        this.dayService = dayService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Day>> getDays() {
        return new ResponseEntity<>(dayService.getDays(), HttpStatus.OK);
    }

    @GetMapping("/{dayId}")
    public ResponseEntity<Day> getDayById(@PathVariable("dayId") Long dayId) {
        Optional<Day> day = dayService.getDay(dayId);

        return day.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new Day(), HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity addNewDay(@RequestBody Day day) {
        dayService.addDay(day);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{dayId}")
    public ResponseEntity updateUser(@PathVariable("dayId") Long dayId, @RequestBody Day day) {
        dayService.updateDay(dayId, day);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{dayId}")
    public ResponseEntity deleteUser(@PathVariable("dayId") Long dayId) {
        dayService.deleteDay(dayId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
