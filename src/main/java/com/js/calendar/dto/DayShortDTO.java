package com.js.calendar.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DayShortDTO {

    private Long id;
    private LocalDate dayOfProject;
    private Boolean workday;
    private LocalDateTime lastModifiedDate;

    public DayShortDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDayOfProject() {
        return dayOfProject;
    }

    public void setDayOfProject(LocalDate dayOfProject) {
        this.dayOfProject = dayOfProject;
    }

    public Boolean getWorkday() {
        return workday;
    }

    public void setWorkday(Boolean workday) {
        this.workday = workday;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
