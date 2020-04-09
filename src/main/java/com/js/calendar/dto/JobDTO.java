package com.js.calendar.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class JobDTO {

    private Long id;
    private String testString;
    private BigDecimal hourlyState;
    private LocalDateTime lastModifiedDate;
    private List<DayShortDTO> dayShortDTOS;
    private UserShortDTO userShortDTO;

    public JobDTO(Long id, String testString, BigDecimal hourlyState, List<DayShortDTO> dayShortDTOS, UserShortDTO userShortDTO) {
        this.id = id;
        this.testString = testString;
        this.hourlyState = hourlyState;
        this.dayShortDTOS = dayShortDTOS;
        this.userShortDTO = userShortDTO;
    }

    public JobDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<DayShortDTO> getDayShortDTOS() {
        return dayShortDTOS;
    }

    public void setDayShortDTOS(List<DayShortDTO> dayShortDTOS) {
        this.dayShortDTOS = dayShortDTOS;
    }

    public UserShortDTO getUserShortDTO() {
        return userShortDTO;
    }

    public void setUserShortDTO(UserShortDTO userShortDTO) {
        this.userShortDTO = userShortDTO;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
