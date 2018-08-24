package com.mhp.usermicro.epo;

import java.io.Serializable;

public class RoleEpo implements Serializable {
   
   private static final long serialVersionUID = 4051491955239879284L;
   
   private long              id;
   private String            name;
   private Type              type;
   
   private RoleEpo() {}
   
   public RoleEpo(long id, String name) {
      this.id = id;
      this.name = name;
      if (name.equals("TEAM COORDINATOR"))
         this.type = Type.valueOf("TEAM_COORD");
      if (name.equals("TEAM LEADER"))
         this.type = Type.valueOf("TEAM_LEAD");
      else
         this.type = Type.valueOf(name);
   }
   
   public long getId() {
      return id;
   }
   
   public String getName() {
      return name;
   }
   
   public Type getType() {
      return type;
   }
   
   @Override
   public String toString() {
      return "RoleEpo{" + "id=" + id + ", name='" + name + '\'' + ", type=" + type + '}';
   }
   
   public enum Type {
                     ADMIN("ADMIN"),
                     EMPLOYEE("EMPLOYEE"),
                     TEAM_COORD("TEAM COORDINATOR"),
                     TEAM_LEAD("TEAM LEAD");
      
      private String type = "";
      
      private Type(String type) {
         this.type = type;
      }
      
      public String getType() {
         return type;
      }
   }
   
   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      
      RoleEpo roleEpo = (RoleEpo)o;
      
      if (id != roleEpo.id)
         return false;
      if (name != null ? !name.equals(roleEpo.name) : roleEpo.name != null)
         return false;
      return type == roleEpo.type;
   }
   
   @Override
   public int hashCode() {
      int result = (int)(id ^ (id >>> 32));
      result = 31 * result + (name != null ? name.hashCode() : 0);
      result = 31 * result + (type != null ? type.hashCode() : 0);
      return result;
   }
}
