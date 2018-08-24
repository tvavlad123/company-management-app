package com.mhp.compmanagementmicro.epo;

import java.io.Serializable;

public class CompManagementUserLanguageEpo implements Serializable {
   
   private static final long              serialVersionUID = -8286395578597217550L;
   
   private long                           id;
   private CompManagementLanguageLevelEpo level;
   private CompManagementLanguageEpo      language;
   private CompManagementUserEpo          user;
   
   public CompManagementUserEpo getUser() {
      return user;
   }
   
   private CompManagementUserLanguageEpo() {}
   
   public CompManagementUserLanguageEpo(long id, CompManagementLanguageLevelEpo level,
                                        CompManagementLanguageEpo language, CompManagementUserEpo user) {
      this.id = id;
      this.level = level;
      this.language = language;
      this.user = user;
   }
   
   public long getId() {
      return id;
   }
   
   public CompManagementLanguageLevelEpo getLevel() {
      return level;
   }
   
   public CompManagementLanguageEpo getLanguage() {
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
      
      CompManagementUserLanguageEpo that = (CompManagementUserLanguageEpo)o;
      
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
