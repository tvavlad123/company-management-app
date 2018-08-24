package com.mhp.compmanagementmicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.compmanagementmicro.epo.CompManagementSkillLevelEpo;

public class CompManagementSkillLevelEpoList implements Serializable {
   
   private static final long                 serialVersionUID = -7726999755155227484L;
   
   private List<CompManagementSkillLevelEpo> epoList;
   
   private CompManagementSkillLevelEpoList() {}
   
   public CompManagementSkillLevelEpoList(List<CompManagementSkillLevelEpo> epoList) {
      this.epoList = epoList;
   }
   
   public List<CompManagementSkillLevelEpo> getEpoList() {
      return epoList;
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((epoList == null) ? 0 : epoList.hashCode());
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
      CompManagementSkillLevelEpoList other = (CompManagementSkillLevelEpoList)obj;
      if (epoList == null) {
         if (other.epoList != null)
            return false;
      } else if (!epoList.equals(other.epoList))
         return false;
      return true;
   }
   
}
