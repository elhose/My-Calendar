package com.js.calendar.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "test_string")
    private String testString;

    @Column(name = "hourly_state")
    private BigDecimal hourlyState;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    @Column(name = "last_modified_date")
    private Timestamp lastModifiedDate;

    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    //możliwe że wyjebie sie na mappedBy bo nie wiem co tu wpierdolić - ma być ponoć mapowana kolumna w tej encji
    @JsonBackReference
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "jobs_days",
            joinColumns = {@JoinColumn(name = "job_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "day_id", referencedColumnName = "id")})
    @JsonManagedReference
    private List<Day> days;

    public Job(String testString, BigDecimal hourlyState) {
        this.testString = testString;
        this.hourlyState = hourlyState;
    }

    public Job() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (!Objects.equals(id, job.id)) return false;
        if (!Objects.equals(testString, job.testString)) return false;
        if (!Objects.equals(hourlyState, job.hourlyState)) return false;
        if (!Objects.equals(createdDate, job.createdDate)) return false;
        if (!Objects.equals(lastModifiedDate, job.lastModifiedDate))
            return false;
        if (!Objects.equals(user, job.user)) return false;
        return Objects.equals(days, job.days);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (testString != null ? testString.hashCode() : 0);
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
                "id=" + id +
                ", testString='" + testString + '\'' +
                ", hourlyState=" + hourlyState +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", user=" + user +
                ", days=" + days +
                '}';
    }
}
