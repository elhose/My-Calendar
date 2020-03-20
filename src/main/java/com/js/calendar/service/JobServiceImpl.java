package com.js.calendar.service;

import com.js.calendar.entities.Job;
import com.js.calendar.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    @Override
    public Iterable<Job> getJobs() {
        return jobRepository.findAll();
    }

    @Override
    public void addJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Optional getJob(Integer id) {
        return jobRepository.findById(id);
    }

    @Override
    public void deleteJob(Integer id) {
        jobRepository.deleteById(id);
    }

    @Override
    public void updateJob(Integer id, Job job) {
        Optional<Job> foundJob = jobRepository.findById(id);

        if (foundJob.isPresent()){
            job.setId(foundJob.get().getId());
        }

        jobRepository.save(job);
    }
}
