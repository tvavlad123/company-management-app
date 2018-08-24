package com.mhp.usermicro.epo;

import java.io.Serializable;

public class UserSkillEpo implements Serializable {
   
   private static final long serialVersionUID = -3231892573962666642L;
   
   private long              id;
   private UserEpo           user;
   private SkillEpo          skill;
   private SkillLevelEpo     level;
   
   private UserSkillEpo() {}
   
   public UserSkillEpo(long id, UserEpo user, SkillEpo skill, SkillLevelEpo level) {
      this.id = id;
      this.user = user;
      this.skill = skill;
      this.level = level;
   }
   
   public long getId() {
      return id;
   }
   
   public UserEpo getUser() {
      return user;
   }
   
   public SkillEpo getSkill() {
      return skill;
   }
   
   public SkillLevelEpo getLevel() {
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
      UserSkillEpo other = (UserSkillEpo)obj;
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
