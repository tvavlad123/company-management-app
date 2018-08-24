package com.mhp.compmanagementmicro.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhp.compmanagementmicro.epo.CompManagementVacationRequestEpo;
import com.mhp.requestmicro.epo.VacationRequestEpo;

@Service
public class CompManagementVacationRequestEpoMapper {
   
   @Autowired
   private CompManagementStatusEpoMapper statusmapper;
   
   public VacationRequestEpo toInternal(CompManagementVacationRequestEpo epo) {
      VacationRequestEpo hor = new VacationRequestEpo(epo.getId(), epo.getUser_id().getId(), epo.getStart_date(),
         epo.getEnd_date(), epo.getComments(), epo.getPicture(), statusmapper.toInternal(epo.getStatus_id()));
      return hor;
   }
   
   public List<VacationRequestEpo> toInternal(List<CompManagementVacationRequestEpo> epoList) {
      List<VacationRequestEpo> list = new LinkedList<VacationRequestEpo>();
      for (CompManagementVacationRequestEpo epo : epoList) {
         list.add(toInternal(epo));
      }
      return list;
   }
   
}
