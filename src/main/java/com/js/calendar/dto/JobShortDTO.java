package com.js.calendar.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class JobShortDTO {

    private Long id;
    private String testString;
    private BigDecimal hourlyState;
    private LocalDateTime lastModifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobShortDTO() {
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

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
