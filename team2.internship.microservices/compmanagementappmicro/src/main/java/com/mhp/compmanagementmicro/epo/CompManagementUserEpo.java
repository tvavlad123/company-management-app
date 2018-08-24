package com.mhp.compmanagementmicro.epo;

import java.io.Serializable;
import java.util.Date;

import com.mhp.compmanagementmicro.epo.list.CompManagementLanguageEpoList;
import com.mhp.compmanagementmicro.epo.list.CompManagementSkillEpoList;

public class CompManagementUserEpo implements Serializable {
   
   private static final long             serialVersionUID = 3113994949122375498L;
   
   private long                          id;
   private String                        firstName;
   private String                        lastName;
   private String                        userName;
   private String                        password;
   private CompManagementAuthorityEpo    role;
   private String                        photoLocation;
   private CompManagementPositionEpo     position;
   private CompManagementTeamEpo         team;
   private CompManagementSkillEpoList    userSkills;
   private CompManagementLanguageEpoList userLanguages;
   private Boolean                       enabled;
   private Date                          lastPasswordResetDate;
   
   private CompManagementUserEpo() {}
   
   public CompManagementUserEpo(long id, String firstName, String lastName, String userName, String password,
                                CompManagementAuthorityEpo role, String photoLocation,
                                CompManagementPositionEpo position, CompManagementTeamEpo team,
                                CompManagementSkillEpoList userSkills, CompManagementLanguageEpoList userLanguages,
                                Boolean enabled, Date lastPasswordResetDate) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.userName = userName;
      this.password = password;
      this.role = role;
      this.photoLocation = photoLocation;
      this.position = position;
      this.team = team;
      this.userSkills = userSkills;
      this.userLanguages = userLanguages;
      this.enabled = enabled;
      this.lastPasswordResetDate = lastPasswordResetDate;
   }
   
   public long getId() {
      return id;
   }
   
   public String getFirstName() {
      return firstName;
   }
   
   public String getLastName() {
      return lastName;
   }
   
   public String getUserName() {
      return userName;
   }
   
   public String getPassword() {
      return password;
   }
   
   public CompManagementAuthorityEpo getRole() {
      return role;
   }
   
   public String getPhotoLocation() {
      return photoLocation;
   }
   
   public CompManagementPositionEpo getPosition() {
      return position;
   }
   
   public CompManagementTeamEpo getTeam() {
      return team;
   }
   
   public CompManagementSkillEpoList getUserSkills() {
      return userSkills;
   }
   
   public CompManagementLanguageEpoList getUserLanguages() {
      return userLanguages;
   }
   
   public Boolean getEnabled() {
      return enabled;
   }
   
   public Date getLastPasswordResetDate() {
      return lastPasswordResetDate;
   }
   
   @Override
   public String toString() {
      return "CompManagementUserEpo [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
             + userName + ", password=" + password + ", role=" + role + ", photoLocation=" + photoLocation
             + ", position=" + position + ", team=" + team + ", userSkills=" + userSkills + ", userLanguages="
             + userLanguages + ", enabled=" + enabled + ", lastPasswordResetDate=" + lastPasswordResetDate + "]";
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
      result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
      result = prime * result + (int)(id ^ (id >>> 32));
      result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
      result = prime * result + ((lastPasswordResetDate == null) ? 0 : lastPasswordResetDate.hashCode());
      result = prime * result + ((password == null) ? 0 : password.hashCode());
      result = prime * result + ((photoLocation == null) ? 0 : photoLocation.hashCode());
      result = prime * result + ((position == null) ? 0 : position.hashCode());
      result = prime * result + ((role == null) ? 0 : role.hashCode());
      result = prime * result + ((team == null) ? 0 : team.hashCode());
      result = prime * result + ((userLanguages == null) ? 0 : userLanguages.hashCode());
      result = prime * result + ((userName == null) ? 0 : userName.hashCode());
      result = prime * result + ((userSkills == null) ? 0 : userSkills.hashCode());
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
      CompManagementUserEpo other = (CompManagementUserEpo)obj;
      if (enabled == null) {
         if (other.enabled != null)
            return false;
      } else if (!enabled.equals(other.enabled))
         return false;
      if (firstName == null) {
         if (other.firstName != null)
            return false;
      } else if (!firstName.equals(other.firstName))
         return false;
      if (id != other.id)
         return false;
      if (lastName == null) {
         if (other.lastName != null)
            return false;
      } else if (!lastName.equals(other.lastName))
         return false;
      if (lastPasswordResetDate == null) {
         if (other.lastPasswordResetDate != null)
            return false;
      } else if (!lastPasswordResetDate.equals(other.lastPasswordResetDate))
         return false;
      if (password == null) {
         if (other.password != null)
            return false;
      } else if (!password.equals(other.password))
         return false;
      if (photoLocation == null) {
         if (other.photoLocation != null)
            return false;
      } else if (!photoLocation.equals(other.photoLocation))
         return false;
      if (position == null) {
         if (other.position != null)
            return false;
      } else if (!position.equals(other.position))
         return false;
      if (role == null) {
         if (other.role != null)
            return false;
      } else if (!role.equals(other.role))
         return false;
      if (team == null) {
         if (other.team != null)
            return false;
      } else if (!team.equals(other.team))
         return false;
      if (userLanguages == null) {
         if (other.userLanguages != null)
            return false;
      } else if (!userLanguages.equals(other.userLanguages))
         return false;
      if (userName == null) {
         if (other.userName != null)
            return false;
      } else if (!userName.equals(other.userName))
         return false;
      if (userSkills == null) {
         if (other.userSkills != null)
            return false;
      } else if (!userSkills.equals(other.userSkills))
         return false;
      return true;
   }
   
}
