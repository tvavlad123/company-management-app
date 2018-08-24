package com.mhp.compmanagementmicro.epo;

import java.io.Serializable;

public class CompManagementTeamEpo implements Serializable {
   
   private static final long           serialVersionUID = -3871259960749977548L;
   
   private long                        id;
   private String                      name;
   private CompManagementDepartmentEpo department;
   
   private CompManagementTeamEpo() {}
   
   public CompManagementTeamEpo(long id, String name, CompManagementDepartmentEpo department) {
      this.id = id;
      this.name = name;
      this.department = department;
   }
   
   public long getId() {
      return id;
   }
   
   public String getName() {
      return name;
   }
   
   public CompManagementDepartmentEpo getDepartment() {
      return department;
   }
   
   @Override
   public String toString() {
      return "TeamEpo{" + "id=" + id + ", name='" + name + '\'' + ", department=" + department + '}';
   }
   
   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      
      CompManagementTeamEpo teamEpo = (CompManagementTeamEpo)o;
      
      if (id != teamEpo.id)
         return false;
      if (name != null ? !name.equals(teamEpo.name) : teamEpo.name != null)
         return false;
      return department != null ? department.equals(teamEpo.department) : teamEpo.department == null;
   }
   
   @Override
   public int hashCode() {
      int result = (int)(id ^ (id >>> 32));
      result = 31 * result + (name != null ? name.hashCode() : 0);
      result = 31 * result + (department != null ? department.hashCode() : 0);
      return result;
   }
   
}
