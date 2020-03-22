package com.js.calendar.controller;

import com.js.calendar.entities.DayInCalendar;
import com.js.calendar.service.DayInCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("days")
public class DayController {

    private DayInCalendarService dayService;

    @Autowired
    public DayController(DayInCalendarService dayService) {
        this.dayService = dayService;
    }

    @GetMapping
    public ResponseEntity<Iterable<DayInCalendar>> getDays() {
        return new ResponseEntity<>(dayService.getDays(), HttpStatus.OK);
    }

    @GetMapping("/{dayId}")
    public ResponseEntity<DayInCalendar> getDayById(@PathVariable("dayId") Integer dayId) {
        Optional<DayInCalendar> day = dayService.getDay(dayId);

        return day.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new DayInCalendar(), HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity addNewDay(@RequestBody DayInCalendar dayInCalendar) {
        dayService.addDay(dayInCalendar);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{dayId}")
    public ResponseEntity updateUser(@PathVariable("dayId") Integer dayId, @RequestBody DayInCalendar dayInCalendar) {
        dayService.updateDay(dayId, dayInCalendar);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{dayId}")
    public ResponseEntity deleteUser(@PathVariable("dayId") Integer dayId) {
        dayService.deleteDay(dayId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
