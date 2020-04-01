package com.js.calendar.service;

import com.js.calendar.entities.Day;

import java.util.Optional;

public interface DayService {
    Iterable<Day> getDays();
    void addDay(Day day);
    void updateDay(Long id, Day day);
    Optional<Day> getDay(Long id);
    void deleteDay(Long id);
}
