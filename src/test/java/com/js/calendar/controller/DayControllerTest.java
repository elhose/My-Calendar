package com.js.calendar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.js.calendar.dto.day.DayDTO;
import com.js.calendar.entities.Day;
import com.js.calendar.mappers.DayMapper;
import com.js.calendar.service.DayService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = DayController.class)
class DayControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DayService dayService;

    @MockBean
    private DayMapper dayMapper;

    @Test
    void getAllEntitiesWhenThereIsSomeTest() throws Exception {
        //given
        Iterable<Day> days = mockDays();
        when(dayService.getEntities()).thenReturn(days);


        MockHttpServletResponse response = mvc.perform(get("/days")
                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();


        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());

    }

    @Test
    void getAllEntitiesWhenThereIsNoneTest() throws Exception {
        //given
        when(dayService.getEntity(anyLong())).thenReturn(Optional.of(mockDay(123l, LocalDate.now(), 3L, true)));

        //when
        MockHttpServletResponse response = mvc.perform(get("/days/" + anyLong()).accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        //then
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());

    }

    private Iterable<Day> mockDays() {
        List<Day> days = new ArrayList<>();

        Day day1 = mockDay(11L, LocalDate.MAX, 12L, true);
        Day day2 = mockDay(31L, LocalDate.MIN, 16L, false);

        days.add(day1);
        days.add(day2);

        List<DayDTO> dtos = days.stream().map(day -> dayMapper.mapEntityToDto(day)).collect(Collectors.toList());

        return iterableToList(days);
    }

    private Day mockDay(Long id, LocalDate dayOfProject, Long hours, Boolean workday) {
        Day day = new Day();
        day.setId(id);
        day.setDayOfProject(dayOfProject);
        day.setHours(BigDecimal.valueOf(hours));
        day.setWorkday(workday);
        return day;
    }

    private <T> List<T> iterableToList(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}