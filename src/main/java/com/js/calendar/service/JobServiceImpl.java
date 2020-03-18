package com.js.calendar.service;

import com.js.calendar.entities.Job;
import com.js.calendar.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Iterable<Job> getJobs() {
        return jobRepository.findAll();
    }

    @Override
    public void addJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Optional getJob(Integer idOfJob) {
        return jobRepository.findById(idOfJob);
    }

    @Override
    public void deleteJob(Integer idOfJob) {
        jobRepository.deleteById(idOfJob);
    }
}
