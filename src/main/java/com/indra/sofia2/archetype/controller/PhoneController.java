package com.indra.sofia2.archetype.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.indra.sofia2.archetype.auth.CurrentUser;
import com.indra.sofia2.archetype.auth.CustomUser;
import com.indra.sofia2.archetype.service.PhoneService;
import com.indra.sofia2.archetype.service.bean.phone.Phone;
import com.indra.sofia2.archetype.service.bean.phone.PhoneWrapper;


@RestController
public class PhoneController {
	
	@Autowired
	private PhoneService phoneService;
	
	@RequestMapping(value = "/phone/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public  @ResponseBody List<PhoneWrapper> list (@CurrentUser CustomUser user) {
		return phoneService.getAllPhones(user.getSessionKey());		
	}
	
	@RequestMapping(value = "/phone/{id}", method = RequestMethod.GET, 
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public  @ResponseBody Phone detail (@AuthenticationPrincipal CustomUser user, @PathVariable(value="id")String id) {		
		
		return phoneService.getPhone(user.getSessionKey(), id).getPhone();
	}
	
	@RequestMapping(value = "/phone/create", method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public  @ResponseBody Map<String, Boolean> create (@CurrentUser CustomUser user, @RequestBody Phone phone) {
		
		PhoneWrapper wrapper = new PhoneWrapper();
		wrapper.setPhone(phone);
		boolean created = phoneService.create(user.getSessionKey(), wrapper);
		return Collections.singletonMap("created", created);		
	}
	
	@RequestMapping(value = "/phone/{id}", method = RequestMethod.DELETE, 
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public  @ResponseBody Map<String, Boolean> delete (@AuthenticationPrincipal CustomUser user, @PathVariable(value="id")String id) {		
	
		boolean deleted = phoneService.delete(user.getSessionKey(), id);
		return Collections.singletonMap("deleted", deleted);
		
	}

}
