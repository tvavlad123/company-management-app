package com.mhp.usermicro.mapper;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhp.usermicro.entity.TeamLead;
import com.mhp.usermicro.entity.UserLanguage;
import com.mhp.usermicro.entity.UserSkill;
import com.mhp.usermicro.epo.TeamLeadEpo;

@Service
public class TeamLeadEpoMapper extends GenericMapper<TeamLead, TeamLeadEpo> {
   
   @Autowired
   private UserEpoMapper uem;
   @Autowired
   private TeamEpoMapper tem;
   
   @Override
   public TeamLead toInternal(TeamLeadEpo epo) {
      return new TeamLead(epo.getId(), uem.toInternal(epo.getUser()), tem.toInternal(epo.getTeam()));
   }
   
   @Override
   public TeamLeadEpo toExternal(TeamLead model) {
      return new TeamLeadEpo(model.getId(),
         uem.toExternal(model.getUser(), new ArrayList<UserSkill>(), new ArrayList<UserLanguage>()),
         tem.toExternal(model.getTeam()));
   }
}
