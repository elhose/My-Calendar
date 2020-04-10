package com.js.calendar.service;

import com.js.calendar.entities.Day;
import com.js.calendar.repository.DayRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DayServiceImpl extends BaseServiceImpl<Day> implements DayService {

    public DayServiceImpl(DayRepository repository) {
        super(repository);
    }
}
