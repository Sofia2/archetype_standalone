package com.indra.sofia2.archetype.auth.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SofiaAuthUserInfo {
	
	private String id;
    private String fullName;
    private String apiKey;
    private String rol;
    private String token;
    private List<String> virtualRoles;
    
    @JsonProperty("identificacion")
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	@JsonProperty("nombrecompleto")
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	@JsonProperty("apiKey")
	public String getApiKey() {
		return apiKey;
	}
	
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	@JsonProperty("rol")
	public String getRol() {
		return rol;
	}
	
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	@JsonProperty("token")
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}

	@JsonProperty("rolesVirtuales")
	public List<String> getVirtualRoles() {
		return virtualRoles;
	}

	public void setVirtualRoles(List<String> virtualRoles) {
		this.virtualRoles = virtualRoles;
	}
}
