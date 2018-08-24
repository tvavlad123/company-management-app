package com.mhp.compmanagementmicro.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhp.compmanagementmicro.epo.CompManagementHomeOfficeRequestEpo;
import com.mhp.requestmicro.epo.HomeOfficeRequestEpo;

@Service
public class CompManagementHomeOfficeRequestEpoMapper {
   
   @Autowired
   private CompManagementStatusEpoMapper  statusmapper;
   @Autowired
   private CompManagementHalfDayEpoMapper halfdaymapper;
   
   public HomeOfficeRequestEpo toInternal(CompManagementHomeOfficeRequestEpo epo) {
      HomeOfficeRequestEpo hor = new HomeOfficeRequestEpo(epo.getId(), epo.getUser_id().getId(), epo.getStart_date(),
         epo.getEnd_date(), halfdaymapper.toInternal(epo.getHalf_day()), statusmapper.toInternal(epo.getStatus_id()));
      return hor;
   }
   
   public List<HomeOfficeRequestEpo> toInternal(List<CompManagementHomeOfficeRequestEpo> epoList) {
      List<HomeOfficeRequestEpo> list = new LinkedList<HomeOfficeRequestEpo>();
      for (CompManagementHomeOfficeRequestEpo epo : epoList) {
         list.add(toInternal(epo));
      }
      return list;
   }
   
}
