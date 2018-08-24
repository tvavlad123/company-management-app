package com.mhp.usermicro.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mhp.usermicro.entity.Skill;
import com.mhp.usermicro.epo.FrontSkillEpo;
import com.mhp.usermicro.epo.SkillEpo;

@Service
public class SkillEpoMapper {
   
   public Skill toInternal(SkillEpo epo) {
      return new Skill(epo.getId(), epo.getName());
   }
   
   public Skill frontToInternal(FrontSkillEpo epo) {
      return new Skill(epo.getId(), epo.getName());
   }
   
   public SkillEpo toExternal(Skill model) {
      return new SkillEpo(model.getId(), model.getName());
   }
   
   public List<FrontSkillEpo> toExternals(List<Skill> skills) {
      List<FrontSkillEpo> epos = new ArrayList<FrontSkillEpo>();
      for (int i = 0; i < skills.size(); i++) {
         epos.add(new FrontSkillEpo(skills.get(i).getId(), skills.get(i).getName()));
      }
      
      return epos;
   }
}
