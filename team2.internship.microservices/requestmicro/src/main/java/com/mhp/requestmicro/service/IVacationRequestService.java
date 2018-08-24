package com.mhp.requestmicro.service;

import java.time.Month;
import java.time.Year;
import java.util.Date;

import com.mhp.requestmicro.entity.VacationRequest;
import com.mhp.requestmicro.epo.VacationRequestEpo;
import com.mhp.requestmicro.epo.list.VacationRequestEpoList;

public interface IVacationRequestService {
   
   public VacationRequestEpoList getAllVacationRequests();
   
   public VacationRequest deleteVacationRequest(long id);
   
   public VacationRequest updateVacationRequestStatus(VacationRequestEpo vacationRequest);
   
   public VacationRequestEpo findVacationRequestById(long id);
   
   public VacationRequestEpoList findVacationRequestsByUser(long user_id);
   
   public VacationRequest createVacationRequest(VacationRequestEpo vacationRequest);
   
   public VacationRequestEpoList findVacationRequestsUnresolvedForTeamCoord(long team_id);
   
   public VacationRequestEpoList findVacationRequestsUnresolvedForUser(long user_id);
   
   public VacationRequestEpoList findVacationRequestsAcceptedForUser(long user_id);
   
   public VacationRequestEpoList findVacationRequestsDeclinedForUser(long user_id);
   
   public VacationRequestEpoList findAllVacationRequestsUnresolved();
   
   public long findNumberOfVacationRequestsUnresolved();
   
   public long findNumberOfVacationRequestsUnresolvedForTeamCoord(long team_id);
   
   public long findNumberOfVacationRequestsAcceptedForUserForCurrentYear(long user_id);
   
   public long findNumberOfDaysLeftAvailableToTake(Date start_date, Date end_date, long user_id);

   VacationRequestEpoList getAllVacationRequestsOfSpecificUserMonthYear(long userId, Month month, Year year);

   VacationRequestEpoList getAllVacationRequestsByUserAndYear(long userId, int year);

   VacationRequestEpoList getAllVacationRequestsByTeamAndYear(long userId, int year);
}
