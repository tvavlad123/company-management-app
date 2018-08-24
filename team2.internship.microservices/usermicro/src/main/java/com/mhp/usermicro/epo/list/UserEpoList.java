package com.mhp.usermicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.usermicro.epo.UserEpo;

public class UserEpoList implements Serializable {
   
   private static final long serialVersionUID = -7852584946776278954L;
   
   private List<UserEpo>     epoList;
   
   private UserEpoList() {}
   
   public UserEpoList(List<UserEpo> epoList) {
      this.epoList = epoList;
   }
   
   public List<UserEpo> getEpoList() {
      return epoList;
   }
   
   @Override
   public String toString() {
      return "UserEpoList{" + "epoList=" + epoList + '}';
   }
   
   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      
      UserEpoList that = (UserEpoList)o;
      
      return epoList != null ? epoList.equals(that.epoList) : that.epoList == null;
   }
   
   @Override
   public int hashCode() {
      return epoList != null ? epoList.hashCode() : 0;
   }
}
