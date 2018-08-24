package com.mhp.usermicro.epo;

import java.io.Serializable;

public class UserLanguageEpo implements Serializable {
   
   private static final long serialVersionUID = 7002252475904098017L;
   
   private long              id;
   private UserEpo           user;
   private LanguageEpo       language;
   private LanguageLevelEpo  level;
   
   public UserEpo getUser() {
      return user;
   }
   
   private UserLanguageEpo() {}
   
   public UserLanguageEpo(long id, UserEpo user, LanguageEpo language, LanguageLevelEpo level) {
      this.id = id;
      this.level = level;
      this.language = language;
      this.user = user;
   }
   
   public long getId() {
      return id;
   }
   
   public LanguageLevelEpo getLevel() {
      return level;
   }
   
   public LanguageEpo getLanguage() {
      return language;
   }
   
   @Override
   public String toString() {
      return "UserLanguageEpo{" + "id=" + id + ", level='" + level + '\'' + ", language=" + language + '}';
   }
   
   @Override
   public boolean equals(Object o) {
      if (this == o)
         return true;
      if (o == null || getClass() != o.getClass())
         return false;
      
      UserLanguageEpo that = (UserLanguageEpo)o;
      
      if (id != that.id)
         return false;
      if (level != null ? !level.equals(that.level) : that.level != null)
         return false;
      return language != null ? language.equals(that.language) : that.language == null;
   }
   
   @Override
   public int hashCode() {
      int result = (int)(id ^ (id >>> 32));
      result = 31 * result + (level != null ? level.hashCode() : 0);
      result = 31 * result + (language != null ? language.hashCode() : 0);
      return result;
   }
}
