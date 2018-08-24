package com.mhp.compmanagementmicro.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mhp.compmanagementmicro.epo.CompManagementLanguageLevelEpo;
import com.mhp.usermicro.epo.LanguageLevelEpo;

@Service
public class CompManagementLanguageLevelEpoMapper {
   
   public LanguageLevelEpo toInternal(CompManagementLanguageLevelEpo epo) {
      LanguageLevelEpo ll = new LanguageLevelEpo(epo.getId(), epo.getName());
      return ll;
   }
   
   public CompManagementLanguageLevelEpo toExternal(LanguageLevelEpo ll) {
      return new CompManagementLanguageLevelEpo(ll.getId(), ll.getName());
   }
   
   public List<LanguageLevelEpo> toInternal(List<CompManagementLanguageLevelEpo> epoList) {
      List<LanguageLevelEpo> list = new LinkedList<LanguageLevelEpo>();
      for (CompManagementLanguageLevelEpo epo : epoList) {
         list.add(toInternal(epo));
      }
      return list;
   }
   
   public List<CompManagementLanguageLevelEpo> toExternal(List<LanguageLevelEpo> llList) {
      List<CompManagementLanguageLevelEpo> list = new LinkedList<CompManagementLanguageLevelEpo>();
      for (LanguageLevelEpo dept : llList) {
         list.add(toExternal(dept));
      }
      return list;
   }
   
}
