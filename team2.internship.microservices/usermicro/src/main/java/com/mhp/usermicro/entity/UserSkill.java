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
@Table (name = "user_skillms")
@SequenceGenerator (sequenceName = "user_skillms_seq", allocationSize = 1, name = "SkillSeq")
public class UserSkill implements Serializable {
   
   private static final long serialVersionUID = 6792717383833864376L;
   
   private long              id;
   private User              user;
   private Skill             skill;
   private SkillLevel        level;
   
   public UserSkill() {}
   
   public UserSkill(long id, User user, Skill skill, SkillLevel level) {
      this.id = id;
      this.user = user;
      this.skill = skill;
      this.level = level;
   }
   
   @Id
   @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "SkillSeq")
   @Column (name = "user_skill_id", unique = true, nullable = false)
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
   @JoinColumn (name = "skill_id")
   public Skill getSkill() {
      return skill;
   }
   
   public void setSkill(Skill skill) {
      this.skill = skill;
   }
   
   @ManyToOne (fetch = FetchType.EAGER)
   @JoinColumn (name = "skill_level_id")
   public SkillLevel getLevel() {
      return level;
   }
   
   public void setLevel(SkillLevel level) {
      this.level = level;
   }
   
   @Override
   public String toString() {
      return "UserSkill{" + "id=" + id + ", userId=" + user + ", skill=" + skill + ", level='" + level + '\'' + '}';
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
      UserSkill other = (UserSkill)obj;
      if (skill == null) {
         if (other.skill != null)
            return false;
      } else if (!skill.getName().equals(other.skill.getName()))
         return false;
      if (level == null) {
         if (other.level != null)
            return false;
      } else if (!level.getName().equals(other.level.getName()))
         return false;
      if (user == null) {
         if (other.user != null)
            return false;
      } else if (!user.equals(other.user))
         return false;
      return true;
   }
   
}
