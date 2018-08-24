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
@Table (name = "rolems")
@SequenceGenerator (sequenceName = "rolems_seq", allocationSize = 1, name = "RoleSeq")
public class Role implements Serializable {
   
   private static final long serialVersionUID = 2469397067706640403L;
   
   private long              id;
   private String            name;
   // private Type type;
   
   public Role() {}
   
   public Role(long id, String name) {
      this.id = id;
      this.name = name;
      // this.type = Type.valueOf(name);
   }
   
   @Id
   @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "RoleSeq")
   @Column (name = "role_id", unique = true, nullable = false)
   public long getId() {
      return id;
   }
   
   public void setId(long id) {
      this.id = id;
   }
   
   @Column (name = "name", unique = true, nullable = false)
   public String getName() {
      // setType(name);
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   /*
    * public Type getType() { return Type.valueOf(name); }
    * 
    * public void setType(String name) { this.type = Type.valueOf(name); }
    * 
    * private static enum Type { ADMIN("ADMIN"), EMPLOYEE("EMPLOYEE"), TEAM_COORD("TEAM COORDINATOR"),
    * TEAM_LEAD("TEAM LEAD");
    * 
    * private String type = "";
    * 
    * private Type(String type) { this.type = type; }
    * 
    * public String getType() { return type; } }
    */
   
   @Override
   public String toString() {
      return "Role{" + "id=" + id + ", name='" + name + '\'' + '}';
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
      Role other = (Role)obj;
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
