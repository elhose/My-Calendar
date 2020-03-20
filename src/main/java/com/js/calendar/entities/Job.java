package com.js.calendar.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    //możliwe że wyjebie sie na mappedBy bo nie wiem co tu wpierdolić - ma być ponoć mapowana kolumna w tej encji
    private User user;

    public Job(String testString, BigDecimal hourlyState, Timestamp createdDate, Timestamp lastModifiedDate) {
        this.testString = testString;
        this.hourlyState = hourlyState;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Job() {}
}
