package com.js.calendar.service;

import com.js.calendar.entities.Day;
import com.js.calendar.repository.DayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@DataJpaTest   //use spring to get beans from context
@ExtendWith(MockitoExtension.class) // use only mockito
public class BaseServiceDayEntityTest {

    private Day testDayMock;

    @Mock
    private DayRepository dayRepository;

    @InjectMocks
    private DayService dayService = new DayServiceImpl(dayRepository);

    @BeforeEach
    private void beforeEach() {
        testDayMock = new Day();
        testDayMock.setId(1L);
        testDayMock.setDayOfProject(LocalDate.now());
        testDayMock.setWorkday(true);
        testDayMock.setHours(BigDecimal.valueOf(8L));
        testDayMock.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        testDayMock.setLastModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
    }

    @Test
    void getAllDaysTest() {
        //given
        List<Day> repositoryValues = new ArrayList<>();
        repositoryValues.add(testDayMock);
        when(dayRepository.findAll()).thenReturn(repositoryValues);

        //when
        Iterable<Day> days = dayService.getEntities();

        //then
        assertNotNull(days);
        assertEquals(repositoryValues.size(), iterableToList(days).size());
        assertEquals(repositoryValues, iterableToList(days));
        verify(dayRepository, times(1)).findAll();
    }

    @Test
    void getDayByIdWhenDayIsPresentTest() {
        //given
        Optional<Day> dayMock = Optional.of(this.testDayMock);
        when(dayRepository.findById(anyLong())).thenReturn(dayMock);

        //when
        Optional<Day> testEntity = dayService.getEntity(anyLong());

        //then
        assertEquals(testEntity, dayMock);
        assertTrue(testEntity.isPresent());
        assertEquals(testEntity.get(), dayMock.get());
        verify(dayRepository, times(1)).findById(anyLong());
    }

    @Test
    void getDayByIdWhenDayIsNotPresentTest() {
        //given
        Optional<Day> resultDay = Optional.empty();
        when(dayRepository.findById(anyLong())).thenReturn(resultDay);

        //when
        Optional<Day> testEntity = dayService.getEntity(anyLong());

        //test
        assertEquals(testEntity, resultDay);
        assertFalse(testEntity.isPresent());
    }

    @Test
    void deleteDayWhenIdPresentTest() {
        //given
        doNothing().when(dayRepository).deleteById(anyLong());

        //when
        dayService.deleteEntity(anyLong());
        dayService.deleteEntity(anyLong());
        dayService.deleteEntity(anyLong());

        //then
        verify(dayRepository, times(3)).deleteById(anyLong());
    }


    @Test
    void addDayTest() {
        //given
        Day addDay = new Day();
        addDay.setId(2L);
        addDay.setDayOfProject(LocalDate.MIN);
        addDay.setWorkday(false);
        addDay.setHours(BigDecimal.valueOf(8L));
        addDay.setCreatedDate(Timestamp.valueOf(LocalDateTime.MIN));
        addDay.setLastModifiedDate(Timestamp.valueOf(LocalDateTime.MIN));
        when(dayRepository.save(addDay)).thenAnswer(mock -> mock.getArgument(0, Day.class));

        //when
        dayService.addEntity(addDay);

        //then
        assertEquals(addDay, dayRepository.save(addDay));
        verify(dayRepository, times(2)).save(addDay);
    }

    @Test
    void updateExistingDayTest() {
        //given
        Day updateDay = new Day();
        updateDay.setId(3L);
        updateDay.setDayOfProject(LocalDate.MAX);
        updateDay.setWorkday(false);
        updateDay.setHours(BigDecimal.valueOf(888L));
        updateDay.setCreatedDate(Timestamp.valueOf(LocalDateTime.MAX));
        updateDay.setLastModifiedDate(Timestamp.valueOf(LocalDateTime.MAX));
        when(dayRepository.findById(anyLong())).thenReturn(Optional.of(testDayMock));

        //when
        dayService.updateEntity(1L, updateDay);

        //then
        verify(dayRepository, times(1)).findById(anyLong());
        verify(dayRepository, times(1)).save(updateDay);
    }

    @Test()
    void updateNotExistingDayTest() {
        //given
        Day updateDay = new Day();
        when(dayRepository.findById(anyLong())).thenReturn(Optional.empty());

        //when
        assertThrows(EmptyResultDataAccessException.class, () -> dayService.updateEntity(1L, updateDay));

        //then
        verify(dayRepository, times(1)).findById(anyLong());
        verify(dayRepository, times(0)).save(updateDay);
    }

    private <T> List<T> iterableToList(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
