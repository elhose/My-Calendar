package com.js.calendar.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "jobs")
public class Job extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "hourly_state", nullable = false)
    private BigDecimal hourlyState;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    @Column(name = "last_modified_date")
    private Timestamp lastModifiedDate;

    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "jobs_days",
            joinColumns = {@JoinColumn(name = "job_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "day_id", referencedColumnName = "id")})
    private List<Day> days;

    public Job() {
    }

    public String getName() {
        return name;
    }

    public void setName(String testString) {
        this.name = testString;
    }

    public BigDecimal getHourlyState() {
        return hourlyState;
    }

    public void setHourlyState(BigDecimal hourlyState) {
        this.hourlyState = hourlyState;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public void addDay(Day day){
        this.getDays().add(day);
        day.getJobs().add(this);
    }

    public void removeDay(Day day) {
        this.getDays().remove(day);
        day.getJobs().remove(this);
    }

    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (!Objects.equals(getId(), job.getId())) return false;
        if (!Objects.equals(name, job.name)) return false;
        if (!Objects.equals(hourlyState, job.hourlyState)) return false;
        if (!Objects.equals(createdDate, job.createdDate)) return false;
        if (!Objects.equals(lastModifiedDate, job.lastModifiedDate))
            return false;
        if (!Objects.equals(user, job.user)) return false;
        return Objects.equals(days, job.days);
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (hourlyState != null ? hourlyState.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (lastModifiedDate != null ? lastModifiedDate.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (days != null ? days.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + getId() +
                ", testString='" + name + '\'' +
                ", hourlyState=" + hourlyState +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", user=" + user +
                ", days=" + days +
                '}';
    }
}
