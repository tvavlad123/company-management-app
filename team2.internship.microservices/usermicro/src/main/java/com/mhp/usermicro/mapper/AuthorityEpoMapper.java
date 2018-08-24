package com.mhp.usermicro.mapper;

import com.mhp.usermicro.entity.Authority;
import com.mhp.usermicro.entity.AuthorityType;
import com.mhp.usermicro.epo.AuthorityEpo;
import org.springframework.stereotype.Service;

@Service
public class AuthorityEpoMapper extends GenericMapper<Authority,AuthorityEpo> {
    @Override
    public Authority toInternal(AuthorityEpo epo) {
        return new Authority(epo.getId(), AuthorityType.getAuthorityType(epo.getAuthorityType()));
    }

    @Override
    public AuthorityEpo toExternal(Authority model) {
        return new AuthorityEpo(model.getId(),AuthorityType.getString(model.getAuthorityType().name()));
    }
}
