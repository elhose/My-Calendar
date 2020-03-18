package com.js.calendar.service;

import com.js.calendar.entities.Job;

import java.util.Optional;

public interface JobService {
    Iterable<Job> getJobs();
    void addJob(Job job);
    Optional getJob(Integer idOfJob);
    void deleteJob(Integer idOfJob);
}
