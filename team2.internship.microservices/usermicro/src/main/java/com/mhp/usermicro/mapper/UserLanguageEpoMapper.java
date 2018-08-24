package com.mhp.usermicro.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhp.usermicro.entity.UserLanguage;
import com.mhp.usermicro.entity.UserSkill;
import com.mhp.usermicro.epo.UserEpo;
import com.mhp.usermicro.epo.UserLanguageEpo;

@Service
public class UserLanguageEpoMapper {
   
   @Autowired
   private LanguageEpoMapper   lem;
   
   @Autowired
   private LanguageLevelMapper lvlm;
   
   @Autowired
   private UserEpoMapper       uem;
   
   public UserLanguage toInternal(UserLanguageEpo epo) {
      UserLanguage userSkill = new UserLanguage(epo.getId(), lvlm.toInternal(epo.getLevel()),
         lem.toInternal(epo.getLanguage()), uem.toInternal(epo.getUser()));
      return userSkill;
   }
   
   public List<UserLanguage> toInternals(List<UserLanguageEpo> lang, UserEpo user) {
      List<UserLanguage> languages = new ArrayList<UserLanguage>();
      for (int i = 0; i < lang.size(); i++) {
         UserLanguage userLang = new UserLanguage(0, lvlm.toInternal(lang.get(i).getLevel()),
            lem.toInternal(lang.get(i).getLanguage()), uem.toInternal(user));
         languages.add(userLang);
      }
      return languages;
   }
   
   public UserLanguageEpo toExternal(UserLanguage model) {
      UserLanguageEpo epo = new UserLanguageEpo(model.getId(),
         uem.toExternal(model.getUser(), new ArrayList<UserSkill>(), new ArrayList<UserLanguage>()),
         lem.toExternal(model.getLanguage()), lvlm.toExternal(model.getLevel()));
      return epo;
      
   }
}
