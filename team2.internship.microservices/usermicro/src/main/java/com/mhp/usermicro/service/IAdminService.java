package com.mhp.usermicro.service;

import java.util.List;

import org.postgresql.util.PSQLException;
import org.springframework.security.access.prepost.PreAuthorize;

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

// @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
public interface IAdminService {
   
   // CRUD User
   
   @PreAuthorize (value = "hasAuthority('ROLE_ADMIN')")
   public User createUser(UserEpo user) throws PSQLException;
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public List<UserEpo> findAllUsers();
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public User updateUser(UserEpo user);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public DepartmentEpoList getAllDepartments();
   
   @PreAuthorize (value = "hasAuthority('ROLE_ADMIN')")
   public Department addDepartment(DepartmentEpo epo);
   
   @PreAuthorize (value = "hasAuthority('ROLE_ADMIN')")
   public Department deleteDepartment(long id);
   
   @PreAuthorize (value = "hasAuthority('ROLE_ADMIN')")
   public User deleteUser(long id);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public UserEpo findUserById(long id);
   
   // CRUD Team
   
   @PreAuthorize (value = "hasAuthority('ROLE_ADMIN')")
   public Team createTeam(TeamEpo team);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public TeamEpoList findAllTeams();
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public AuthorityEpoList findAllAuthorities();
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public LanguageLevelEpoList findAllLanguageLevels();
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public SkillLevelEpoList findAllSkillLevels();
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public PositionEpoList findAllPositions();
   
   @PreAuthorize (value = "hasAuthority('ROLE_ADMIN')")
   public Team updateTeam(Team team);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public TeamEpo findTeamById(long id);
   
   @PreAuthorize (value = "hasAuthority('ROLE_ADMIN')")
   public void deleteTeam(TeamEpo epo);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public List<UserSkillEpo> findSkillsByUser(long user_id);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public FrontSkillEpoList findSkillsNotFromUser(long user_id);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public FrontSkillEpoList findSkills();
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public Skill addSkill(FrontSkillEpo epo);
   
   @PreAuthorize (value = "hasAuthority('ROLE_ADMIN')")
   public Skill removeSkill(FrontSkillEpo epo);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public FrontLanguageEpoList findLanguages();
   
   @PreAuthorize (value = "hasAuthority('ROLE_ADMIN')")
   public Language addLanguage(FrontLanguageEpo epo);
   
   @PreAuthorize (value = "hasAuthority('ROLE_ADMIN')")
   public Language removeLanguage(FrontLanguageEpo epo);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public UserSkill addUserSkill(UserSkillEpo epo);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public UserSkill removeUserSkill(UserSkillEpo epo);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public List<UserLanguageEpo> getLanguagesByUser(long id);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public FrontLanguageEpoList getAvailableLanguages(long id);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public UserLanguage addUserLanguage(UserLanguageEpo epo);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public UserLanguage removeUserLanguage(UserLanguageEpo epo);
   
   @PreAuthorize (value = "hasAuthority('ROLE_ADMIN')")
   public TeamLead assignTeamLead(TeamLeadEpo epo);
   
   @PreAuthorize (value = "hasAuthority('ROLE_ADMIN')")
   public TeamLead removeTeamLead(TeamLeadEpo epo);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public List<TeamLeadEpo> teamLeadByUser(long user_id);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public List<TeamEpo> availableTeamsByUser(long user_id);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public List<UserEpo> getUsersByTeam(TeamEpo epo);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public List<UserEpo> getUsersByName(String name);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public long changePassword(PasswordChangeRequest request);
   
   @PreAuthorize (value = "hasAuthority('ROLE_ADMIN')")
   public Boolean resetPassword(long id);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public Long getIdByUsername(String username);
   
   @PreAuthorize (value = "hasAuthority('ROLE_ADMIN')")
   public User addUserToTeam(long userId, long teamId);
   
   @PreAuthorize (value = "hasAuthority('ROLE_ADMIN')")
   public User deleteMemberFromTeam(long userId);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public List<UserEpo> getUsersRemaining(long id);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public List<UserEpo> getUsersByNameFromTeam(TeamEpo epo);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public List<UserEpo> getUsersByNameNotFromTeam(TeamEpo epo);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public long findNrOfUsersFromATeam(long team_id);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public List<String> findUserNamesFromATeam(long team_id);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public long findNrOfTeamsOfATeamLead(long user_id);
   
   @PreAuthorize (value = "hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TEAM_COORD','ROLE_TEAM_LEAD')")
   public TeamEpo getTeamByTeamID(long team_id);
   
   @PreAuthorize (value = "hasAuthority('ROLE_ADMIN')")
   public List<TeamEpo> getTeamsWithoutCoord();
   
   @PreAuthorize (value = "hasAuthority('ROLE_ADMIN')")
   public boolean adminLogin();
   
}
