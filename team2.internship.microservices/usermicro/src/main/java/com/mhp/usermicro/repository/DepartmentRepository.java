package com.mhp.usermicro.repository;

import org.springframework.data.repository.CrudRepository;

import com.mhp.usermicro.entity.Department;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
   
}
