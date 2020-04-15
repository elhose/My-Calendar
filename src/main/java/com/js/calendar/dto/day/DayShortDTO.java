package com.js.calendar.dto.day;

import com.js.calendar.dto.ShortDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class DayShortDTO extends ShortDTO {

    private Long id;
    private LocalDate dayOfProject;
    private Boolean workday;
    private BigDecimal hours;
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

    public BigDecimal getHours() {
        return hours;
    }

    public void setHours(BigDecimal hours) {
        this.hours = hours;
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

        DayShortDTO that = (DayShortDTO) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(dayOfProject, that.dayOfProject)) return false;
        if (!Objects.equals(workday, that.workday)) return false;
        if (!Objects.equals(hours, that.hours)) return false;
        return Objects.equals(lastModifiedDate, that.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dayOfProject != null ? dayOfProject.hashCode() : 0);
        result = 31 * result + (workday != null ? workday.hashCode() : 0);
        result = 31 * result + (hours != null ? hours.hashCode() : 0);
        result = 31 * result + (lastModifiedDate != null ? lastModifiedDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DayShortDTO{" +
                "id=" + id +
                ", dayOfProject=" + dayOfProject +
                ", workday=" + workday +
                ", hours=" + hours +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
