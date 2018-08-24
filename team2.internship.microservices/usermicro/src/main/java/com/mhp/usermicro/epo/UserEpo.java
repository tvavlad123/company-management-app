package com.mhp.usermicro.epo;

import java.io.Serializable;
import java.util.Date;

import com.mhp.usermicro.epo.list.LanguageEpoList;
import com.mhp.usermicro.epo.list.SkillEpoList;

public class UserEpo implements Serializable {
   
   private static final long serialVersionUID = 7751436828159478617L;
   
   private long              id;
   private String            firstName;
   private String            lastName;
   private String            userName;
   private String            password;
   private AuthorityEpo      authority;
   private String            photoLocation;
   private PositionEpo       position;
   private TeamEpo           team;
   private SkillEpoList      userSkills;
   private LanguageEpoList   userLanguages;
   private Boolean           enabled;
   private Date              lastPasswordResetDate;
   
   private UserEpo() {}
   
   public UserEpo(long id, String firstName, String lastName, String userName, String password, AuthorityEpo authority,
                  String photoLocation, PositionEpo position, TeamEpo team, SkillEpoList userSkills,
                  LanguageEpoList userLanguages, Boolean enabled, Date lastPasswordResetDate) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.userName = userName;
      this.password = password;
      this.authority = authority;
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
   
   public AuthorityEpo getAuthority() {
      return authority;
   }
   
   public String getPhotoLocation() {
      return photoLocation;
   }
   
   public PositionEpo getPosition() {
      return position;
   }
   
   public TeamEpo getTeam() {
      return team;
   }
   
   public SkillEpoList getUserSkills() {
      return userSkills;
   }
   
   public LanguageEpoList getUserLanguages() {
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
      return "UserEpo{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
             + ", userName='" + userName + '\'' + ", password='" + password + '\'' + ", authority=" + authority
             + ", photoLocation='" + photoLocation + '\'' + ", position=" + position + ", team=" + team
             + ", userSkills=" + userSkills + ", userLanguages=" + userLanguages + ", enabled=" + enabled
             + ", lastPasswordResetDate=" + lastPasswordResetDate + '}';
   }
   
   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      
      UserEpo userEpo = (UserEpo)o;
      
      if (id != userEpo.id)
         return false;
      if (firstName != null ? !firstName.equals(userEpo.firstName) : userEpo.firstName != null)
         return false;
      if (lastName != null ? !lastName.equals(userEpo.lastName) : userEpo.lastName != null)
         return false;
      if (userName != null ? !userName.equals(userEpo.userName) : userEpo.userName != null)
         return false;
      if (password != null ? !password.equals(userEpo.password) : userEpo.password != null)
         return false;
      if (authority != null ? !authority.equals(userEpo.authority) : userEpo.authority != null)
         return false;
      if (photoLocation != null ? !photoLocation.equals(userEpo.photoLocation) : userEpo.photoLocation != null)
         return false;
      if (position != null ? !position.equals(userEpo.position) : userEpo.position != null)
         return false;
      if (team != null ? !team.equals(userEpo.team) : userEpo.team != null)
         return false;
      if (userSkills != null ? !userSkills.equals(userEpo.userSkills) : userEpo.userSkills != null)
         return false;
      if (userLanguages != null ? !userLanguages.equals(userEpo.userLanguages) : userEpo.userLanguages != null)
         return false;
      if (enabled != null ? !enabled.equals(userEpo.enabled) : userEpo.enabled != null)
         return false;
      return lastPasswordResetDate != null ? lastPasswordResetDate.equals(userEpo.lastPasswordResetDate)
                                           : userEpo.lastPasswordResetDate == null;
   }
   
   @Override
   public int hashCode() {
      int result = (int)(id ^ (id >>> 32));
      result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
      result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
      result = 31 * result + (userName != null ? userName.hashCode() : 0);
      result = 31 * result + (password != null ? password.hashCode() : 0);
      result = 31 * result + (authority != null ? authority.hashCode() : 0);
      result = 31 * result + (photoLocation != null ? photoLocation.hashCode() : 0);
      result = 31 * result + (position != null ? position.hashCode() : 0);
      result = 31 * result + (team != null ? team.hashCode() : 0);
      result = 31 * result + (userSkills != null ? userSkills.hashCode() : 0);
      result = 31 * result + (userLanguages != null ? userLanguages.hashCode() : 0);
      result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
      result = 31 * result + (lastPasswordResetDate != null ? lastPasswordResetDate.hashCode() : 0);
      return result;
   }
}
