package com.indra.sofia2.archetype.service.bean.kpi.request;

public abstract class KpiSessionRequest extends KpiRequest {
	
	private String sessionKey;
	
	public KpiSessionRequest(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
}
