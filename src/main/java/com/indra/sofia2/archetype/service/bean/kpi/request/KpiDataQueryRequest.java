package com.indra.sofia2.archetype.service.bean.kpi.request;


public abstract class KpiDataQueryRequest extends KpiOntologyRequest {
	
	private String data;
	
	public KpiDataQueryRequest(String sessionKey, String ontologyName, String data) {
		super(sessionKey, ontologyName);
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
