package com.mhp.compmanagementmicro.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mhp.compmanagementmicro.epo.CompManagementRoleEpo;
import com.mhp.usermicro.epo.RoleEpo;

@Service
public class CompManagementRoleEpoMapper {
   
   public RoleEpo toInternal(CompManagementRoleEpo epo) {
      RoleEpo role = new RoleEpo(epo.getId(), epo.getName());
      return role;
   }
   
   public CompManagementRoleEpo toExternal(RoleEpo rl) {
      return new CompManagementRoleEpo(rl.getId(), rl.getName());
   }
   
   public List<RoleEpo> toInternal(List<CompManagementRoleEpo> epoList) {
      List<RoleEpo> list = new LinkedList<RoleEpo>();
      for (CompManagementRoleEpo epo : epoList) {
         list.add(toInternal(epo));
      }
      return list;
   }
   
   public List<CompManagementRoleEpo> toExternal(List<RoleEpo> rlList) {
      List<CompManagementRoleEpo> list = new LinkedList<CompManagementRoleEpo>();
      for (RoleEpo role : rlList) {
         list.add(toExternal(role));
      }
      return list;
   }
   
}
