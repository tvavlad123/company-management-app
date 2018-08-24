package com.mhp.compmanagementmicro.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mhp.compmanagementmicro.epo.CompManagementFrontLanguageEpo;
import com.mhp.usermicro.epo.FrontLanguageEpo;

@Service
public class CompManagementFrontLanguageEpoMapper {
   
   public FrontLanguageEpo toInternal(CompManagementFrontLanguageEpo epo) {
      FrontLanguageEpo fl = new FrontLanguageEpo(epo.getId(), epo.getName());
      return fl;
   }
   
   public CompManagementFrontLanguageEpo toExternal(FrontLanguageEpo fl) {
      return new CompManagementFrontLanguageEpo(fl.getId(), fl.getName());
   }
   
   public List<FrontLanguageEpo> toInternal(List<CompManagementFrontLanguageEpo> epoList) {
      List<FrontLanguageEpo> list = new LinkedList<FrontLanguageEpo>();
      for (CompManagementFrontLanguageEpo epo : epoList) {
         list.add(toInternal(epo));
      }
      return list;
   }
   
   public List<CompManagementFrontLanguageEpo> toExternal(List<FrontLanguageEpo> flList) {
      List<CompManagementFrontLanguageEpo> list = new LinkedList<CompManagementFrontLanguageEpo>();
      for (FrontLanguageEpo fl : flList) {
         list.add(toExternal(fl));
      }
      return list;
   }
   
}
