package com.js.calendar.service;

import com.js.calendar.entities.Day;
import com.js.calendar.repository.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class DayServiceImpl implements DayService {

    private DayRepository dayRepository;

    @Autowired
    public DayServiceImpl(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    @Override
    public Iterable<Day> getDays() {
        return dayRepository.findAll();
    }

    @Override
    public void addDay(Day day) {
        dayRepository.save(day);
    }

    @Override
    public void updateDay(Integer id, Day day) {
        Optional<Day> foundDay = dayRepository.findById(id);

        foundDay.ifPresent(value -> day.setId(value.getId()));

        dayRepository.save(day);
    }

    @Override
    public Optional<Day> getDay(Integer id) {
        return dayRepository.findById(id);
    }

    @Override
    public void deleteDay(Integer id) {
        dayRepository.deleteById(id);
    }
}
