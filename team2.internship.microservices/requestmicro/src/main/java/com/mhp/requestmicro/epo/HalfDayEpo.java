package com.mhp.requestmicro.epo;

import java.io.Serializable;

public class HalfDayEpo implements Serializable {
   
   private static final long serialVersionUID = -4567868478430447729L;
   
   private long              id;
   private String            type;
   
   private HalfDayEpo() {}
   
   public HalfDayEpo(long id, String type) {
      this.id = id;
      this.type = type;
   }
   
   public long getId() {
      return id;
   }
   
   public String getType() {
      return type;
   }
   
   @Override
   public String toString() {
      return "HalfDayEpo [id=" + id + ", type=" + type + "]";
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
      HalfDayEpo other = (HalfDayEpo)obj;
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
