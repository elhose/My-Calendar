package com.js.calendar.mappers;

import com.js.calendar.dto.DayDTO;
import com.js.calendar.dto.DayShortDTO;
import com.js.calendar.entities.Day;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateTimeMapper.class})
public interface DayMapper {

    @Mapping(source = "jobs", target = "jobShortDTOS")
    DayDTO mapDayToDto(Day day);

    @InheritInverseConfiguration
    Day mapDtoToDay(DayDTO dayDTO);

    DayShortDTO mapDayToDayShortDto(Day day);

    @InheritInverseConfiguration
    Day mapDayShortDtoToDay(DayShortDTO dayShortDTO);
}
