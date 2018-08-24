package com.mhp.usermicro.security;

import com.mhp.usermicro.entity.AuthorityType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    static final String CLAIM_KEY_ID = "id";
    static final String CLAIM_KEY_FIRSTNAME = "fname";
    static final String CLAIM_KEY_LASTNAME = "lname";
    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_AUDIENCE = "audience";
    static final String CLAIM_KEY_CREATED = "created";
    static final String CLAIM_KEY_AUTHORITIES="authorities";
    static final String CLAIM_KEY_AUTHORITY="authority";
    static final String CLAIM_KEY_EXPIRATION="expiration";

    private static final String AUDIENCE_UNKNOWN = "unknown";
    private static final String AUDIENCE_WEB = "web";
    private static final String AUDIENCE_MOBILE = "mobile";
    private static final String AUDIENCE_TABLET = "tablet";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public String getAudienceFromToken(String token) {
        String audience;
        try {
            final Claims claims = getClaimsFromToken(token);
            audience = (String) claims.get(CLAIM_KEY_AUDIENCE);
        } catch (Exception e) {
            audience = null;
        }
        return audience;
    }

    public Collection<GrantedAuthority> getAuthoritiesFromToken(String token){
        Collection<GrantedAuthority> authorities;
        try{
            final Claims claims = getClaimsFromToken(token);
            authorities = (List)claims.get(CLAIM_KEY_AUTHORITIES);
        } catch (Exception e){
            authorities = null;
        }
        return authorities;
    }

    public String getAuthorityFromToken(String token){
        String authority;
        try{
            final Claims claims = getClaimsFromToken(token);
            authority = (String)claims.get(CLAIM_KEY_AUTHORITY);
        } catch (Exception e){
            authority = null;
        }
        return authority;
    }

    public Long getIdFromToken(String token){
        Long id;
        try{
            final Claims claims = getClaimsFromToken(token);
            id = (Long)claims.get(CLAIM_KEY_ID);
        } catch (Exception e){
            id = null;
        }
        return id;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        System.out.println(System.currentTimeMillis());
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    private String generateAudience(Device device) {
        String audience = AUDIENCE_UNKNOWN;
        if (device.isNormal()) {
            audience = AUDIENCE_WEB;
        } else if (device.isTablet()) {
            audience = AUDIENCE_TABLET;
        } else if (device.isMobile()) {
            audience = AUDIENCE_MOBILE;
        }
        return audience;
    }

    private Boolean ignoreTokenExpiration(String token) {
        String audience = getAudienceFromToken(token);
        return (AUDIENCE_TABLET.equals(audience) || AUDIENCE_MOBILE.equals(audience));
    }

    public String generateToken(JwtUser user, Device device) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_ID,user.getId());
        claims.put(CLAIM_KEY_USERNAME, user.getUsername());
        claims.put(CLAIM_KEY_AUDIENCE, generateAudience(device));
        claims.put(CLAIM_KEY_CREATED, new Date());
        List<GrantedAuthority> authorities =(List)user.getAuthorities();
        claims.put(CLAIM_KEY_AUTHORITY, authorities.get(0).getAuthority());
        claims.put(CLAIM_KEY_EXPIRATION,generateExpirationDate());
        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        final String username = getUsernameFromToken(token);
        final String authority = getAuthorityFromToken(token);
        if (!username.equals(user.getUsername()))
            LOGGER.info("Invalid Token because of username;");
        if (isTokenExpired(token))
            LOGGER.info("Invalid Token because token is expired");
        if (!user.getAuthorities().contains(new SimpleGrantedAuthority(authority))) {
            LOGGER.info("Invalid Token because of authorities");
        }
        return (username.equals(user.getUsername())
                && !isTokenExpired(token)
                &&user.getAuthorities().contains(new SimpleGrantedAuthority(authority))
        );
    }
}