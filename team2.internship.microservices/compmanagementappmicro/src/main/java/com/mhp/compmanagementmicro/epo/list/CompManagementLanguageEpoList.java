package com.mhp.compmanagementmicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.compmanagementmicro.epo.CompManagementLanguageEpo;
import com.mhp.compmanagementmicro.epo.CompManagementUserLanguageEpo;

public class CompManagementLanguageEpoList implements Serializable {
   
   private static final long               serialVersionUID = 5473867246117565315L;
   
   private List<CompManagementUserLanguageEpo> epos;
   
   private CompManagementLanguageEpoList() {}

   public CompManagementLanguageEpoList(List<CompManagementUserLanguageEpo> epos) {
      this.epos = epos;
   }

   public List<CompManagementUserLanguageEpo> getEpos() {
      return epos;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      CompManagementLanguageEpoList that = (CompManagementLanguageEpoList) o;

      return epos != null ? epos.equals(that.epos) : that.epos == null;
   }

   @Override
   public int hashCode() {
      return epos != null ? epos.hashCode() : 0;
   }
}
