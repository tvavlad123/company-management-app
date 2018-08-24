package com.mhp.compmanagementmicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.compmanagementmicro.epo.CompManagementDepartmentEpo;

public class CompManagementDepartmentEpoList implements Serializable {
   
   private static final long                 serialVersionUID = 6433584504648297571L;
   private List<CompManagementDepartmentEpo> departmentEpoList;
   
   private CompManagementDepartmentEpoList() {}
   
   public CompManagementDepartmentEpoList(List<CompManagementDepartmentEpo> depts) {
      this.departmentEpoList = depts;
   }
   
   public List<CompManagementDepartmentEpo> getDepartmentEpoList() {
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
      CompManagementDepartmentEpoList other = (CompManagementDepartmentEpoList)obj;
      if (departmentEpoList == null) {
         if (other.departmentEpoList != null)
            return false;
      } else if (!departmentEpoList.equals(other.departmentEpoList))
         return false;
      return true;
   }
   
}
