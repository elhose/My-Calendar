package com.js.calendar.service;

import com.js.calendar.entities.DayInCalendar;

import java.util.Optional;

public interface DayInCalendarService {
    Iterable<DayInCalendar> getDays();
    void addDay(DayInCalendar dayInCalendar);
    void updateDay(Integer id, DayInCalendar dayInCalendar);
    Optional getDay(Integer id);
    void deleteDay(Integer id);
}
