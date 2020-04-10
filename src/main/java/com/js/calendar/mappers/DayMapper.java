package com.js.calendar.mappers;

import com.js.calendar.dto.day.DayDTO;
import com.js.calendar.dto.day.DayShortDTO;
import com.js.calendar.dto.day.DayUpdateDTO;
import com.js.calendar.entities.Day;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateTimeMapper.class, JobMapper.class})
public interface DayMapper extends BaseMapper<DayDTO, DayShortDTO, DayUpdateDTO ,Day>{

    @Mapping(source = "jobs", target = "jobShortDTOS")
    DayDTO mapEntityToDto(Day day);

    Day mapDtoToEntity(DayDTO dayDTO);

    DayShortDTO mapEntityToShortDto(Day day);

    Day mapShortDtoToEntity(DayShortDTO dayShortDTO);

    DayUpdateDTO mapEntityToUpdateDto(Day day);

    Day mapUpdateDtoToEntity(DayUpdateDTO dayUpdateDTO);
}
