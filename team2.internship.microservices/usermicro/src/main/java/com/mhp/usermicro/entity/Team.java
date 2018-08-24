package com.mhp.usermicro.entity;

import java.io.Serializable;

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
@Table (name = "teamms")
@SequenceGenerator (sequenceName = "teamms_seq", allocationSize = 1, name = "TeamSeq")
public class Team implements Serializable {
   
   private static final long serialVersionUID = 1744209809099708706L;
   
   private long              id;
   private String            name;
   private Department        department;
   
   public Team() {}
   
   public Team(long id, String name, Department department) {
      this.id = id;
      this.name = name;
      this.department = department;
   }
   
   @Id
   @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "TeamSeq")
   @Column (name = "team_id", unique = true, nullable = false)
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
   
   @ManyToOne (fetch = FetchType.EAGER)
   @JoinColumn (name = "department_id")
   public Department getDepartment() {
      return department;
   }
   
   public void setDepartment(Department department) {
      this.department = department;
   }
   
   @Override
   public String toString() {
      return "Team{" + "id=" + id + ", name='" + name + '\'' + ", department=" + department + '}';
   }
   
   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      
      Team team = (Team)o;
      
      if (id != team.id)
         return false;
      if (name != null ? !name.equals(team.name) : team.name != null)
         return false;
      return department != null ? department.equals(team.department) : team.department == null;
   }
   
   @Override
   public int hashCode() {
      int result = (int)(id ^ (id >>> 32));
      result = 31 * result + (name != null ? name.hashCode() : 0);
      result = 31 * result + (department != null ? department.hashCode() : 0);
      return result;
   }
}
