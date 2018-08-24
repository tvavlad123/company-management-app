package com.mhp.usermicro.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mhp.usermicro.entity.Language;
import com.mhp.usermicro.epo.FrontLanguageEpo;
import com.mhp.usermicro.epo.LanguageEpo;
import com.mhp.usermicro.epo.list.FrontLanguageEpoList;

@Service
public class LanguageEpoMapper {
   
   public Language toInternal(LanguageEpo epo) {
      return new Language(epo.getId(), epo.getName());
   }
   
   public LanguageEpo toExternal(Language model) {
      return new LanguageEpo(model.getId(), model.getName());
   }
   
   public Language frontToInternal(FrontLanguageEpo epo) {
      return new Language(epo.getId(), epo.getName());
   }
   
   public FrontLanguageEpo toFront(Language lang) {
      return new FrontLanguageEpo(lang.getId(), lang.getName());
   }
   
   public FrontLanguageEpoList toFronts(List<Language> langs) {
      List<FrontLanguageEpo> languages = new ArrayList<FrontLanguageEpo>();
      for (int i = 0; i < langs.size(); i++) {
         languages.add(this.toFront(langs.get(i)));
      }
      return new FrontLanguageEpoList(languages);
   }
}
