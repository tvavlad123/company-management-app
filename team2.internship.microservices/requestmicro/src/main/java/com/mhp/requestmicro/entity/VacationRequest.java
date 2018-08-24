package com.mhp.requestmicro.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name = "vacation_requestms")
@SequenceGenerator (sequenceName = "vacation_requestms_seq", allocationSize = 1, name = "VacationSeq")
public class VacationRequest implements Serializable {
   
   private static final long serialVersionUID = 2906756194292624413L;
   
   private long              id;
   private long              user_id;
   private Date              start_date;
   private Date              end_date;
   private String            comments;
   private String            picture;
   private Status            status_id;
   
   public VacationRequest() {}
   
   public VacationRequest(long id, long user_id, Date start_date, Date end_date, String comments, String picture,
                          Status status_id) {
      
      this.id = id;
      this.user_id = user_id;
      this.start_date = start_date;
      this.end_date = end_date;
      this.comments = comments;
      this.picture = picture;
      this.status_id = status_id;
   }
   
   @Id
   @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "VacationSeq")
   @Column (name = "vacation_request_id", unique = true, nullable = false)
   public long getId() {
      return id;
   }
   
   public void setId(long id) {
      this.id = id;
   }
   
   @Column (name = "user_id", nullable = false)
   public long getUser_id() {
      return user_id;
   }
   
   public void setUser_id(long user_id) {
      this.user_id = user_id;
   }
   
   @Column (name = "start_date", nullable = false)
   public Date getStart_date() {
      return start_date;
   }
   
   public void setStart_date(Date start_date) {
      this.start_date = start_date;
   }
   
   @Column (name = "end_date", nullable = false)
   public Date getEnd_date() {
      return end_date;
   }
   
   public void setEnd_date(Date end_date) {
      this.end_date = end_date;
   }
   
   @Column (name = "comments")
   public String getComments() {
      return comments;
   }
   
   public void setComments(String comments) {
      this.comments = comments;
   }
   
   @Column (name = "picture")
   public String getPicture() {
      return picture;
   }
   
   public void setPicture(String picture) {
      this.picture = picture;
   }
   
   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "status_id", nullable = false)
   public Status getStatus_id() {
      return status_id;
   }
   
   public void setStatus_id(Status status_id) {
      this.status_id = status_id;
   }
   
   @Override
   public String toString() {
      return "VacationRequest [id=" + id + ", user_id=" + user_id + ", start_date=" + start_date + ", end_date="
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
      VacationRequest other = (VacationRequest)obj;
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
