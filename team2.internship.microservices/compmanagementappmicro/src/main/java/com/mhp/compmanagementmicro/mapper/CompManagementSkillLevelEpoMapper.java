package com.mhp.compmanagementmicro.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mhp.compmanagementmicro.epo.CompManagementSkillLevelEpo;
import com.mhp.usermicro.epo.SkillLevelEpo;

@Service
public class CompManagementSkillLevelEpoMapper {
   
   public SkillLevelEpo toInternal(CompManagementSkillLevelEpo epo) {
      SkillLevelEpo skll = new SkillLevelEpo(epo.getId(), epo.getName());
      return skll;
   }
   
   public CompManagementSkillLevelEpo toExternal(SkillLevelEpo skll) {
      return new CompManagementSkillLevelEpo(skll.getId(), skll.getName());
   }
   
   public List<SkillLevelEpo> toInternal(List<CompManagementSkillLevelEpo> epoList) {
      List<SkillLevelEpo> list = new LinkedList<SkillLevelEpo>();
      for (CompManagementSkillLevelEpo epoo : epoList) {
         list.add(toInternal(epoo));
      }
      return list;
   }
   
   public List<CompManagementSkillLevelEpo> toExternal(List<SkillLevelEpo> skllList) {
      List<CompManagementSkillLevelEpo> list = new LinkedList<CompManagementSkillLevelEpo>();
      for (SkillLevelEpo dept : skllList) {
         list.add(toExternal(dept));
      }
      return list;
   }
   
}
