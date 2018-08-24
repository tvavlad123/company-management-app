package com.mhp.usermicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.usermicro.epo.UserLanguageEpo;

public class LanguageEpoList implements Serializable {
   
   private static final long     serialVersionUID = -3831578577069858527L;
   
   private List<UserLanguageEpo> epos;
   
   private LanguageEpoList() {}
   
   public LanguageEpoList(List<UserLanguageEpo> epos) {
      this.epos = epos;
   }
   
   public List<UserLanguageEpo> getEpos() {
      return epos;
   }
   
   @Override
   public String toString() {
      return "UserLanguageEpoList{" + "epos=" + epos + '}';
   }
   
   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      
      LanguageEpoList that = (LanguageEpoList)o;
      
      return epos != null ? epos.equals(that.epos) : that.epos == null;
   }
   
   @Override
   public int hashCode() {
      return epos != null ? epos.hashCode() : 0;
   }
}
