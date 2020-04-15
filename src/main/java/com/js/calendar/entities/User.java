package com.js.calendar.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean enabled = true; //default value set to true

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    @Column(name = "last_modified_date")
    private Timestamp lastModifiedDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Job> jobs;

    public User() {
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

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public void addJob(Job job) {
        this.getJobs().add(job);
        job.setUser(this);
    }

    public void removeJob(Job job) {
        this.getJobs().remove(job);
        job.setUser(null);
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!Objects.equals(getId(), user.getId())) return false;
        if (!Objects.equals(username, user.username)) return false;
        if (!Objects.equals(password, user.password)) return false;
        if (!Objects.equals(enabled, user.enabled)) return false;
        if (!Objects.equals(createdDate, user.createdDate)) return false;
        if (!Objects.equals(lastModifiedDate, user.lastModifiedDate))
            return false;
        return Objects.equals(jobs, user.jobs);
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (lastModifiedDate != null ? lastModifiedDate.hashCode() : 0);
        result = 31 * result + (jobs != null ? jobs.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", jobs=" + jobs +
                '}';
    }
}
