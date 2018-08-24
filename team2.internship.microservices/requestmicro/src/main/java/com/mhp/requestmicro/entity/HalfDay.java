package com.mhp.requestmicro.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name = "half_dayms")
@SequenceGenerator (sequenceName = "half_dayms_seq", allocationSize = 1, name = "HalfDaySeq")
public class HalfDay implements Serializable {
   
   private static final long serialVersionUID = -7457077734727176349L;
   
   private long              id;
   private String            type;
   
   public HalfDay() {}
   
   public HalfDay(long id, String type) {
      this.id = id;
      this.type = type;
   }
   
   @Id
   @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "HalfDaySeq")
   @Column (name = "half_day_id", unique = true, nullable = false)
   public long getId() {
      return id;
   }
   
   public void setId(long id) {
      this.id = id;
   }
   
   @Column (name = "type", unique = true, nullable = false)
   public String getType() {
      return type;
   }
   
   public void setType(String type) {
      this.type = type;
   }
   
   @Override
   public String toString() {
      return "HalfDay [id=" + id + ", type=" + type + "]";
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (int)(id ^ (id >>> 32));
      result = prime * result + ((type == null) ? 0 : type.hashCode());
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
      HalfDay other = (HalfDay)obj;
      if (id != other.id)
         return false;
      if (type == null) {
         if (other.type != null)
            return false;
      } else if (!type.equals(other.type))
         return false;
      return true;
   }
   
}
