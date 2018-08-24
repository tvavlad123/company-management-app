package com.mhp.compmanagementmicro.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhp.compmanagementmicro.epo.CompManagementSkillEpo;
import com.mhp.usermicro.epo.SkillEpo;

@Service
public class CompManagementSkillEpoMapper {
   
   @Autowired
   private CompManagementSkillLevelEpoMapper fskepomapper;
   
   public SkillEpo toInternal(CompManagementSkillEpo epo) {
      SkillEpo skill = new SkillEpo(epo.getId(), epo.getName());
      return skill;
   }
   
   public CompManagementSkillEpo toExternal(SkillEpo skl) {
      return new CompManagementSkillEpo(skl.getId(), skl.getName());
   }
   
   public List<SkillEpo> toInternal(List<CompManagementSkillEpo> epoList) {
      List<SkillEpo> list = new LinkedList<SkillEpo>();
      for (CompManagementSkillEpo epo : epoList) {
         list.add(toInternal(epo));
      }
      return list;
   }
   
   public List<CompManagementSkillEpo> toExternal(List<SkillEpo> sklList) {
      List<CompManagementSkillEpo> list = new LinkedList<CompManagementSkillEpo>();
      for (SkillEpo status : sklList) {
         list.add(toExternal(status));
      }
      return list;
   }
   
}
