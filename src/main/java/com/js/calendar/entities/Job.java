package com.js.calendar.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "testString")
    private String testString;

    @Column(name = "hourlyState")
    private BigDecimal hourlyState;

    @CreationTimestamp
    @Column(name = "createdDate", updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    @Column(name = "lastModifiedDate")
    private Timestamp lastModifiedDate;
}
