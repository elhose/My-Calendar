package com.js.calendar.repository;

import com.js.calendar.entities.BaseEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepository<T extends BaseEntity> extends PagingAndSortingRepository<T, Long> {

}
