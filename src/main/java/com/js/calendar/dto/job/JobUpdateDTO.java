package com.js.calendar.dto.job;

import com.js.calendar.dto.UpdateDTO;

import java.math.BigDecimal;
import java.util.Objects;

public class JobUpdateDTO extends UpdateDTO {

    private String name;
    private BigDecimal hourlyState;

    public JobUpdateDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getHourlyState() {
        return hourlyState;
    }

    public void setHourlyState(BigDecimal hourlyState) {
        this.hourlyState = hourlyState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobUpdateDTO that = (JobUpdateDTO) o;
        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(hourlyState, that.hourlyState);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (hourlyState != null ? hourlyState.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JobUpdateDTO{" +
                "testString='" + name + '\'' +
                ", hourlyState=" + hourlyState +
                '}';
    }
}
