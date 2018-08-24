package com.mhp.usermicro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mhp.usermicro.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
   
}
