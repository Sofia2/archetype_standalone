package com.indra.sofia2.archetype.service.bean.kpi.response;


public class KpiJoinResponse extends KpiResponse {
	
	private String sessionKey;
	
	public KpiJoinResponse(String data, String sessionKey) {
		super(data);
		this.sessionKey = sessionKey;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
}
