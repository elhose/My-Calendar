package com.js.calendar.mappers;

import com.js.calendar.dto.JobDTO;
import com.js.calendar.dto.JobShortDTO;
import com.js.calendar.dto.JobUpdateDTO;
import com.js.calendar.entities.Job;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateTimeMapper.class, DayMapper.class, UserMapper.class})
public interface JobMapper {

    @Mapping(source = "user", target = "userShortDTO")
    @Mapping(source = "days", target = "dayShortDTOS")
    JobDTO mapJobToJobDTO(Job job);

    @InheritInverseConfiguration(name = "mapJobToJobDTO")
    Job mapDtoToJob(JobDTO jobDTO);

    JobShortDTO mapJobToShortJobDTO(Job job);

    Job mapJobShortDtoToJob(JobShortDTO jobShortDTO);

    JobUpdateDTO mapJobToUpdateJobDto(Job job);

    Job mapJobUpdateDtoToJob(JobUpdateDTO jobUpdateDTO);
}
