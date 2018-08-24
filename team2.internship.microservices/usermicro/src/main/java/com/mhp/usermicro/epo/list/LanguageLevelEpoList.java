package com.mhp.usermicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.usermicro.epo.LanguageLevelEpo;

public class LanguageLevelEpoList implements Serializable {
   
   private static final long      serialVersionUID = -8917876405162524990L;
   
   private List<LanguageLevelEpo> epos;
   
   private LanguageLevelEpoList() {}
   
   public LanguageLevelEpoList(List<LanguageLevelEpo> epos) {
      this.epos = epos;
   }
   
   public List<LanguageLevelEpo> getEpos() {
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
      
      LanguageLevelEpoList that = (LanguageLevelEpoList)o;
      
      return epos != null ? epos.equals(that.epos) : that.epos == null;
   }
   
   @Override
   public int hashCode() {
      return epos != null ? epos.hashCode() : 0;
   }
}
