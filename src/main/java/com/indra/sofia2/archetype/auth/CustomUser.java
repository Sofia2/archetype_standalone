package com.indra.sofia2.archetype.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {

	private static final long serialVersionUID = -3866595787670028235L;
	private String sessionKey;

	public CustomUser(String username, String password, String sessionKey,
					  Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.sessionKey = sessionKey;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	
}
