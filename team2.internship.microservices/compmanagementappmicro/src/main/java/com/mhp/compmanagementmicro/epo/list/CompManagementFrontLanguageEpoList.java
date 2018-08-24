package com.mhp.compmanagementmicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.compmanagementmicro.epo.CompManagementFrontLanguageEpo;

public class CompManagementFrontLanguageEpoList implements Serializable {
   
   private static final long                    serialVersionUID = 1937888832926370939L;
   
   private List<CompManagementFrontLanguageEpo> languageEpoList;
   
   private CompManagementFrontLanguageEpoList() {}
   
   public CompManagementFrontLanguageEpoList(List<CompManagementFrontLanguageEpo> epoList) {
      this.languageEpoList = epoList;
   }
   
   public List<CompManagementFrontLanguageEpo> getLanguageEpoList() {
      return languageEpoList;
   }
   
   @Override
   public String toString() {
      return "FrontLanguageEpoList [languageEpoList=" + languageEpoList + "]";
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((languageEpoList == null) ? 0 : languageEpoList.hashCode());
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
      CompManagementFrontLanguageEpoList other = (CompManagementFrontLanguageEpoList)obj;
      if (languageEpoList == null) {
         if (other.languageEpoList != null)
            return false;
      } else if (!languageEpoList.equals(other.languageEpoList))
         return false;
      return true;
   }
   
}
