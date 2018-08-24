package com.mhp.usermicro.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mhp.usermicro.entity.SkillLevel;
import com.mhp.usermicro.epo.SkillLevelEpo;

@Service
public class SkillLevelMapper extends GenericMapper<SkillLevel, SkillLevelEpo> {
   
   public SkillLevel toInternal(SkillLevelEpo epo) {
      return new SkillLevel(epo.getId(), epo.getName());
   }
   
   public SkillLevel frontToInternal(SkillLevelEpo epo) {
      return new SkillLevel(epo.getId(), epo.getName());
   }
   
   public SkillLevelEpo toExternal(SkillLevel model) {
      return new SkillLevelEpo(model.getId(), model.getName());
   }
   
   public List<SkillLevelEpo> toFronts(List<SkillLevel> skillslevel) {
      List<SkillLevelEpo> epos = new ArrayList<SkillLevelEpo>();
      for (int i = 0; i < skillslevel.size(); i++) {
         epos.add(new SkillLevelEpo(skillslevel.get(i).getId(), skillslevel.get(i).getName()));
      }
      
      return epos;
   }
   
}
