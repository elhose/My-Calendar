package com.js.calendar.repository;

import com.js.calendar.entities.Job;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface JobRepository extends PagingAndSortingRepository<Job, Integer> {

}
