package com.mhp.usermicro.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhp.usermicro.entity.User;
import com.mhp.usermicro.entity.UserLanguage;
import com.mhp.usermicro.entity.UserSkill;
import com.mhp.usermicro.epo.UserEpo;
import com.mhp.usermicro.epo.UserLanguageEpo;
import com.mhp.usermicro.epo.UserSkillEpo;
import com.mhp.usermicro.epo.list.LanguageEpoList;
import com.mhp.usermicro.epo.list.SkillEpoList;
import com.mhp.usermicro.repository.UserLanguageRepository;
import com.mhp.usermicro.repository.UserRepository;
import com.mhp.usermicro.repository.UserSkillRepository;

@Service
public class UserEpoMapper {
   
   @Autowired
   private AuthorityEpoMapper     aem;
   @Autowired
   private PositionEpoMapper      pem;
   @Autowired
   private TeamEpoMapper          tem;
   @Autowired
   private SkillEpoMapper         sem;
   @Autowired
   private UserSkillEpoMapper     usem;
   @Autowired
   private UserLanguageEpoMapper  ulem;
   @Autowired
   private LanguageEpoMapper      lem;
   @Autowired
   private LanguageLevelMapper    lvlm;
   @Autowired
   private SkillLevelMapper       sklm;
   @Autowired
   private UserSkillRepository    userSkillRepository;
   @Autowired
   private UserLanguageRepository userLangRepository;
   @Autowired
   private UserRepository         userRepository;
   
   public User toInternal(UserEpo epo) {
      User user = new User(epo.getId(), epo.getFirstName(), epo.getLastName(), epo.getUserName(), epo.getPassword(),
         aem.toInternal(epo.getAuthority()), epo.getPhotoLocation(), pem.toInternal(epo.getPosition()),
         tem.toInternal(epo.getTeam()), epo.getEnabled(), epo.getLastPasswordResetDate());
      return user;
   }
   
   public UserEpo toExternal(User model, List<UserSkill> skills, List<UserLanguage> languages) {
      long userId = model.getId();
      List<UserSkillEpo> skillsEpo = new ArrayList<UserSkillEpo>();
      List<UserLanguageEpo> languagesEpo = new ArrayList<UserLanguageEpo>();
      for (int i = 0; i < skills.size(); i++) {
         if (skills.get(i).getUser().getId() == userId) {
            skillsEpo.add(usem.toExternal(skills.get(i)));
         }
      }
      for (int i = 0; i < languages.size(); i++) {
         if (languages.get(i).getUser().getId() == userId) {
            languagesEpo.add(ulem.toExternal(languages.get(i)));
         }
      }
      
      LanguageEpoList langlist = new LanguageEpoList(languagesEpo);
      SkillEpoList skilllist = new SkillEpoList(skillsEpo);
      UserEpo userEpo = new UserEpo(model.getId(), model.getFirstName(), model.getLastName(), model.getUsername(),
         model.getPassword(), aem.toExternal(model.getAuthority()), model.getPhotoLocation(),
         pem.toExternal(model.getPosition()), tem.toExternal(model.getTeam()), skilllist, langlist, model.isEnabled(),
         model.getLastPasswordResetDate());
      return userEpo;
      
   }
   
   public List<UserEpo> toExternals(Collection<User> models) {
      List<UserEpo> list = new LinkedList<>();
      List<User> users = (List<User>)userRepository.findAll();
      for (int i = 0; i < models.size(); i++) {
         List<UserSkill> skills = (List<UserSkill>)userSkillRepository.findSkillsByUser(users.get(i).getId());
         List<UserLanguage> languages = (List<UserLanguage>)userLangRepository.findLanguageByUser(users.get(i).getId());
         list.add(toExternal(users.get(i), skills, languages));
      }
      return list;
   }
   
}
