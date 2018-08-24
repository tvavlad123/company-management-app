package com.mhp.requestmicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.requestmicro.epo.StatusEpo;

public class StatusEpoList implements Serializable {
   
   private static final long serialVersionUID = 4809165512516261330L;
   
   private List<StatusEpo>   statusepolist;
   
   private StatusEpoList() {}
   
   public StatusEpoList(List<StatusEpo> statusepolist) {
      this.statusepolist = statusepolist;
   }
   
   public List<StatusEpo> getStatusepolist() {
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
      StatusEpoList other = (StatusEpoList)obj;
      if (statusepolist == null) {
         if (other.statusepolist != null)
            return false;
      } else if (!statusepolist.equals(other.statusepolist))
         return false;
      return true;
   }
   
}
