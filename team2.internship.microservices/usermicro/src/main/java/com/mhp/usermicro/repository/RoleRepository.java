package com.mhp.usermicro.repository;

import org.springframework.data.repository.CrudRepository;

import com.mhp.usermicro.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
   
}
