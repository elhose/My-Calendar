package com.js.calendar.repository;

import com.js.calendar.entities.DayInCalendar;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayInCalendarRepository extends PagingAndSortingRepository<DayInCalendar, Integer> {

}
