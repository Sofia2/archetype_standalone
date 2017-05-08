package com.indra.sofia2.archetype.service.impl;

import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.indra.sofia2.archetype.service.KpiService;
import com.indra.sofia2.archetype.service.PhoneService;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiDeleteRequest;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiInsertRequest;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiQueryRequest;
import com.indra.sofia2.archetype.service.bean.kpi.response.KpiResponse;
import com.indra.sofia2.archetype.service.bean.phone.PhoneWrapper;
import com.indra.sofia2.ssap.ssap.SSAPQueryType;

@Service
public class PhoneServiceImpl implements PhoneService{
	
	private static final Logger logger = Logger.getLogger(PhoneServiceImpl.class);
	
	private static final String QUERY_ALL = "db.{0}.find();";
	private static final String QUERY_GET_PHONE = "db.{0}.find('{' ''{0}.id'': ''{1}'' '}');";
	
	
	
	@Autowired
	private KpiService kpiService;
	
	@Value("${phone.kpi.ontology.name:}")
	private String phoneKpiOntologyName;
	
	@Override
	public List<PhoneWrapper> getAllPhones(String sessionKey) {
		
		List<PhoneWrapper> phones = new ArrayList<PhoneWrapper> ();
		
		try {
			
			MessageFormat form = new MessageFormat(QUERY_ALL);
			String query = form.format(new Object[] {phoneKpiOntologyName});
			
			KpiResponse responseQuery = kpiService.query(new KpiQueryRequest(sessionKey, 
														phoneKpiOntologyName, query,
														SSAPQueryType.NATIVE));
			
			logger.info("query result: " + responseQuery.getData());
			
			phones = parseAllPhoneResponse(responseQuery);
			
		} catch (Exception e) {
			logger.error("Error getting cases ", e);
		} 
		
		return phones;
	}

	@Override
	public PhoneWrapper getPhone(String sessionKey, String id) {
		
		PhoneWrapper phone = null;
		
		logger.info("get phone whith id " + id);
		
		try {
			
			MessageFormat form = new MessageFormat(QUERY_GET_PHONE);
			String query = form.format(new Object[] {phoneKpiOntologyName, id});
			
			KpiResponse responseQuery = kpiService.query(new KpiQueryRequest(sessionKey, 
														phoneKpiOntologyName, query,
														SSAPQueryType.NATIVE));
			
			logger.info("query result: " + responseQuery.getData());
			
			phone = parseGetPhoneResponse(responseQuery);
			
		} catch (Exception e) {
			logger.error("Error getting caseItem ", e);
		} 
		
		return phone;
	}
	
	private List<PhoneWrapper> parseAllPhoneResponse (KpiResponse responseQuery){
		
		Gson gson = new Gson();
		List<PhoneWrapper> cases = new ArrayList<>();
		if (responseQuery.getData() != null) {
			Type listType = new TypeToken<ArrayList<PhoneWrapper>>(){}.getType();
			cases = gson.fromJson(responseQuery.getData(), listType);		
		}
		
		return cases;
	}
	
	private PhoneWrapper parseGetPhoneResponse (KpiResponse responseQuery){
		
		Gson gson = new Gson();
		List<PhoneWrapper> caseWrapperList = new ArrayList<>();
		if (responseQuery.getData() != null) {
			Type listType = new TypeToken<ArrayList<PhoneWrapper>>(){}.getType();
			caseWrapperList = gson.fromJson(responseQuery.getData(), listType);		
		}
		PhoneWrapper phoneWrapper = (!caseWrapperList.isEmpty())?caseWrapperList.get(0) : new PhoneWrapper();
		return phoneWrapper;
	}

	@Override
	public boolean create(String sessionKey, PhoneWrapper phone) {
		
		boolean created = false;
		
		try {
			
			logger.info("create phone");
			
			Gson gson = new Gson();			
			String data = gson.toJson(phone);
			
			KpiResponse responseQuery = kpiService.insert(new KpiInsertRequest(sessionKey, 
														  phoneKpiOntologyName, data));
			
			logger.info("query result: " + responseQuery.getData());
			
			created = true;
			
		} catch (Exception e) {
			logger.error("Error creating task exec ", e);
		} 
		
		return created;
	}

	@Override
	public boolean delete(String sessionKey, String id) {
		
		PhoneWrapper phone = getPhone(sessionKey, id);
		boolean deleted = false;
		logger.info("delete phone with id " + id);
		
		if (phone != null){
			
			Gson gson = new Gson();			
			String data = gson.toJson(phone);
			KpiResponse responseQuery = kpiService.delete(new KpiDeleteRequest(sessionKey, phoneKpiOntologyName, data));
		
			logger.info("query result: " + responseQuery.getData());
			
			deleted = true;
		}
		
		return deleted;
	}

}
