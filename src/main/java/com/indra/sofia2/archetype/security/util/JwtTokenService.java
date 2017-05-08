package com.indra.sofia2.archetype.security.util;

import com.indra.sofia2.archetype.auth.CustomUser;



public interface JwtTokenService {

	
	/**
     * Generates a JWT token containing username as subject, and role as additional claims. 
     * These properties are taken from the specified
     * User object. Tokens validity is infinite.
     *
     * @param u the user for which the token will be generated
     * @return the JWT token
     */
	
	String generateToken(CustomUser u);
	
	/**
	 * Parse a jwt token and returns an user entity
	 * @param token
	 * @return
	 */
	
	/**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username and role (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     *
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
	
	CustomUser parseToken(String token); 
}
