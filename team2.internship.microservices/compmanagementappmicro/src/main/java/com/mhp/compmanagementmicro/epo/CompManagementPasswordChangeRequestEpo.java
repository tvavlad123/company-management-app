package com.mhp.compmanagementmicro.epo;

import java.io.Serializable;

public class CompManagementPasswordChangeRequestEpo implements Serializable {
   
   private static final long serialVersionUID = -123883059624443982L;
   
   private long              userId;
   private String            currentPassword;
   private String            newPassword;
   
   private CompManagementPasswordChangeRequestEpo() {}
   
   private CompManagementPasswordChangeRequestEpo(long userId, String currentPassword, String newPassword) {
      this.userId = userId;
      this.currentPassword = currentPassword;
      this.newPassword = newPassword;
   }
   
   public long getUserId() {
      return userId;
   }
   
   public String getCurrentPassword() {
      return currentPassword;
   }
   
   public String getNewPassword() {
      return newPassword;
   }
   
   @Override
   public String toString() {
      return "PasswordChangeRequest [userId=" + userId + ", currentPassword=" + currentPassword + ", newPassword="
             + newPassword + "]";
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((currentPassword == null) ? 0 : currentPassword.hashCode());
      result = prime * result + ((newPassword == null) ? 0 : newPassword.hashCode());
      result = prime * result + (int)(userId ^ (userId >>> 32));
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
      CompManagementPasswordChangeRequestEpo other = (CompManagementPasswordChangeRequestEpo)obj;
      if (currentPassword == null) {
         if (other.currentPassword != null)
            return false;
      } else if (!currentPassword.equals(other.currentPassword))
         return false;
      if (newPassword == null) {
         if (other.newPassword != null)
            return false;
      } else if (!newPassword.equals(other.newPassword))
         return false;
      if (userId != other.userId)
         return false;
      return true;
   }
   
}
