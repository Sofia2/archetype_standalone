package com.indra.sofia2.archetype.security.model;

import java.io.Serializable;

public class LoginResponse implements Serializable {
	
	
	private static final long serialVersionUID = -7715560431458014981L;
	
	private String token;
	private boolean authenticated;
	
	public LoginResponse(String token, boolean authenticated) {
		
		super();
		this.token = token;
		this.authenticated = authenticated;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

}
