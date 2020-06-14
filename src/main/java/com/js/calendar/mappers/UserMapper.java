package com.js.calendar.mappers;

import com.js.calendar.dto.user.UserDTO;
import com.js.calendar.dto.user.UserShortDTO;
import com.js.calendar.dto.user.UserUpdateDTO;
import com.js.calendar.entities.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateTimeMapper.class, JobMapper.class})
public interface UserMapper extends BaseMapper<UserDTO, UserShortDTO, UserUpdateDTO, User>{

    @Mapping(source = "jobs", target = "jobShortDTOS")
    UserDTO mapEntityToDto(User user);

    @InheritInverseConfiguration(name = "mapEntityToDto")
    User mapDtoToEntity(UserDTO userDTO);

    UserShortDTO mapEntityToShortDto(User user);

    User mapShortDtoToEntity(UserShortDTO userShortDTO);

    UserUpdateDTO mapEntityToUpdateDto(User user);

    User mapUpdateDtoToEntity(UserUpdateDTO userUpdateDTO);
}
