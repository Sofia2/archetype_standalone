package com.indra.sofia2.archetype.security.util.impl;

import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.indra.sofia2.archetype.exception.JwtTokenMissingException;
import com.indra.sofia2.archetype.security.util.TokenExtractor;

@Component
public class JwtHeaderTokenExtractor implements TokenExtractor {
	
	private static final Logger logger = Logger.getLogger(JwtHeaderTokenExtractor.class);

	public static String HEADER_PREFIX = "Bearer ";

    @Override
    public String extract(String header) {
    	
        if (StringUtils.isBlank(header)) {
        	logger.error("Authorization header cannot be blank!");
            throw new JwtTokenMissingException("Authorization header cannot be blank!");
        }

        if (header.length() < HEADER_PREFIX.length()) {
        	logger.error("Invalid authorization header size.");
            throw new JwtTokenMissingException("Invalid authorization header size.");
        }

        return header.substring(HEADER_PREFIX.length(), header.length());
    }
    
}
