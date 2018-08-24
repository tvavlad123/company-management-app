package com.mhp.compmanagementmicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.compmanagementmicro.epo.CompManagementSkillEpo;
import com.mhp.compmanagementmicro.epo.CompManagementUserEpo;
import com.mhp.compmanagementmicro.epo.CompManagementUserSkillEpo;

public class CompManagementSkillEpoList implements Serializable {
   
   private static final long            serialVersionUID = -2628339963410637902L;
   
   private List<CompManagementUserSkillEpo> epoList;
   
   private CompManagementSkillEpoList() {}

   public CompManagementSkillEpoList(List<CompManagementUserSkillEpo> epoList) {
      this.epoList = epoList;
   }

   public List<CompManagementUserSkillEpo> getEpoList() {
      return epoList;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      CompManagementSkillEpoList that = (CompManagementSkillEpoList) o;

      return epoList != null ? epoList.equals(that.epoList) : that.epoList == null;
   }

   @Override
   public int hashCode() {
      return epoList != null ? epoList.hashCode() : 0;
   }
}
