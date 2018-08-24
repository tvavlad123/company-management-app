package com.mhp.compmanagementmicro.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mhp.compmanagementmicro.epo.CompManagementHalfDayEpo;
import com.mhp.requestmicro.epo.HalfDayEpo;

@Service
public class CompManagementHalfDayEpoMapper {
   
   public HalfDayEpo toInternal(CompManagementHalfDayEpo epo) {
      HalfDayEpo hd = new HalfDayEpo(epo.getId(), epo.getType());
      return hd;
   }
   
   public CompManagementHalfDayEpo toExternal(HalfDayEpo hd) {
      return new CompManagementHalfDayEpo(hd.getId(), hd.getType());
   }
   
   public List<HalfDayEpo> toInternal(List<CompManagementHalfDayEpo> epoList) {
      List<HalfDayEpo> list = new LinkedList<HalfDayEpo>();
      for (CompManagementHalfDayEpo epo : epoList) {
         list.add(toInternal(epo));
      }
      return list;
   }
   
   public List<CompManagementHalfDayEpo> toExternal(List<HalfDayEpo> hdList) {
      List<CompManagementHalfDayEpo> list = new LinkedList<CompManagementHalfDayEpo>();
      for (HalfDayEpo hd : hdList) {
         list.add(toExternal(hd));
      }
      return list;
   }
}
