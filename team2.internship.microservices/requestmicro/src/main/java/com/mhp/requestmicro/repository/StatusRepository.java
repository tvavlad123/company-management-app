package com.mhp.requestmicro.repository;

import org.springframework.data.repository.CrudRepository;

import com.mhp.requestmicro.entity.Status;

public interface StatusRepository extends CrudRepository<Status, Long> {
   
}
