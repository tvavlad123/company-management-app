package com.mhp.usermicro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mhp.usermicro.entity.TeamLead;
import com.mhp.usermicro.entity.User;

public interface TeamLeadRepository extends CrudRepository<TeamLead, Long> {
   
   @Query (value = "SELECT p.team_lead_id,p.user_id,p.team_id FROM team_leadms p WHERE p.user_id = :uid", nativeQuery = true)
   public List<TeamLead> findTeamLeadByUser(@Param ("uid") long user_id);
   
   @Query (value = "SELECT COUNT(*) FROM team_leadms WHERE userms us INNER JOIN team_leadms tl ON us.user_id=tl.user_id INNER JOIN teamms tm ON tl.team_id=tm.team_id WHERE tl.user_id=:uid", nativeQuery = true)
   public long findNrOfTeamsOfATeamLead(@Param ("uid") long user_id);
   
   public void deleteByUser(User user);
   
}
