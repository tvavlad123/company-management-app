package com.mhp.requestmicro.epo;

import java.io.Serializable;
import java.sql.Date;

public class HomeOfficeRequestEpo implements Serializable {
   
   private static final long serialVersionUID = 2601486668594783080L;
   
   private long              id;
   private long              user_id;
   private Date              start_date;
   private Date              end_date;
   private HalfDayEpo        half_day;
   private StatusEpo         status_id;
   
   private HomeOfficeRequestEpo() {}
   
   public HomeOfficeRequestEpo(long id, long user_id, Date start_date, Date end_date, HalfDayEpo half_day,
                               StatusEpo status_id) {
      this.id = id;
      this.user_id = user_id;
      this.start_date = start_date;
      this.end_date = end_date;
      this.half_day = half_day;
      this.status_id = status_id;
   }
   
   public long getId() {
      return id;
   }
   
   public long getUser_id() {
      return user_id;
   }
   
   public Date getStart_date() {
      return start_date;
   }
   
   public Date getEnd_date() {
      return end_date;
   }
   
   public HalfDayEpo getHalf_day() {
      return half_day;
   }
   
   public StatusEpo getStatus_id() {
      return status_id;
   }
   
   @Override
   public String toString() {
      return "HomeOfficeRequestEpo [id=" + id + ", user_id=" + user_id + ", start_date=" + start_date + ", end_date="
             + end_date + ", half_day=" + half_day + ", status_id=" + status_id + "]";
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((end_date == null) ? 0 : end_date.hashCode());
      result = prime * result + ((half_day == null) ? 0 : half_day.hashCode());
      result = prime * result + (int)(id ^ (id >>> 32));
      result = prime * result + ((start_date == null) ? 0 : start_date.hashCode());
      result = prime * result + ((status_id == null) ? 0 : status_id.hashCode());
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
      HomeOfficeRequestEpo other = (HomeOfficeRequestEpo)obj;
      if (end_date == null) {
         if (other.end_date != null)
            return false;
      } else if (!end_date.equals(other.end_date))
         return false;
      if (half_day == null) {
         if (other.half_day != null)
            return false;
      } else if (!half_day.equals(other.half_day))
         return false;
      if (id != other.id)
         return false;
      if (start_date == null) {
         if (other.start_date != null)
            return false;
      } else if (!start_date.equals(other.start_date))
         return false;
      if (status_id == null) {
         if (other.status_id != null)
            return false;
      } else if (!status_id.equals(other.status_id))
         return false;
      if (user_id != other.user_id)
         return false;
      return true;
   }
   
}
