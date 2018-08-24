package com.mhp.compmanagementmicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.compmanagementmicro.epo.CompManagementVacationRequestEpo;

public class CompManagementVacationRequestEpoList implements Serializable {
   
   private static final long                      serialVersionUID = -3041370580728851044L;
   
   private List<CompManagementVacationRequestEpo> vacationreqepolist;
   
   private CompManagementVacationRequestEpoList() {}
   
   public CompManagementVacationRequestEpoList(List<CompManagementVacationRequestEpo> vacationreqepolist) {
      this.vacationreqepolist = vacationreqepolist;
   }
   
   public List<CompManagementVacationRequestEpo> getVacationreqepolist() {
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
      CompManagementVacationRequestEpoList other = (CompManagementVacationRequestEpoList)obj;
      if (vacationreqepolist == null) {
         if (other.vacationreqepolist != null)
            return false;
      } else if (!vacationreqepolist.equals(other.vacationreqepolist))
         return false;
      return true;
   }
   
}
