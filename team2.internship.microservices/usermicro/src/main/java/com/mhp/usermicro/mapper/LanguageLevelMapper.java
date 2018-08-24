package com.mhp.usermicro.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mhp.usermicro.entity.LanguageLevel;
import com.mhp.usermicro.epo.LanguageLevelEpo;
import com.mhp.usermicro.epo.list.LanguageLevelEpoList;

@Service
public class LanguageLevelMapper extends GenericMapper<LanguageLevel, LanguageLevelEpo> {
   
   public LanguageLevel toInternal(LanguageLevelEpo epo) {
      return new LanguageLevel(epo.getId(), epo.getName());
   }
   
   public LanguageLevelEpo toExternal(LanguageLevel model) {
      return new LanguageLevelEpo(model.getId(), model.getName());
   }
   
   public LanguageLevel frontToInternal(LanguageLevelEpo epo) {
      return new LanguageLevel(epo.getId(), epo.getName());
   }
   
   public LanguageLevelEpoList toFronts(List<LanguageLevel> langs) {
      List<LanguageLevelEpo> languages = new ArrayList<LanguageLevelEpo>();
      for (int i = 0; i < langs.size(); i++) {
         languages.add(this.toExternal(langs.get(i)));
      }
      return new LanguageLevelEpoList(languages);
   }
   
}
