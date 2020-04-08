package com.js.calendar.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "days")
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day", nullable = false)
    private LocalDate dayOfProject;

    @Column(name = "is_workday", nullable = false)
    private Boolean isWorkday;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    @Column(name = "last_modified_date")
    private Timestamp lastModifiedDate;

    @ManyToMany(mappedBy = "days") //mapped by field name in Job.class
    private List<Job> jobs;

    public Day(LocalDate dayOfProject, Boolean isWorkday) {
        this.dayOfProject = dayOfProject;
        this.isWorkday = isWorkday;
    }

    public Day() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDayOfProject() {
        return dayOfProject;
    }

    public void setDayOfProject(LocalDate day) {
        this.dayOfProject = day;
    }

    public Boolean getIsWorkday() {
        return isWorkday;
    }

    public void setIsWorkday(Boolean workday) {
        isWorkday = workday;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Day day1 = (Day) o;

        if (!Objects.equals(id, day1.id)) return false;
        if (!Objects.equals(dayOfProject, day1.dayOfProject)) return false;
        if (!Objects.equals(isWorkday, day1.isWorkday)) return false;
        if (!Objects.equals(createdDate, day1.createdDate)) return false;
        if (!Objects.equals(lastModifiedDate, day1.lastModifiedDate))
            return false;
        return Objects.equals(jobs, day1.jobs);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dayOfProject != null ? dayOfProject.hashCode() : 0);
        result = 31 * result + (isWorkday != null ? isWorkday.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (lastModifiedDate != null ? lastModifiedDate.hashCode() : 0);
        result = 31 * result + (jobs != null ? jobs.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Day{" +
                "id=" + id +
                ", day=" + dayOfProject +
                ", isWorkday=" + isWorkday +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", jobs=" + jobs +
                '}';
    }
}
