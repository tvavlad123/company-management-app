package com.mhp.usermicro.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorityms")
@SequenceGenerator(name = "authority_seq", sequenceName = "authority_seq", allocationSize = 1)
public class Authority implements Serializable {

    private static final long serialVersionUID = -3993277153891087023L;

    private long id;
    private AuthorityType authorityType;

    public Authority(){}

    public Authority(long id, AuthorityType authorityType) {
        this.id = id;
        this.authorityType =authorityType;
    }

    @Id
    @Column(name = "authority_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_seq")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    public AuthorityType getAuthorityType() {
        return authorityType;
    }

    public void setAuthorityType(AuthorityType authorityType) {
        this.authorityType = authorityType;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", authorityType=" + authorityType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Authority authority = (Authority) o;

        if (id != authority.id) return false;
        return authorityType == authority.authorityType;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (authorityType != null ? authorityType.hashCode() : 0);
        return result;
    }
}