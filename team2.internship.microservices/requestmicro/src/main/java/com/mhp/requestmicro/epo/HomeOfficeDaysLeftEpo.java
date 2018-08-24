package com.mhp.requestmicro.epo;

import java.io.Serializable;
import java.sql.Date;

public class HomeOfficeDaysLeftEpo implements Serializable {
   
   private static final long serialVersionUID = 2601486668594783080L;
   

   private long              user_id;
   private Date              start_date;
   private Date              end_date;
   private long              half_day_id;
   
   private HomeOfficeDaysLeftEpo() {}
   
   public HomeOfficeDaysLeftEpo(long user_id, Date start_date, Date end_date, long half_day_id
                              ) {
     
      this.user_id = user_id;
      this.start_date = start_date;
      this.end_date = end_date;
      this.half_day_id = half_day_id;
   }
   
   
   public Date getStart_date() {
      return start_date;
   }
   
   public Date getEnd_date() {
      return end_date;
   }
      
   public long getUser_id() {
	return user_id;
   }

   public long getHalf_day_id() {
	return half_day_id;
   }

   @Override
   public String toString() {
	return "HomeOfficeDaysLeftEpo [user_id=" + user_id + ", start_date=" + start_date + ", end_date=" + end_date
			+ ", half_day_id=" + half_day_id + "]";
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((end_date == null) ? 0 : end_date.hashCode());
      result = prime * result + (int)(half_day_id ^ (half_day_id >>> 32));
      result = prime * result + ((start_date == null) ? 0 : start_date.hashCode());
      result = prime * result + (int)(user_id ^ (user_id >>> 32));
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
      HomeOfficeDaysLeftEpo other = (HomeOfficeDaysLeftEpo)obj;
      if (end_date == null) {
         if (other.end_date != null)
            return false;
      } else if (!end_date.equals(other.end_date))
         return false;
      if (half_day_id != other.half_day_id) {
         return false;
      }
      if (start_date == null) {
         if (other.start_date != null)
            return false;
      } else if (!start_date.equals(other.start_date))
         return false;
      if (user_id != other.user_id)
         return false;
      return true;
   }
   
   
}
