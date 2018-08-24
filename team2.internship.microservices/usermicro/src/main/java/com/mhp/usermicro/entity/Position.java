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
@Table (name = "positionms")
@SequenceGenerator (sequenceName = "positionms_seq", allocationSize = 1, name = "PositionSeq")
public class Position implements Serializable {
   
   private static final long serialVersionUID = 4835961426796019516L;
   
   private long              id;
   private String            name;
   
   public Position() {}
   
   public Position(long id, String name) {
      this.id = id;
      this.name = name;
   }
   
   @Id
   @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "PositionSeq")
   @Column (name = "position_id", unique = true, nullable = false)
   public long getId() {
      return id;
   }
   
   public void setId(long id) {
      this.id = id;
   }
   
   @Column (name = "name", unique = true, nullable = false)
   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   
   @Override
   public String toString() {
      return "Position{" + "id=" + id + ", name='" + name + '\'' + '}';
   }
   
   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      
      Position position = (Position)o;
      
      if (id != position.id)
         return false;
      return name != null ? name.equals(position.name) : position.name == null;
   }
   
   @Override
   public int hashCode() {
      int result = (int)(id ^ (id >>> 32));
      result = 31 * result + (name != null ? name.hashCode() : 0);
      return result;
   }
}
