package com.mhp.usermicro.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mhp.usermicro.entity.Department;
import com.mhp.usermicro.entity.Language;
import com.mhp.usermicro.entity.Skill;
import com.mhp.usermicro.entity.Team;
import com.mhp.usermicro.entity.TeamLead;
import com.mhp.usermicro.entity.User;
import com.mhp.usermicro.entity.UserLanguage;
import com.mhp.usermicro.entity.UserSkill;
import com.mhp.usermicro.epo.DepartmentEpo;
import com.mhp.usermicro.epo.FrontLanguageEpo;
import com.mhp.usermicro.epo.FrontSkillEpo;
import com.mhp.usermicro.epo.PasswordChangeRequest;
import com.mhp.usermicro.epo.TeamEpo;
import com.mhp.usermicro.epo.TeamLeadEpo;
import com.mhp.usermicro.epo.UserEpo;
import com.mhp.usermicro.epo.UserLanguageEpo;
import com.mhp.usermicro.epo.UserSkillEpo;
import com.mhp.usermicro.epo.list.AuthorityEpoList;
import com.mhp.usermicro.epo.list.DepartmentEpoList;
import com.mhp.usermicro.epo.list.FrontLanguageEpoList;
import com.mhp.usermicro.epo.list.FrontSkillEpoList;
import com.mhp.usermicro.epo.list.LanguageLevelEpoList;
import com.mhp.usermicro.epo.list.PositionEpoList;
import com.mhp.usermicro.epo.list.SkillLevelEpoList;
import com.mhp.usermicro.epo.list.TeamEpoList;
import com.mhp.usermicro.mapper.UserEpoMapper;
import com.mhp.usermicro.service.IAdminService;
import com.mhp.usermicro.service.SearchService;

@RestController
@CrossOrigin (origins = "http://localhost:4200")
// @PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
   
   private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
   
   @Autowired
   private IAdminService                 adminService;
   @Autowired
   private UserEpoMapper                 userEpoMapper;
   @Autowired
   private SearchService                 searchService;
   
   @RequestMapping (value = "/getallusers", method = RequestMethod.GET)
   public List<UserEpo> getAllUsers() {
      
      return adminService.findAllUsers();
   }
   
   // @CrossOrigin (origins = "http://localhost:4200")
   // @RequestMapping (value = "/searchliveuser/{query}", method = RequestMethod.GET)
   // public List<UserEpo> getAllUsers(@PathVariable String query) {
   //
   // return searchService.searchUserByName(query);
   // }
   @RequestMapping (value = "/getskillsbyuser", method = RequestMethod.POST)
   public List<UserSkillEpo> findSkillsByUser(@RequestBody long parameter) {
      
      return adminService.findSkillsByUser(parameter);
   }
   
   @RequestMapping (value = "/getallteams", method = RequestMethod.GET)
   public TeamEpoList findAllTeams() {
      
      return adminService.findAllTeams();
   }
   
   @RequestMapping (value = "/getallauthorities", method = RequestMethod.GET)
   public AuthorityEpoList findAllAuthorities() {
      return adminService.findAllAuthorities();
   }
   
   @RequestMapping (value = "/getallskills", method = RequestMethod.GET)
   public FrontSkillEpoList findAllSkills() {
      
      return adminService.findSkills();
   }
   
   @RequestMapping (value = "/getallpositions", method = RequestMethod.GET)
   public PositionEpoList findAllPositions() {
      
      return adminService.findAllPositions();
   }
   
   @RequestMapping (value = "/addUser", method = RequestMethod.POST)
   public User addUser(@RequestBody UserEpo user) {
      User createdUser = new User();
      try {
         createdUser = adminService.createUser(user);
      } catch (Exception s) {
         LOGGER.error(
            "Can`t create user with username: [ " + user.getUserName() + " ] because this username is already in db");
      }
      finally {
         return createdUser;
      }
   }
   
   @RequestMapping (value = "/deleteuser", method = RequestMethod.POST)
   public User deleteUser(@RequestBody long user_id) {
      return adminService.deleteUser(user_id);
   }
   
   @RequestMapping (value = "/deletedepartment", method = RequestMethod.POST)
   public Department deleteDepartment(@RequestBody long department_id) {
      return adminService.deleteDepartment(department_id);
   }
   
   @RequestMapping (value = "/adddepartment", method = RequestMethod.POST)
   public Department addDepartment(@RequestBody DepartmentEpo dept) {
      return adminService.addDepartment(dept);
   }
   
   @RequestMapping (value = "/updateUser", method = RequestMethod.POST)
   public User updateUser(@RequestBody UserEpo user) {
      
      return adminService.updateUser(user);
   }
   
   @RequestMapping (value = "/getalldepartments", method = RequestMethod.GET)
   public DepartmentEpoList getAllDepartments() {
      
      return adminService.getAllDepartments();
   }
   
   @RequestMapping (value = "/addTeam", method = RequestMethod.POST)
   public Team addTeam(@RequestBody TeamEpo team) {
      
      return adminService.createTeam(team);
   }
   
   @RequestMapping (value = "/deleteTeam", method = RequestMethod.POST)
   public void deleteTeam(@RequestBody TeamEpo team) {
      
      adminService.deleteTeam(team);
   }
   
   @RequestMapping (value = "/addlanguage", method = RequestMethod.POST)
   public Language addLanguage(@RequestBody FrontLanguageEpo lang) {
      
      return adminService.addLanguage(lang);
   }
   
   @RequestMapping (value = "/deletelanguage", method = RequestMethod.POST)
   public Language removeLanguage(@RequestBody FrontLanguageEpo lang) {
      
      return adminService.removeLanguage(lang);
   }
   
   @RequestMapping (value = "/getalllanguages", method = RequestMethod.GET)
   public FrontLanguageEpoList getAllLanguages() {
      
      return adminService.findLanguages();
   }
   
   @RequestMapping (value = "/addskill", method = RequestMethod.POST)
   public Skill addSkill(@RequestBody FrontSkillEpo skill) {
      
      return adminService.addSkill(skill);
   }
   
   @RequestMapping (value = "/deleteskill", method = RequestMethod.POST)
   public Skill removeSkill(@RequestBody FrontSkillEpo skill) {
      
      return adminService.removeSkill(skill);
   }
   
   @RequestMapping (value = "/getskillsremaining", method = RequestMethod.POST)
   public FrontSkillEpoList getRemainingSkills(@RequestBody long id) {
      
      return adminService.findSkillsNotFromUser(id);
   }
   
   @RequestMapping (value = "/adduserskill", method = RequestMethod.POST)
   public UserSkill addUserSkill(@RequestBody UserSkillEpo skill) {
      
      return adminService.addUserSkill(skill);
   }
   
   @RequestMapping (value = "/deleteuserskill", method = RequestMethod.POST)
   public UserSkill removeUserSkill(@RequestBody UserSkillEpo skill) {
      
      return adminService.removeUserSkill(skill);
   }
   
   @RequestMapping (value = "/getlanguagesremaining", method = RequestMethod.POST)
   public FrontLanguageEpoList getRemainingLanguages(@RequestBody long id) {
      
      return adminService.getAvailableLanguages(id);
   }
   
   @RequestMapping (value = "/getlanguagesbyuser", method = RequestMethod.POST)
   public List<UserLanguageEpo> findLanguagesByUser(@RequestBody long id) {
      
      return adminService.getLanguagesByUser(id);
   }
   
   @RequestMapping (value = "/adduserlanguage", method = RequestMethod.POST)
   public UserLanguage addUserLanguage(@RequestBody UserLanguageEpo language) {
      
      return adminService.addUserLanguage(language);
   }
   
   @RequestMapping (value = "/deleteuserlanguage", method = RequestMethod.POST)
   public UserLanguage removeUserLanguage(@RequestBody UserLanguageEpo language) {
      
      return adminService.removeUserLanguage(language);
   }
   
   @RequestMapping (value = "/getteamsremaining", method = RequestMethod.POST)
   public List<TeamEpo> getRemainingTeams(@RequestBody long id) {
      
      return adminService.availableTeamsByUser(id);
   }
   
   @RequestMapping (value = "/getteamsbyuser", method = RequestMethod.POST)
   public List<TeamLeadEpo> findTeamsByUser(@RequestBody long id) {
      
      return adminService.teamLeadByUser(id);
   }
   
   @RequestMapping (value = "/addteamlead", method = RequestMethod.POST)
   public TeamLead addTeamLead(@RequestBody TeamLeadEpo teamLead) {
      
      return adminService.assignTeamLead(teamLead);
   }
   
   @RequestMapping (value = "/deleteteamlead", method = RequestMethod.POST)
   public TeamLead removeTeamLead(@RequestBody TeamLeadEpo teamLead) {
      
      return adminService.removeTeamLead(teamLead);
   }
   
   @RequestMapping (value = "/getusersbyteam", method = RequestMethod.POST)
   public List<UserEpo> getUsersByTeam(@RequestBody TeamEpo team) {
      
      return adminService.getUsersByTeam(team);
   }
   
   @RequestMapping (value = "/getusersbyname", method = RequestMethod.POST)
   public List<UserEpo> getUsersByName(@RequestBody String name) {
      
      return adminService.getUsersByName(name);
   }
   
   @RequestMapping (value = "/resetpassword", method = RequestMethod.POST)
   public Boolean resetPassword(@RequestBody long id) {
      
      return adminService.resetPassword(id);
   }
   
   @RequestMapping (value = "/changepassword", method = RequestMethod.POST)
   public long changePassword(@RequestBody PasswordChangeRequest request) {
      
      return adminService.changePassword(request);
   }
   /*
    * @RequestMapping (value = "/getuserbyid/{id}", method = RequestMethod.POST) public UserEpo
    * getUserById(@PathVariable long id) { return userEpoMapper.toExternal(adminService.findUserById(id)); }
    */
   
   @RequestMapping (value = "/getallskilllevels", method = RequestMethod.GET)
   public SkillLevelEpoList getAllSkillLevels() {
      
      return adminService.findAllSkillLevels();
   }
   
   @RequestMapping (value = "/getalllanguagelevels", method = RequestMethod.GET)
   public LanguageLevelEpoList getAllLanguageLevels() {
      
      return adminService.findAllLanguageLevels();
   }
   
   @RequestMapping (value = "/getidbyusername", method = RequestMethod.GET)
   public Long getIdByUsername(@RequestBody String username) {
      Long id = adminService.getIdByUsername(username);
      LOGGER.info(username + "got id: " + id);
      return id;
   }
   
   @RequestMapping (value = "/addusertoteam", method = RequestMethod.POST)
   public User addUserToTeam(@RequestBody List<Integer> list) {
      
      return adminService.addUserToTeam(list.get(0), list.get(1));
   }
   
   @RequestMapping (value = "/removemember", method = RequestMethod.POST)
   public User removeMemberFromTeam(@RequestBody long id) {
      
      return adminService.deleteMemberFromTeam(id);
   }
   
   @RequestMapping (value = "/getremainingusers", method = RequestMethod.POST)
   public List<UserEpo> getRemainingUsers(@RequestBody long id) {
      
      return adminService.getUsersRemaining(id);
   }
   
   @RequestMapping (value = "/getusersbynamefromteam", method = RequestMethod.POST)
   public List<UserEpo> getUsersByNameFromTeam(@RequestBody TeamEpo epo) {
      
      return adminService.getUsersByNameFromTeam(epo);
   }
   
   @RequestMapping (value = "/getusersbynamenotfromteam", method = RequestMethod.POST)
   public List<UserEpo> getUsersByNameNotFromTeam(@RequestBody TeamEpo epo) {
      
      return adminService.getUsersByNameNotFromTeam(epo);
   }
   
   @RequestMapping (value = "/getnrofusersfromateam", method = RequestMethod.POST)
   public long getNrOfUsersFromATeam(@RequestBody long team_id) {
      
      return adminService.findNrOfUsersFromATeam(team_id);
   }
   
   @RequestMapping (value = "/getnameofusersfromateam", method = RequestMethod.POST)
   public List<String> getNameOfUsersFromATeam(@RequestBody long team_id) {
      
      return adminService.findUserNamesFromATeam(team_id);
   }
   
   @RequestMapping (value = "/getnrofteamsfromateamlead", method = RequestMethod.POST)
   public long getNrOfTeamsFromATeamLead(@RequestBody long user_id) {
      
      return adminService.findNrOfTeamsOfATeamLead(user_id);
   }
   
   @RequestMapping (value = "/getteambyid", method = RequestMethod.POST)
   public TeamEpo getTeamByTeamId(@RequestBody long team_id) {
      
      return adminService.getTeamByTeamID(team_id);
   }
   
   @RequestMapping (value = "/getteamswithoutcoord", method = RequestMethod.GET)
   public List<TeamEpo> getTeamsWithoutCoord() {
      return adminService.getTeamsWithoutCoord();
   }
   
   @RequestMapping (value = "/checkadmin", method = RequestMethod.GET)
   public boolean adminCheck() {
      return adminService.adminLogin();
   }
   
}
