package com.indra.sofia2.archetype.service.bean.phone;

public class Phone  {
	
	private String id;
	private String name;
	private String snippet;
	private String imageUrl;
	
	public Phone() {
		super();
	}

	public Phone(String id, String name, String snippet,String imageUrl) {
		super();
		this.id = id;
		this.name = name;
		this.snippet = snippet;
		this.imageUrl = imageUrl;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getSnippet() {
		return snippet;
	}

	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}		
}
