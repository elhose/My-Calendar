package com.js.calendar.dto.job;

import com.js.calendar.dto.UpdateDTO;

import java.math.BigDecimal;
import java.util.Objects;

public class JobUpdateDTO extends UpdateDTO {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobUpdateDTO that = (JobUpdateDTO) o;
        if (!Objects.equals(testString, that.testString)) return false;
        return Objects.equals(hourlyState, that.hourlyState);
    }

    @Override
    public int hashCode() {
        int result = testString != null ? testString.hashCode() : 0;
        result = 31 * result + (hourlyState != null ? hourlyState.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JobUpdateDTO{" +
                "testString='" + testString + '\'' +
                ", hourlyState=" + hourlyState +
                '}';
    }
}
