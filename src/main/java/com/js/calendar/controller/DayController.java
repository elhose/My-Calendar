package com.js.calendar.controller;

import com.js.calendar.dto.day.DayDTO;
import com.js.calendar.dto.day.DayShortDTO;
import com.js.calendar.dto.day.DayUpdateDTO;
import com.js.calendar.entities.Day;
import com.js.calendar.mappers.DayMapper;
import com.js.calendar.service.DayService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("days")
public class DayController extends BaseController<DayDTO, DayShortDTO, DayUpdateDTO, Day>{

    private DayMapper mapper;
    private DayService service;

    public DayController(DayMapper mapper, DayService service) {
        super(mapper, service);
        this.mapper = mapper;
        this.service = service;
    }
}
