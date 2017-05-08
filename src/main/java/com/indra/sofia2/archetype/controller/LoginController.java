package com.indra.sofia2.archetype.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.indra.sofia2.archetype.security.model.LoginResponse;
import com.indra.sofia2.archetype.security.model.UserLogin;

import com.indra.sofia2.archetype.service.LoginService;

@RestController
@RequestMapping(value = "/auth")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public  @ResponseBody LoginResponse login (@RequestBody UserLogin authLogin) {
		return loginService.login(authLogin);		
	}		
	
}
