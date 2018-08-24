package com.mhp.compmanagementmicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.compmanagementmicro.epo.CompManagementUserEpo;

public class CompManagementUserEpoList implements Serializable {
   
   private static final long           serialVersionUID = 5808170129057967384L;
   
   private List<CompManagementUserEpo> epoList;
   
   private CompManagementUserEpoList() {}
   
   public CompManagementUserEpoList(List<CompManagementUserEpo> epoList) {
      this.epoList = epoList;
   }
   
   @Override
   public String toString() {
      return "UserEpoList{" + "epoList=" + epoList + '}';
   }
   
   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      
      CompManagementUserEpoList that = (CompManagementUserEpoList)o;
      
      return epoList != null ? epoList.equals(that.epoList) : that.epoList == null;
   }
   
   @Override
   public int hashCode() {
      return epoList != null ? epoList.hashCode() : 0;
   }
   
}
