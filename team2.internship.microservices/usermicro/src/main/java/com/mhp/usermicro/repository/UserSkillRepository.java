package com.mhp.usermicro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mhp.usermicro.entity.User;
import com.mhp.usermicro.entity.UserSkill;

public interface UserSkillRepository extends CrudRepository<UserSkill, Long> {
   
   @Query (value = "SELECT p.user_skill_id,p.user_id,p.skill_id,p.skill_level_id FROM user_skillms p WHERE p.user_id = :uid", nativeQuery = true)
   public List<UserSkill> findSkillsByUser(@Param ("uid") long user_id);
   
   public void deleteByUser(User user);
}
