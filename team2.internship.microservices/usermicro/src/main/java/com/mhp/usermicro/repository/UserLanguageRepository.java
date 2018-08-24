package com.mhp.usermicro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mhp.usermicro.entity.User;
import com.mhp.usermicro.entity.UserLanguage;

public interface UserLanguageRepository extends CrudRepository<UserLanguage, Long> {
   
   @Query (value = "SELECT p.user_language_id,p.user_id,p.language_id,p.language_level_id FROM user_languagems p WHERE p.user_id = :uid", nativeQuery = true)
   public List<UserLanguage> findLanguageByUser(@Param ("uid") long user_id);
   
   public void deleteByUser(User user);
}
