package com.js.calendar.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String username;

    private String password;

    private Boolean enabled;

    @CreationTimestamp
    @Column(name = "createdDate", updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    @Column(name = "lastModifiedDate")
    private Timestamp lastModifiedDate;

    @OneToMany(mappedBy = "user") // też może sie przez mapowanie wyjebać, ma być to nazwa fielda w klasie Job
    private Set<Job> jobs;
}
