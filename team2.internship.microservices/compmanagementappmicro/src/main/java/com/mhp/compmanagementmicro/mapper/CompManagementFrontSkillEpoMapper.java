package com.mhp.compmanagementmicro.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mhp.compmanagementmicro.epo.CompManagementFrontSkillEpo;
import com.mhp.usermicro.epo.FrontSkillEpo;

@Service
public class CompManagementFrontSkillEpoMapper {
   
   public FrontSkillEpo toInternal(CompManagementFrontSkillEpo epo) {
      FrontSkillEpo sl = new FrontSkillEpo(epo.getId(), epo.getName());
      return sl;
   }
   
   public CompManagementFrontSkillEpo toExternal(FrontSkillEpo sl) {
      return new CompManagementFrontSkillEpo(sl.getId(), sl.getName());
   }
   
   public List<FrontSkillEpo> toInternal(List<CompManagementFrontSkillEpo> epoList) {
      List<FrontSkillEpo> list = new LinkedList<FrontSkillEpo>();
      for (CompManagementFrontSkillEpo epo : epoList) {
         list.add(toInternal(epo));
      }
      return list;
   }
   
   public List<CompManagementFrontSkillEpo> toExternal(List<FrontSkillEpo> slList) {
      List<CompManagementFrontSkillEpo> list = new LinkedList<CompManagementFrontSkillEpo>();
      for (FrontSkillEpo sl : slList) {
         list.add(toExternal(sl));
      }
      return list;
   }
   
}
