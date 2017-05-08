package com.indra.sofia2.archetype.service.bean.common;

import com.google.gson.annotations.SerializedName;

public class SofiaObject {
	
	@SerializedName("_id")
	private SofiaObjectId id;
	
	public SofiaObjectId getId() {
		return id;
	}

	public void setId(SofiaObjectId id) {
		this.id = id;
	}
}
