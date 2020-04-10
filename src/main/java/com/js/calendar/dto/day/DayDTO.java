package com.js.calendar.dto.day;

import com.js.calendar.dto.BaseDTO;
import com.js.calendar.dto.job.JobShortDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class DayDTO extends BaseDTO {

    private Long id;
    private LocalDate dayOfProject;
    private Boolean workday;
    private LocalDateTime lastModifiedDate;
    private List<JobShortDTO> jobShortDTOS;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DayDTO dayDTO = (DayDTO) o;

        if (!Objects.equals(id, dayDTO.id)) return false;
        if (!Objects.equals(dayOfProject, dayDTO.dayOfProject))
            return false;
        if (!Objects.equals(workday, dayDTO.workday)) return false;
        if (!Objects.equals(lastModifiedDate, dayDTO.lastModifiedDate))
            return false;
        return Objects.equals(jobShortDTOS, dayDTO.jobShortDTOS);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dayOfProject != null ? dayOfProject.hashCode() : 0);
        result = 31 * result + (workday != null ? workday.hashCode() : 0);
        result = 31 * result + (lastModifiedDate != null ? lastModifiedDate.hashCode() : 0);
        result = 31 * result + (jobShortDTOS != null ? jobShortDTOS.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DayDTO{" +
                "id=" + id +
                ", dayOfProject=" + dayOfProject +
                ", workday=" + workday +
                ", lastModifiedDate=" + lastModifiedDate +
                ", jobShortDTOS=" + jobShortDTOS +
                '}';
    }
}
