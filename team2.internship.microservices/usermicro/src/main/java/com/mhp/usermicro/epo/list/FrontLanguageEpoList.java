package com.mhp.usermicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.usermicro.epo.FrontLanguageEpo;

public class FrontLanguageEpoList implements Serializable {
   
   /**
    * 
    */
   private static final long      serialVersionUID = -2566895051045109134L;
   
   private List<FrontLanguageEpo> languageEpoList;
   
   private FrontLanguageEpoList() {}
   
   public FrontLanguageEpoList(List<FrontLanguageEpo> epoList) {
      this.languageEpoList = epoList;
   }
   
   public List<FrontLanguageEpo> getLanguageEpoList() {
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
      FrontLanguageEpoList other = (FrontLanguageEpoList)obj;
      if (languageEpoList == null) {
         if (other.languageEpoList != null)
            return false;
      } else if (!languageEpoList.equals(other.languageEpoList))
         return false;
      return true;
   }
   
}
