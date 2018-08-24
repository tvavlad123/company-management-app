package com.mhp.requestmicro.service;

import java.time.Month;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mhp.requestmicro.entity.VacationRequest;
import com.mhp.requestmicro.epo.VacationRequestEpo;
import com.mhp.requestmicro.epo.list.VacationRequestEpoList;
import com.mhp.requestmicro.mapper.StatusEpoMapper;
import com.mhp.requestmicro.mapper.VacationRequestEpoMapper;
import com.mhp.requestmicro.repository.VacationRequestRepository;

@Service
public class VacationRequestService implements IVacationRequestService {
   
   private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(VacationRequestService.class);
   
   @Autowired
   private VacationRequestEpoMapper      vacationepomapper;
   @Autowired
   private VacationRequestRepository     vacationRequestRepository;
   @Autowired
   private StatusEpoMapper               statusepomapper;
   
   @Transactional (readOnly = true)
   @Override
   public VacationRequestEpoList getAllVacationRequests() {
      LOGGER.debug("Getting all vacation requests from repository");
      return new VacationRequestEpoList(
         vacationepomapper.toExternals((List<VacationRequest>)vacationRequestRepository.findAll()));
   }
   
   @Transactional
   @Override
   public VacationRequest deleteVacationRequest(long id) {
      LOGGER.debug("Deleting vacation request under id: " + id);
      VacationRequest deletedReq = vacationRequestRepository.findOne(id);
      LOGGER.debug("With this info: " + deletedReq);
      vacationRequestRepository.delete(deletedReq);
      return deletedReq;
   }
   
   @Transactional
   @Override
   public VacationRequest updateVacationRequestStatus(VacationRequestEpo vacationRequest) {
      VacationRequest updatedVacationRequest = vacationRequestRepository.findOne(vacationRequest.getId());
      LOGGER.debug("Updating vacation request: " + updatedVacationRequest);
      updatedVacationRequest.setStatus_id(statusepomapper.toInternal(vacationRequest.getStatus_id()));
      LOGGER.debug("To vacation request: " + updatedVacationRequest);
      return updatedVacationRequest;
   }
   
   @Transactional (readOnly = true)
   @Override
   public VacationRequestEpo findVacationRequestById(long id) {
      LOGGER.debug("Find a vacation request by id: " + id);
      return vacationepomapper.toExternal(vacationRequestRepository.findOne(id));
   }
   
   @Transactional (readOnly = true)
   @Override
   public VacationRequestEpoList findVacationRequestsByUser(long user_id) {
      LOGGER.debug("Find a vacation request by user id: " + user_id);
      return vacationepomapper
         .toFronts((List<VacationRequest>)vacationRequestRepository.findVacationRequestByUser(user_id));
   }
   
   @Transactional
   @Override
   public VacationRequest createVacationRequest(VacationRequestEpo vacationRequest) {
      VacationRequest createdVacationRequest = vacationepomapper.toInternal(vacationRequest);
      LOGGER.debug("Creating vacation request: " + vacationRequest);
      return vacationRequestRepository.save(createdVacationRequest);
   }
   
   @Transactional (readOnly = true)
   @Override
   public VacationRequestEpoList findVacationRequestsUnresolvedForTeamCoord(long team_id) {
      LOGGER.debug("Find a vacation request by for all the members with status UNRESOLVED for team: " + team_id);
      return vacationepomapper.toFronts(
         (List<VacationRequest>)vacationRequestRepository.findVacationRequestsUnresolvedForTeamCoord(team_id));
   }
   
   @Transactional (readOnly = true)
   @Override
   public VacationRequestEpoList findVacationRequestsUnresolvedForUser(long user_id) {
      LOGGER.debug("Find vacation requests unresolved for user id: " + user_id);
      return vacationepomapper
         .toFronts((List<VacationRequest>)vacationRequestRepository.findVacationRequestUnresolvedForUser(user_id));
   }
   
   @Transactional (readOnly = true)
   @Override
   public VacationRequestEpoList findVacationRequestsAcceptedForUser(long user_id) {
      LOGGER.debug("Find vacation requests unresolved for user id: " + user_id);
      return vacationepomapper
         .toFronts((List<VacationRequest>)vacationRequestRepository.findVacationRequestAcceptedForUser(user_id));
   }
   
   @Transactional (readOnly = true)
   @Override
   public VacationRequestEpoList findVacationRequestsDeclinedForUser(long user_id) {
      LOGGER.debug("Find vacation requests declined for user id: " + user_id);
      return vacationepomapper
         .toFronts((List<VacationRequest>)vacationRequestRepository.findVacationRequestDeclinedForUser(user_id));
   }
   
   @Transactional (readOnly = true)
   @Override
   public VacationRequestEpoList findAllVacationRequestsUnresolved() {
      LOGGER.debug("Find all vacation requests unresolved ");
      return vacationepomapper
         .toFronts((List<VacationRequest>)vacationRequestRepository.findAllVacationRequestsUnresolved());
   }
   
   @Transactional
   @Override
   public long findNumberOfVacationRequestsUnresolved() {
      LOGGER.debug("Find number of vacation requests unresolved ");
      return vacationRequestRepository.findNumberOfVacationRequestsUnresolved();
   }
   
   @Transactional
   @Override
   public long findNumberOfVacationRequestsUnresolvedForTeamCoord(long team_id) {
      LOGGER.debug("Find number of vacation requests unresolved for team coordinator of the team: " + team_id);
      return vacationRequestRepository.findNumberOfVacationRequestsUnresolvedForTeamCoord(team_id);
   }
   
   @Transactional
   @Override
   public long findNumberOfVacationRequestsAcceptedForUserForCurrentYear(long user_id) {
      LOGGER.debug("Find number of vacation requests accepted for user for current year: " + user_id);
      List<VacationRequest> acceptedReq = vacationRequestRepository.findVacationRequestAcceptedForUser(user_id);
      long totalDays = 0;
      for (VacationRequest vr : acceptedReq) {
         Calendar start = Calendar.getInstance();
         start.setTime(vr.getStart_date());
         Calendar end = Calendar.getInstance();
         end.setTime(vr.getEnd_date());
         for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek != 7 && dayOfWeek != 1) {
               ++totalDays;
            }
         }
         Calendar c = Calendar.getInstance();
         c.setTime(vr.getEnd_date());
         int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
         if (dayOfWeek != 7 && dayOfWeek != 1) {
            ++totalDays;
         }
      }
      return totalDays;
   }
   
   @Transactional
   @Override
   public long findNumberOfDaysLeftAvailableToTake(Date start_date, Date end_date, long user_id) {
      long totalNrOfDaysAvailable = 25;
      long totalNrOfDaysTaken = this.findNumberOfVacationRequestsAcceptedForUserForCurrentYear(user_id);
      Calendar start = Calendar.getInstance();
      start.setTime(start_date);
      Calendar end = Calendar.getInstance();
      end.setTime(end_date);
      long days = 0;
      for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
         Calendar c = Calendar.getInstance();
         c.setTime(date);
         int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
         if (dayOfWeek != 7 && dayOfWeek != 1) {
            ++days;
         }
      }
      Calendar c = Calendar.getInstance();
      c.setTime(end_date);
      int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
      if (dayOfWeek != 7 && dayOfWeek != 1) {
         ++days;
      }
      totalNrOfDaysAvailable = totalNrOfDaysAvailable - (totalNrOfDaysTaken + days);
      return totalNrOfDaysAvailable;
   }

   @Transactional
   @Override
   public VacationRequestEpoList getAllVacationRequestsOfSpecificUserMonthYear(long userId, Month month, Year year) {
      VacationRequestEpoList v =new VacationRequestEpoList(vacationepomapper.toExternals(
              (List<VacationRequest>)vacationRequestRepository.findVacationRequestByUser_idaAndStart_dateAndEnd_date
                      (userId,month.getValue(),year.getValue())));
      LOGGER.info(v.toString());
      return v;
   }

   @Override
   public VacationRequestEpoList getAllVacationRequestsByUserAndYear(long userId, int year) {
      VacationRequestEpoList v =new VacationRequestEpoList(vacationepomapper.toExternals(
              (List<VacationRequest>)vacationRequestRepository.findVacationRequestByUserAndYear(userId,year)));
      LOGGER.info(v.toString());
      return v;
   }

   @Override
   public VacationRequestEpoList getAllVacationRequestsByTeamAndYear(long userId, int year) {
      VacationRequestEpoList v =new VacationRequestEpoList(vacationepomapper.toExternals(
              (List<VacationRequest>)vacationRequestRepository.findVacationRequestByTeamAndYear(userId,year)));
      LOGGER.info(v.toString());
      return v;
   }
}
