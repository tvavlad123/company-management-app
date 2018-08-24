package com.mhp.compmanagementmicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.compmanagementmicro.epo.CompManagementLanguageLevelEpo;

public class CompManagementLanguageLevelEpoList implements Serializable {
   
   private static final long                    serialVersionUID = -7378538384519532575L;
   
   private List<CompManagementLanguageLevelEpo> epos;
   
   private CompManagementLanguageLevelEpoList() {}
   
   public CompManagementLanguageLevelEpoList(List<CompManagementLanguageLevelEpo> epos) {
      this.epos = epos;
   }
   
   public List<CompManagementLanguageLevelEpo> getEpos() {
      return epos;
   }
   
   @Override
   public String toString() {
      return "UserLanguageLevelEpoList{" + "epos=" + epos + '}';
   }
   
   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      
      CompManagementLanguageLevelEpoList that = (CompManagementLanguageLevelEpoList)o;
      
      return epos != null ? epos.equals(that.epos) : that.epos == null;
   }
   
   @Override
   public int hashCode() {
      return epos != null ? epos.hashCode() : 0;
   }
   
}
