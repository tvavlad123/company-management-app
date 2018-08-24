package com.mhp.usermicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.usermicro.epo.RoleEpo;

public class RoleEpoList implements Serializable {
   
   /**
    * 
    */
   private static final long serialVersionUID = 5356763570908296023L;
   List<RoleEpo>             roleEpoList;
   
   private RoleEpoList() {}
   
   public RoleEpoList(List<RoleEpo> roleEpoList) {
      this.roleEpoList = roleEpoList;
   }
   
   public List<RoleEpo> getRoleEpoList() {
      return roleEpoList;
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((roleEpoList == null) ? 0 : roleEpoList.hashCode());
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
      RoleEpoList other = (RoleEpoList)obj;
      if (roleEpoList == null) {
         if (other.roleEpoList != null)
            return false;
      } else if (!roleEpoList.equals(other.roleEpoList))
         return false;
      return true;
   }
   
}
