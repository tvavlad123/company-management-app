package com.mhp.usermicro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mhp.usermicro.entity.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
   
   @Query (value = "SELECT p.team_id,p.name,p.department_id FROM teamms p WHERE p.team_id NOT IN (SELECT s.team_id FROM team_leadms s WHERE s.user_id=:uid)", nativeQuery = true)
   public List<Team> findTeamsRemainingForUser(@Param ("uid") long user_id);
   
   @Query (value = "SELECT * FROM teamms WHERE team_id NOT IN (SELECT team_id FROM userms WHERE authority_id=2)", nativeQuery = true)
   public List<Team> findTeamsWithoutCoord();
   
}
