package com.mhp.requestmicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.requestmicro.epo.VacationRequestEpo;

public class VacationRequestEpoList implements Serializable {
   
   private static final long        serialVersionUID = 2759358606677098907L;
   
   private List<VacationRequestEpo> vacationreqepolist;
   
   private VacationRequestEpoList() {}
   
   public VacationRequestEpoList(List<VacationRequestEpo> vacationreqepolist) {
      this.vacationreqepolist = vacationreqepolist;
   }
   
   public List<VacationRequestEpo> getVacationreqepolist() {
      return vacationreqepolist;
   }
   
   @Override
   public String toString() {
      return "VacationRequestEpoList [vacationreqepolist=" + vacationreqepolist + "]";
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((vacationreqepolist == null) ? 0 : vacationreqepolist.hashCode());
      return result;
   }
   
   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      VacationRequestEpoList other = (VacationRequestEpoList)obj;
      if (vacationreqepolist == null) {
         if (other.vacationreqepolist != null)
            return false;
      } else if (!vacationreqepolist.equals(other.vacationreqepolist))
         return false;
      return true;
   }
   
}
