package com.js.calendar.controller;

import com.js.calendar.mappers.UserMapper;
import com.js.calendar.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController extends BaseController{

    private UserMapper mapper;
    private UserService service;

    public UserController(UserMapper mapper, UserService service) {
        super(mapper, service);
        this.mapper = mapper;
        this.service = service;
    }


}
