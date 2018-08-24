package com.mhp.compmanagementmicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.compmanagementmicro.epo.CompManagementFrontSkillEpo;

public class CompManagementFrontSkillEpoList implements Serializable {
   
   private static final long                 serialVersionUID = 7085695025495982787L;
   private List<CompManagementFrontSkillEpo> skillEpoList;
   
   private CompManagementFrontSkillEpoList() {}
   
   public CompManagementFrontSkillEpoList(List<CompManagementFrontSkillEpo> epos) {
      this.skillEpoList = epos;
   }
   
   public List<CompManagementFrontSkillEpo> getSkillEpoList() {
      return skillEpoList;
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((skillEpoList == null) ? 0 : skillEpoList.hashCode());
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
      CompManagementFrontSkillEpoList other = (CompManagementFrontSkillEpoList)obj;
      if (skillEpoList == null) {
         if (other.skillEpoList != null)
            return false;
      } else if (!skillEpoList.equals(other.skillEpoList))
         return false;
      return true;
   }
   
}
