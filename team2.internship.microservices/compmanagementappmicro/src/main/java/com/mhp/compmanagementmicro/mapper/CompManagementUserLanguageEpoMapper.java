package com.mhp.compmanagementmicro.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhp.compmanagementmicro.epo.CompManagementUserLanguageEpo;
import com.mhp.usermicro.epo.UserLanguageEpo;

@Service
public class CompManagementUserLanguageEpoMapper {
   
   @Autowired
   private CompManagementLanguageLevelEpoMapper llmapper;
   @Autowired
   private CompManagementUserEpoMapper          usermapper;
   @Autowired
   private CompManagementLanguageEpoMapper      lmapper;
   
   public UserLanguageEpo toInternal(CompManagementUserLanguageEpo epo) {
      UserLanguageEpo ul = new UserLanguageEpo(epo.getId(), usermapper.toInternal(epo.getUser()),
         lmapper.toInternal(epo.getLanguage()), llmapper.toInternal(epo.getLevel()));
      return ul;
   }
   
   public CompManagementUserLanguageEpo toExternal(UserLanguageEpo ul) {
      return new CompManagementUserLanguageEpo(ul.getId(), llmapper.toExternal(ul.getLevel()),
         lmapper.toExternal(ul.getLanguage()), usermapper.toExternal(ul.getUser()));
   }
   
   public List<UserLanguageEpo> toInternal(List<CompManagementUserLanguageEpo> epoList) {
      List<UserLanguageEpo> list = new LinkedList<UserLanguageEpo>();
      for (CompManagementUserLanguageEpo epo : epoList) {
         list.add(toInternal(epo));
      }
      return list;
   }
   
   public List<CompManagementUserLanguageEpo> toExternal(List<UserLanguageEpo> ulList) {
      List<CompManagementUserLanguageEpo> list = new LinkedList<CompManagementUserLanguageEpo>();
      for (UserLanguageEpo ul : ulList) {
         list.add(toExternal(ul));
      }
      return list;
   }
   
}
