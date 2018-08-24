package com.mhp.usermicro.epo.list;

import com.mhp.usermicro.epo.AuthorityEpo;

import java.io.Serializable;
import java.util.List;

public class AuthorityEpoList implements Serializable {

    private static final long serialVersionUID = 1696259768444576667L;

    private List<AuthorityEpo> authorityEpos;

    private  AuthorityEpoList(){}

    public AuthorityEpoList(List<AuthorityEpo> authorityEpos) {
        this.authorityEpos = authorityEpos;
    }

    public List<AuthorityEpo> getAuthorityEpos() {
        return authorityEpos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorityEpoList that = (AuthorityEpoList) o;

        return authorityEpos != null ? authorityEpos.equals(that.authorityEpos) : that.authorityEpos == null;
    }

    @Override
    public int hashCode() {
        return authorityEpos != null ? authorityEpos.hashCode() : 0;
    }
}
