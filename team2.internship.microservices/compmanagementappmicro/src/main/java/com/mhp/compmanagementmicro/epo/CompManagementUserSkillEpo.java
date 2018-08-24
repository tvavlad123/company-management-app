package com.mhp.compmanagementmicro.epo;

import java.io.Serializable;

public class CompManagementUserSkillEpo implements Serializable {
   
   private static final long           serialVersionUID = 2395178442809477252L;
   
   private long                        id;
   private CompManagementUserEpo       user;
   private CompManagementSkillEpo      skill;
   private CompManagementSkillLevelEpo level;
   
   private CompManagementUserSkillEpo() {}
   
   public CompManagementUserSkillEpo(long id, CompManagementUserEpo user, CompManagementSkillEpo skill,
                                     CompManagementSkillLevelEpo level) {
      this.id = id;
      this.user = user;
      this.skill = skill;
      this.level = level;
   }
   
   public long getId() {
      return id;
   }
   
   public CompManagementUserEpo getUser() {
      return user;
   }
   
   public CompManagementSkillEpo getSkill() {
      return skill;
   }
   
   public CompManagementSkillLevelEpo getLevel() {
      return level;
   }
   
   @Override
   public String toString() {
      return "UserSkillEpo{" + "id=" + id + ", user=" + user + ", skill=" + skill + ", level='" + level + '\'' + '}';
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (int)(id ^ (id >>> 32));
      result = prime * result + ((level == null) ? 0 : level.hashCode());
      result = prime * result + ((skill == null) ? 0 : skill.hashCode());
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
      CompManagementUserSkillEpo other = (CompManagementUserSkillEpo)obj;
      if (id != other.id)
         return false;
      if (level == null) {
         if (other.level != null)
            return false;
      } else if (!level.equals(other.level))
         return false;
      if (skill == null) {
         if (other.skill != null)
            return false;
      } else if (!skill.equals(other.skill))
         return false;
      if (user == null) {
         if (other.user != null)
            return false;
      } else if (!user.equals(other.user))
         return false;
      return true;
   }
   
}
