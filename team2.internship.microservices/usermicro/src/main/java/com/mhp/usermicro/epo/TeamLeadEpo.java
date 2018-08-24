package com.mhp.usermicro.epo;

import java.io.Serializable;

public class TeamLeadEpo implements Serializable {
   
   private static final long serialVersionUID = 5865817746423065551L;
   
   private long              id;
   private UserEpo           user;
   private TeamEpo           team;
   
   private TeamLeadEpo() {}
   
   public TeamLeadEpo(long id, UserEpo user, TeamEpo team) {
      this.id = id;
      this.user = user;
      this.team = team;
   }
   
   public long getId() {
      return id;
   }
   
   public UserEpo getUser() {
      return user;
   }
   
   public TeamEpo getTeam() {
      return team;
   }
   
   @Override
   public String toString() {
      return "TeamLeadEpo [id=" + id + ", user=" + user + ", team=" + team + "]";
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
      TeamLeadEpo other = (TeamLeadEpo)obj;
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
