package com.indra.sofia2.archetype.service.bean.common;


public class Geometry {
	
	private Double[] coordinates;
	private GeometryType type;
	
	public Geometry () {
		super();
		this.coordinates = new Double [2];
	}
	
	public Double[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Double[] coordinates) {
		this.coordinates = coordinates;
	}

	public GeometryType getType() {
		return type;
	}

	public void setType(GeometryType type) {
		this.type = type;
	}
}
