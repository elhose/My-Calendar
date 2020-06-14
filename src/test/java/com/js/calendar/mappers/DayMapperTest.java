package com.js.calendar.mappers;

import com.js.calendar.dto.day.DayDTO;
import com.js.calendar.dto.day.DayShortDTO;
import com.js.calendar.dto.day.DayUpdateDTO;
import com.js.calendar.dto.job.JobShortDTO;
import com.js.calendar.entities.Day;
import com.js.calendar.entities.Job;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DayMapperTest {

    @Autowired
    private DayMapper dayMapper;

    @Autowired
    private JobMapper jobMapper;

    @Test
    void mapBaseEntityToDtoTest() {
        //given
        Day day = new Day();
        day.setId(1L);
        day.setWorkday(true);
        day.setDayOfProject(LocalDate.of(2020, 1, 1));
        day.setHours(BigDecimal.valueOf(12L));
        day.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        day.setLastModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
        day.setJobs(mockJobs());

        //when
        DayDTO dayDTO = dayMapper.mapEntityToDto(day);

        //then
        assertNotNull(dayDTO);
        assertEquals(day.getId(), dayDTO.getId());
        assertEquals(day.getWorkday(), dayDTO.getWorkday());
        assertEquals(day.getDayOfProject(), dayDTO.getDayOfProject());
        assertEquals(day.getHours(), dayDTO.getHours());
        assertEquals(day.getLastModifiedDate(), localDateTimeToTimestamp(dayDTO.getLastModifiedDate()));
        assertEquals(day.getJobs().size(), dayDTO.getJobShortDTOS().size());
        assertEquals(day.getJobs(), jobShortDtosToJobs(dayDTO.getJobShortDTOS()));
    }

    @Test
    void mapDtoToBaseEntityEntityTest() {
        //given
        DayDTO dayDto = new DayDTO();
        dayDto.setId(1L);
        dayDto.setWorkday(true);
        dayDto.setDayOfProject(LocalDate.of(2020, 1, 1));
        dayDto.setHours(BigDecimal.valueOf(12L));
        dayDto.setLastModifiedDate(LocalDateTime.now());
        dayDto.setJobShortDTOS(mockJobShortDtos());

        //when
        Day day = dayMapper.mapDtoToEntity(dayDto);

        //then
        assertNotNull(day);
        assertEquals(dayDto.getId(), day.getId());
        assertEquals(dayDto.getWorkday(), day.getWorkday());
        assertEquals(dayDto.getDayOfProject(), day.getDayOfProject());
        assertEquals(dayDto.getHours(), day.getHours());
        assertEquals(dayDto.getLastModifiedDate(), timestampToLocalDateTime(day.getLastModifiedDate()));
        assertEquals(dayDto.getJobShortDTOS().size(), day.getJobs().size());
        assertEquals(dayDto.getJobShortDTOS(), jobsToJobShortDtos(day.getJobs()));
    }

    @Test
    void mapBaseEntityToShortDtoTest() {
        //given
        Day day = new Day();
        day.setId(1L);
        day.setWorkday(true);
        day.setDayOfProject(LocalDate.of(2020, 1, 1));
        day.setHours(BigDecimal.valueOf(12L));
        day.setLastModifiedDate(Timestamp.valueOf(LocalDateTime.now()));

        //when
        DayShortDTO dayShortDTO = dayMapper.mapEntityToShortDto(day);

        //then
        assertNotNull(dayShortDTO);
        assertEquals(day.getId(), dayShortDTO.getId());
        assertEquals(day.getWorkday(), dayShortDTO.getWorkday());
        assertEquals(day.getDayOfProject(), dayShortDTO.getDayOfProject());
        assertEquals(day.getHours(), dayShortDTO.getHours());
        assertEquals(day.getLastModifiedDate(), localDateTimeToTimestamp(dayShortDTO.getLastModifiedDate()));
    }

    @Test
    void mapShortDtoToBaseEntityTest() {
        //given
        DayShortDTO dayShortDTO = new DayShortDTO();
        dayShortDTO.setId(1L);
        dayShortDTO.setWorkday(true);
        dayShortDTO.setDayOfProject(LocalDate.of(2020, 1, 1));
        dayShortDTO.setHours(BigDecimal.valueOf(12L));
        dayShortDTO.setLastModifiedDate(LocalDateTime.now());

        //when
        Day day = dayMapper.mapShortDtoToEntity(dayShortDTO);

        //then
        assertNotNull(day);
        assertEquals(dayShortDTO.getId(), day.getId());
        assertEquals(dayShortDTO.getWorkday(), day.getWorkday());
        assertEquals(dayShortDTO.getDayOfProject(), day.getDayOfProject());
        assertEquals(dayShortDTO.getHours(), day.getHours());
        assertEquals(dayShortDTO.getLastModifiedDate(), timestampToLocalDateTime(day.getLastModifiedDate()));
    }

    @Test
    void mapBaseEntityToUpdateDtoTest() {
        //given
        Day day = new Day();
        day.setWorkday(true);
        day.setDayOfProject(LocalDate.of(2020, 1, 1));
        day.setHours(BigDecimal.valueOf(12L));

        //when
        DayUpdateDTO dayUpdateDTO = dayMapper.mapEntityToUpdateDto(day);

        //then
        assertNotNull(dayUpdateDTO);
        assertEquals(day.getWorkday(), dayUpdateDTO.getWorkday());
        assertEquals(day.getDayOfProject(), dayUpdateDTO.getDayOfProject());
        assertEquals(day.getHours(), dayUpdateDTO.getHours());
    }

    @Test
    void mapUpdateDtoToBaseEntityTest() {
        //given
        DayUpdateDTO dayUpdateDTO = new DayUpdateDTO();
        dayUpdateDTO.setWorkday(true);
        dayUpdateDTO.setDayOfProject(LocalDate.of(2020, 1, 1));
        dayUpdateDTO.setHours(BigDecimal.valueOf(12L));

        //when
        Day day = dayMapper.mapUpdateDtoToEntity(dayUpdateDTO);

        //then
        assertNotNull(day);
        assertEquals(dayUpdateDTO.getWorkday(), day.getWorkday());
        assertEquals(dayUpdateDTO.getDayOfProject(), day.getDayOfProject());
        assertEquals(dayUpdateDTO.getHours(), day.getHours());
    }

    private List<JobShortDTO> mockJobShortDtos() {
        List<JobShortDTO> jobShortDTOS = new ArrayList<>();

        JobShortDTO jobShortDTO1 = new JobShortDTO();
        jobShortDTO1.setId(1L);
        jobShortDTO1.setName("John Snow");
        jobShortDTO1.setHourlyState(BigDecimal.valueOf(123L));
        jobShortDTO1.setLastModifiedDate(LocalDateTime.now());

        JobShortDTO jobShortDTO2 = new JobShortDTO();
        jobShortDTO2.setId(2L);
        jobShortDTO2.setName("Mike Wazovsky");
        jobShortDTO2.setHourlyState(BigDecimal.valueOf(333L));
        jobShortDTO2.setLastModifiedDate(LocalDateTime.now());

        jobShortDTOS.add(jobShortDTO1);
        jobShortDTOS.add(jobShortDTO2);

        return jobShortDTOS;
    }

    private List<Job> mockJobs() {
        List<Job> jobs = new ArrayList<>();

        Job job1 = new Job();
        job1.setId(1L);
        job1.setName("John Snow");
        job1.setHourlyState(BigDecimal.valueOf(123L));
        job1.setLastModifiedDate(Timestamp.valueOf(LocalDateTime.now()));

        Job job2 = new Job();
        job2.setId(2L);
        job2.setName("Mike Wazovsky");
        job2.setHourlyState(BigDecimal.valueOf(333L));
        job2.setLastModifiedDate(Timestamp.valueOf(LocalDateTime.now()));

        jobs.add(job1);
        jobs.add(job2);

        return jobs;
    }

    private List<Job> jobShortDtosToJobs(List<JobShortDTO> jobShortDTOS) {
        return jobShortDTOS.stream().map(jobMapper::mapShortDtoToEntity).collect(Collectors.toList());
    }

    private List<JobShortDTO> jobsToJobShortDtos(List<Job> jobs) {
        return jobs.stream().map(jobMapper::mapEntityToShortDto).collect(Collectors.toList());
    }

    private Timestamp localDateTimeToTimestamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    private LocalDateTime timestampToLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }
}
