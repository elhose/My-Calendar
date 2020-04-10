package com.js.calendar.mappers;

import com.js.calendar.dto.BaseDTO;
import com.js.calendar.dto.ShortDTO;
import com.js.calendar.dto.UpdateDTO;
import com.js.calendar.entities.BaseEntity;

public interface BaseMapper<T extends BaseDTO, S extends ShortDTO, U extends UpdateDTO, V extends BaseEntity> {

    T mapEntityToDto(V entity);

    V mapDtoToEntity(T dto);

    S mapEntityToShortDto(V entity);

    V mapShortDtoToEntity(S shortDTO);

    U mapEntityToUpdateDto(V entity);

    V mapUpdateDtoToEntity(U updateDTO);

}
