package com.mhp.compmanagementmicro.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhp.compmanagementmicro.epo.CompManagementTeamLeadEpo;
import com.mhp.usermicro.epo.TeamLeadEpo;

@Service
public class CompManagementTeamLeadEpoMapper {
   
   @Autowired
   private CompManagementUserEpoMapper usermapper;
   @Autowired
   private CompManagementTeamEpoMapper teammapper;
   
   public TeamLeadEpo toInternal(CompManagementTeamLeadEpo epo) {
      TeamLeadEpo teamlead = new TeamLeadEpo(epo.getId(), usermapper.toInternal(epo.getUser()),
         teammapper.toInternal(epo.getTeam()));
      return teamlead;
   }
   
   public CompManagementTeamLeadEpo toExternal(TeamLeadEpo tl) {
      return new CompManagementTeamLeadEpo(tl.getId(), usermapper.toExternal(tl.getUser()),
         teammapper.toExternal(tl.getTeam()));
   }
   
   public List<TeamLeadEpo> toInternal(List<CompManagementTeamLeadEpo> epoList) {
      List<TeamLeadEpo> list = new LinkedList<TeamLeadEpo>();
      for (CompManagementTeamLeadEpo epo : epoList) {
         list.add(toInternal(epo));
      }
      return list;
   }
   
   public List<CompManagementTeamLeadEpo> toExternal(List<TeamLeadEpo> tlList) {
      List<CompManagementTeamLeadEpo> list = new LinkedList<CompManagementTeamLeadEpo>();
      for (TeamLeadEpo tl : tlList) {
         list.add(toExternal(tl));
      }
      return list;
   }
   
}
