package com.indra.sofia2.archetype.service;

import java.util.List;

import com.indra.sofia2.archetype.service.bean.phone.PhoneWrapper;

public interface PhoneService {
	
	/**
	 * Get all phones
	 * @param sessionKey
	 * @return
	 */
	
	List<PhoneWrapper> getAllPhones(String sessionKey);
	
	/**
	 * Get a phone by id
	 * @param sessionKey
	 * @param id
	 * @return
	 */
	
	PhoneWrapper getPhone(String sessionKey, String id);
	
	/**
	 * Create a phone
	 * @param sessionKey
	 * @param phone
	 * @return
	 */
	
	boolean create (String sessionKey, PhoneWrapper phone);
	
	/**
	 * Delete a phone
	 * @param sessionKey
	 * @param id
	 * @return
	 */
	
	boolean delete (String sessionKey, String id);

}
