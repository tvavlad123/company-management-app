package com.mhp.usermicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.usermicro.epo.UserSkillEpo;

public class SkillEpoList implements Serializable {
   
   private static final long  serialVersionUID = 2165002638743247375L;
   
   private List<UserSkillEpo> epoList;
   
   private SkillEpoList() {}
   
   public SkillEpoList(List<UserSkillEpo> epoList) {
      this.epoList = epoList;
   }
   
   public List<UserSkillEpo> getEpoList() {
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
      SkillEpoList other = (SkillEpoList)obj;
      if (epoList == null) {
         if (other.epoList != null)
            return false;
      } else if (!epoList.equals(other.epoList))
         return false;
      return true;
   }
   
}
