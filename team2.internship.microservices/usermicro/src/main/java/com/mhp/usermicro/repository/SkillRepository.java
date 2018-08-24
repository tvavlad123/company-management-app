package com.mhp.usermicro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mhp.usermicro.entity.Skill;

public interface SkillRepository extends CrudRepository<Skill, Long> {
   
   @Query (value = "SELECT p.skill_id,p.name FROM skillms p WHERE p.skill_id NOT IN (SELECT s.skill_id FROM user_skillms s WHERE s.user_id=:uid)", nativeQuery = true)
   public List<Skill> findSkillsRemainingForUser(@Param ("uid") long user_id);
}
