package com.indra.sofia2.archetype.auth.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.indra.sofia2.archetype.auth.AuthenticationService;
import com.indra.sofia2.archetype.auth.beans.AuthResponse;
import com.indra.sofia2.archetype.auth.beans.SofiaAuthResponse;
import com.indra.sofia2.archetype.util.RestUtil;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Value("${authentication.url}")
	private String authenticationUrl;
	
	private static final Logger log = Logger.getLogger(AuthenticationServiceImpl.class);

	@Override
	public AuthResponse authenticate(String user, String password) {
		
		AuthResponse response = new AuthResponse(false, null);
		
		try {
			
			log.info("authenticate user " + user);
			
			String authKey = RestUtil.encrypt(user, password);
			
			RestTemplate restTemplate = new RestTemplate();
	
			HttpHeaders headers = RestUtil.getAuthHeader(authKey);
			
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
	
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
	
			List<HttpMessageConverter<?>> messageConverters = RestUtil.getMessageConverters();
			
			restTemplate.setMessageConverters(messageConverters);
			
			SofiaAuthResponse sofiaResponse = restTemplate.postForObject(authenticationUrl, 
																		 request, SofiaAuthResponse.class); 
			
			log.info("user authenticated");
			response = new AuthResponse(true, sofiaResponse);
			
			
		} catch (Exception e){
			log.error("error authenticate", e);
		}
		
		return response;
	}
	
}
