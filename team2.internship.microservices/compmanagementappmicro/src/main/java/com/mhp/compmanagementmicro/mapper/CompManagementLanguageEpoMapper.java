package com.mhp.compmanagementmicro.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhp.compmanagementmicro.epo.CompManagementLanguageEpo;
import com.mhp.usermicro.epo.LanguageEpo;

@Service
public class CompManagementLanguageEpoMapper {
   
   @Autowired
   private CompManagementLanguageLevelEpoMapper llepomapper;
   
   public LanguageEpo toInternal(CompManagementLanguageEpo epo) {
      LanguageEpo skill = new LanguageEpo(epo.getId(), epo.getName());
      return skill;
   }
   
   public CompManagementLanguageEpo toExternal(LanguageEpo skl) {
      return new CompManagementLanguageEpo(skl.getId(), skl.getName());
   }
   
   public List<LanguageEpo> toInternal(List<CompManagementLanguageEpo> epoList) {
      List<LanguageEpo> list = new LinkedList<LanguageEpo>();
      for (CompManagementLanguageEpo epo : epoList) {
         list.add(toInternal(epo));
      }
      return list;
   }
   
   public List<CompManagementLanguageEpo> toExternal(List<LanguageEpo> sklList) {
      List<CompManagementLanguageEpo> list = new LinkedList<CompManagementLanguageEpo>();
      for (LanguageEpo status : sklList) {
         list.add(toExternal(status));
      }
      return list;
   }
   
}
