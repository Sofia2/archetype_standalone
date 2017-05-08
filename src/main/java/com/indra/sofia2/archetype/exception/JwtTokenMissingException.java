package com.indra.sofia2.archetype.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenMissingException extends AuthenticationException {
	
	private static final long serialVersionUID = -7868295978819095998L;

	public JwtTokenMissingException(String msg) {
        super(msg);
    }
}
