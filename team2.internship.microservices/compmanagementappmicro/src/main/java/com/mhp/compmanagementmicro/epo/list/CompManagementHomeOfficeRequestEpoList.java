package com.mhp.compmanagementmicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.compmanagementmicro.epo.CompManagementHomeOfficeRequestEpo;

public class CompManagementHomeOfficeRequestEpoList implements Serializable {
   
   private static final long                        serialVersionUID = -3980534688103100732L;
   
   private List<CompManagementHomeOfficeRequestEpo> homereqepolist;
   
   private CompManagementHomeOfficeRequestEpoList() {}
   
   public CompManagementHomeOfficeRequestEpoList(List<CompManagementHomeOfficeRequestEpo> homereqepolist) {
      this.homereqepolist = homereqepolist;
   }
   
   public List<CompManagementHomeOfficeRequestEpo> getHomereqepolist() {
      return homereqepolist;
   }
   
   @Override
   public String toString() {
      return "HomeOfficeRequestEpoList [homereqepolist=" + homereqepolist + "]";
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((homereqepolist == null) ? 0 : homereqepolist.hashCode());
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
      CompManagementHomeOfficeRequestEpoList other = (CompManagementHomeOfficeRequestEpoList)obj;
      if (homereqepolist == null) {
         if (other.homereqepolist != null)
            return false;
      } else if (!homereqepolist.equals(other.homereqepolist))
         return false;
      return true;
   }
   
}
