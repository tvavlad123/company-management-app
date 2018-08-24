package com.mhp.requestmicro.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhp.requestmicro.entity.HomeOfficeRequest;
import com.mhp.requestmicro.epo.HomeOfficeRequestEpo;
import com.mhp.requestmicro.epo.list.HomeOfficeRequestEpoList;

@Service
public class HomeOfficeRequestEpoMapper extends GenericMapper<HomeOfficeRequest, HomeOfficeRequestEpo> {
   
   @Autowired
   private StatusEpoMapper  sm;
   @Autowired
   private HalfDayEpoMapper hdm;
   
   @Override
   public HomeOfficeRequest toInternal(HomeOfficeRequestEpo epo) {
      return new HomeOfficeRequest(epo.getId(), epo.getUser_id(), epo.getStart_date(), epo.getEnd_date(),
         hdm.toInternal(epo.getHalf_day()), sm.toInternal(epo.getStatus_id()));
   }
   
   @Override
   public HomeOfficeRequestEpo toExternal(HomeOfficeRequest model) {
      return new HomeOfficeRequestEpo(model.getId(), model.getUser_id(), model.getStart_date(), model.getEnd_date(),
         hdm.toExternal(model.getHalf_day()), sm.toExternal(model.getStatus_id()));
   }
   
   public HomeOfficeRequestEpoList toFronts(List<HomeOfficeRequest> hrs) {
      List<HomeOfficeRequestEpo> homereqs = new ArrayList<HomeOfficeRequestEpo>();
      for (int i = 0; i < hrs.size(); i++) {
         homereqs.add(this.toExternal(hrs.get(i)));
      }
      return new HomeOfficeRequestEpoList(homereqs);
   }
   
}
