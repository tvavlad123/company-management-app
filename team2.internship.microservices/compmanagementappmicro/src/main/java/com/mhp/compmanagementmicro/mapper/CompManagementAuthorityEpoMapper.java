package com.mhp.compmanagementmicro.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mhp.compmanagementmicro.epo.CompManagementAuthorityEpo;
import com.mhp.usermicro.epo.AuthorityEpo;

@Service
public class CompManagementAuthorityEpoMapper {
   
   public AuthorityEpo toInternal(CompManagementAuthorityEpo epo) {
      AuthorityEpo role = new AuthorityEpo(epo.getId(), epo.getAuthorityType());
      return role;
   }
   
   public CompManagementAuthorityEpo toExternal(AuthorityEpo rl) {
      return new CompManagementAuthorityEpo(rl.getId(), rl.getAuthorityType());
   }
   
   public List<AuthorityEpo> toInternal(List<CompManagementAuthorityEpo> epoList) {
      List<AuthorityEpo> list = new LinkedList<AuthorityEpo>();
      for (CompManagementAuthorityEpo epo : epoList) {
         list.add(toInternal(epo));
      }
      return list;
   }
   
   public List<CompManagementAuthorityEpo> toExternal(List<AuthorityEpo> rlList) {
      List<CompManagementAuthorityEpo> list = new LinkedList<CompManagementAuthorityEpo>();
      for (AuthorityEpo role : rlList) {
         list.add(toExternal(role));
      }
      return list;
   }
   
}
