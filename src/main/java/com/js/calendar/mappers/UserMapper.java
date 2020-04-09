package com.js.calendar.mappers;

import com.js.calendar.dto.UserDTO;
import com.js.calendar.dto.UserShortDTO;
import com.js.calendar.dto.UserUpdateDTO;
import com.js.calendar.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateTimeMapper.class, JobMapper.class})
public interface UserMapper {

    @Mapping(source = "jobs", target = "jobShortDTOS")
    UserDTO mapUserToUserDTO(User user);

    User mapDtoToUser(UserDTO userDTO);

    UserShortDTO mapUserToUserShortDto(User user);

    User mapUserShortDtoToUser(UserShortDTO userShortDTO);

    UserUpdateDTO mapUserToUserUpdateDto(User user);

    User mapUserUpdateDtoToUser(UserUpdateDTO userUpdateDTO);
}
