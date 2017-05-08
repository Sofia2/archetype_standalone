package com.indra.sofia2.archetype.util;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.codec.Base64;

public class RestUtil {
	
	public static String encrypt (String user, String password) {
		String key = user + ":" + password;		
	    String encryptedKey =  new String(Base64.encode(key.getBytes()), Charset.forName("UTF-8"));
	    key = "Basic " + encryptedKey;
		return key;
	}
	
	public static HttpHeaders getAuthHeader (String authKey) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8); 
		headers.set("Authorization", authKey);
		return headers;
	}
	
	public static List<HttpMessageConverter<?>> getMessageConverters(){
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new MappingJackson2HttpMessageConverter());    
		messageConverters.add(new FormHttpMessageConverter());
		return messageConverters;
	}

}
