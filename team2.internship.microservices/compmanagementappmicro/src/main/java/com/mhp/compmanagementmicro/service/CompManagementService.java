package com.mhp.compmanagementmicro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mhp.compmanagementmicro.epo.CompManagementDepartmentEpo;
import com.mhp.compmanagementmicro.epo.CompManagementTeamEpo;
import com.mhp.compmanagementmicro.epo.CompManagementUserEpo;
import com.mhp.compmanagementmicro.epo.CompManagementUserSkillEpo;
import com.mhp.compmanagementmicro.mapper.CompManagementAuthorityEpoMapper;
import com.mhp.compmanagementmicro.mapper.CompManagementDepartmentEpoMapper;
import com.mhp.compmanagementmicro.mapper.CompManagementFrontLanguageEpoMapper;
import com.mhp.compmanagementmicro.mapper.CompManagementFrontSkillEpoMapper;
import com.mhp.compmanagementmicro.mapper.CompManagementHomeOfficeRequestEpoMapper;
import com.mhp.compmanagementmicro.mapper.CompManagementLanguageEpoListMapper;
import com.mhp.compmanagementmicro.mapper.CompManagementLanguageEpoMapper;
import com.mhp.compmanagementmicro.mapper.CompManagementLanguageLevelEpoMapper;
import com.mhp.compmanagementmicro.mapper.CompManagementPositionEpoMapper;
import com.mhp.compmanagementmicro.mapper.CompManagementSkillEpoListMapper;
import com.mhp.compmanagementmicro.mapper.CompManagementSkillEpoMapper;
import com.mhp.compmanagementmicro.mapper.CompManagementSkillLevelEpoMapper;
import com.mhp.compmanagementmicro.mapper.CompManagementStatusEpoMapper;
import com.mhp.compmanagementmicro.mapper.CompManagementTeamEpoMapper;
import com.mhp.compmanagementmicro.mapper.CompManagementTeamLeadEpoMapper;
import com.mhp.compmanagementmicro.mapper.CompManagementUserEpoMapper;
import com.mhp.compmanagementmicro.mapper.CompManagementUserLanguageEpoMapper;
import com.mhp.compmanagementmicro.mapper.CompManagementUserSkillEpoMapper;
import com.mhp.compmanagementmicro.mapper.CompManagementVacationRequestEpoMapper;
import com.mhp.usermicro.epo.DepartmentEpo;
import com.mhp.usermicro.epo.TeamEpo;
import com.mhp.usermicro.epo.UserEpo;
import com.mhp.usermicro.epo.list.UserEpoList;
import com.mhp.usermicro.epo.list.UserSkillEpoList;

@Service
public class CompManagementService {
   
   private final String                             userServiceUrl    = "http://localhost:9123";
   private final String                             requestServiceUrl = "http://localhost:9124";
   
   private RestTemplate                             restTemplate      = new RestTemplate();
   
   @Autowired
   private CompManagementAuthorityEpoMapper         authorityMapper;
   @Autowired
   private CompManagementDepartmentEpoMapper        departmentMapper;
   @Autowired
   private CompManagementFrontLanguageEpoMapper     frontLanguageMapper;
   @Autowired
   private CompManagementFrontSkillEpoMapper        frontSkillMapper;
   @Autowired
   private CompManagementHomeOfficeRequestEpoMapper homeOfficeRequestMapper;
   @Autowired
   private CompManagementLanguageEpoListMapper      languageListMapper;
   @Autowired
   private CompManagementLanguageEpoMapper          languageMapper;
   @Autowired
   private CompManagementLanguageLevelEpoMapper     languageLevelMapper;
   @Autowired
   private CompManagementPositionEpoMapper          positionMapper;
   @Autowired
   private CompManagementSkillEpoListMapper         skillListMapper;
   @Autowired
   private CompManagementSkillEpoMapper             skillMapper;
   @Autowired
   private CompManagementSkillLevelEpoMapper        skillLevelMapper;
   @Autowired
   private CompManagementStatusEpoMapper            statusMapper;
   @Autowired
   private CompManagementTeamEpoMapper              teamMapper;
   @Autowired
   private CompManagementTeamLeadEpoMapper          teamLeadMapper;
   @Autowired
   private CompManagementUserEpoMapper              userMapper;
   @Autowired
   private CompManagementUserLanguageEpoMapper      userLanguageMapper;
   @Autowired
   private CompManagementUserSkillEpoMapper         userSkillMapper;
   @Autowired
   private CompManagementVacationRequestEpoMapper   vacationRequestMapper;
   
   public CompManagementUserEpo createUser(CompManagementUserEpo user) {
      UserEpo epo = restTemplate.postForEntity(userServiceUrl + "/addUser", userMapper.toInternal(user), UserEpo.class)
         .getBody();
      return userMapper.toExternal(epo);
   }
   
   public List<CompManagementUserEpo> getAllUsers() {
      UserEpoList epoList = restTemplate.getForEntity(userServiceUrl + "/getallusers", UserEpoList.class).getBody();
      return userMapper.toExternal(epoList.getEpoList());
   }
   
   public List<CompManagementUserSkillEpo> findSkillsByUser(long user_id) {
      UserSkillEpoList epos = restTemplate
         .postForEntity(userServiceUrl + "/getskillsbyuser", new Long(user_id), UserSkillEpoList.class).getBody();
      return userSkillMapper.toExternal(epos.getEpoList());
   }
   
   public CompManagementUserEpo updateUser(CompManagementUserEpo user) {
      UserEpo epo = restTemplate
         .postForEntity(userServiceUrl + "/updateUser", userMapper.toInternal(user), UserEpo.class).getBody();
      return userMapper.toExternal(epo);
   }
   
   public CompManagementDepartmentEpo createDepartment(CompManagementDepartmentEpo dept) {
      DepartmentEpo epo = restTemplate
         .postForEntity(userServiceUrl + "/adddepartment", departmentMapper.toInternal(dept), DepartmentEpo.class)
         .getBody();
      return departmentMapper.toExternal(epo);
   }
   
   public CompManagementTeamEpo createTeam(CompManagementTeamEpo team) {
      TeamEpo epo = restTemplate.postForEntity(userServiceUrl + "/addTeam", teamMapper.toInternal(team), TeamEpo.class)
         .getBody();
      return teamMapper.toExternal(epo);
   }
}
