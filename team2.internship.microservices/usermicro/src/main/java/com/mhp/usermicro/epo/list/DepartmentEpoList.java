package com.mhp.usermicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.usermicro.epo.DepartmentEpo;

public class DepartmentEpoList implements Serializable {
   
   private static final long   serialVersionUID = -7873506065335028044L;
   private List<DepartmentEpo> departmentEpoList;
   
   private DepartmentEpoList() {}
   
   public DepartmentEpoList(List<DepartmentEpo> depts) {
      this.departmentEpoList = depts;
   }
   
   public List<DepartmentEpo> getDepartmentEpoList() {
      return departmentEpoList;
   }
   
   @Override
   public String toString() {
      return "DepartmentEpoList [departmentEpoList=" + departmentEpoList + "]";
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((departmentEpoList == null) ? 0 : departmentEpoList.hashCode());
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
      DepartmentEpoList other = (DepartmentEpoList)obj;
      if (departmentEpoList == null) {
         if (other.departmentEpoList != null)
            return false;
      } else if (!departmentEpoList.equals(other.departmentEpoList))
         return false;
      return true;
   }
   
}
