package com.js.calendar.service;

import com.js.calendar.entities.Job;

import java.util.Optional;

public interface JobService {
    Iterable<Job> getJobs();
    void addJob(Job job);
    void updateJob(Integer id, Job job);
    Optional getJob(Integer id);
    void deleteJob(Integer id);
}
