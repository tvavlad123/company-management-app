package com.mhp.requestmicro.epo;

import java.io.Serializable;
import java.sql.Date;

public class VacationRequestEpo implements Serializable {
   
   private static final long serialVersionUID = 8184678428362864090L;
   
   private long              id;
   private long              user_id;
   private Date              start_date;
   private Date              end_date;
   private String            comments;
   private String            picture;
   private StatusEpo         status_id;
   
   private VacationRequestEpo() {}
   
   public VacationRequestEpo(long id, long user_id, Date start_date, Date end_date, String comments, String picture,
                             StatusEpo status_id) {
      this.id = id;
      this.user_id = user_id;
      this.start_date = start_date;
      this.end_date = end_date;
      this.comments = comments;
      this.picture = picture;
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
   
   public String getComments() {
      return comments;
   }
   
   public String getPicture() {
      return picture;
   }
   
   public StatusEpo getStatus_id() {
      return status_id;
   }
   
   @Override
   public String toString() {
      return "VacationRequestEpo [id=" + id + ", user_id=" + user_id + ", start_date=" + start_date + ", end_date="
             + end_date + ", comments=" + comments + ", picture=" + picture + ", status_id=" + status_id + "]";
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((comments == null) ? 0 : comments.hashCode());
      result = prime * result + ((end_date == null) ? 0 : end_date.hashCode());
      result = prime * result + (int)(id ^ (id >>> 32));
      result = prime * result + ((picture == null) ? 0 : picture.hashCode());
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
      VacationRequestEpo other = (VacationRequestEpo)obj;
      if (comments == null) {
         if (other.comments != null)
            return false;
      } else if (!comments.equals(other.comments))
         return false;
      if (end_date == null) {
         if (other.end_date != null)
            return false;
      } else if (!end_date.equals(other.end_date))
         return false;
      if (id != other.id)
         return false;
      if (picture == null) {
         if (other.picture != null)
            return false;
      } else if (!picture.equals(other.picture))
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
