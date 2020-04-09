package com.js.calendar.dto;

import java.math.BigDecimal;

public class JobUpdateDTO {

    private String testString;
    private BigDecimal hourlyState;

    public JobUpdateDTO() {
    }

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }

    public BigDecimal getHourlyState() {
        return hourlyState;
    }

    public void setHourlyState(BigDecimal hourlyState) {
        this.hourlyState = hourlyState;
    }
}
