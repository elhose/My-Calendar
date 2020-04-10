package com.js.calendar.dto.user;

import com.js.calendar.dto.BaseDTO;
import com.js.calendar.dto.job.JobShortDTO;

import java.util.List;
import java.util.Objects;

public class UserDTO extends BaseDTO {

    private String username;
    private String password;
    private Boolean enabled;
    private List<JobShortDTO> jobShortDTOS;

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<JobShortDTO> getJobShortDTOS() {
        return jobShortDTOS;
    }

    public void setJobShortDTOS(List<JobShortDTO> jobShortDTOS) {
        this.jobShortDTOS = jobShortDTOS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        if (!Objects.equals(username, userDTO.username)) return false;
        if (!Objects.equals(password, userDTO.password)) return false;
        if (!Objects.equals(enabled, userDTO.enabled)) return false;
        return Objects.equals(jobShortDTOS, userDTO.jobShortDTOS);
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        result = 31 * result + (jobShortDTOS != null ? jobShortDTOS.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", jobShortDTOS=" + jobShortDTOS +
                '}';
    }
}
