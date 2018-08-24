package com.mhp.requestmicro.controller;

import java.time.Month;
import java.time.Year;

import com.mhp.requestmicro.entity.table.TableEntryRequest;
import com.mhp.requestmicro.entity.table.TableEntry;
import com.mhp.requestmicro.service.IExportService;
import com.mhp.requestmicro.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mhp.requestmicro.entity.HomeOfficeRequest;
import com.mhp.requestmicro.entity.VacationRequest;
import com.mhp.requestmicro.epo.HomeOfficeDaysLeftEpo;
import com.mhp.requestmicro.epo.HomeOfficeRequestEpo;
import com.mhp.requestmicro.epo.VacationDaysLeftEpo;
import com.mhp.requestmicro.epo.VacationRequestEpo;
import com.mhp.requestmicro.epo.list.HalfDayEpoList;
import com.mhp.requestmicro.epo.list.HomeOfficeRequestEpoList;
import com.mhp.requestmicro.epo.list.StatusEpoList;
import com.mhp.requestmicro.epo.list.VacationRequestEpoList;
import com.mhp.requestmicro.service.IHomeOfficeRequestService;
import com.mhp.requestmicro.service.IVacationRequestService;

@RestController
@CrossOrigin (origins = "http://localhost:4200")
public class RequestController {
   
   @Autowired
   private IHomeOfficeRequestService homeOfficeRequestService;
   @Autowired
   private IVacationRequestService   vacationRequestService;
   @Autowired
   private ITableService tableService;
   @Autowired
   private IExportService exportService;
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/getallhomerequests", method = RequestMethod.GET)
   public HomeOfficeRequestEpoList getAllHomeOfficeRequests() {
      
      return homeOfficeRequestService.getAllHomeOfficeRequests();
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/getallvacationrequests", method = RequestMethod.GET)
   public VacationRequestEpoList getAllVacationRequests() {
      
      return vacationRequestService.getAllVacationRequests();
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/getallstatustypes", method = RequestMethod.GET)
   public StatusEpoList getAllStatusTypes() {
      
      return homeOfficeRequestService.getAllStatusType();
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/deletehomeofficerequest", method = RequestMethod.POST)
   public HomeOfficeRequest deleteHomeOfficeRequest(@RequestBody long homeoffice_request_id) {
      return homeOfficeRequestService.deleteHomeOfficeRequest(homeoffice_request_id);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/deletevacationrequest", method = RequestMethod.POST)
   public VacationRequest deleteVacationRequest(@RequestBody long vacation_request_id) {
      return vacationRequestService.deleteVacationRequest(vacation_request_id);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/updatehomeofficerequest", method = RequestMethod.POST)
   public HomeOfficeRequest updateHomeOfficeRequestStatus(@RequestBody HomeOfficeRequestEpo homeOfficeRequest) {
      
      return homeOfficeRequestService.updateHomeOfficeRequestStatus(homeOfficeRequest);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/updatevacationrequest", method = RequestMethod.POST)
   public VacationRequest updateVacationRequestStatus(@RequestBody VacationRequestEpo vacationRequest) {
      
      return vacationRequestService.updateVacationRequestStatus(vacationRequest);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/gethomeofficerequestbyid", method = RequestMethod.POST)
   public HomeOfficeRequestEpo getHomeOfficeRequestById(@RequestBody Long id) {
      
      return homeOfficeRequestService.findHomeOfficeRequestById(id);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/getvacationrequestbyid", method = RequestMethod.POST)
   public VacationRequestEpo getVacationRequestById(@RequestBody Long id) {
      
      return vacationRequestService.findVacationRequestById(id);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/gethomeofficerequestsbyuser", method = RequestMethod.POST)
   public HomeOfficeRequestEpoList getHomeOfficeRequestsByUser(@RequestBody Long user_id) {
      
      return homeOfficeRequestService.findHomeOfficeRequestsByUser(user_id);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/getvacationrequestsbyuser", method = RequestMethod.POST)
   public VacationRequestEpoList getVacationRequestsByUser(@RequestBody Long user_id) {
      
      return vacationRequestService.findVacationRequestsByUser(user_id);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/addHomeOfficeRequest", method = RequestMethod.POST)
   public HomeOfficeRequest addHomeOfficeRequest(@RequestBody HomeOfficeRequestEpo homeOfficeRequest) {
      
      return homeOfficeRequestService.createHomeOfficeRequest(homeOfficeRequest);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/addVacationRequest", method = RequestMethod.POST)
   public VacationRequest addVacationRequest(@RequestBody VacationRequestEpo vacationRequest) {
      
      return vacationRequestService.createVacationRequest(vacationRequest);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/gethomeofficerequestsunresolvedforteam", method = RequestMethod.POST)
   public HomeOfficeRequestEpoList findHomeOfficeRequestsUnresolvedForTeamCoord(@RequestBody Long team_id) {
      
      return homeOfficeRequestService.findHomeOfficeRequestsUnresolvedForTeamCoord(team_id);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/getvacationrequestsunresolvedforteam", method = RequestMethod.POST)
   public VacationRequestEpoList findVacationRequestsUnresolvedForTeamCoord(@RequestBody Long team_id) {
      
      return vacationRequestService.findVacationRequestsUnresolvedForTeamCoord(team_id);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/getallhalfdaytypes", method = RequestMethod.GET)
   public HalfDayEpoList getAllHalfDayTypes() {
      
      return homeOfficeRequestService.getAllHalfDayType();
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/getvacationrequestsunresolvedforuser", method = RequestMethod.POST)
   public VacationRequestEpoList findVacationRequestsUnresolvedForUser(@RequestBody Long user_id) {
      
      return vacationRequestService.findVacationRequestsUnresolvedForUser(user_id);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/getvacationrequestsacceptedforuser", method = RequestMethod.POST)
   public VacationRequestEpoList findVacationRequestsAcceptedForUser(@RequestBody Long user_id) {
      
      return vacationRequestService.findVacationRequestsAcceptedForUser(user_id);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/getvacationrequestsdeclinedforuser", method = RequestMethod.POST)
   public VacationRequestEpoList findVacationRequestsDeclinedForUser(@RequestBody Long user_id) {
      
      return vacationRequestService.findVacationRequestsDeclinedForUser(user_id);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/gethomeofficerequestsunresolvedforuser", method = RequestMethod.POST)
   public HomeOfficeRequestEpoList findHomeOfficeRequestsUnresolvedForUser(@RequestBody Long user_id) {
      
      return homeOfficeRequestService.findHomeOfficeRequestsUnresolvedForUser(user_id);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/gethomeofficerequestsacceptedforuser", method = RequestMethod.POST)
   public HomeOfficeRequestEpoList findHomeOfficeRequestsAcceptedForUser(@RequestBody Long user_id) {
      
      return homeOfficeRequestService.findHomeOfficeRequestsAcceptedForUser(user_id);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/gethomeofficerequestsdeclinedforuser", method = RequestMethod.POST)
   public HomeOfficeRequestEpoList findHomeOfficeRequestsDeclinedForUser(@RequestBody Long user_id) {
      
      return homeOfficeRequestService.findHomeOfficeRequestsDeclinedForUser(user_id);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/getallhomeofficerequestsunresolved", method = RequestMethod.GET)
   public HomeOfficeRequestEpoList findAllHomeOfficeRequestsUnresolved() {
      
      return homeOfficeRequestService.findAllHomeOfficeRequestsUnresolved();
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/getallvacationrequestsunresolved", method = RequestMethod.GET)
   public VacationRequestEpoList findAllVacationRequestsUnresolved() {
      
      return vacationRequestService.findAllVacationRequestsUnresolved();
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/getnrofhomeofficerequestsunresolved", method = RequestMethod.GET)
   public long findNrOfHomeOfficeRequestsUnresolved() {
      
      return homeOfficeRequestService.findNumberOfHomeOfficeRequestsUnresolved();
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/getnrofvacationrequestsunresolved", method = RequestMethod.GET)
   public long findNrOfVacationRequestsUnresolved() {
      
      return vacationRequestService.findNumberOfVacationRequestsUnresolved();
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/getnrofvacationrequestsunresolvedforteam", method = RequestMethod.POST)
   public long findNrOfVacationRequestsUnresolvedForTeamCoord(@RequestBody Long team_id) {
      
      return vacationRequestService.findNumberOfVacationRequestsUnresolvedForTeamCoord(team_id);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/getnrofhomeofficerequestsunresolvedforteam", method = RequestMethod.POST)
   public long findNrOfHomeOfficeRequestsUnresolvedForTeamCoord(@RequestBody Long team_id) {
      
      return homeOfficeRequestService.findNumberOfHomeOfficeRequestsUnresolvedForTeamCoord(team_id);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/getnrofvacationrequestsacceptedforuserperyear", method = RequestMethod.POST)
   public long findNrOfVacationRequestsAcceptedForUserPerYear(@RequestBody Long user_id) {
      
      return vacationRequestService.findNumberOfVacationRequestsAcceptedForUserForCurrentYear(user_id);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/getnrofhomeofficerequestsacceptedforuserperyear", method = RequestMethod.POST)
   public Float findNrOfHomeOfficeRequestsAcceptedForUserPerYear(@RequestBody Long user_id) {
      
      return homeOfficeRequestService.findNumberOfHomeOfficeRequestsAcceptedForUserForCurrentYear(user_id);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/getnrofvacationdaysavailabletotake", method = RequestMethod.POST)
   public long findNrOfAvailbleVacationDaysToTake(@RequestBody VacationDaysLeftEpo vacationDayLeftEpo) {
      
      return vacationRequestService.findNumberOfDaysLeftAvailableToTake(vacationDayLeftEpo.getStart_date(), vacationDayLeftEpo.getEnd_date(), vacationDayLeftEpo.getUser_id());
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/getnohomeofficedaysavailabletotake", method = RequestMethod.POST)
   public Float findNrOfAvailbleHomeOfficeDaysToTake(@RequestBody HomeOfficeDaysLeftEpo homeOfficeDaysLeftEpo) {
      
      return homeOfficeRequestService.findNumberOfDaysLeftAvailableToTake(homeOfficeDaysLeftEpo.getStart_date(), homeOfficeDaysLeftEpo.getEnd_date(), homeOfficeDaysLeftEpo.getHalf_day_id(), homeOfficeDaysLeftEpo.getUser_id());
   }

   @RequestMapping(value = "/gettableforuser/{userId}/{month}/{year}", method = RequestMethod.POST)
   public TableEntry getTableEntryForUserPath(@PathVariable int userId, @PathVariable int month, @PathVariable int year){
      Month m= Month.of(month);
      Year y = Year.of(year);
      return tableService.createTableEntryForUser(userId,m,y);
   }

   @RequestMapping(value = "/gettableforuser", method = RequestMethod.POST)
   public TableEntry getTableEntryForUser(@RequestBody TableEntryRequest tableEntryRequest){
      Month m= Month.of(tableEntryRequest.getMonth());
      Year y = Year.of(tableEntryRequest.getYear());
      return tableService.createTableEntryForUser(tableEntryRequest.getId(),m,y);
   }

   @RequestMapping(value = "/createexcel/{id}", method = RequestMethod.POST)
   public void createExcel(@PathVariable long id){
      exportService.generateExcel(id,"TeamOverview.xls");
   }
}
