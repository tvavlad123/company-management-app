package com.mhp.requestmicro.mapper;

import org.springframework.stereotype.Service;

import com.mhp.requestmicro.entity.Status;
import com.mhp.requestmicro.epo.StatusEpo;

@Service
public class StatusEpoMapper extends GenericMapper<Status, StatusEpo> {
   
   public Status toInternal(StatusEpo epo) {
      return new Status(epo.getId(), epo.getName());
   }
   
   public StatusEpo toExternal(Status model) {
      return new StatusEpo(model.getId(), model.getName());
   }
   
}
