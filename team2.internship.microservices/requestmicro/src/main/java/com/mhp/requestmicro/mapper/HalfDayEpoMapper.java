package com.mhp.requestmicro.mapper;

import org.springframework.stereotype.Service;

import com.mhp.requestmicro.entity.HalfDay;
import com.mhp.requestmicro.epo.HalfDayEpo;

@Service
public class HalfDayEpoMapper extends GenericMapper<HalfDay, HalfDayEpo> {
   
   public HalfDay toInternal(HalfDayEpo epo) {
      return new HalfDay(epo.getId(), epo.getType());
   }
   
   public HalfDayEpo toExternal(HalfDay model) {
      return new HalfDayEpo(model.getId(), model.getType());
   }
   
}
