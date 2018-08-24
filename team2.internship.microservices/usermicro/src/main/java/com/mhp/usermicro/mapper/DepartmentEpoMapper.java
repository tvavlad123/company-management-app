package com.mhp.usermicro.mapper;

import com.mhp.usermicro.entity.Department;
import com.mhp.usermicro.epo.DepartmentEpo;

import org.springframework.stereotype.Service;

@Service
public class DepartmentEpoMapper extends GenericMapper<Department,DepartmentEpo> {
    @Override
    public Department toInternal(DepartmentEpo epo) {
        return new Department(epo.getId(),epo.getName());
    }

    @Override
    public DepartmentEpo toExternal(Department model) {
        return new DepartmentEpo(model.getId(),model.getName());
    }
}
