package com.mhp.compmanagementmicro.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhp.compmanagementmicro.epo.CompManagementUserEpo;
import com.mhp.usermicro.epo.UserEpo;

@Service
public class CompManagementUserEpoMapper {
   
   @Autowired
   private CompManagementAuthorityEpoMapper     aemapper;
   @Autowired
   private CompManagementTeamEpoMapper          teammapper;
   @Autowired
   private CompManagementPositionEpoMapper      positionmapper;
   @Autowired
   private CompManagementSkillEpoMapper         sem;
   @Autowired
   private CompManagementLanguageEpoMapper      lem;
   @Autowired
   private CompManagementLanguageLevelEpoMapper lvlm;
   @Autowired
   private CompManagementSkillLevelEpoMapper    sklm;
   @Autowired
   private CompManagementSkillEpoListMapper     sm;
   @Autowired
   private CompManagementLanguageEpoListMapper  lm;
   
   public UserEpo toInternal(CompManagementUserEpo epo) {
      UserEpo user = new UserEpo(epo.getId(), epo.getFirstName(), epo.getLastName(), epo.getUserName(),
         epo.getPassword(), aemapper.toInternal(epo.getRole()), epo.getPhotoLocation(),
         positionmapper.toInternal(epo.getPosition()), teammapper.toInternal(epo.getTeam()),
         sm.toInternal(epo.getUserSkills()), lm.toInternal(epo.getUserLanguages()), epo.getEnabled(),
         epo.getLastPasswordResetDate());
      return user;
   }
   
   public CompManagementUserEpo toExternal(UserEpo user) {
      return new CompManagementUserEpo(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName(),
         user.getPassword(), aemapper.toExternal(user.getAuthority()), user.getPhotoLocation(),
         positionmapper.toExternal(user.getPosition()), teammapper.toExternal(user.getTeam()),
         sm.toExternal(user.getUserSkills()), lm.toExternal(user.getUserLanguages()), user.getEnabled(),
         user.getLastPasswordResetDate());
   }
   
   public List<UserEpo> toInternal(List<CompManagementUserEpo> epoList) {
      List<UserEpo> list = new LinkedList<UserEpo>();
      for (CompManagementUserEpo epo : epoList) {
         list.add(toInternal(epo));
      }
      return list;
   }
   
   public List<CompManagementUserEpo> toExternal(List<UserEpo> userList) {
      List<CompManagementUserEpo> list = new LinkedList<CompManagementUserEpo>();
      for (UserEpo user : userList) {
         list.add(toExternal(user));
      }
      return list;
   }
   
}
