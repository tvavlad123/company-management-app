package com.mhp.usermicro.service;

import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mhp.usermicro.entity.Authority;
import com.mhp.usermicro.entity.Department;
import com.mhp.usermicro.entity.Language;
import com.mhp.usermicro.entity.LanguageLevel;
import com.mhp.usermicro.entity.Position;
import com.mhp.usermicro.entity.Skill;
import com.mhp.usermicro.entity.SkillLevel;
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
import com.mhp.usermicro.mapper.AuthorityEpoMapper;
import com.mhp.usermicro.mapper.DepartmentEpoMapper;
import com.mhp.usermicro.mapper.LanguageEpoMapper;
import com.mhp.usermicro.mapper.LanguageLevelMapper;
import com.mhp.usermicro.mapper.PositionEpoMapper;
import com.mhp.usermicro.mapper.SkillEpoMapper;
import com.mhp.usermicro.mapper.SkillLevelMapper;
import com.mhp.usermicro.mapper.TeamEpoMapper;
import com.mhp.usermicro.mapper.TeamLeadEpoMapper;
import com.mhp.usermicro.mapper.UserEpoMapper;
import com.mhp.usermicro.mapper.UserLanguageEpoMapper;
import com.mhp.usermicro.mapper.UserSkillEpoMapper;
import com.mhp.usermicro.repository.AuthorityRepository;
import com.mhp.usermicro.repository.DepartmentRepository;
import com.mhp.usermicro.repository.LanguageLevelRepository;
import com.mhp.usermicro.repository.LanguageRepository;
import com.mhp.usermicro.repository.PositionRepository;
import com.mhp.usermicro.repository.SkillLevelRepository;
import com.mhp.usermicro.repository.SkillRepository;
import com.mhp.usermicro.repository.TeamLeadRepository;
import com.mhp.usermicro.repository.TeamRepository;
import com.mhp.usermicro.repository.UserLanguageRepository;
import com.mhp.usermicro.repository.UserRepository;
import com.mhp.usermicro.repository.UserSkillRepository;

@Service
public class AdminServiceImpl implements IAdminService {
   private static final org.slf4j.Logger LOGGER   = LoggerFactory.getLogger(AdminServiceImpl.class);
   private static final String           PASSWORD = "init";
   @Autowired
   private PasswordEncoder               passwordEncoder;
   @Autowired
   private UserRepository                userRepository;
   @Autowired
   private UserLanguageRepository        userLangRepository;
   @Autowired
   private LanguageRepository            languageRepository;
   @Autowired
   private UserSkillRepository           userSkillRepository;
   @Autowired
   private TeamRepository                teamRepository;
   @Autowired
   private AuthorityRepository           authorityRepository;
   @Autowired
   private PositionRepository            positionRepository;
   @Autowired
   private TeamLeadRepository            teamLeadRepository;
   @Autowired
   private DepartmentRepository          departmentRepository;
   @Autowired
   private SkillRepository               skillRepository;
   @Autowired
   private UserEpoMapper                 uem;
   @Autowired
   private UserLanguageEpoMapper         ulem;
   @Autowired
   private UserSkillEpoMapper            usem;
   @Autowired
   private AuthorityEpoMapper            rem;
   @Autowired
   private TeamEpoMapper                 tem;
   @Autowired
   private AuthorityEpoMapper            aem;
   @Autowired
   private PositionEpoMapper             pem;
   @Autowired
   private DepartmentEpoMapper           dem;
   @Autowired
   private SkillEpoMapper                sem;
   @Autowired
   private LanguageEpoMapper             lem;
   @Autowired
   private TeamLeadEpoMapper             tlem;
   @Autowired
   private SkillLevelRepository          skillLevelRepository;
   @Autowired
   private SkillLevelMapper              sklm;
   @Autowired
   private LanguageLevelRepository       languageLevelRepository;
   @Autowired
   private LanguageLevelMapper           lglm;
   
   // done
   @Transactional
   public User createUser(UserEpo user) throws PSQLException {
      User createdUser = uem.toInternal(user);
      LOGGER.debug("Creating user: " + user);
      createdUser.setEnabled(true);
      createdUser.setPassword(passwordEncoder.encode(PASSWORD));
      return userRepository.save(createdUser);
   }
   
   // done
   @Transactional (readOnly = true)
   public List<UserEpo> findAllUsers() {
      LOGGER.debug("Getting all users from repository");
      List<User> users = (List<User>)userRepository.findAll();
      List<UserEpo> finalUsers = new ArrayList<UserEpo>();
      for (int i = 0; i < users.size(); i++) {
         List<UserSkill> skills = (List<UserSkill>)userSkillRepository.findSkillsByUser(users.get(i).getId());
         List<UserLanguage> languages = (List<UserLanguage>)userLangRepository.findLanguageByUser(users.get(i).getId());
         finalUsers.add(uem.toExternal(users.get(i), skills, languages));
      }
      return finalUsers;
   }
   
   // done
   @Transactional (readOnly = true)
   public List<UserSkillEpo> findSkillsByUser(long user_id) {
      List<UserSkillEpo> epos = new ArrayList<UserSkillEpo>();
      List<UserSkill> skills = userSkillRepository.findSkillsByUser(user_id);
      for (int i = 0; i < skills.size(); i++) {
         epos.add(usem.toExternal(skills.get(i)));
      }
      return epos;
   }
   
   // done
   @Transactional
   public User updateUser(UserEpo user) {
      User updatedUser = userRepository.findOne(user.getId());
      LOGGER.debug("Updating user: " + updatedUser);
      updatedUser.setFirstName(user.getFirstName());
      updatedUser.setLastName(user.getLastName());
      updatedUser.setUsername(user.getUserName());
      updatedUser.setPassword(user.getPassword());
      updatedUser.setAuthority(rem.toInternal(user.getAuthority()));
      System.out.println(updatedUser.getPhotoLocation());
      System.out.println(user.getPhotoLocation());
      updatedUser.setPhotoLocation(user.getPhotoLocation());
      updatedUser.setPosition(pem.toInternal(user.getPosition()));
      System.out.println(updatedUser.getPosition() + "<---------- NEW POSITION YO");
      updatedUser.setTeam(tem.toInternal(user.getTeam()));
      saveSkillsForUser(updatedUser, usem.toInternals(user.getUserSkills().getEpoList(), user));
      saveLanguagesForUser(updatedUser, ulem.toInternals(user.getUserLanguages().getEpos(), user));
      LOGGER.debug("To user: " + updatedUser);
      
      return userRepository.save(updatedUser);
   }
   
   @Transactional
   public void saveSkillsForUser(User user, List<UserSkill> newSkills) {
      List<UserSkill> currentSkills = userSkillRepository.findSkillsByUser(user.getId());
      for (int i = 0; i < currentSkills.size(); i++) {
         if (!(newSkills.contains(currentSkills.get(i)))) {
            userSkillRepository.delete(currentSkills.get(i).getId());
         } else {
            for (int j = 0; j < newSkills.size(); j++) {
               if (newSkills.get(j).getUser().getId() == currentSkills.get(i).getUser().getId()
                   && newSkills.get(j).getSkill().getId() == currentSkills.get(i).getSkill().getId()) {
                  newSkills.get(j).setId(currentSkills.get(i).getId());
               }
            }
         }
      }
      for (int i = 0; i < newSkills.size(); i++) {
         userSkillRepository.save(newSkills.get(i));
      }
   }
   
   @Transactional
   public void saveLanguagesForUser(User user, List<UserLanguage> newLanguages) {
      List<UserLanguage> currentLanguages = userLangRepository.findLanguageByUser(user.getId());
      for (int i = 0; i < currentLanguages.size(); i++) {
         if (!(newLanguages.contains(currentLanguages.get(i)))) {
            userLangRepository.delete(currentLanguages.get(i).getId());
         }
      }
      for (int i = 0; i < newLanguages.size(); i++) {
         userLangRepository.save(newLanguages.get(i));
      }
   }
   
   @Transactional
   public User deleteUser(long id) {
      LOGGER.debug("Deleting user under id: " + id);
      User deletedUser = userRepository.findOne(id);
      userSkillRepository.deleteByUser(deletedUser);
      userLangRepository.deleteByUser(deletedUser);
      // if (deletedUser.getAuthority().getAuthorityType().equals(AuthorityType.ROLE_TEAM_LEAD)) {
      teamLeadRepository.deleteByUser(deletedUser);
      // }
      LOGGER.debug("With this info: " + deletedUser);
      userRepository.delete(deletedUser);
      return deletedUser;
   }
   
   @Transactional (readOnly = true)
   public UserEpo findUserById(long id) {
      LOGGER.debug("Finding user under id: " + id);
      User user = userRepository.findOne(id);
      List<UserSkill> skills = (List<UserSkill>)userSkillRepository.findSkillsByUser(user.getId());
      List<UserLanguage> languages = (List<UserLanguage>)userLangRepository.findLanguageByUser(user.getId());
      UserEpo epo = uem.toExternal(user, skills, languages);
      LOGGER.debug("With this info: " + user);
      return epo;
   }
   
   // done
   @Transactional
   public Team createTeam(TeamEpo team) {
      Team createdTeam = tem.toInternal(team);
      LOGGER.debug("Creating team: " + team);
      return teamRepository.save(createdTeam);
   }
   
   @Transactional
   public void deleteTeam(TeamEpo epo) {
      Team createdTeam = tem.toInternal(epo);
      LOGGER.debug("Creating team: " + epo);
      teamRepository.delete(createdTeam);
   }
   
   @Transactional (readOnly = true)
   public TeamEpoList findAllTeams() {
      LOGGER.debug("Getting all teams from repository");
      return new TeamEpoList(tem.toExternals((List<Team>)teamRepository.findAll()));
   }
   
   @Transactional
   public Team updateTeam(Team team) {
      Team updatedTeam = teamRepository.findOne(team.getId());
      LOGGER.debug("Updating team: " + updatedTeam);
      updatedTeam.setName(team.getName());
      updatedTeam.setDepartment(team.getDepartment());
      LOGGER.debug("To team: " + updatedTeam);
      return updatedTeam;
   }
   
   @Transactional
   public Team deleteTeam(long id) {
      LOGGER.debug("Deleting team under id: " + id);
      Team deletedTeam = teamRepository.findOne(id);
      LOGGER.debug("With this info: " + deletedTeam);
      teamRepository.delete(deletedTeam);
      return deletedTeam;
   }
   
   @Transactional (readOnly = true)
   public TeamEpo findTeamById(long id) {
      LOGGER.debug("Finding team under id: " + id);
      Team team = teamRepository.findOne(id);
      TeamEpo epo = tem.toExternal(team);
      LOGGER.debug("With this info: " + team);
      return epo;
   }
   
   @Transactional
   public boolean assignEmployeeToTeam(long userId, long teamId) {
      LOGGER.debug("Assigning employee with id: " + userId + " to the team under id: " + teamId);
      User employee = userRepository.findOne(userId);
      Team assignedTeam = teamRepository.findOne(teamId);
      if (employee != null && assignedTeam != null) {
         LOGGER.debug("Successful assignment");
         employee.setTeam(assignedTeam);
         userRepository.save(employee);
         return true;
      } else {
         LOGGER.debug("Unsuccessful assignment");
         return false;
      }
   }
   
   @Override
   public AuthorityEpoList findAllAuthorities() {
      LOGGER.debug("Getting all authorities from repo");
      return new AuthorityEpoList(aem.toExternals((List<Authority>)authorityRepository.findAll()));
   }
   
   @Override
   public PositionEpoList findAllPositions() {
      LOGGER.debug("Getting all positions from repo");
      return new PositionEpoList(pem.toExternals((List<Position>)positionRepository.findAll()));
   }
   
   @Override
   public DepartmentEpoList getAllDepartments() {
      LOGGER.debug("Getting all depoartaments from repo");
      return new DepartmentEpoList(dem.toExternals((List<Department>)departmentRepository.findAll()));
   }
   
   @Override
   public FrontSkillEpoList findSkills() {
      return new FrontSkillEpoList(sem.toExternals((List<Skill>)skillRepository.findAll()));
   }
   
   // done
   @Override
   public Department addDepartment(DepartmentEpo epo) {
      Department dept = dem.toInternal(epo);
      return departmentRepository.save(dept);
   }
   
   @Override
   public Department deleteDepartment(long id) {
      Department dept = departmentRepository.findOne(id);
      departmentRepository.delete(dept);
      return dept;
   }
   
   @Override
   public Skill addSkill(FrontSkillEpo epo) {
      Skill skill = sem.frontToInternal(epo);
      return skillRepository.save(skill);
   }
   
   @Override
   public Skill removeSkill(FrontSkillEpo epo) {
      Skill skill = sem.frontToInternal(epo);
      skillRepository.delete(skill);
      return skill;
   }
   
   @Override
   public FrontLanguageEpoList findLanguages() {
      return lem.toFronts((List<Language>)languageRepository.findAll());
   }
   
   @Override
   public Language addLanguage(FrontLanguageEpo epo) {
      Language lang = lem.frontToInternal(epo);
      
      return languageRepository.save(lang);
   }
   
   @Override
   public Language removeLanguage(FrontLanguageEpo epo) {
      Language lang = lem.frontToInternal(epo);
      languageRepository.delete(lang);
      return lang;
   }
   
   @Override
   public FrontSkillEpoList findSkillsNotFromUser(long user_id) {
      FrontSkillEpoList finished = new FrontSkillEpoList(
         sem.toExternals(skillRepository.findSkillsRemainingForUser(user_id)));
      return finished;
   }
   
   @Override
   public UserSkill addUserSkill(UserSkillEpo epo) {
      UserSkill skillToAdd = usem.toInternal(epo);
      return userSkillRepository.save(skillToAdd);
   }
   
   @Override
   public UserSkill removeUserSkill(UserSkillEpo epo) {
      UserSkill skillToRemove = usem.toInternal(epo);
      System.out.println(epo);
      userSkillRepository.delete(skillToRemove);
      return skillToRemove;
   }
   
   @Override
   public List<UserLanguageEpo> getLanguagesByUser(long id) {
      List<UserLanguageEpo> epos = new ArrayList<UserLanguageEpo>();
      List<UserLanguage> languages = userLangRepository.findLanguageByUser(id);
      for (int i = 0; i < languages.size(); i++) {
         epos.add(ulem.toExternal(languages.get(i)));
      }
      return epos;
   }
   
   @Override
   public FrontLanguageEpoList getAvailableLanguages(long id) {
      return lem.toFronts((List<Language>)languageRepository.findLanguagesRemainingForUser(id));
   }
   
   @Override
   public UserLanguage addUserLanguage(UserLanguageEpo epo) {
      UserLanguage languageToAdd = ulem.toInternal(epo);
      return userLangRepository.save(languageToAdd);
   }
   
   @Override
   public UserLanguage removeUserLanguage(UserLanguageEpo epo) {
      UserLanguage languageToDelete = ulem.toInternal(epo);
      userLangRepository.delete(languageToDelete);
      return languageToDelete;
   }
   
   @Override
   public TeamLead assignTeamLead(TeamLeadEpo epo) {
      TeamLead teamLead = tlem.toInternal(epo);
      return teamLeadRepository.save(teamLead);
   }
   
   @Override
   public TeamLead removeTeamLead(TeamLeadEpo epo) {
      TeamLead teamLead = tlem.toInternal(epo);
      teamLeadRepository.delete(teamLead);
      return teamLead;
   }
   
   @Override
   public List<TeamLeadEpo> teamLeadByUser(long user_id) {
      List<TeamLead> teams = teamLeadRepository.findTeamLeadByUser(user_id);
      return tlem.toExternals(teamLeadRepository.findTeamLeadByUser(user_id));
   }
   
   @Override
   public List<TeamEpo> availableTeamsByUser(long user_id) {
      return tem.toExternals(teamRepository.findTeamsRemainingForUser(user_id));
   }
   
   @Override
   public List<UserEpo> getUsersByTeam(TeamEpo epo) {
      List<User> users = userRepository.findUserByTeam(tem.toInternal(epo));
      List<UserEpo> finalUsers = new ArrayList<UserEpo>();
      for (int i = 0; i < users.size(); i++) {
         List<UserSkill> skills = (List<UserSkill>)userSkillRepository.findSkillsByUser(users.get(i).getId());
         List<UserLanguage> languages = (List<UserLanguage>)userLangRepository.findLanguageByUser(users.get(i).getId());
         finalUsers.add(uem.toExternal(users.get(i), skills, languages));
      }
      return finalUsers;
   }
   
   @Override
   public List<UserEpo> getUsersByName(String name) {
      System.out.println("GetUserByName with argument:" + name);
      List<User> users = (List<User>)userRepository.searchForUser(name);
      List<UserEpo> finalUsers = new ArrayList<UserEpo>();
      for (int i = 0; i < users.size(); i++) {
         List<UserSkill> skills = (List<UserSkill>)userSkillRepository.findSkillsByUser(users.get(i).getId());
         List<UserLanguage> languages = (List<UserLanguage>)userLangRepository.findLanguageByUser(users.get(i).getId());
         finalUsers.add(uem.toExternal(users.get(i), skills, languages));
      }
      return finalUsers;
   }
   
   @Override
   public long changePassword(PasswordChangeRequest request) {
      User user = userRepository.findOne(request.getUserId());
      if (passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
         user.setPassword(passwordEncoder.encode(request.getNewPassword()));
         userRepository.save(user);
         return 1;
      }
      return 0;
   }
   
   @Override
   public Boolean resetPassword(long id) {
      User user = userRepository.findOne(id);
      if (user == null)
         return false;
      user.setPassword(passwordEncoder.encode(PASSWORD));
      userRepository.save(user);
      return true;
   }
   
   public LanguageLevelEpoList findAllLanguageLevels() {
      return new LanguageLevelEpoList(lglm.toExternals((List<LanguageLevel>)languageLevelRepository.findAll()));
   }
   
   @Override
   public SkillLevelEpoList findAllSkillLevels() {
      return new SkillLevelEpoList(sklm.toExternals((List<SkillLevel>)skillLevelRepository.findAll()));
   }
   
   @Override
   public Long getIdByUsername(String username) {
      Long id = userRepository.getIdByUsername(username);
      LOGGER.info(username + "got id: " + id);
      return id;
   }
   
   @Override
   public User addUserToTeam(long userId, long teamId) {
      User user = userRepository.findOne(userId);
      Team team = teamRepository.findOne(teamId);
      user.setTeam(team);
      return userRepository.save(user);
   }
   
   @Override
   public User deleteMemberFromTeam(long userId) {
      User user = userRepository.findOne(userId);
      Team team = teamRepository.findOne(1l);
      user.setTeam(team);
      return userRepository.save(user);
      
   }
   
   @Override
   public List<UserEpo> getUsersRemaining(long id) {
      List<User> users = (List<User>)userRepository.getRemainingUsers(id);
      List<UserEpo> finalUsers = new ArrayList<UserEpo>();
      for (int i = 0; i < users.size(); i++) {
         List<UserSkill> skills = (List<UserSkill>)userSkillRepository.findSkillsByUser(users.get(i).getId());
         List<UserLanguage> languages = (List<UserLanguage>)userLangRepository.findLanguageByUser(users.get(i).getId());
         finalUsers.add(uem.toExternal(users.get(i), skills, languages));
      }
      return finalUsers;
   }
   
   @Override
   public List<UserEpo> getUsersByNameFromTeam(TeamEpo epo) {
      System.out.println("GetUserByName with argument:" + epo.getName());
      List<User> users = (List<User>)userRepository.searchForUserFromTeam(epo.getName(), epo.getId());
      List<UserEpo> finalUsers = new ArrayList<UserEpo>();
      for (int i = 0; i < users.size(); i++) {
         List<UserSkill> skills = (List<UserSkill>)userSkillRepository.findSkillsByUser(users.get(i).getId());
         List<UserLanguage> languages = (List<UserLanguage>)userLangRepository.findLanguageByUser(users.get(i).getId());
         finalUsers.add(uem.toExternal(users.get(i), skills, languages));
      }
      return finalUsers;
   }
   
   @Override
   public List<UserEpo> getUsersByNameNotFromTeam(TeamEpo epo) {
      System.out.println("GetUserByName with argument:" + epo.getName());
      List<User> users = (List<User>)userRepository.searchForUserNotFromTeam(epo.getName(), epo.getId());
      List<UserEpo> finalUsers = new ArrayList<UserEpo>();
      for (int i = 0; i < users.size(); i++) {
         List<UserSkill> skills = (List<UserSkill>)userSkillRepository.findSkillsByUser(users.get(i).getId());
         List<UserLanguage> languages = (List<UserLanguage>)userLangRepository.findLanguageByUser(users.get(i).getId());
         finalUsers.add(uem.toExternal(users.get(i), skills, languages));
      }
      return finalUsers;
   }
   
   @Override
   public long findNrOfUsersFromATeam(long team_id) {
      LOGGER.debug("Find the number of members of the team with the id: " + team_id);
      return userRepository.findNrOfUsersFromATeam(team_id);
   }
   
   @Override
   public List<String> findUserNamesFromATeam(long team_id) {
      LOGGER.debug("Find the names of members of the team with the id: " + team_id);
      return userRepository.findUserNamesFromATeam(team_id);
   }
   
   @Override
   public long findNrOfTeamsOfATeamLead(long user_id) {
      LOGGER.debug("Find the number of teams for a team lead with the id: " + user_id);
      return teamLeadRepository.findNrOfTeamsOfATeamLead(user_id);
   }
   
   @Override
   public TeamEpo getTeamByTeamID(long team_id) {
      return tem.toExternal(teamRepository.findOne(team_id));
   }
   
   @Override
   public List<TeamEpo> getTeamsWithoutCoord() {
      return tem.toExternals(teamRepository.findTeamsWithoutCoord());
   }
   
   @Override
   public boolean adminLogin() {
      System.out.println("Admin trying to login");
      return true;
   }
}
