package com.mhp.compmanagementmicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.compmanagementmicro.epo.CompManagementStatusEpo;

public class CompManagementStatusEpoList implements Serializable {
   
   private static final long             serialVersionUID = 2381872445731393213L;
   
   private List<CompManagementStatusEpo> statusepolist;
   
   private CompManagementStatusEpoList() {}
   
   public CompManagementStatusEpoList(List<CompManagementStatusEpo> statusepolist) {
      this.statusepolist = statusepolist;
   }
   
   public List<CompManagementStatusEpo> getStatusepolist() {
      return statusepolist;
   }
   
   @Override
   public String toString() {
      return "StatusEpoList [statusepolist=" + statusepolist + "]";
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((statusepolist == null) ? 0 : statusepolist.hashCode());
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
      CompManagementStatusEpoList other = (CompManagementStatusEpoList)obj;
      if (statusepolist == null) {
         if (other.statusepolist != null)
            return false;
      } else if (!statusepolist.equals(other.statusepolist))
         return false;
      return true;
   }
   
}
