package com.mhp.compmanagementmicro.epo;

import java.io.Serializable;

public class CompManagementTeamLeadEpo implements Serializable {
   
   private static final long     serialVersionUID = -1942310085922072947L;
   
   private long                  id;
   private CompManagementUserEpo user;
   private CompManagementTeamEpo team;
   
   private CompManagementTeamLeadEpo() {}
   
   public CompManagementTeamLeadEpo(long id, CompManagementUserEpo user, CompManagementTeamEpo team) {
      this.id = id;
      this.user = user;
      this.team = team;
   }
   
   public long getId() {
      return id;
   }
   
   public CompManagementUserEpo getUser() {
      return user;
   }
   
   public CompManagementTeamEpo getTeam() {
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
      CompManagementTeamLeadEpo other = (CompManagementTeamLeadEpo)obj;
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
