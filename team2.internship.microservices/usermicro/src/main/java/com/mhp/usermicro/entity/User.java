package com.mhp.usermicro.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name = "userms")
@SequenceGenerator (sequenceName = "userms_seq", allocationSize = 1, name = "UserSeq")
public class User implements Serializable {
   
   private static final long serialVersionUID = 7899908807478391263L;
   
   private long              id;
   private String            firstName;
   private String            lastName;
   private String            username;
   private String            password;
   private Authority         authority;
   private String            photoLocation;
   private Position          position;
   private Team              team;
   private Boolean           enabled;
   private Date              lastPasswordResetDate;


   public User() {}

   public User(long id, String firstName, String lastName, String username, String password, Authority authority, String photoLocation,
               Position position, Team team, Boolean enabled, Date lastPasswordResetDate) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.username = username;
      this.password = password;
      this.authority = authority;
      this.photoLocation = photoLocation;
      this.position = position;
      this.team = team;
      this.enabled = enabled;
      this.lastPasswordResetDate = lastPasswordResetDate;
      if (this.lastPasswordResetDate==null)
         this.lastPasswordResetDate= new Date();
   }

   @Id
   @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "UserSeq")
   @Column (name = "user_id", unique = true, nullable = false)
   public long getId() {
      return id;
   }
   
   public void setId(long id) {
      this.id = id;
   }
   
   @Column (name = "first_name", nullable = false)
   public String getFirstName() {
      return firstName;
   }
   
   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }
   
   @Column (name = "last_name", nullable = false)
   public String getLastName() {
      return lastName;
   }
   
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }
   
   @Column (name = "username", nullable = false, unique = true)
   public String getUsername() {
      return username;
   }
   
   public void setUsername(String username) {
      this.username = username;
   }
   
   @Column (name = "password", nullable = false)
   public String getPassword() {
      return password;
   }
   
   public void setPassword(String password) {
      this.password = password;
   }
   
   @ManyToOne (fetch = FetchType.EAGER)
   @JoinColumn (name = "authority_id")
   public Authority getAuthority() {
      return authority;
   }
   
   public void setAuthority(Authority authority) {
      this.authority = authority;
   }
   
   @Column (name = "photo")
   public String getPhotoLocation() {
      return photoLocation;
   }
   
   public void setPhotoLocation(String photoLocation) {
      this.photoLocation = photoLocation;
   }
   
   @ManyToOne (fetch = FetchType.EAGER)
   @JoinColumn (name = "position_id")
   public Position getPosition() {
      return position;
   }
   
   public void setPosition(Position position) {
      this.position = position;
   }
   
   @ManyToOne (fetch = FetchType.EAGER)
   @JoinColumn (name = "team_id")
   public Team getTeam() {
      return team;
   }
   
   public void setTeam(Team team) {
      this.team = team;
   }

   @Column(name = "enabled")
   public Boolean isEnabled() {
      return enabled;
   }

   public void setEnabled(Boolean enabled) {
      this.enabled = enabled;
   }

   @Column(name = "lastpasswordresetdate")
   public Date getLastPasswordResetDate() {
      return lastPasswordResetDate;
   }

   public void setLastPasswordResetDate(Date lastPasswordResetDate) {
      this.lastPasswordResetDate = lastPasswordResetDate;
   }

   @Override
   public String toString() {
      return "User{" +
              "id=" + id +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", username='" + username + '\'' +
              ", password='" + password + '\'' +
              ", authority=" + authority +
              ", photoLocation='" + photoLocation + '\'' +
              ", position=" + position +
              ", team=" + team +
              ", enabled=" + enabled +
              ", lastPasswordResetDate=" + lastPasswordResetDate +
              '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      User user = (User) o;

      if (id != user.id) return false;
      if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
      if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
      if (username != null ? !username.equals(user.username) : user.username != null) return false;
      if (password != null ? !password.equals(user.password) : user.password != null) return false;
      if (authority != null ? !authority.equals(user.authority) : user.authority != null) return false;
      if (photoLocation != null ? !photoLocation.equals(user.photoLocation) : user.photoLocation != null) return false;
      if (position != null ? !position.equals(user.position) : user.position != null) return false;
      if (team != null ? !team.equals(user.team) : user.team != null) return false;
      if (enabled != null ? !enabled.equals(user.enabled) : user.enabled != null) return false;
      return lastPasswordResetDate != null ? lastPasswordResetDate.equals(user.lastPasswordResetDate) : user.lastPasswordResetDate == null;
   }

   @Override
   public int hashCode() {
      int result = (int) (id ^ (id >>> 32));
      result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
      result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
      result = 31 * result + (username != null ? username.hashCode() : 0);
      result = 31 * result + (password != null ? password.hashCode() : 0);
      result = 31 * result + (authority != null ? authority.hashCode() : 0);
      result = 31 * result + (photoLocation != null ? photoLocation.hashCode() : 0);
      result = 31 * result + (position != null ? position.hashCode() : 0);
      result = 31 * result + (team != null ? team.hashCode() : 0);
      result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
      result = 31 * result + (lastPasswordResetDate != null ? lastPasswordResetDate.hashCode() : 0);
      return result;
   }
}
