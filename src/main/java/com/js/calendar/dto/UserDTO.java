package com.js.calendar.dto;

import java.util.List;

public class UserDTO {

    private String username;
    private String password;
    private Boolean enabled;
    private List<JobShortDTO> jobShortDTOS;

    public UserDTO(String username, String password, Boolean enabled, List<JobShortDTO> jobShortDTOS) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.jobShortDTOS = jobShortDTOS;
    }

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
}
