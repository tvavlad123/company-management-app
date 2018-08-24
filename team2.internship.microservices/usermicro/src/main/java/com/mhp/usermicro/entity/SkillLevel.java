package com.mhp.usermicro.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name = "skill_levelms")
@SequenceGenerator (sequenceName = "skill_levelms_seq", allocationSize = 1, name = "SkillLevelSeq")
public class SkillLevel implements Serializable {
   
   private static final long serialVersionUID = 3593671904728464461L;
   
   private long              id;
   private String            name;
   
   public SkillLevel() {}
   
   public SkillLevel(long id, String name) {
      this.id = id;
      this.name = name;
   }
   
   @Id
   @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "SkillLevelSeq")
   @Column (name = "skill_level_id", unique = true, nullable = false)
   public long getId() {
      return id;
   }
   
   public void setId(long id) {
      this.id = id;
   }
   
   @Column (name = "name", nullable = false)
   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   
   @Override
   public String toString() {
      return "SkillLevel [id=" + id + ", name=" + name + "]";
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((name == null) ? 0 : name.hashCode());
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
      SkillLevel other = (SkillLevel)obj;
      if (name == null) {
         if (other.name != null)
            return false;
      } else if (!name.equals(other.name))
         return false;
      return true;
   }
   
}
