package com.mhp.usermicro.mapper;

import com.mhp.usermicro.entity.Role;
import com.mhp.usermicro.epo.RoleEpo;

import org.springframework.stereotype.Service;

@Service
public class RoleEpoMapper extends GenericMapper<Role,RoleEpo> {
    @Override
    public Role toInternal(RoleEpo epo) {
        return new Role(epo.getId(),epo.getName());
    }

    @Override
    public RoleEpo toExternal(Role model) {
        return new RoleEpo(model.getId(),model.getName());
    }
}
