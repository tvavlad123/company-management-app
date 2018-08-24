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
@Table (name = "user_languagems")
@SequenceGenerator (sequenceName = "user_languagems_seq", allocationSize = 1, name = "UserLanguageSeq")
public class UserLanguage implements Serializable {
   
   private static final long serialVersionUID = -22095476641086619L;
   
   private long              id;
   private User              user;
   private LanguageLevel     level;
   private Language          language;
   
   public UserLanguage() {}
   
   public UserLanguage(long id, LanguageLevel level, Language language, User user) {
      this.id = id;
      this.level = level;
      this.language = language;
      this.user = user;
   }
   
   @Id
   @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "UserLanguageSeq")
   @Column (name = "user_language_id", unique = true, nullable = false)
   public long getId() {
      return id;
   }
   
   public void setId(long id) {
      this.id = id;
   }
   
   @ManyToOne (fetch = FetchType.EAGER)
   @JoinColumn (name = "user_id")
   public User getUser() {
      return user;
   }
   
   public void setUser(User user) {
      this.user = user;
   }
   
   @ManyToOne (fetch = FetchType.EAGER)
   @JoinColumn (name = "language_level_id")
   public LanguageLevel getLevel() {
      return level;
   }
   
   public void setLevel(LanguageLevel level) {
      this.level = level;
   }
   
   @ManyToOne (fetch = FetchType.EAGER)
   @JoinColumn (name = "language_id")
   public Language getLanguage() {
      return language;
   }
   
   public void setLanguage(Language language) {
      this.language = language;
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((language == null) ? 0 : language.hashCode());
      result = prime * result + ((level == null) ? 0 : level.hashCode());
      result = prime * result + ((user == null) ? 0 : user.hashCode());
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
      UserLanguage other = (UserLanguage)obj;
      if (language == null) {
         if (other.language != null)
            return false;
      } else if (!language.getName().equals(other.language.getName()))
         return false;
      if (level == null) {
         if (other.level != null)
            return false;
      } else if (!level.getName().equals(other.level.getName()))
         return false;
      if (user == null) {
         if (other.user != null)
            return false;
      } else if (!user.equals(other.user))
         return false;
      return true;
   }
   
}
