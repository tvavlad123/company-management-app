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
@Table (name = "languagems")
@SequenceGenerator (sequenceName = "languagems_seq", allocationSize = 1, name = "LanguageSeq")
public class Language implements Serializable {
   
   private static final long serialVersionUID = -7128427585059782405L;
   
   private long              id;
   private String            name;
   
   public Language() {}
   
   public Language(long id, String name) {
      this.id = id;
      this.name = name;
   }
   
   @Id
   @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "LanguageSeq")
   @Column (name = "language_id", unique = true, nullable = false)
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
      return "Language{" + "id=" + id + ", name='" + name + '\'' + '}';
   }
   
   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      
      Language language = (Language)o;
      
      if (id != language.id)
         return false;
      return name != null ? name.equals(language.name) : language.name == null;
   }
   
   @Override
   public int hashCode() {
      int result = (int)(id ^ (id >>> 32));
      result = 31 * result + (name != null ? name.hashCode() : 0);
      return result;
   }
}
