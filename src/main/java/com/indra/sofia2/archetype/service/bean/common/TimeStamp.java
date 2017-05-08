package com.indra.sofia2.archetype.service.bean.common;

import java.beans.Transient;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

import com.indra.sofia2.archetype.util.CalendarUtil;


public class TimeStamp {
	private String $date;
	

	public TimeStamp(String date) {
		super();
		this.$date = date;
	}

	@JsonProperty("$date")
	public String get$Date() {
		return $date;
	}

	public void set$Date(String date) {
		this.$date = date;
	}
	
	@Transient
	public Date getDate () {
		return CalendarUtil.convert (this.$date);
	}
}
