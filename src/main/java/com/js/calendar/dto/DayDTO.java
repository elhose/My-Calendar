package com.js.calendar.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class DayDTO {

    private Long id;
    private LocalDate dayOfProject;
    private Boolean workday;
    private LocalDateTime lastModifiedDate;
    private List<JobShortDTO> jobShortDTOS;

    public DayDTO(Long id, LocalDate dayOfProject, Boolean workday, List<JobShortDTO> jobShortDTOS) {
        this.id = id;
        this.dayOfProject = dayOfProject;
        this.workday = workday;
        this.jobShortDTOS = jobShortDTOS;
    }

    public DayDTO() {
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

    public List<JobShortDTO> getJobShortDTOS() {
        return jobShortDTOS;
    }

    public void setJobShortDTOS(List<JobShortDTO> jobShortDTOS) {
        this.jobShortDTOS = jobShortDTOS;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
