package com.js.calendar.service;

import com.js.calendar.entities.Job;
import com.js.calendar.repository.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@DataJpaTest   //use spring to get beans from context
@ExtendWith(MockitoExtension.class) // use only mockito
public class BaseServiceJobEntityTest {

    private Job testJobMock;

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobService jobService = new JobServiceImpl(jobRepository);

    @BeforeEach
    private void beforeEach() {
        testJobMock = new Job();
        testJobMock.setId(1L);
        testJobMock.setName("John Snow");
        testJobMock.setHourlyState(BigDecimal.valueOf(8888L));
        testJobMock.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        testJobMock.setLastModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
    }

    @Test
    void getAllJobsTest() {
        //given
        List<Job> repositoryValues = new ArrayList<>();
        repositoryValues.add(testJobMock);
        when(jobRepository.findAll()).thenReturn(repositoryValues);

        //when
        Iterable<Job> jobs = jobService.getEntities();

        //then
        assertNotNull(jobs);
        assertEquals(repositoryValues.size(), iterableToList(jobs).size());
        assertEquals(repositoryValues, iterableToList(jobs));
        verify(jobRepository, times(1)).findAll();
    }

    @Test
    void getJobByIdWhenJobIsPresentTest() {
        //given
        Optional<Job> jobMock = Optional.of(this.testJobMock);
        when(jobRepository.findById(anyLong())).thenReturn(jobMock);

        //when
        Optional<Job> testEntity = jobService.getEntity(anyLong());

        //then
        assertEquals(testEntity, jobMock);
        assertTrue(testEntity.isPresent());
        assertEquals(testEntity.get(), jobMock.get());
        verify(jobRepository, times(1)).findById(anyLong());
    }

    @Test
    void getJobByIdWhenJobIsNotPresentTest() {
        //given
        Optional<Job> resultJob = Optional.empty();
        when(jobRepository.findById(anyLong())).thenReturn(resultJob);

        //when
        Optional<Job> testEntity = jobService.getEntity(anyLong());

        //test
        assertEquals(testEntity, resultJob);
        assertFalse(testEntity.isPresent());
    }

    @Test
    void deleteJobWhenIdPresentTest() {
        //given
        doNothing().when(jobRepository).deleteById(anyLong());

        //when
        jobService.deleteEntity(anyLong());
        jobService.deleteEntity(anyLong());
        jobService.deleteEntity(anyLong());

        //then
        verify(jobRepository, times(3)).deleteById(anyLong());
    }

    @Test
    void addJobTest() {
        //given
        Job addJob = new Job();
        addJob.setId(2L);
        addJob.setName("Tonald Drump");
        addJob.setHourlyState(BigDecimal.valueOf(987654321L));
        addJob.setCreatedDate(Timestamp.valueOf(LocalDateTime.MIN));
        addJob.setLastModifiedDate(Timestamp.valueOf(LocalDateTime.MIN));
        when(jobRepository.save(addJob)).thenAnswer(mock -> mock.getArgument(0, Job.class));

        //when
        jobService.addEntity(addJob);

        //then
        assertEquals(addJob, jobRepository.save(addJob));
        verify(jobRepository, times(2)).save(addJob);
    }

    @Test
    void updateExistingJobTest() {
        //given
        Job updateJob = new Job();
        updateJob.setId(3L);
        updateJob.setName("Rick Sanchez");
        updateJob.setHourlyState(BigDecimal.valueOf(222L));
        updateJob.setCreatedDate(Timestamp.valueOf(LocalDateTime.MAX));
        updateJob.setLastModifiedDate(Timestamp.valueOf(LocalDateTime.MAX));
        when(jobRepository.findById(anyLong())).thenReturn(Optional.of(testJobMock));

        //when
        jobService.updateEntity(1L, updateJob);

        //then
        verify(jobRepository, times(1)).findById(anyLong());
        verify(jobRepository, times(1)).save(updateJob);
    }

    @Test()
    void updateNotExistingJobTest() {
        //given
        Job updateJob = new Job();
        when(jobRepository.findById(anyLong())).thenReturn(Optional.empty());

        //when
        assertThrows(EmptyResultDataAccessException.class, () -> jobService.updateEntity(1L, updateJob));

        //then
        verify(jobRepository, times(1)).findById(anyLong());
        verify(jobRepository, times(0)).save(updateJob);
    }

    private <T> List<T> iterableToList(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
