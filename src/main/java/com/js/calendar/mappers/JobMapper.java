package com.js.calendar.mappers;

import com.js.calendar.dto.job.JobDTO;
import com.js.calendar.dto.job.JobShortDTO;
import com.js.calendar.dto.job.JobUpdateDTO;
import com.js.calendar.entities.Job;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateTimeMapper.class, DayMapper.class, UserMapper.class})
public interface JobMapper extends BaseMapper<JobDTO, JobShortDTO, JobUpdateDTO, Job>{

    @Mapping(source = "user", target = "userShortDTO")
    @Mapping(source = "days", target = "dayShortDTOS")
    JobDTO mapEntityToDto(Job job);

    @InheritInverseConfiguration(name = "mapEntityToDto")
    Job mapDtoToEntity(JobDTO jobDTO);

    JobShortDTO mapEntityToShortDto(Job job);

    Job mapShortDtoToEntity(JobShortDTO jobShortDTO);

    JobUpdateDTO mapEntityToUpdateDto(Job job);

    Job mapUpdateDtoToEntity(JobUpdateDTO jobUpdateDTO);
}
