package com.mhp.compmanagementmicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.compmanagementmicro.epo.CompManagementPositionEpo;

public class CompManagementPositionEpoList implements Serializable {
   
   private static final long       serialVersionUID = 3313249468155145481L;
   
   List<CompManagementPositionEpo> epos;
   
   private CompManagementPositionEpoList() {}
   
   public CompManagementPositionEpoList(List<CompManagementPositionEpo> pos) {
      this.epos = pos;
   }
   
   public List<CompManagementPositionEpo> getEpos() {
      return epos;
   }
   
   @Override
   public String toString() {
      return "PositionEpoList [epos=" + epos + "]";
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((epos == null) ? 0 : epos.hashCode());
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
      CompManagementPositionEpoList other = (CompManagementPositionEpoList)obj;
      if (epos == null) {
         if (other.epos != null)
            return false;
      } else if (!epos.equals(other.epos))
         return false;
      return true;
   }
   
}
