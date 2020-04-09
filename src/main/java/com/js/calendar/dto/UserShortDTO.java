package com.js.calendar.dto;

public class UserShortDTO {

    private String username;
    private Boolean enabled;

    public UserShortDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
