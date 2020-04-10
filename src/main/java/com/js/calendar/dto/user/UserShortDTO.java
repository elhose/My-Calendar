package com.js.calendar.dto.user;

import com.js.calendar.dto.ShortDTO;

import java.util.Objects;

public class UserShortDTO extends ShortDTO {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserShortDTO that = (UserShortDTO) o;

        if (!Objects.equals(username, that.username)) return false;
        return Objects.equals(enabled, that.enabled);
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserShortDTO{" +
                "username='" + username + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
