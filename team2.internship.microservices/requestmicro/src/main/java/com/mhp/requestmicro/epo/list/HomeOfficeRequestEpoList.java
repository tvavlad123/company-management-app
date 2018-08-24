package com.mhp.requestmicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.requestmicro.epo.HomeOfficeRequestEpo;

public class HomeOfficeRequestEpoList implements Serializable {
   
   private static final long          serialVersionUID = -3512284027781966356L;
   
   private List<HomeOfficeRequestEpo> homereqepolist;
   
   private HomeOfficeRequestEpoList() {}
   
   public HomeOfficeRequestEpoList(List<HomeOfficeRequestEpo> homereqepolist) {
      this.homereqepolist = homereqepolist;
   }
   
   public List<HomeOfficeRequestEpo> getHomereqepolist() {
      return homereqepolist;
   }
   
   @Override
   public String toString() {
      return "HomeOfficeRequestEpoList [homereqepolist=" + homereqepolist + "]";
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((homereqepolist == null) ? 0 : homereqepolist.hashCode());
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
      HomeOfficeRequestEpoList other = (HomeOfficeRequestEpoList)obj;
      if (homereqepolist == null) {
         if (other.homereqepolist != null)
            return false;
      } else if (!homereqepolist.equals(other.homereqepolist))
         return false;
      return true;
   }
   
}
