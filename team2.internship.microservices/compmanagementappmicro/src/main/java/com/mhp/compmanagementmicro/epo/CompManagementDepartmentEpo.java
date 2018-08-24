package com.mhp.compmanagementmicro.epo;

import java.io.Serializable;

public class CompManagementDepartmentEpo implements Serializable {
   
   private static final long serialVersionUID = -1302737631830394264L;
   
   private long              id;
   private String            name;
   
   private CompManagementDepartmentEpo() {}
   
   public CompManagementDepartmentEpo(long id, String name) {
      this.id = id;
      this.name = name;
   }
   
   public long getId() {
      return id;
   }
   
   public String getName() {
      return name;
   }
   
   @Override
   public String toString() {
      return "DepartmentEpo{" + "id=" + id + ", name='" + name + '\'' + '}';
   }
   
   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      
      CompManagementDepartmentEpo that = (CompManagementDepartmentEpo)o;
      
      if (id != that.id)
         return false;
      return name != null ? name.equals(that.name) : that.name == null;
   }
   
   @Override
   public int hashCode() {
      int result = (int)(id ^ (id >>> 32));
      result = 31 * result + (name != null ? name.hashCode() : 0);
      return result;
   }
}
