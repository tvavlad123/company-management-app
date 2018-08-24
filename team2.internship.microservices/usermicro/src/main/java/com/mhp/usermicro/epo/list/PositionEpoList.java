package com.mhp.usermicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.usermicro.epo.PositionEpo;

public class PositionEpoList implements Serializable {
   
   private static final long serialVersionUID = -142623624414282348L;
   
   List<PositionEpo>         epos;
   
   private PositionEpoList() {}
   
   public PositionEpoList(List<PositionEpo> pos) {
      this.epos = pos;
   }
   
   public List<PositionEpo> getEpos() {
      return epos;
   }
   
   @Override
   public String toString() {
      return "PositionEpoList [epos=" + epos + "]";
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((epos == null) ? 0 : epos.hashCode());
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
      PositionEpoList other = (PositionEpoList)obj;
      if (epos == null) {
         if (other.epos != null)
            return false;
      } else if (!epos.equals(other.epos))
         return false;
      return true;
   }
   
}
