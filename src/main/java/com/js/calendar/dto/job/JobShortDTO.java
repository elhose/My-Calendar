package com.js.calendar.dto.job;

import com.js.calendar.dto.ShortDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class JobShortDTO extends ShortDTO {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobShortDTO that = (JobShortDTO) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(testString, that.testString)) return false;
        if (!Objects.equals(hourlyState, that.hourlyState)) return false;
        return Objects.equals(lastModifiedDate, that.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (testString != null ? testString.hashCode() : 0);
        result = 31 * result + (hourlyState != null ? hourlyState.hashCode() : 0);
        result = 31 * result + (lastModifiedDate != null ? lastModifiedDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JobShortDTO{" +
                "id=" + id +
                ", testString='" + testString + '\'' +
                ", hourlyState=" + hourlyState +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
