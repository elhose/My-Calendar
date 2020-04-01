package com.js.calendar.repository;

import com.js.calendar.entities.Day;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayRepository extends PagingAndSortingRepository<Day, Integer> {

}
