package com.mhp.compmanagementmicro.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhp.compmanagementmicro.epo.CompManagementUserSkillEpo;
import com.mhp.usermicro.epo.UserSkillEpo;

@Service
public class CompManagementUserSkillEpoMapper {
   
   @Autowired
   private CompManagementSkillLevelEpoMapper slmapper;
   @Autowired
   private CompManagementUserEpoMapper       usermapper;
   @Autowired
   private CompManagementSkillEpoMapper      smapper;
   
   public UserSkillEpo toInternal(CompManagementUserSkillEpo epo) {
      UserSkillEpo ul = new UserSkillEpo(epo.getId(), usermapper.toInternal(epo.getUser()),
         smapper.toInternal(epo.getSkill()), slmapper.toInternal(epo.getLevel()));
      return ul;
   }
   
   public CompManagementUserSkillEpo toExternal(UserSkillEpo us) {
      return new CompManagementUserSkillEpo(us.getId(), usermapper.toExternal(us.getUser()),
         smapper.toExternal(us.getSkill()), slmapper.toExternal(us.getLevel()));
   }
   
   public List<UserSkillEpo> toInternal(List<CompManagementUserSkillEpo> epoList) {
      List<UserSkillEpo> list = new LinkedList<UserSkillEpo>();
      for (CompManagementUserSkillEpo epo : epoList) {
         list.add(toInternal(epo));
      }
      return list;
   }
   
   public List<CompManagementUserSkillEpo> toExternal(List<UserSkillEpo> sklList) {
      List<CompManagementUserSkillEpo> list = new LinkedList<CompManagementUserSkillEpo>();
      for (UserSkillEpo us : sklList) {
         list.add(toExternal(us));
      }
      return list;
   }
}
