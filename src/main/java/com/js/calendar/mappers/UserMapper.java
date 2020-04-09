package com.js.calendar.mappers;

import com.js.calendar.dto.UserDTO;
import com.js.calendar.dto.UserShortDTO;
import com.js.calendar.entities.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateTimeMapper.class})
public interface UserMapper {

    @Mapping(source = "jobs", target = "jobShortDTOS")
    UserDTO mapUserToUserDTO(User user);

    @InheritInverseConfiguration
    User mapDtoToUser(UserDTO userDTO);

    UserShortDTO mapUserToUserShortDto(User user);

    User mapUserShortDtoToUser(UserShortDTO userShortDTO);
}
