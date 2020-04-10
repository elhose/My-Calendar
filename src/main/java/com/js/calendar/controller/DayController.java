package com.js.calendar.controller;

import com.js.calendar.mappers.DayMapper;
import com.js.calendar.service.DayService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("days")
public class DayController extends BaseController{

    private DayMapper mapper;
    private DayService service;

    public DayController(DayMapper mapper, DayService service) {
        super(mapper, service);
        this.mapper = mapper;
        this.service = service;
    }
}
