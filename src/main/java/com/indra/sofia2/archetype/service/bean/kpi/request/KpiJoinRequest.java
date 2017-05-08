package com.indra.sofia2.archetype.service.bean.kpi.request;

public class KpiJoinRequest extends KpiRequest {
	
	private String token;
	private String instance;

	public KpiJoinRequest(String token, String instance) {
		this.token = token;
		this.instance = instance;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}
}
