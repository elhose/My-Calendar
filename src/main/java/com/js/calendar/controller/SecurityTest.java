package com.js.calendar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityTest {

    @RequestMapping("/hello")
    public String hello() {
        return "HACKER SO MUCH";
    }
}
