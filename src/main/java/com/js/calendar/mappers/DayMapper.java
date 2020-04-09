package com.js.calendar.mappers;

import com.js.calendar.dto.DayDTO;
import com.js.calendar.dto.DayShortDTO;
import com.js.calendar.dto.DayUpdateDTO;
import com.js.calendar.entities.Day;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateTimeMapper.class, JobMapper.class})
public interface DayMapper {

    @Mapping(source = "jobs", target = "jobShortDTOS")
    DayDTO mapDayToDto(Day day);

    Day mapDtoToDay(DayDTO dayDTO);

    DayShortDTO mapDayToDayShortDto(Day day);

    Day mapDayShortDtoToDay(DayShortDTO dayShortDTO);

    DayUpdateDTO mapDayToDayUpdateDto(Day day);

    Day mapDayUpdateDtoToDay(DayUpdateDTO dayUpdateDTO);
}
