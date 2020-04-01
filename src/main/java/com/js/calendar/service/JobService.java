package com.js.calendar.service;

import com.js.calendar.entities.Job;

import java.util.Optional;

public interface JobService {
    Iterable<Job> getJobs();
    void addJob(Job job);
    void updateJob(Long id, Job job);
    Optional<Job> getJob(Long id);
    void deleteJob(Long id);
}
