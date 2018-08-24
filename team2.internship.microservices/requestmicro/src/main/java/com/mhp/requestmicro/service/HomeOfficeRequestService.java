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

import com.mhp.requestmicro.entity.HalfDay;
import com.mhp.requestmicro.entity.HomeOfficeRequest;
import com.mhp.requestmicro.entity.Status;
import com.mhp.requestmicro.epo.HomeOfficeRequestEpo;
import com.mhp.requestmicro.epo.list.HalfDayEpoList;
import com.mhp.requestmicro.epo.list.HomeOfficeRequestEpoList;
import com.mhp.requestmicro.epo.list.StatusEpoList;
import com.mhp.requestmicro.mapper.HalfDayEpoMapper;
import com.mhp.requestmicro.mapper.HomeOfficeRequestEpoMapper;
import com.mhp.requestmicro.mapper.StatusEpoMapper;
import com.mhp.requestmicro.repository.HalfDayRepository;
import com.mhp.requestmicro.repository.HomeOfficeRequestRepository;
import com.mhp.requestmicro.repository.StatusRepository;

@Service
public class HomeOfficeRequestService implements IHomeOfficeRequestService {
   
   private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(HomeOfficeRequestService.class);
   
   @Autowired
   private HomeOfficeRequestEpoMapper    homeepomapper;
   @Autowired
   private HomeOfficeRequestRepository   homeOfficeRequestRepository;
   @Autowired
   private StatusRepository              statusRepository;
   @Autowired
   private HalfDayRepository             halfDayRepository;
   @Autowired
   private StatusEpoMapper               statusepomapper;
   @Autowired
   private HalfDayEpoMapper              halfdayepomapper;
   
   @Transactional (readOnly = true)
   @Override
   public HomeOfficeRequestEpoList getAllHomeOfficeRequests() {
      LOGGER.debug("Getting all home office requests from repository");
      return new HomeOfficeRequestEpoList(
         homeepomapper.toExternals((List<HomeOfficeRequest>)homeOfficeRequestRepository.findAll()));
   }
   
   @Transactional (readOnly = true)
   @Override
   public StatusEpoList getAllStatusType() {
      LOGGER.debug("Getting all status types from repository");
      return new StatusEpoList(statusepomapper.toExternals((List<Status>)statusRepository.findAll()));
   }
   
   @Transactional
   @Override
   public HomeOfficeRequest deleteHomeOfficeRequest(long id) {
      LOGGER.debug("Deleting home office request under id: " + id);
      HomeOfficeRequest deletedReq = homeOfficeRequestRepository.findOne(id);
      LOGGER.debug("With this info: " + deletedReq);
      homeOfficeRequestRepository.delete(deletedReq);
      return deletedReq;
   }
   
   @Transactional
   @Override
   public HomeOfficeRequest updateHomeOfficeRequestStatus(HomeOfficeRequestEpo homeOfficeRequest) {
      HomeOfficeRequest updatedHomeOfficeRequest = homeOfficeRequestRepository.findOne(homeOfficeRequest.getId());
      LOGGER.debug("Updating home office request: " + updatedHomeOfficeRequest);
      updatedHomeOfficeRequest.setStatus_id(statusepomapper.toInternal(homeOfficeRequest.getStatus_id()));
      LOGGER.debug("To home office request: " + updatedHomeOfficeRequest);
      return updatedHomeOfficeRequest;
   }
   
   @Transactional (readOnly = true)
   @Override
   public HomeOfficeRequestEpo findHomeOfficeRequestById(long id) {
      LOGGER.debug("Find a home office request by id: " + id);
      return homeepomapper.toExternal(homeOfficeRequestRepository.findOne(id));
   }
   
   @Transactional (readOnly = true)
   @Override
   public HomeOfficeRequestEpoList findHomeOfficeRequestsByUser(long user_id) {
      LOGGER.debug("Find home offices request by user id: " + user_id);
      return homeepomapper
         .toFronts((List<HomeOfficeRequest>)homeOfficeRequestRepository.findHomeOfficeRequestByUser(user_id));
   }
   
   @Transactional
   @Override
   public HomeOfficeRequest createHomeOfficeRequest(HomeOfficeRequestEpo homeOfficeRequest) {
      HomeOfficeRequest createdHomeOfficeRequest = homeepomapper.toInternal(homeOfficeRequest);
      LOGGER.debug("Creating home office request: " + homeOfficeRequest);
      return homeOfficeRequestRepository.save(createdHomeOfficeRequest);
   }
   
   @Transactional (readOnly = true)
   @Override
   public HomeOfficeRequestEpoList findHomeOfficeRequestsUnresolvedForTeamCoord(long team_id) {
      LOGGER.debug("Find a home office request by for all the members with status UNRESOLVED for team: " + team_id);
      return homeepomapper.toFronts(
         (List<HomeOfficeRequest>)homeOfficeRequestRepository.findHomeOfficeRequestsUnresolvedForTeamCoord(team_id));
   }
   
   @Transactional (readOnly = true)
   @Override
   public HalfDayEpoList getAllHalfDayType() {
      LOGGER.debug("Getting all half day types from repository");
      return new HalfDayEpoList(halfdayepomapper.toExternals((List<HalfDay>)halfDayRepository.findAll()));
   }
   
   @Transactional (readOnly = true)
   @Override
   public HomeOfficeRequestEpoList findHomeOfficeRequestsUnresolvedForUser(long user_id) {
      LOGGER.debug("Find home office requests unresolved for user id: " + user_id);
      return homeepomapper.toFronts(
         (List<HomeOfficeRequest>)homeOfficeRequestRepository.findHomeOfficeRequestUnresolvedForUser(user_id));
   }
   
   @Transactional (readOnly = true)
   @Override
   public HomeOfficeRequestEpoList findHomeOfficeRequestsAcceptedForUser(long user_id) {
      LOGGER.debug("Find home office requests accepted for user id: " + user_id);
      return homeepomapper
         .toFronts((List<HomeOfficeRequest>)homeOfficeRequestRepository.findHomeOfficeRequestAcceptedForUser(user_id));
   }
   
   @Transactional (readOnly = true)
   @Override
   public HomeOfficeRequestEpoList findHomeOfficeRequestsDeclinedForUser(long user_id) {
      LOGGER.debug("Find home office requests declined for user id: " + user_id);
      return homeepomapper
         .toFronts((List<HomeOfficeRequest>)homeOfficeRequestRepository.findHomeOfficeRequestDeclinedForUser(user_id));
   }
   
   @Transactional (readOnly = true)
   @Override
   public HomeOfficeRequestEpoList findAllHomeOfficeRequestsUnresolved() {
      LOGGER.debug("Find all home office requests unresolved");
      return homeepomapper
         .toFronts((List<HomeOfficeRequest>)homeOfficeRequestRepository.findAllHomeOfficeRequestsUnresolved());
   }
   
   @Transactional
   @Override
   public long findNumberOfHomeOfficeRequestsUnresolved() {
      LOGGER.debug("Find number of home office requests unresolved");
      return homeOfficeRequestRepository.findNumberOfHomeOfficeRequestsUnresolved();
   }
   
   @Transactional
   @Override
   public long findNumberOfHomeOfficeRequestsUnresolvedForTeamCoord(long team_id) {
      LOGGER.debug("Find number of home office requests unresolved for team coor of the team: " + team_id);
      return homeOfficeRequestRepository.findNumberOfHomeOfficeRequestsUnresolvedForTeamCoord(team_id);
   }
   
   @Transactional
   @Override
   public Float findNumberOfHomeOfficeRequestsAcceptedForUserForCurrentYear(long user_id) {
      LOGGER.debug("Find number of home office accepted for user for current year: " + user_id);
      List<HomeOfficeRequest> acceptedReq = homeOfficeRequestRepository.findHomeOfficeRequestAcceptedForUser(user_id);
      Float totalDays = 0.0f;
      for (HomeOfficeRequest hr : acceptedReq) {
         Date startDate = hr.getStart_date();
         Date endDate = hr.getEnd_date();
         if (startDate.equals(endDate)) {
            if (hr.getHalf_day().getId() == 1 || hr.getHalf_day().getId() == 2) {
               totalDays = totalDays + 0.5f;
            } else {
               ++totalDays;
            }
         } else {
            Calendar start = Calendar.getInstance();
            start.setTime(hr.getStart_date());
            Calendar end = Calendar.getInstance();
            end.setTime(hr.getEnd_date());
            for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
               Calendar c = Calendar.getInstance();
               c.setTime(date);
               int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
               if (dayOfWeek != 7 && dayOfWeek != 1) {
                  ++totalDays;
               }
            }
            Calendar c = Calendar.getInstance();
            c.setTime(hr.getEnd_date());
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek != 7 && dayOfWeek != 1) {
               ++totalDays;
            }
         }
      }
      return totalDays;
   }
   
   @Transactional
   @Override
   public Float findNumberOfDaysLeftAvailableToTake(Date start_date, Date end_date, long half_day_id, long user_id) {
      Calendar c = Calendar.getInstance();
      Integer month = c.get(Calendar.MONTH);
      Integer intAvailableDays = 2 * month;
      Float availableDays = intAvailableDays.floatValue();
      Float takenDays = this.findNumberOfHomeOfficeRequestsAcceptedForUserForCurrentYear(user_id);
      availableDays = availableDays - takenDays;
      Float days = 0.0f;
      if (start_date.equals(end_date)) {
         if (half_day_id == 1 || half_day_id == 2) {
            days += 0.5f;
         } else {
            ++days;
         }
      } else {
         Calendar start = Calendar.getInstance();
         start.setTime(start_date);
         Calendar end = Calendar.getInstance();
         end.setTime(end_date);
         for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            Calendar cale = Calendar.getInstance();
            cale.setTime(date);
            int dayOfWeek = cale.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek != 7 && dayOfWeek != 1) {
               ++days;
            }
         }
         Calendar cale = Calendar.getInstance();
         cale.setTime(end_date);
         int dayOfWeek = cale.get(Calendar.DAY_OF_WEEK);
         if (dayOfWeek != 7 && dayOfWeek != 1) {
            ++days;
         }
      }
      availableDays = availableDays - days;
      return availableDays;
   }

   @Override
   @Transactional
   public HomeOfficeRequestEpoList getAllHomeOfficeRequestsOfSpecificUserMonthYear(long userId, Month month, Year year) {
      HomeOfficeRequestEpoList h = new HomeOfficeRequestEpoList(
              homeepomapper.toExternals((List<HomeOfficeRequest>)homeOfficeRequestRepository.
                      findHomeOfficeRequestByUser_idaAndStart_dateAndEnd_date(userId,month.getValue(),year.getValue())));
      LOGGER.info(h.toString());
      return h;
   }

   @Override
   public HomeOfficeRequestEpoList getAllHomeOfficeRequestsByUserAndYear(long userId, int year) {
      HomeOfficeRequestEpoList h = new HomeOfficeRequestEpoList(
              homeepomapper.toExternals((List<HomeOfficeRequest>)homeOfficeRequestRepository.findHomeOfficeRequestByUserAndYear(userId,year)));
      LOGGER.info(h.toString());
      return h;
   }

   @Override
   public HomeOfficeRequestEpoList getAllHomeOfficeRequestsByTeamAndYear(long userId, int year) {
      HomeOfficeRequestEpoList h = new HomeOfficeRequestEpoList(
              homeepomapper.toExternals((List<HomeOfficeRequest>)homeOfficeRequestRepository.findHomeOfficeRequestByTeamAndYear(userId,year)));
      LOGGER.info(h.toString());
      return h;
   }
}
