package com.mhp.compmanagementmicro.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhp.compmanagementmicro.epo.CompManagementTeamEpo;
import com.mhp.usermicro.epo.TeamEpo;

@Service
public class CompManagementTeamEpoMapper {
   
   @Autowired
   private CompManagementDepartmentEpoMapper deptepomapper;
   
   public TeamEpo toInternal(CompManagementTeamEpo epo) {
      TeamEpo team = new TeamEpo(epo.getId(), epo.getName(), deptepomapper.toInternal(epo.getDepartment()));
      return team;
   }
   
   public CompManagementTeamEpo toExternal(TeamEpo team) {
      return new CompManagementTeamEpo(team.getId(), team.getName(), deptepomapper.toExternal(team.getDepartment()));
   }
   
   public List<TeamEpo> toInternal(List<CompManagementTeamEpo> epoList) {
      List<TeamEpo> list = new LinkedList<TeamEpo>();
      for (CompManagementTeamEpo epo : epoList) {
         list.add(toInternal(epo));
      }
      return list;
   }
   
   public List<CompManagementTeamEpo> toExternal(List<TeamEpo> teamList) {
      List<CompManagementTeamEpo> list = new LinkedList<CompManagementTeamEpo>();
      for (TeamEpo team : teamList) {
         list.add(toExternal(team));
      }
      return list;
   }
   
}
