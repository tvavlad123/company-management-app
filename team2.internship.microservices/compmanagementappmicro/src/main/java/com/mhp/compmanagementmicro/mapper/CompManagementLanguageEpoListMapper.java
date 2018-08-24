package com.mhp.compmanagementmicro.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhp.compmanagementmicro.epo.list.CompManagementLanguageEpoList;
import com.mhp.usermicro.epo.list.LanguageEpoList;

@Service
public class CompManagementLanguageEpoListMapper {
   
   @Autowired
   private CompManagementUserLanguageEpoMapper lmapper;
   
   public LanguageEpoList toInternal(CompManagementLanguageEpoList epo) {
      LanguageEpoList pos = new LanguageEpoList(lmapper.toInternal(epo.getEpos()));
      return pos;
   }
   
   public CompManagementLanguageEpoList toExternal(LanguageEpoList pos) {
      return new CompManagementLanguageEpoList(lmapper.toExternal(pos.getEpos()));
   }
   
}
