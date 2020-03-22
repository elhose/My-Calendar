package com.js.calendar.service;

import com.js.calendar.entities.DayInCalendar;
import com.js.calendar.repository.DayInCalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class DayInCalendarServiceImpl implements DayInCalendarService {

    private DayInCalendarRepository dayRepository;

    @Autowired
    public DayInCalendarServiceImpl(DayInCalendarRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    @Override
    public Iterable<DayInCalendar> getDays() {
        return dayRepository.findAll();
    }

    @Override
    public void addDay(DayInCalendar dayInCalendar) {
        dayRepository.save(dayInCalendar);
    }

    @Override
    public void updateDay(Integer id, DayInCalendar dayInCalendar) {
        Optional<DayInCalendar> foundDay = dayRepository.findById(id);

        if (foundDay.isPresent()) {
            dayInCalendar.setId(foundDay.get().getId());
        }

        dayRepository.save(dayInCalendar);
    }

    @Override
    public Optional getDay(Integer id) {
        return dayRepository.findById(id);
    }

    @Override
    public void deleteDay(Integer id) {
        dayRepository.deleteById(id);
    }
}
