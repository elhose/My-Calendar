package com.js.calendar.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "days")
public class Day extends BaseEntity {

    @Column(name = "day", nullable = false)
    private LocalDate dayOfProject;

    @Column(name = "is_workday", nullable = false)
    private Boolean workday;

    @DecimalMin(value = "0.0", inclusive = false, message = "Hours must be positive number!")
    @DecimalMax(value = "24.0", message = "You can't work more than 24H per day!")
    @Digits(integer = 2, fraction = 2)
    @Column(nullable = false)
    private BigDecimal hours;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    @Column(name = "last_modified_date")
    private Timestamp lastModifiedDate;

    @ManyToMany(mappedBy = "days") //mapped by field name in Job.class
    private List<Job> jobs;

    public Day() {
    }

    public LocalDate getDayOfProject() {
        return dayOfProject;
    }

    public void setDayOfProject(LocalDate day) {
        this.dayOfProject = day;
    }

    public Boolean getWorkday() {
        return workday;
    }

    public void setWorkday(Boolean workday) {
        this.workday = workday;
    }

    public BigDecimal getHours() {
        return hours;
    }

    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public void addJob(Job job) {
        this.jobs.add(job);
        job.getDays().add(this);
    }

    public void removeJob(Job job) {
        this.jobs.remove(job);
        job.getDays().remove(this);
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

        Day day = (Day) o;

        if (!Objects.equals(dayOfProject, day.dayOfProject)) return false;
        if (!Objects.equals(workday, day.workday)) return false;
        if (!Objects.equals(hours, day.hours)) return false;
        if (!Objects.equals(createdDate, day.createdDate)) return false;
        if (!Objects.equals(lastModifiedDate, day.lastModifiedDate))
            return false;
        return Objects.equals(jobs, day.jobs);
    }

    @Override
    public int hashCode() {
        int result = dayOfProject != null ? dayOfProject.hashCode() : 0;
        result = 31 * result + (workday != null ? workday.hashCode() : 0);
        result = 31 * result + (hours != null ? hours.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (lastModifiedDate != null ? lastModifiedDate.hashCode() : 0);
        result = 31 * result + (jobs != null ? jobs.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Day{" +
                "dayOfProject=" + dayOfProject +
                ", workday=" + workday +
                ", hours=" + hours +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", jobs=" + jobs +
                '}';
    }
}
