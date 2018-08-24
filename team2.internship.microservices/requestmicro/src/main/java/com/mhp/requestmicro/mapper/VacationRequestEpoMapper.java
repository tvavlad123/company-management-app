package com.mhp.requestmicro.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhp.requestmicro.entity.VacationRequest;
import com.mhp.requestmicro.epo.VacationRequestEpo;
import com.mhp.requestmicro.epo.list.VacationRequestEpoList;

@Service
public class VacationRequestEpoMapper extends GenericMapper<VacationRequest, VacationRequestEpo> {
   
   @Autowired
   private StatusEpoMapper sm;
   
   @Override
   public VacationRequest toInternal(VacationRequestEpo epo) {
      return new VacationRequest(epo.getId(), epo.getUser_id(), epo.getStart_date(), epo.getEnd_date(),
         epo.getComments(), epo.getPicture(), sm.toInternal(epo.getStatus_id()));
   }
   
   @Override
   public VacationRequestEpo toExternal(VacationRequest model) {
      return new VacationRequestEpo(model.getId(), model.getUser_id(), model.getStart_date(), model.getEnd_date(),
         model.getComments(), model.getPicture(), sm.toExternal(model.getStatus_id()));
   }
   
   public VacationRequestEpoList toFronts(List<VacationRequest> vys) {
      List<VacationRequestEpo> vacays = new ArrayList<VacationRequestEpo>();
      for (int i = 0; i < vys.size(); i++) {
         vacays.add(this.toExternal(vys.get(i)));
      }
      return new VacationRequestEpoList(vacays);
   }
   
}
