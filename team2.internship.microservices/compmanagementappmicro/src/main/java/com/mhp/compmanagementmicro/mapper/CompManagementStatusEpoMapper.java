package com.mhp.compmanagementmicro.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mhp.compmanagementmicro.epo.CompManagementStatusEpo;
import com.mhp.requestmicro.epo.StatusEpo;

@Service
public class CompManagementStatusEpoMapper {
   
   public StatusEpo toInternal(CompManagementStatusEpo epo) {
      StatusEpo sts = new StatusEpo(epo.getId(), epo.getName());
      return sts;
   }
   
   public CompManagementStatusEpo toExternal(StatusEpo sts) {
      return new CompManagementStatusEpo(sts.getId(), sts.getName());
   }
   
   public List<StatusEpo> toInternal(List<CompManagementStatusEpo> epoList) {
      List<StatusEpo> list = new LinkedList<StatusEpo>();
      for (CompManagementStatusEpo epo : epoList) {
         list.add(toInternal(epo));
      }
      return list;
   }
   
   public List<CompManagementStatusEpo> toExternal(List<StatusEpo> stsList) {
      List<CompManagementStatusEpo> list = new LinkedList<CompManagementStatusEpo>();
      for (StatusEpo status : stsList) {
         list.add(toExternal(status));
      }
      return list;
   }
   
}
