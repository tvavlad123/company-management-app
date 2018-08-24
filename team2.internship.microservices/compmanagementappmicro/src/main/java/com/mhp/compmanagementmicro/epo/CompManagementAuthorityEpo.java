package com.mhp.compmanagementmicro.epo;

import java.io.Serializable;

public class CompManagementAuthorityEpo implements Serializable {
   
   private static final long serialVersionUID = -673655925345897028L;
   
   private long              id;
   private String            authorityType;
   
   private CompManagementAuthorityEpo() {}
   
   public CompManagementAuthorityEpo(long id, String authorityType) {
      this.id = id;
      this.authorityType = authorityType;
   }
   
   public long getId() {
      return id;
   }
   
   public String getAuthorityType() {
      return authorityType;
   }
   
   @Override
   public String toString() {
      return "AuthorityEpo{" + "id=" + id + ", authorityType=" + authorityType + '}';
   }
   
   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      
      CompManagementAuthorityEpo that = (CompManagementAuthorityEpo)o;
      
      if (id != that.id)
         return false;
      return authorityType == that.authorityType;
   }
   
   @Override
   public int hashCode() {
      int result = (int)(id ^ (id >>> 32));
      result = 31 * result + (authorityType != null ? authorityType.hashCode() : 0);
      return result;
   }
   
}
