package com.js.calendar.service;

import com.js.calendar.entities.Day;
import com.js.calendar.repository.DayRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//@DataJpaTest   //use spring to get beans from context
@ExtendWith(MockitoExtension.class) // use only mockito
public class BaseServiceDayEntityTest {

    private final List<Day> testDayCollection = new ArrayList<>();

    @Mock
    private DayRepository dayRepository;

    @InjectMocks
    private DayService dayService = new DayServiceImpl(dayRepository);

    @BeforeEach
    private void beforeEach() {

        MockitoAnnotations.initMocks(this);

        Day day1 = new Day(1L, LocalDate.now(), true, BigDecimal.valueOf(8L), Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
        Day day2 = new Day(2L, LocalDate.of(2020, 1, 1), false, BigDecimal.valueOf(0L), Timestamp.valueOf(LocalDateTime.MIN), Timestamp.valueOf(LocalDateTime.MIN));
        Day day3 = new Day(3L, LocalDate.of(2020, 4, 1), true, BigDecimal.valueOf(8L), Timestamp.valueOf(LocalDateTime.MAX), Timestamp.valueOf(LocalDateTime.MAX));

        testDayCollection.add(day1);
        testDayCollection.add(day2);
        testDayCollection.add(day3);
    }

    @AfterEach
    private void cleanTestList() {
        testDayCollection.clear();
    }

    @Test
    public void getAllDaysTest() {
        Iterable<Day> repositoryValues = dayRepository.findAll();

        when(repositoryValues).thenReturn(testDayCollection);

        //test
        Iterable<Day> days = dayService.getEntities();
        
        assertEquals(testDayCollection.size(), iterableToList(days).size());
        verify(dayRepository, times(1)).findAll();

    }

    @Test
    public void getDayByIdWhenDayIsPresentId() {
        long id = 1;
        Optional<Day> repositoryDay = dayRepository.findById(id);
        Optional<Day> resultDay = Optional.ofNullable(testDayCollection.get(0));

        when(repositoryDay).thenReturn(resultDay);

        //test
        Optional<Day> testEntity = dayService.getEntity(id);

        assertEquals(testEntity, resultDay);
        assertEquals(testEntity.get(), resultDay.get());
    }

    @Test
    public void getDayByIdWhenDayIsNotPresent() {
        long id = 12312312312L;
        Optional<Day> repositoryDay = dayRepository.findById(id);
        Optional<Day> resultDay = Optional.empty();

        when(repositoryDay).thenReturn(resultDay);

        //test
        Optional<Day> testEntity = dayService.getEntity(id);

        assertEquals(testEntity.isPresent(), false);
        assertEquals(testEntity, resultDay);
    }

    @Test
    public void deleteDayWhenIdPresent() {
        long id = 1;

        when(dayRepository.findAll()).thenReturn(testDayCollection);
        //test

        dayService.deleteEntity(id);
        testDayCollection.remove(0);

        Iterable<Day> allEntitiesMinusOneWithId1 = dayService.getEntities();
        assertEquals(testDayCollection, iterableToList(allEntitiesMinusOneWithId1));
    }

    @Test
    public void deleteDayWhenIdNotPresent() {
        long id = 12312312312L;

        when(dayRepository.findAll()).thenReturn(testDayCollection);
        //test

        dayService.deleteEntity(id);

        Iterable<Day> allEntities = dayService.getEntities();
        assertEquals(testDayCollection, iterableToList(allEntities));
    }

    private <T> List<T> iterableToList(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
