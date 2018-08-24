package com.mhp.usermicro.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
   
   private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
   
   @Autowired
   private UserDetailsService            userDetailsService;
   @Autowired
   private JwtTokenUtil                  jwtTokenUtil;
   @Value ("${jwt.header}")
   private String                        tokenHeader;
   
   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                   FilterChain chain) throws ServletException, IOException {
      String authToken = request.getHeader(this.tokenHeader);
      LOGGER.info("Token: " + authToken);
      if (authToken != null && authToken.startsWith("Bearer ")) {
         authToken = authToken.substring(7);
      }
      
      String username = jwtTokenUtil.getUsernameFromToken(authToken);
      
      LOGGER.info("Checking authentication for user " + username);
      LOGGER.debug("Auth: " + SecurityContextHolder.getContext().getAuthentication() + " (needs to be null)");
      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
         
         // Need to store the info in token for less db loadings
         UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
         LOGGER.debug("Found : " + userDetails);
         if (jwtTokenUtil.validateToken(authToken, userDetails)) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
               null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            LOGGER.info("Authenticated user " + username + ", setting security context.");
            SecurityContextHolder.getContext().setAuthentication(authentication);
         }
      }
      chain.doFilter(request, response);
   }
}
