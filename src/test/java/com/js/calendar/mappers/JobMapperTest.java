package com.js.calendar.mappers;

import com.js.calendar.dto.day.DayShortDTO;
import com.js.calendar.dto.job.JobDTO;
import com.js.calendar.dto.job.JobShortDTO;
import com.js.calendar.dto.job.JobUpdateDTO;
import com.js.calendar.dto.user.UserShortDTO;
import com.js.calendar.entities.Day;
import com.js.calendar.entities.Job;
import com.js.calendar.entities.User;
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
public class JobMapperTest {

    @Autowired
    private DayMapper dayMapper;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    void mapBaseEntityToDtoTest() {
        Job job = new Job();
        job.setId(1L);
        job.setName("Software Developer");
        job.setHourlyState(BigDecimal.valueOf(12L));
        job.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        job.setLastModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
        job.setUser(mockUser());
        job.setDays(mockDays());

        JobDTO jobDTO = jobMapper.mapEntityToDto(job);

        assertNotNull(jobDTO);
        assertEquals(job.getId(), jobDTO.getId());
        assertEquals(job.getName(), jobDTO.getName());
        assertEquals(job.getHourlyState(), jobDTO.getHourlyState());
        assertEquals(job.getLastModifiedDate(), localDateTimeToTimestamp(jobDTO.getLastModifiedDate()));
        assertNotNull(jobDTO.getUserShortDTO());
        assertEquals(job.getUser(), userShortDtoToUser(jobDTO.getUserShortDTO()));
        assertEquals(job.getDays().size(), jobDTO.getDayShortDTOS().size());
        assertEquals(job.getDays(), dayShortDtosToEntites(jobDTO.getDayShortDTOS()));
    }

    @Test
    void mapDtoToBaseEntityEntityTest() {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(1L);
        jobDTO.setName("Connecting microservices");
        jobDTO.setHourlyState(BigDecimal.valueOf(234L));
        jobDTO.setLastModifiedDate(LocalDateTime.now());
        jobDTO.setUserShortDTO(mockShortUserDto());
        jobDTO.setDayShortDTOS(mockShortDayDto());

        Job job = jobMapper.mapDtoToEntity(jobDTO);

        assertNotNull(job);
        assertEquals(jobDTO.getId(), job.getId());
        assertEquals(jobDTO.getName(), job.getName());
        assertEquals(jobDTO.getHourlyState(), job.getHourlyState());
        assertEquals(jobDTO.getLastModifiedDate(), timestampToLocalDateTime(job.getLastModifiedDate()));
        assertNotNull(job.getUser());
        assertEquals(jobDTO.getUserShortDTO(), userToShortUserDto(job.getUser()));
        assertEquals(jobDTO.getDayShortDTOS().size(), job.getDays().size());
        assertEquals(jobDTO.getDayShortDTOS(), daysEntitiesToDayShortDtos(job.getDays()));
    }

    @Test
    void mapBaseEntityToShortDtoTest() {
        Job job = new Job();
        job.setId(1L);
        job.setName("Developing front-end in Angular");
        job.setHourlyState(BigDecimal.valueOf(88L));
        job.setLastModifiedDate(Timestamp.valueOf(LocalDateTime.now()));

        JobShortDTO jobShortDTO = jobMapper.mapEntityToShortDto(job);

        assertNotNull(jobShortDTO);
        assertEquals(job.getId(), jobShortDTO.getId());
        assertEquals(job.getName(), jobShortDTO.getName());
        assertEquals(job.getHourlyState(), jobShortDTO.getHourlyState());
        assertEquals(job.getLastModifiedDate(), localDateTimeToTimestamp(jobShortDTO.getLastModifiedDate()));
    }

    @Test
    void mapShortDtoToBaseEntityTest() {
        JobShortDTO jobShortDTO = new JobShortDTO();
        jobShortDTO.setId(1L);
        jobShortDTO.setName("Developing Tests");
        jobShortDTO.setHourlyState(BigDecimal.valueOf(420L));
        jobShortDTO.setLastModifiedDate(LocalDateTime.now());

        Job job = jobMapper.mapShortDtoToEntity(jobShortDTO);

        assertNotNull(job);
        assertEquals(jobShortDTO.getId(), job.getId());
        assertEquals(jobShortDTO.getName(), job.getName());
        assertEquals(jobShortDTO.getHourlyState(), job.getHourlyState());
        assertEquals(jobShortDTO.getLastModifiedDate(), timestampToLocalDateTime(job.getLastModifiedDate()));
    }

    @Test
    void mapBaseEntityToUpdateDtoTest() {
        Job job = new Job();
        job.setName("true");
        job.setHourlyState(BigDecimal.valueOf(100L));

        JobUpdateDTO jobUpdateDTO = jobMapper.mapEntityToUpdateDto(job);

        assertNotNull(jobUpdateDTO);
        assertEquals(job.getName(), jobUpdateDTO.getName());
        assertEquals(job.getHourlyState(), jobUpdateDTO.getHourlyState());
    }

    @Test
    void mapUpdateDtoToBaseEntityTest() {
        JobUpdateDTO jobUpdateDTO = new JobUpdateDTO();
        jobUpdateDTO.setName("Webscraping with Selenium");
        jobUpdateDTO.setHourlyState(BigDecimal.valueOf(3333L));

        Job job = jobMapper.mapUpdateDtoToEntity(jobUpdateDTO);

        assertNotNull(job);
        assertEquals(jobUpdateDTO.getName(), job.getName());
        assertEquals(jobUpdateDTO.getHourlyState(), job.getHourlyState());
    }

    private User mockUser() {
        User user = new User();
        user.setUsername("MY NAME JEFF");
        user.setEnabled(false);
        return user;
    }

    private List<Day> mockDays() {
        List<Day> days = new ArrayList<>();
        Day day1 = new Day();
        day1.setId(5412L);
        day1.setDayOfProject(LocalDate.of(2020, 02, 02));
        day1.setWorkday(false);
        day1.setHours(BigDecimal.valueOf(987L));
        day1.setLastModifiedDate(Timestamp.valueOf(LocalDateTime.now()));

        Day day2 = new Day();
        day2.setId(31231L);
        day2.setDayOfProject(LocalDate.of(3030, 03, 03));
        day2.setWorkday(true);
        day2.setHours(BigDecimal.valueOf(9871231L));
        day2.setLastModifiedDate(Timestamp.valueOf(LocalDateTime.now()));

        days.add(day1);
        days.add(day2);
        return days;
    }

    private User userShortDtoToUser(UserShortDTO userShortDTO) {
        return userMapper.mapShortDtoToEntity(userShortDTO);
    }

    private List<Day> dayShortDtosToEntites(List<DayShortDTO> dayShortDTOS) {
        return dayShortDTOS.stream().map(dayMapper::mapShortDtoToEntity).collect(Collectors.toList());
    }

    private UserShortDTO mockShortUserDto() {
        UserShortDTO userShortDTO = new UserShortDTO();
        userShortDTO.setUsername("Uncle Sam");
        userShortDTO.setEnabled(false);
        return userShortDTO;
    }

    private List<DayShortDTO> mockShortDayDto() {
        List<DayShortDTO> dayShortDTOS = new ArrayList<>();
        DayShortDTO dayShortDTO1 = new DayShortDTO();
        dayShortDTO1.setId(567L);
        dayShortDTO1.setWorkday(true);
        dayShortDTO1.setDayOfProject(LocalDate.MIN);
        dayShortDTO1.setHours(BigDecimal.valueOf(765L));
        dayShortDTO1.setLastModifiedDate(LocalDateTime.now());

        DayShortDTO dayShortDTO2 = new DayShortDTO();
        dayShortDTO2.setId(889l);
        dayShortDTO2.setWorkday(false);
        dayShortDTO2.setDayOfProject(LocalDate.MAX);
        dayShortDTO2.setHours(BigDecimal.valueOf(911L));
        dayShortDTO2.setLastModifiedDate(LocalDateTime.now());

        dayShortDTOS.add(dayShortDTO1);
        dayShortDTOS.add(dayShortDTO2);

        return dayShortDTOS;
    }

    private UserShortDTO userToShortUserDto(User user) {
        return userMapper.mapEntityToShortDto(user);
    }

    private List<DayShortDTO> daysEntitiesToDayShortDtos(List<Day> days) {
        return days.stream().map(day -> dayMapper.mapEntityToShortDto(day)).collect(Collectors.toList());
    }

    private Timestamp localDateTimeToTimestamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    private LocalDateTime timestampToLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }
}
