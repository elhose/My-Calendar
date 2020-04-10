package com.js.calendar.service;

import com.js.calendar.entities.Job;
import com.js.calendar.repository.JobRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class JobServiceImpl extends BaseServiceImpl<Job> implements JobService {

    public JobServiceImpl(JobRepository repository) {
        super(repository);
    }

}
