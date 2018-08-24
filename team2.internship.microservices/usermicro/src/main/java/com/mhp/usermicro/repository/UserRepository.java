package com.mhp.usermicro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mhp.usermicro.entity.Team;
import com.mhp.usermicro.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
   
   public User findByUsername(String name);
   
   public Long getIdByUsername(String username);
   
   public List<User> findUserByTeam(Team team);
   
   @Query (value = "SELECT * FROM userms WHERE LOWER(CONCAT(first_name,CONCAT(' ',last_name))) LIKE LOWER(CONCAT(?1, '%')) OR LOWER(CONCAT(last_name,CONCAT(' ',first_name))) LIKE LOWER(CONCAT(?1, '%'))", nativeQuery = true)
   public List<User> searchForUser(@Param ("name") String name);
   
   @Query (value = "SELECT * FROM userms Where team_id!=:id", nativeQuery = true)
   public List<User> getRemainingUsers(@Param ("id") long id);
   
   @Query (value = "SELECT * FROM userms WHERE (LOWER(CONCAT(first_name,CONCAT(' ',last_name))) LIKE LOWER(CONCAT(?1, '%')) OR LOWER(CONCAT(last_name,CONCAT(' ',first_name))) LIKE LOWER(CONCAT(?1, '%'))) AND team_id=?2", nativeQuery = true)
   public List<User> searchForUserFromTeam(@Param ("name") String name, @Param ("uid") long id);
   
   @Query (value = "SELECT * FROM userms WHERE (LOWER(CONCAT(first_name,CONCAT(' ',last_name))) LIKE LOWER(CONCAT(?1, '%')) OR LOWER(CONCAT(last_name,CONCAT(' ',first_name))) LIKE LOWER(CONCAT(?1, '%'))) AND team_id!=?2", nativeQuery = true)
   public List<User> searchForUserNotFromTeam(@Param ("name") String name, @Param ("uid") long id);
   
   @Query (value = "SELECT COUNT(*) FROM userms us WHERE us.team_id=:tid ", nativeQuery = true)
   public long findNrOfUsersFromATeam(@Param ("tid") long id);
   
   @Query (value = "SELECT CONCAT(us.first_name,CONCAT(' ',us.last_name)) FROM userms us WHERE us.team_id=:tid", nativeQuery = true)
   public List<String> findUserNamesFromATeam(@Param ("tid") long team_id);
   
}
