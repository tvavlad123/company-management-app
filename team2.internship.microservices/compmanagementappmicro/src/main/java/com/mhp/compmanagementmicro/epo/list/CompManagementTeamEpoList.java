package com.mhp.compmanagementmicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.compmanagementmicro.epo.CompManagementTeamEpo;

public class CompManagementTeamEpoList implements Serializable {
   
   private static final long   serialVersionUID = -2878094369582755251L;
   
   List<CompManagementTeamEpo> teamEpoList;
   
   private CompManagementTeamEpoList() {}
   
   public CompManagementTeamEpoList(List<CompManagementTeamEpo> epoList) {
      this.teamEpoList = epoList;
   }
   
   public List<CompManagementTeamEpo> getTeamEpoList() {
      return teamEpoList;
   }
   
   @Override
   public String toString() {
      return "TeamEpoList{" + "epoList=" + teamEpoList + '}';
   }
   
   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      
      CompManagementTeamEpoList that = (CompManagementTeamEpoList)o;
      
      return teamEpoList != null ? teamEpoList.equals(that.teamEpoList) : that.teamEpoList == null;
   }
   
   @Override
   public int hashCode() {
      return teamEpoList != null ? teamEpoList.hashCode() : 0;
   }
   
}
