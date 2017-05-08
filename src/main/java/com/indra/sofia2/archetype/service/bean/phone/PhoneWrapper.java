package com.indra.sofia2.archetype.service.bean.phone;

import com.google.gson.annotations.SerializedName;
import com.indra.sofia2.archetype.service.bean.common.SofiaObject;

public class PhoneWrapper extends SofiaObject {
	
	@SerializedName("archetype_phone")
	private Phone phone;

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

}
