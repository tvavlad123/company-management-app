package com.mhp.compmanagementmicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.compmanagementmicro.epo.CompManagementAuthorityEpo;

public class CompManagementAuthorityEpoList implements Serializable {
   
   private static final long   serialVersionUID = -3406666756938946671L;
   
   List<CompManagementAuthorityEpo> roleEpoList;
   
   private CompManagementAuthorityEpoList() {}
   
   public CompManagementAuthorityEpoList(List<CompManagementAuthorityEpo> roleEpoList) {
      this.roleEpoList = roleEpoList;
   }
   
   public List<CompManagementAuthorityEpo> getRoleEpoList() {
      return roleEpoList;
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((roleEpoList == null) ? 0 : roleEpoList.hashCode());
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
      CompManagementAuthorityEpoList other = (CompManagementAuthorityEpoList)obj;
      if (roleEpoList == null) {
         if (other.roleEpoList != null)
            return false;
      } else if (!roleEpoList.equals(other.roleEpoList))
         return false;
      return true;
   }
   
}
