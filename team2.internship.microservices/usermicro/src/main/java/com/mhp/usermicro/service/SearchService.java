package com.mhp.usermicro.service;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

import java.util.Iterator;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.mhp.usermicro.entity.User;
import com.mhp.usermicro.epo.UserEpo;
import com.mhp.usermicro.mapper.UserEpoMapper;
import com.mhp.usermicro.repository.UserRepo;
import com.mhp.usermicro.repository.UserRepository;

@Service
public class SearchService {
   
   private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SearchService.class);
   
   @Autowired
   private UserRepository                userRepository;
   @Autowired
   private UserRepo                      userrepo;
   @Autowired
   private UserEpoMapper                 uem;
   
   public List<User> searchByQuery(String query) {
      LOGGER.debug("Getting all users with this querry: " + query + "in their full name");
      List<User> userList = (List<User>)userRepository.findAll();
      for (Iterator<User> it = userList.iterator(); it.hasNext();) {
         User user = it.next();
         String fullName = user.getFirstName() + " " + user.getLastName();
         if (!fullName.toLowerCase().contains(query.toLowerCase())) {
            it.remove();
         }
      }
      return userList;
   }
   
   public List<User> searchByFirstName(String query) {
      LOGGER.debug("Getting all users with this querry: " + query + "in their first name");
      List<User> userList = (List<User>)userRepository.findAll();
      for (Iterator<User> it = userList.iterator(); it.hasNext();) {
         User user = it.next();
         String firstName = user.getFirstName();
         if (!firstName.toLowerCase().contains(query.toLowerCase())) {
            it.remove();
         }
      }
      return userList;
   }
   
   public List<User> searchByLastName(String query) {
      LOGGER.debug("Getting all users with this querry: " + query + "in their last name");
      List<User> userList = (List<User>)userRepository.findAll();
      for (Iterator<User> it = userList.iterator(); it.hasNext();) {
         User user = it.next();
         String lastName = user.getLastName();
         if (!lastName.toLowerCase().contains(query.toLowerCase())) {
            it.remove();
         }
      }
      return userList;
   }
   
   public User searchByUsername(String userName) {
      LOGGER.debug("Getting user with this username: " + userName);
      List<User> userList = (List<User>)userRepository.findAll();
      for (User user : userList) {
         if (user.getUsername().equals(userName)) {
            LOGGER.debug("Found user: " + user);
            return user;
         }
      }
      LOGGER.debug("Found no user with user name: " + userName);
      return null;
   }
   
   public List<UserEpo> searchUserByName(String query) {
      LOGGER.trace("searchUserByName: query={}", query);
      
      ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("firstName", startsWith().ignoreCase())
         .withMatcher("lastName", startsWith().ignoreCase())
         .withIgnorePaths("userName", "password", "role", "photoLocation", "position", "team");
      User user = new User();
      Example<User> example = Example.of(user, matcher);
      List<User> users = userrepo.findAll(example);
      
      LOGGER.trace("searchUserByName: users={}", users);
      List<UserEpo> finalUsers = uem.toExternals(users);
      
      return finalUsers;
   }
}
