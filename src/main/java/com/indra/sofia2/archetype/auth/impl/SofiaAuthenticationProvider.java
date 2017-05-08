package com.indra.sofia2.archetype.auth.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.indra.sofia2.archetype.auth.CustomUser;
import com.indra.sofia2.archetype.security.model.JwtAuthenticationToken;
import com.indra.sofia2.archetype.security.util.JwtTokenService;

@Component
public class SofiaAuthenticationProvider implements AuthenticationProvider {
	
	private static final Logger logger = Logger.getLogger(SofiaAuthenticationProvider.class);
	
	@Autowired
    private JwtTokenService jwtTokenService;
	

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
			JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
			String token = jwtAuthenticationToken.getToken();
        
			logger.info("trying to authenticate with token");
	        
	        CustomUser user = jwtTokenService.parseToken(token);
	        
	        if (user != null) {	        
	        	return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
	        } else{ 
	        	return null;
	        }
	}

	
	@Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));        
    }
	
}
