package com.mhp.compmanagementmicro.mapper;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mhp.compmanagementmicro.epo.CompManagementDepartmentEpo;
import com.mhp.usermicro.epo.DepartmentEpo;

@Service
public class CompManagementDepartmentEpoMapper {
   
   public DepartmentEpo toInternal(CompManagementDepartmentEpo epo) {
      DepartmentEpo dept = new DepartmentEpo(epo.getId(), epo.getName());
      return dept;
   }
   
   public CompManagementDepartmentEpo toExternal(DepartmentEpo dept) {
      return new CompManagementDepartmentEpo(dept.getId(), dept.getName());
   }
   
   public List<DepartmentEpo> toInternal(List<CompManagementDepartmentEpo> epoList) {
      List<DepartmentEpo> list = new LinkedList<DepartmentEpo>();
      for (CompManagementDepartmentEpo epo : epoList) {
         list.add(toInternal(epo));
      }
      return list;
   }
   
   public List<CompManagementDepartmentEpo> toExternal(List<DepartmentEpo> departmentList) {
      List<CompManagementDepartmentEpo> list = new LinkedList<CompManagementDepartmentEpo>();
      for (DepartmentEpo dept : departmentList) {
         list.add(toExternal(dept));
      }
      return list;
   }
}
