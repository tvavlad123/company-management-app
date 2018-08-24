package com.mhp.requestmicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.requestmicro.epo.HalfDayEpo;

public class HalfDayEpoList implements Serializable {
   
   private static final long serialVersionUID = 1591636006632075468L;
   
   private List<HalfDayEpo>  halfdayepolist;
   
   private HalfDayEpoList() {}
   
   public HalfDayEpoList(List<HalfDayEpo> halfdayepolist) {
      this.halfdayepolist = halfdayepolist;
   }
   
   public List<HalfDayEpo> getHalfdayepolist() {
      return halfdayepolist;
   }
   
   @Override
   public String toString() {
      return "HalfDayEpoList [halfdayepolist=" + halfdayepolist + "]";
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((halfdayepolist == null) ? 0 : halfdayepolist.hashCode());
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
      HalfDayEpoList other = (HalfDayEpoList)obj;
      if (halfdayepolist == null) {
         if (other.halfdayepolist != null)
            return false;
      } else if (!halfdayepolist.equals(other.halfdayepolist))
         return false;
      return true;
   }
   
}
