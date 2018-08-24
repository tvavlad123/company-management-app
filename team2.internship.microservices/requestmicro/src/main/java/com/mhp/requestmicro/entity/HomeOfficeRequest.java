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
@Table (name = "homeoffice_requestms")
@SequenceGenerator (sequenceName = "homeoffice_requestms_seq", allocationSize = 1, name = "HomeOfficeSeq")
public class HomeOfficeRequest implements Serializable {
   
   private static final long serialVersionUID = 6366632746408938067L;
   
   private long              id;
   private long              user_id;
   private Date              start_date;
   private Date              end_date;
   private HalfDay           half_day;
   private Status            status_id;
   
   public HomeOfficeRequest() {}
   
   public HomeOfficeRequest(long id, long user_id, Date start_date, Date end_date, HalfDay half_day, Status status_id) {
      this.id = id;
      this.user_id = user_id;
      this.start_date = start_date;
      this.end_date = end_date;
      this.half_day = half_day;
      this.status_id = status_id;
   }
   
   @Id
   @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "HomeOfficeSeq")
   @Column (name = "homeoffice_request_id", unique = true, nullable = false)
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
   
   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "half_day_id", nullable = false)
   public HalfDay getHalf_day() {
      return half_day;
   }
   
   public void setHalf_day(HalfDay half_day) {
      this.half_day = half_day;
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
      return "HomeOfficeRequest [id=" + id + ", user_id=" + user_id + ", start_date=" + start_date + ", end_date="
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
      HomeOfficeRequest other = (HomeOfficeRequest)obj;
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
