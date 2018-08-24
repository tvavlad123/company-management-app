package com.mhp.usermicro.repository;

import org.springframework.data.repository.CrudRepository;

import com.mhp.usermicro.entity.Position;

public interface PositionRepository extends CrudRepository<Position, Long> {
   
}
