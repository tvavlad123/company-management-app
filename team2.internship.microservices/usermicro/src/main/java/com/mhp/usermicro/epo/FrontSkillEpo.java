package com.mhp.usermicro.epo;

import java.io.Serializable;

public class FrontSkillEpo implements Serializable {
   
   /**
    * 
    */
   private static final long serialVersionUID = 3541754786262135042L;
   private long              id;
   private String            name;
   
   private FrontSkillEpo() {}
   
   public FrontSkillEpo(long id, String name) {
      this.id = id;
      this.name = name;
   }
   
   public long getId() {
      return id;
   }
   
   public String getName() {
      return name;
   }
   
   @Override
   public String toString() {
      return "FrontSkillEpo [id=" + id + ", name=" + name + "]";
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (int)(id ^ (id >>> 32));
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
      FrontSkillEpo other = (FrontSkillEpo)obj;
      if (id != other.id)
         return false;
      if (name == null) {
         if (other.name != null)
            return false;
      } else if (!name.equals(other.name))
         return false;
      return true;
   }
   
}
