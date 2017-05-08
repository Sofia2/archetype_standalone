package com.indra.sofia2.archetype.auth.beans;

public class AuthResponse {
	
	private boolean isAuthenticated;
	private SofiaAuthResponse response;
	
	public AuthResponse(boolean isAuthenticated, SofiaAuthResponse response) {
		super();
		this.isAuthenticated = isAuthenticated;
		this.response = response;
	}

	public boolean isAuthenticated() {
		return isAuthenticated;
	}
	
	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
	
	public SofiaAuthResponse getResponse() {
		return response;
	}
	
	public void setResponse(SofiaAuthResponse response) {
		this.response = response;
	}
}
