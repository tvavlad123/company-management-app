package com.mhp.usermicro.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mhp.usermicro.entity.Position;
import com.mhp.usermicro.entity.Team;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class JwtUser implements UserDetails {

    private final             Long id;
    private String            firstName;
    private String            lastName;
    private String            username;
    private String            password;
    private final Collection<? extends GrantedAuthority> authorities;
    private String            photoLocation;
    private Position          position;
    private Team              team;
    private Boolean           enabled;
    private Date              lastPasswordResetDate;

    public JwtUser(Long id, String firstName, String lastName, String username, String password,
                   Collection<? extends GrantedAuthority> authorities, String photoLocation,
                   Position position, Team team, Boolean enabled, Date lastPasswordResetDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.photoLocation = photoLocation;
        this.position = position;
        this.team = team;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhotoLocation() {
        return photoLocation;
    }

    public Position getPosition() {
        return position;
    }

    public Team getTeam() {
        return team;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    @Override
    public String toString() {
        return "JwtUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                ", photoLocation='" + photoLocation + '\'' +
                ", position=" + position +
                ", team=" + team +
                ", enabled=" + enabled +
                ", lastPasswordResetDate=" + lastPasswordResetDate +
                '}';
    }
}
