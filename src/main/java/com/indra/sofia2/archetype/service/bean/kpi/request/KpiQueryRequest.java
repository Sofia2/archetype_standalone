package com.indra.sofia2.archetype.service.bean.kpi.request;

import com.indra.sofia2.ssap.ssap.SSAPQueryType;

public class KpiQueryRequest extends KpiOntologyRequest {
	
	private String query;
	private SSAPQueryType queryType;
	
	public KpiQueryRequest(String sessionKey, String ontologyName, String query) {
		super(sessionKey, ontologyName);
		this.query = query;
	}
	
	public KpiQueryRequest(String sessionKey, String ontologyName, String query, SSAPQueryType queryType) {
		super(sessionKey, ontologyName);
		this.query = query;
		this.queryType = queryType;
	}

	public String getQuery() {
		return query;
	}
	
	public void setQuery(String query) {
		this.query = query;
	}

	public SSAPQueryType getQueryType() {
		return queryType;
	}

	public void setQueryType(SSAPQueryType queryType) {
		this.queryType = queryType;
	}
}
