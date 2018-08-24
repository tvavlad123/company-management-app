package com.mhp.usermicro.security;

import com.mhp.usermicro.entity.Authority;
import com.mhp.usermicro.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        List<Authority> authorities = new LinkedList<>();
        authorities.add(user.getAuthority());
        return new JwtUser(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(authorities),
                user.getPhotoLocation(),
                user.getPosition(),
                user.getTeam(),
                user.isEnabled(),
                user.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityType().name()))
                .collect(Collectors.toList());
    }
}
