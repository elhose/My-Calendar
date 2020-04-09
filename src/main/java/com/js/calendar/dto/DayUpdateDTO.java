package com.js.calendar.dto;

import java.time.LocalDate;

public class DayUpdateDTO {

    private LocalDate dayOfProject;
    private Boolean workday;

    public DayUpdateDTO() {
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
}
