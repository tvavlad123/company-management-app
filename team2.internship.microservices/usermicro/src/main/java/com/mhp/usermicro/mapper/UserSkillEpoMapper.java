package com.mhp.usermicro.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhp.usermicro.entity.UserLanguage;
import com.mhp.usermicro.entity.UserSkill;
import com.mhp.usermicro.epo.UserEpo;
import com.mhp.usermicro.epo.UserSkillEpo;

@Service
public class UserSkillEpoMapper {
   
   @Autowired
   private SkillEpoMapper sem;
   @Autowired
   UserEpoMapper          uem;
   @Autowired
   SkillLevelMapper       skl;
   
   public UserSkill toInternal(UserSkillEpo epo) {
      UserSkill userSkill = new UserSkill(epo.getId(), uem.toInternal(epo.getUser()), sem.toInternal(epo.getSkill()),
         skl.toInternal(epo.getLevel()));
      return userSkill;
   }
   
   public List<UserSkill> toInternals(List<UserSkillEpo> epo, UserEpo userepo) {
      List<UserSkill> skills = new ArrayList<UserSkill>();
      for (int i = 0; i < epo.size(); i++) {
         UserSkill userSkill = new UserSkill(epo.get(i).getId(), uem.toInternal(userepo),
            sem.toInternal(epo.get(i).getSkill()), skl.toInternal(epo.get(i).getLevel()));
         skills.add(userSkill);
      }
      
      return skills;
      
   }
   
   public UserSkillEpo toExternal(UserSkill model) {
      UserSkillEpo epo = new UserSkillEpo(model.getId(),
         uem.toExternal(model.getUser(), new ArrayList<UserSkill>(), new ArrayList<UserLanguage>()),
         sem.toExternal(model.getSkill()), skl.toExternal(model.getLevel()));
      return epo;
      
   }
   
}
