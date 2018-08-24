package com.mhp.compmanagementmicro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mhp.compmanagementmicro.epo.CompManagementDepartmentEpo;
import com.mhp.compmanagementmicro.epo.CompManagementTeamEpo;
import com.mhp.compmanagementmicro.epo.CompManagementUserEpo;
import com.mhp.compmanagementmicro.service.CompManagementService;

public class CompManagementAdminController {
   
   @Autowired
   private CompManagementService service;
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/addUser", method = RequestMethod.POST)
   public CompManagementUserEpo createUser(@RequestBody CompManagementUserEpo epo) {
      return service.createUser(epo);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/getallusers", method = RequestMethod.GET)
   public List<CompManagementUserEpo> getAllUsers() {
      
      return service.getAllUsers();
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/updateUser", method = RequestMethod.POST)
   public CompManagementUserEpo updateUse(@RequestBody CompManagementUserEpo epo) {
      return service.updateUser(epo);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/adddepartment", method = RequestMethod.POST)
   public CompManagementDepartmentEpo createDepartment(@RequestBody CompManagementDepartmentEpo epo) {
      return service.createDepartment(epo);
   }
   
   @CrossOrigin (origins = "http://localhost:4200")
   @RequestMapping (value = "/addTeam", method = RequestMethod.POST)
   public CompManagementTeamEpo createTeam(@RequestBody CompManagementTeamEpo epo) {
      return service.createTeam(epo);
   }
   
}
