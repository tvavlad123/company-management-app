package com.mhp.requestmicro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mhp.requestmicro.entity.VacationRequest;

public interface VacationRequestRepository extends CrudRepository<VacationRequest, Long> {
   
   @Query (value = "SELECT v.vacation_request_id,v.user_id,v.start_date,v.end_date,v.comments,v.picture,v.status_id FROM vacation_requestms v WHERE v.user_id = :uid", nativeQuery = true)
   public List<VacationRequest> findVacationRequestByUser(@Param ("uid") long user_id);
   
   @Query (value = "SELECT v.vacation_request_id,v.user_id,v.start_date,v.end_date,v.comments,v.picture,v.status_id FROM userms us INNER JOIN vacation_requestms v ON v.user_id=us.user_id INNER JOIN statusms sm ON v.status_id=sm.status_id WHERE us.team_id = :tid AND sm.name='UNRESOLVED'", nativeQuery = true)
   public List<VacationRequest> findVacationRequestsUnresolvedForTeamCoord(@Param ("tid") long team_id);
   
   @Query (value = "SELECT v.vacation_request_id,v.user_id,v.start_date,v.end_date,v.comments,v.picture,v.status_id FROM vacation_requestms v INNER JOIN statusms sts ON v.status_id=sts.status_id WHERE v.user_id = :uid AND sts.name='UNRESOLVED'", nativeQuery = true)
   public List<VacationRequest> findVacationRequestUnresolvedForUser(@Param ("uid") long user_id);
   
   @Query (value = "SELECT v.vacation_request_id,v.user_id,v.start_date,v.end_date,v.comments,v.picture,v.status_id FROM vacation_requestms v INNER JOIN statusms sts ON v.status_id=sts.status_id WHERE v.user_id = :uid AND sts.name='ACCEPTED'", nativeQuery = true)
   public List<VacationRequest> findVacationRequestAcceptedForUser(@Param ("uid") long user_id);
   
   @Query (value = "SELECT v.vacation_request_id,v.user_id,v.start_date,v.end_date,v.comments,v.picture,v.status_id FROM vacation_requestms v INNER JOIN statusms sts ON v.status_id=sts.status_id WHERE v.user_id = :uid AND sts.name='DECLINED'", nativeQuery = true)
   public List<VacationRequest> findVacationRequestDeclinedForUser(@Param ("uid") long user_id);
   
   @Query (value = "SELECT v.vacation_request_id,v.user_id,v.start_date,v.end_date,v.comments,v.picture,v.status_id FROM vacation_requestms v INNER JOIN statusms sts ON v.status_id=sts.status_id WHERE sts.name='UNRESOLVED'", nativeQuery = true)
   public List<VacationRequest> findAllVacationRequestsUnresolved();
   
   @Query (value = "SELECT COUNT(*) FROM vacation_requestms v INNER JOIN statusms sts ON v.status_id=sts.status_id WHERE sts.name='UNRESOLVED'", nativeQuery = true)
   public long findNumberOfVacationRequestsUnresolved();
   
   @Query (value = "SELECT COUNT(*) FROM userms us INNER JOIN vacation_requestms v ON v.user_id=us.user_id INNER JOIN statusms sm ON v.status_id=sm.status_id WHERE us.team_id = :tid AND sm.name='UNRESOLVED'", nativeQuery = true)
   public long findNumberOfVacationRequestsUnresolvedForTeamCoord(@Param ("tid") long team_id);
   
   @Query (value = "SELECT v.vacation_request_id,v.user_id,v.start_date,v.end_date,v.comments,v.picture,v.status_id FROM vacation_requestms v INNER JOIN statusms sts ON v.status_id=sts.status_id WHERE v.user_id = :uid AND sts.name='ACCEPTED' AND EXTRACT(YEAR FROM v.start_date)=date_part('year', CURRENT_DATE)", nativeQuery = true)
   public long findNumberOfVacationRequestsAcceptedForUserForCurrentYear(@Param ("uid") long user_id);

   @Query (value = "SELECT v.vacation_request_id,v.user_id,v.start_date,v.end_date,v.comments,v.picture,v.status_id FROM vacation_requestms v WHERE (extract(month from v.start_date) =:m AND extract(year from v.start_date) =:y AND v.user_id =:uid) OR (extract(month from v.end_date) =:m AND extract(year from v.end_date) =:y AND v.user_id =:uid)", nativeQuery = true)
   public List<VacationRequest>findVacationRequestByUser_idaAndStart_dateAndEnd_date(@Param ("uid") long userId, @Param ("m") int month, @Param("y")int year);

   @Query (value = "SELECT v.vacation_request_id,v.user_id,v.start_date,v.end_date,v.comments,v.picture,v.status_id FROM vacation_requestms v WHERE (extract(year from v.start_date) =:y AND v.user_id =:uid) OR (extract(year from v.end_date) =:y AND v.user_id =:uid)", nativeQuery = true)
   public List<VacationRequest> findVacationRequestByUserAndYear(@Param ("uid") long id, @Param("y")int year);

   @Query (value = "SELECT v.vacation_request_id,v.user_id,v.start_date,v.end_date,v.comments,v.picture,v.status_id FROM userms us INNER JOIN vacation_requestms v ON v.user_id=us.user_id INNER JOIN statusms sm ON v.status_id=sm.status_id WHERE us.team_id = :tid AND sm.name='ACCEPTED' AND ((extract(year from v.start_date) =:y) OR (extract(year from v.end_date) =:y))", nativeQuery = true)
   public List<VacationRequest> findVacationRequestByTeamAndYear(@Param ("tid") long id, @Param("y")int year);
}
