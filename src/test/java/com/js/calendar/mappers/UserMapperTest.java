package com.js.calendar.mappers;

import com.js.calendar.dto.job.JobShortDTO;
import com.js.calendar.dto.user.UserDTO;
import com.js.calendar.dto.user.UserShortDTO;
import com.js.calendar.dto.user.UserUpdateDTO;
import com.js.calendar.entities.Job;
import com.js.calendar.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JobMapper jobMapper;

    @Test
    void mapBaseEntityToDtoTest() {
        User user = new User();
        user.setUsername("Jeff Bezos");
        user.setPassword("S3cUr3");
        user.setEnabled(true);
        user.setJobs(mockJobs());

        UserDTO userDTO = userMapper.mapEntityToDto(user);

        assertNotNull(userDTO);
        assertEquals(user.getUsername(), userDTO.getUsername());
        assertEquals(user.getPassword(), userDTO.getPassword());
        assertEquals(user.getEnabled(), userDTO.getEnabled());
        assertEquals(user.getJobs().size(), userDTO.getJobShortDTOS().size());
        assertEquals(user.getJobs(), jobShortDtosToEntites(userDTO.getJobShortDTOS()));
    }

    @Test
    void mapDtoToBaseEntityEntityTest() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("John Doe");
        userDTO.setPassword("Not Secure at all");
        userDTO.setEnabled(false);
        userDTO.setJobShortDTOS(mockShortJobDtos());

        User user = userMapper.mapDtoToEntity(userDTO);

        assertNotNull(user);
        assertEquals(userDTO.getUsername(), user.getUsername());
        assertEquals(userDTO.getPassword(), user.getPassword());
        assertEquals(userDTO.getEnabled(), user.getEnabled());
        assertEquals(userDTO.getJobShortDTOS().size(), user.getJobs().size());
        assertEquals(userDTO.getJobShortDTOS(), jobsToShortJobDtos(user.getJobs()));
    }

    @Test
    void mapBaseEntityToShortDtoTest() {
        User user = new User();
        user.setUsername("Dan Bilzerian");
        user.setEnabled(true);

        UserShortDTO userShortDTO = userMapper.mapEntityToShortDto(user);

        assertNotNull(userShortDTO);
        assertEquals(user.getUsername(), userShortDTO.getUsername());
        assertEquals(user.getEnabled(), userShortDTO.getEnabled());
    }

    @Test
    void mapShortDtoToBaseEntityTest() {
        UserShortDTO userShortDTO = new UserShortDTO();
        userShortDTO.setUsername("Yoandy Villarutia");
        userShortDTO.setEnabled(false);

        User user = userMapper.mapShortDtoToEntity(userShortDTO);

        assertNotNull(user);
        assertEquals(userShortDTO.getUsername(), user.getUsername());
        assertEquals(userShortDTO.getEnabled(), user.getEnabled());
    }

    @Test
    void mapBaseEntityToUpdateDtoTest() {
        User user = new User();
        user.setUsername("JROC");
        user.setPassword("Lit Password");

        UserUpdateDTO userUpdateDTO = userMapper.mapEntityToUpdateDto(user);

        assertNotNull(userUpdateDTO);
        assertEquals(user.getUsername(), userUpdateDTO.getUsername());
        assertEquals(user.getPassword(), userUpdateDTO.getPassword());
    }

    @Test
    void mapUpdateDtoToBaseEntityTest() {
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setUsername("Clark Kent");
        userUpdateDTO.setPassword("Im batman");

        User user = userMapper.mapUpdateDtoToEntity(userUpdateDTO);

        assertNotNull(user);
        assertEquals(userUpdateDTO.getUsername(), user.getUsername());
        assertEquals(userUpdateDTO.getPassword(), user.getPassword());
    }

    private List<Job> mockJobs() {
        List<Job> jobs = new ArrayList<>();

        Job job1 = new Job();
        job1.setId(3456L);
        job1.setName("Lecturing on Univeristy");
        job1.setHourlyState(BigDecimal.valueOf(333L));
        job1.setLastModifiedDate(Timestamp.valueOf(LocalDateTime.now()));

        Job job2 = new Job();
        job2.setId(1111L);
        job2.setName("Lecturing on High School");
        job2.setHourlyState(BigDecimal.valueOf(33L));
        job2.setLastModifiedDate(Timestamp.valueOf(LocalDateTime.now()));

        jobs.add(job1);
        jobs.add(job2);

        return jobs;
    }

    private List<Job> jobShortDtosToEntites(List<JobShortDTO> jobShortDTOS) {
        return jobShortDTOS.stream().map(jobMapper::mapShortDtoToEntity).collect(Collectors.toList());
    }

    private List<JobShortDTO> mockShortJobDtos() {
        List<JobShortDTO> jobShortDTOS = new ArrayList<>();

        JobShortDTO jobShortDTO1 = new JobShortDTO();
        jobShortDTO1.setId(32L);
        jobShortDTO1.setName("Piloting Boeing 474");
        jobShortDTO1.setHourlyState(BigDecimal.valueOf(333L));
        jobShortDTO1.setLastModifiedDate(LocalDateTime.now());

        JobShortDTO jobShortDTO2 = new JobShortDTO();
        jobShortDTO2.setId(45L);
        jobShortDTO2.setName("Saving the wolrd");
        jobShortDTO2.setHourlyState(BigDecimal.valueOf(1L));
        jobShortDTO2.setLastModifiedDate(LocalDateTime.now());

        jobShortDTOS.add(jobShortDTO1);
        jobShortDTOS.add(jobShortDTO2);

        return jobShortDTOS;
    }

    private List<JobShortDTO> jobsToShortJobDtos(List<Job> jobs) {
        return jobs.stream().map(jobMapper::mapEntityToShortDto).collect(Collectors.toList());
    }

    private Timestamp localDateTimeToTimestamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    private LocalDateTime timestampToLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }
}
