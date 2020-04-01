package com.js.calendar.service;

import com.js.calendar.entities.Day;

import java.util.Optional;

public interface DayService {
    Iterable<Day> getDays();
    void addDay(Day day);
    void updateDay(Integer id, Day day);
    Optional<Day> getDay(Integer id);
    void deleteDay(Integer id);
}
