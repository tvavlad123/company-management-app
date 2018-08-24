package com.mhp.compmanagementmicro.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mhp.compmanagementmicro.epo.CompManagementPositionEpo;
import com.mhp.usermicro.epo.PositionEpo;

@Service
public class CompManagementPositionEpoMapper {
   
   public PositionEpo toInternal(CompManagementPositionEpo epo) {
      PositionEpo pos = new PositionEpo(epo.getId(), epo.getName());
      return pos;
   }
   
   public CompManagementPositionEpo toExternal(PositionEpo pos) {
      return new CompManagementPositionEpo(pos.getId(), pos.getName());
   }
   
   public List<PositionEpo> toInternal(List<CompManagementPositionEpo> epoList) {
      List<PositionEpo> list = new LinkedList<PositionEpo>();
      for (CompManagementPositionEpo epo : epoList) {
         list.add(toInternal(epo));
      }
      return list;
   }
   
   public List<CompManagementPositionEpo> toExternal(List<PositionEpo> posList) {
      List<CompManagementPositionEpo> list = new LinkedList<CompManagementPositionEpo>();
      for (PositionEpo posi : posList) {
         list.add(toExternal(posi));
      }
      return list;
   }
   
}
