package com.js.calendar.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@Table(name = "jobs")
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

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

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", nullable = false)
    //możliwe że wyjebie sie na mappedBy bo nie wiem co tu wpierdolić - ma być ponoć mapowana kolumna w tej encji
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "jobs_days", joinColumns = {@JoinColumn(name = "job_id")}, inverseJoinColumns = {@JoinColumn(name = "day_id")})
    private Set<Day> days;

    public Job(String testString, BigDecimal hourlyState, Timestamp createdDate, Timestamp lastModifiedDate) {
        this.testString = testString;
        this.hourlyState = hourlyState;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Job() {
    }
}
