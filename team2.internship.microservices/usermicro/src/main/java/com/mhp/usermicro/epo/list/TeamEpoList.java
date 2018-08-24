package com.mhp.usermicro.epo.list;

import java.io.Serializable;
import java.util.List;

import com.mhp.usermicro.epo.TeamEpo;

public class TeamEpoList implements Serializable {
   
   private static final long serialVersionUID = -4391947468961430972L;
   
   List<TeamEpo>             teamEpoList;
   
   private TeamEpoList() {}
   
   public TeamEpoList(List<TeamEpo> epoList) {
      this.teamEpoList = epoList;
   }
   
   public List<TeamEpo> getTeamEpoList() {
      return teamEpoList;
   }
   
   @Override
   public String toString() {
      return "TeamEpoList{" + "epoList=" + teamEpoList + '}';
   }
   
   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      
      TeamEpoList that = (TeamEpoList)o;
      
      return teamEpoList != null ? teamEpoList.equals(that.teamEpoList) : that.teamEpoList == null;
   }
   
   @Override
   public int hashCode() {
      return teamEpoList != null ? teamEpoList.hashCode() : 0;
   }
}
