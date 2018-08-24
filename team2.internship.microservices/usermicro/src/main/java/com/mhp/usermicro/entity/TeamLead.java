package com.mhp.usermicro.entity;

import java.io.Serializable;

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
@Table (name = "team_leadms")
@SequenceGenerator (sequenceName = "team_leadms_seq", allocationSize = 1, name = "TeamLeadSeq")
public class TeamLead implements Serializable {
   
   private static final long serialVersionUID = 6114175940705103075L;
   
   private long              id;
   private User              user;
   private Team              team;
   
   public TeamLead() {}
   
   public TeamLead(long id, User user, Team team) {
      this.id = id;
      this.user = user;
      this.team = team;
   }
   
   @Id
   @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "TeamLeadSeq")
   @Column (name = "team_lead_id", unique = true, nullable = false)
   public long getId() {
      return id;
   }
   
   public void setId(long id) {
      this.id = id;
   }
   
   @ManyToOne (fetch = FetchType.EAGER)
   @JoinColumn (name = "user_id")
   public User getUser() {
      return user;
   }
   
   public void setUser(User userId) {
      this.user = userId;
   }
   
   @ManyToOne (fetch = FetchType.EAGER)
   @JoinColumn (name = "team_id")
   public Team getTeam() {
      return team;
   }
   
   public void setTeam(Team teamId) {
      this.team = teamId;
   }
   
   @Override
   public String toString() {
      return "TeamLead [id=" + id + ", user=" + user + ", team=" + team + "]";
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (int)(id ^ (id >>> 32));
      result = prime * result + ((team == null) ? 0 : team.hashCode());
      result = prime * result + ((user == null) ? 0 : user.hashCode());
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
      TeamLead other = (TeamLead)obj;
      if (id != other.id)
         return false;
      if (team == null) {
         if (other.team != null)
            return false;
      } else if (!team.equals(other.team))
         return false;
      if (user == null) {
         if (other.user != null)
            return false;
      } else if (!user.equals(other.user))
         return false;
      return true;
   }
   
}
