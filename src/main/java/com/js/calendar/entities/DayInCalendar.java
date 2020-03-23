package com.js.calendar.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "days")
public class DayInCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate day;

    @Column(name = "is_workday")
    private Boolean isWorkday;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    @Column(name = "last_modified_date")
    private Timestamp lastModifiedDate;

    @ManyToMany(mappedBy = "days") //mapped by field name in Job.class
    private Set<Job> jobs;

    public DayInCalendar(LocalDate day, Boolean isWorkday, Timestamp createdDate, Timestamp lastModifiedDate) {
        this.day = day;
        this.isWorkday = isWorkday;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public DayInCalendar() {}
}
