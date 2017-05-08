package com.indra.sofia2.archetype.service.bean.kpi.request;


public abstract class KpiOntologyRequest extends KpiSessionRequest {
	
	private String ontologyName;
	
	public KpiOntologyRequest(String sessionKey, String ontologyName) {
		super(sessionKey);
		this.ontologyName = ontologyName;
	}

	public String getOntologyName() {
		return ontologyName;
	}

	public void setOntologyName(String ontologyName) {
		this.ontologyName = ontologyName;
	}
}
