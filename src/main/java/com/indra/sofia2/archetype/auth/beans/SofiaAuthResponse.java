package com.indra.sofia2.archetype.auth.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class SofiaAuthResponse {
	private String sessionKey;
	
	private SofiaAuthUserInfo user;

	@JsonProperty("SessionKey")
	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	@JsonProperty("user")
	public SofiaAuthUserInfo getUser() {
		return user;
	}

	public void setUser(SofiaAuthUserInfo user) {
		this.user = user;
	}
	
}
