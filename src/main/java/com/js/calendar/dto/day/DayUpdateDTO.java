package com.js.calendar.dto.day;

import com.js.calendar.dto.UpdateDTO;

import java.time.LocalDate;
import java.util.Objects;

public class DayUpdateDTO extends UpdateDTO {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DayUpdateDTO that = (DayUpdateDTO) o;

        if (!Objects.equals(dayOfProject, that.dayOfProject)) return false;
        return Objects.equals(workday, that.workday);
    }

    @Override
    public int hashCode() {
        int result = dayOfProject != null ? dayOfProject.hashCode() : 0;
        result = 31 * result + (workday != null ? workday.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DayUpdateDTO{" +
                "dayOfProject=" + dayOfProject +
                ", workday=" + workday +
                '}';
    }
}
