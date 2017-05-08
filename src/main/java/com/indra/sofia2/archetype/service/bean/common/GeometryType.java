package com.indra.sofia2.archetype.service.bean.common;

import com.google.gson.annotations.SerializedName;

public enum GeometryType {
	
	@SerializedName("Point")
	Point("Point");
	
	private String name;
	
	private GeometryType(String name) {
		this.name = name;
	}
	
    public String getName() {
        return name;
    }
}
