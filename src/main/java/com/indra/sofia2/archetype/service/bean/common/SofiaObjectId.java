package com.indra.sofia2.archetype.service.bean.common;

import com.google.gson.annotations.SerializedName;

public class SofiaObjectId {
	
	@SerializedName("$oid")
	private String oid;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}
}
