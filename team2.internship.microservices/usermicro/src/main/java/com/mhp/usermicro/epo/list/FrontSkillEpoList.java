package com.mhp.usermicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.usermicro.epo.FrontSkillEpo;

public class FrontSkillEpoList implements Serializable {
   
   /**
    * 
    */
   private static final long   serialVersionUID = 8407703227000461134L;
   
   private List<FrontSkillEpo> skillEpoList;
   
   private FrontSkillEpoList() {}
   
   public FrontSkillEpoList(List<FrontSkillEpo> epos) {
      this.skillEpoList = epos;
   }
   
   public List<FrontSkillEpo> getSkillEpoList() {
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
      FrontSkillEpoList other = (FrontSkillEpoList)obj;
      if (skillEpoList == null) {
         if (other.skillEpoList != null)
            return false;
      } else if (!skillEpoList.equals(other.skillEpoList))
         return false;
      return true;
   }
   
}
