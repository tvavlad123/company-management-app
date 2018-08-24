package com.mhp.requestmicro.service;

import java.time.Month;
import java.time.Year;
import java.util.Date;

import com.mhp.requestmicro.entity.HomeOfficeRequest;
import com.mhp.requestmicro.epo.HomeOfficeRequestEpo;
import com.mhp.requestmicro.epo.list.HalfDayEpoList;
import com.mhp.requestmicro.epo.list.HomeOfficeRequestEpoList;
import com.mhp.requestmicro.epo.list.StatusEpoList;

public interface IHomeOfficeRequestService {
   
   public HomeOfficeRequestEpoList getAllHomeOfficeRequests();
   
   public StatusEpoList getAllStatusType();
   
   public HomeOfficeRequest deleteHomeOfficeRequest(long id);
   
   public HomeOfficeRequest updateHomeOfficeRequestStatus(HomeOfficeRequestEpo homeOfficeRequest);
   
   public HomeOfficeRequestEpo findHomeOfficeRequestById(long id);
   
   public HomeOfficeRequestEpoList findHomeOfficeRequestsByUser(long user_id);
   
   public HomeOfficeRequest createHomeOfficeRequest(HomeOfficeRequestEpo homeOfficeRequest);
   
   public HomeOfficeRequestEpoList findHomeOfficeRequestsUnresolvedForTeamCoord(long team_id);
   
   public HalfDayEpoList getAllHalfDayType();
   
   public HomeOfficeRequestEpoList findHomeOfficeRequestsUnresolvedForUser(long user_id);
   
   public HomeOfficeRequestEpoList findHomeOfficeRequestsAcceptedForUser(long user_id);
   
   public HomeOfficeRequestEpoList findHomeOfficeRequestsDeclinedForUser(long user_id);
   
   public HomeOfficeRequestEpoList findAllHomeOfficeRequestsUnresolved();
   
   public long findNumberOfHomeOfficeRequestsUnresolved();
   
   public long findNumberOfHomeOfficeRequestsUnresolvedForTeamCoord(long team_id);
   
   public Float findNumberOfHomeOfficeRequestsAcceptedForUserForCurrentYear(long user_id);
   
   public Float findNumberOfDaysLeftAvailableToTake(Date start_date, Date end_date, long half_day_id, long user_id);

   HomeOfficeRequestEpoList getAllHomeOfficeRequestsOfSpecificUserMonthYear(long userId, Month month, Year year);

   HomeOfficeRequestEpoList getAllHomeOfficeRequestsByUserAndYear(long userId, int year);

   HomeOfficeRequestEpoList getAllHomeOfficeRequestsByTeamAndYear(long userId, int year);
}
