package com.js.calendar.dto.job;

import com.js.calendar.dto.BaseDTO;
import com.js.calendar.dto.user.UserShortDTO;
import com.js.calendar.dto.day.DayShortDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class JobDTO extends BaseDTO {

    private Long id;
    private String name;
    private BigDecimal hourlyState;
    private LocalDateTime lastModifiedDate;
    private List<DayShortDTO> dayShortDTOS;
    private UserShortDTO userShortDTO;

    public JobDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobDTO jobDTO = (JobDTO) o;
        if (!Objects.equals(id, jobDTO.id)) return false;
        if (!Objects.equals(name, jobDTO.name)) return false;
        if (!Objects.equals(hourlyState, jobDTO.hourlyState)) return false;
        if (!Objects.equals(lastModifiedDate, jobDTO.lastModifiedDate)) {
            return false;
        }
        if (!Objects.equals(dayShortDTOS, jobDTO.dayShortDTOS))
            return false;
        return Objects.equals(userShortDTO, jobDTO.userShortDTO);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (hourlyState != null ? hourlyState.hashCode() : 0);
        result = 31 * result + (lastModifiedDate != null ? lastModifiedDate.hashCode() : 0);
        result = 31 * result + (dayShortDTOS != null ? dayShortDTOS.hashCode() : 0);
        result = 31 * result + (userShortDTO != null ? userShortDTO.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JobDTO{" +
                "id=" + id +
                ", testString='" + name + '\'' +
                ", hourlyState=" + hourlyState +
                ", lastModifiedDate=" + lastModifiedDate +
                ", dayShortDTOS=" + dayShortDTOS +
                ", userShortDTO=" + userShortDTO +
                '}';
    }
}
