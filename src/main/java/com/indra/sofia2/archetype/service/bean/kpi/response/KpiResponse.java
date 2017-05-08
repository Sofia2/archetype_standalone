package com.indra.sofia2.archetype.service.bean.kpi.response;

public class KpiResponse {
	
	private String data;
	
	public KpiResponse(String data) {
		super();		
		this.data = data;		
	}

	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
}
