package com.indra.sofia2.archetype.security.util.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import com.indra.sofia2.archetype.auth.CustomUser;
import com.indra.sofia2.archetype.security.util.JwtTokenConst;
import com.indra.sofia2.archetype.security.util.JwtTokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenServiceImpl implements JwtTokenService {
	
	private static final String CLAIM_KEY_CREATED = "created";
	
	@Value("${jwt.secret}")
    private String secret;
	
	@Value("${jwt.expiration:86400}")
	private Long expiration;
	
	private static final Logger logger = Logger.getLogger(JwtTokenService.class);
	
	
	@Override
    public String generateToken(CustomUser u) {
    	
        Claims claims = Jwts.claims().setSubject(u.getUsername());
        claims.put(JwtTokenConst.PASSWORD_KEY, u.getPassword());
        claims.put(JwtTokenConst.SESSIONKEY_KEY, u.getSessionKey());
        
        List <String> authorities = new ArrayList<>();
        
        if (u.getAuthorities() != null) {
       	 for (GrantedAuthority authority : u.getAuthorities()) {
       		 authorities.add(authority.getAuthority());            
       	 }
        }
        
        String commaSepRoles = StringUtils.join(authorities, ',');  
    	
    	claims.put(JwtTokenConst.ROLE_KEY, commaSepRoles);
        
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, this.secret)
                .compact();
    }
    
	@Override
    public CustomUser parseToken(String token) {
    	
    	CustomUser user = null;

        try {
        	 Claims body = getClaimsFromToken(token);

             String username = body.getSubject(); 
             String password = (String) body.get(JwtTokenConst.PASSWORD_KEY);
             String sessionKey = (String) body.get(JwtTokenConst.SESSIONKEY_KEY);
             String roles = (String) body.get(JwtTokenConst.ROLE_KEY);
             
	         List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
             
	         user = new CustomUser(username, password, sessionKey, authorities);
           

        } catch (JwtException e) {
        	logger.error("Error parsing token");
        }
        return user;
    }
	
	private Claims getClaimsFromToken(String token) {
		
        Claims  claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        
        return claims;
    }
	
	private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
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
	
	private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, this.secret)
                .compact();
    }
}
