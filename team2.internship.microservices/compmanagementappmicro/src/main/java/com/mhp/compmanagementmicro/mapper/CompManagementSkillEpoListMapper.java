package com.mhp.compmanagementmicro.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhp.compmanagementmicro.epo.list.CompManagementSkillEpoList;
import com.mhp.usermicro.epo.list.SkillEpoList;

@Service
public class CompManagementSkillEpoListMapper {
   
   @Autowired
   private CompManagementUserSkillEpoMapper smapper;
   
   public SkillEpoList toInternal(CompManagementSkillEpoList epo) {
      SkillEpoList pos = new SkillEpoList(smapper.toInternal(epo.getEpoList()));
      return pos;
   }
   
   public CompManagementSkillEpoList toExternal(SkillEpoList pos) {
      return new CompManagementSkillEpoList(smapper.toExternal(pos.getEpoList()));
   }
}
