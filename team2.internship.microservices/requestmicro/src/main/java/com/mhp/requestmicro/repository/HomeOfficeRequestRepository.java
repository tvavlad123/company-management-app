package com.mhp.requestmicro.repository;

import java.time.Month;
import java.time.Year;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mhp.requestmicro.entity.HomeOfficeRequest;

public interface HomeOfficeRequestRepository extends CrudRepository<HomeOfficeRequest, Long> {
   
   @Query (value = "SELECT hr.homeoffice_request_id,hr.user_id,hr.start_date,hr.end_date,hr.half_day_id,hr.status_id FROM homeoffice_requestms hr WHERE hr.user_id = :uid", nativeQuery = true)
   public List<HomeOfficeRequest> findHomeOfficeRequestByUser(@Param ("uid") long user_id);
   
   @Query (value = "SELECT hr.homeoffice_request_id,hr.user_id,hr.start_date,hr.end_date,hr.half_day_id,hr.status_id FROM userms us INNER JOIN homeoffice_requestms hr ON hr.user_id=us.user_id INNER JOIN statusms sm ON hr.status_id=sm.status_id WHERE us.team_id = :tid AND sm.name='UNRESOLVED'", nativeQuery = true)
   public List<HomeOfficeRequest> findHomeOfficeRequestsUnresolvedForTeamCoord(@Param ("tid") long team_id);
   
   @Query (value = "SELECT hr.homeoffice_request_id,hr.user_id,hr.start_date,hr.end_date,hr.half_day_id,hr.status_id FROM homeoffice_requestms hr INNER JOIN statusms sts ON hr.status_id=sts.status_id WHERE hr.user_id = :uid AND sts.name='UNRESOLVED'", nativeQuery = true)
   public List<HomeOfficeRequest> findHomeOfficeRequestUnresolvedForUser(@Param ("uid") long user_id);
   
   @Query (value = "SELECT hr.homeoffice_request_id,hr.user_id,hr.start_date,hr.end_date,hr.half_day_id,hr.status_id FROM homeoffice_requestms hr INNER JOIN statusms sts ON hr.status_id=sts.status_id WHERE hr.user_id = :uid AND sts.name='ACCEPTED'", nativeQuery = true)
   public List<HomeOfficeRequest> findHomeOfficeRequestAcceptedForUser(@Param ("uid") long user_id);
   
   @Query (value = "SELECT hr.homeoffice_request_id,hr.user_id,hr.start_date,hr.end_date,hr.half_day_id,hr.status_id FROM homeoffice_requestms hr INNER JOIN statusms sts ON hr.status_id=sts.status_id WHERE hr.user_id = :uid AND sts.name='DECLINED'", nativeQuery = true)
   public List<HomeOfficeRequest> findHomeOfficeRequestDeclinedForUser(@Param ("uid") long user_id);
   
   @Query (value = "SELECT hr.homeoffice_request_id,hr.user_id,hr.start_date,hr.end_date,hr.half_day_id,hr.status_id FROM homeoffice_requestms hr INNER JOIN statusms sts ON hr.status_id=sts.status_id WHERE sts.name='UNRESOLVED'", nativeQuery = true)
   public List<HomeOfficeRequest> findAllHomeOfficeRequestsUnresolved();
   
   @Query (value = "SELECT COUNT(*) FROM homeoffice_requestms hr INNER JOIN statusms sts ON hr.status_id=sts.status_id WHERE sts.name='UNRESOLVED'", nativeQuery = true)
   public long findNumberOfHomeOfficeRequestsUnresolved();
   
   @Query (value = "SELECT COUNT(*) FROM userms us INNER JOIN homeoffice_requestms hr ON hr.user_id=us.user_id INNER JOIN statusms sm ON hr.status_id=sm.status_id WHERE us.team_id = :tid AND sm.name='UNRESOLVED'", nativeQuery = true)
   public long findNumberOfHomeOfficeRequestsUnresolvedForTeamCoord(@Param ("tid") long team_id);
   
   @Query (value = "SELECT hr.homeoffice_request_id,hr.user_id,hr.start_date,hr.end_date,hr.half_day_id,hr.status_id FROM homeoffice_requestms hr INNER JOIN statusms sts ON hr.status_id=sts.status_id WHERE hr.user_id = :uid AND sts.name='ACCEPTED' AND EXTRACT(YEAR FROM hr.start_date)=date_part('year', CURRENT_DATE)", nativeQuery = true)
   public List<HomeOfficeRequest> findNumberOfHomeOfficeRequestAcceptedForUserForCurrentYear(@Param ("uid") long user_id);

   @Query (value = "SELECT hr.homeoffice_request_id,hr.user_id,hr.start_date,hr.end_date,hr.half_day_id,hr.status_id FROM homeoffice_requestms hr WHERE (extract(month from hr.start_date) =:m AND extract(year from hr.start_date) =:y AND hr.user_id =:uid) OR (extract(month from hr.end_date) =:m AND extract(year from hr.end_date) =:y AND hr.user_id =:uid)", nativeQuery = true)
   public List<HomeOfficeRequest>findHomeOfficeRequestByUser_idaAndStart_dateAndEnd_date(@Param ("uid") long userId,@Param ("m") int month, @Param("y")int year);

   @Query (value = "SELECT hr.homeoffice_request_id,hr.user_id,hr.start_date,hr.end_date,hr.half_day_id,hr.status_id FROM homeoffice_requestms hr WHERE (extract(year from hr.start_date) =:y AND hr.user_id =:uid) OR (extract(year from hr.end_date) =:y AND hr.user_id =:uid)", nativeQuery = true)
   public List<HomeOfficeRequest> findHomeOfficeRequestByUserAndYear(@Param ("uid") long id, @Param("y")int year);

   @Query (value = "SELECT hr.homeoffice_request_id,hr.user_id,hr.start_date,hr.end_date,hr.half_day_id,hr.status_id FROM userms us INNER JOIN homeoffice_requestms hr ON hr.user_id=us.user_id INNER JOIN statusms sm ON hr.status_id=sm.status_id WHERE us.team_id =:tid AND sm.name='ACCEPTED' AND ((extract(year from hr.start_date) =:y) OR (extract(year from hr.end_date) =:y))", nativeQuery = true)
   public List<HomeOfficeRequest> findHomeOfficeRequestByTeamAndYear(@Param ("tid") long id, @Param("y")int year);

}
