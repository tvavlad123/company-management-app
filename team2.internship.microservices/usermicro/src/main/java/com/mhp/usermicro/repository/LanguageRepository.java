package com.mhp.usermicro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mhp.usermicro.entity.Language;

public interface LanguageRepository extends CrudRepository<Language, Long> {
   
   @Query (value = "SELECT p.language_id,p.name FROM languagems p WHERE p.language_id NOT IN (SELECT s.language_id FROM user_languagems s WHERE s.user_id=:uid)", nativeQuery = true)
   public List<Language> findLanguagesRemainingForUser(@Param ("uid") long user_id);
}
