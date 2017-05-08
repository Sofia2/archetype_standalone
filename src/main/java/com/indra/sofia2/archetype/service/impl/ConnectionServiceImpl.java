package com.indra.sofia2.archetype.service.impl;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.indra.sofia2.archetype.exception.RetryApiException;
import com.indra.sofia2.archetype.service.ConnectionService;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiDeleteRequest;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiInsertRequest;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiJoinRequest;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiLeaveRequest;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiQueryRequest;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiUpdateRequest;
import com.indra.sofia2.archetype.service.bean.kpi.response.KpiJoinResponse;
import com.indra.sofia2.archetype.service.bean.kpi.response.KpiResponse;
import com.indra.sofia2.ssap.kp.implementations.rest.SSAPResourceAPI;
import com.indra.sofia2.ssap.kp.implementations.rest.exception.ResponseMapperException;
import com.indra.sofia2.ssap.kp.implementations.rest.resource.SSAPResource;

@Service
public class ConnectionServiceImpl implements ConnectionService{
	
	private static final Logger log = Logger.getLogger(ConnectionServiceImpl.class);
	
	private SSAPResourceAPI api = null;
	
	@Value("${sib.rest.api}")
	private String sibRestUrl;
	
	
	@PostConstruct
	private void init () {
		if (this.api == null)
			this.api = new SSAPResourceAPI(sibRestUrl);
	}
	
	@Override
	public KpiJoinResponse joinByToken(KpiJoinRequest request) throws RetryApiException {
		
		KpiJoinResponse kpiResponse = null;
		
		try{
		
			SSAPResource ssapJoin=new SSAPResource();		
			ssapJoin.setJoin(true);
			ssapJoin.setInstanceKP(request.getInstance());
			ssapJoin.setToken(request.getToken());
			
			log.info("Sending JOIN message to SIB:");
			
			Response response = api.insert(ssapJoin);
			
			log.info("Receiving response from SIB: ");
			
			checkResponse(response);
			
			SSAPResource bodyReturn = new SSAPResource();
			bodyReturn = api.responseAsSsap(response);
				
			kpiResponse = new KpiJoinResponse(bodyReturn.getData(), bodyReturn.getSessionKey());
			
			log.info("sessionkey " + kpiResponse.getSessionKey());
			
		} catch (Exception e) {
			log.error("Error joinByToken", e);
			throw new RetryApiException(e);
		}
		return kpiResponse;
	}
	
	@Override
	public KpiResponse leaveByToken(KpiLeaveRequest request) throws RetryApiException {
		
		KpiResponse kpiResponse = null;
		
		try{
			
			//generate LEAVE message
			SSAPResource ssapLeave = new SSAPResource();
			ssapLeave.setLeave(true);
			ssapLeave.setSessionKey(request.getSessionKey());
			
			log.info("Sending LEAVE message to SIB: ");
			
			Response response = api.insert(ssapLeave);
			
			log.info("Receiving response from SIB: ");
			
			kpiResponse = (isOkResponse(response)) ? new KpiResponse("OK"): new KpiResponse("KO");
			
		} catch (Exception e) {
			log.error("Erro leaveByToken ", e);
			throw new RetryApiException(e);
		}
		
		return kpiResponse;
	}

	@Override
	public KpiResponse query(KpiQueryRequest request) throws RetryApiException {
		
		KpiResponse kpiResponse = null;
		
		try{
			
			log.info("Sending QUERY message to SIB: ");
			
			Response response = api.query(request.getSessionKey(), request.getOntologyName(), 
									request.getQuery(), null, request.getQueryType().toString());
							
			log.info("Receiving response from SIB: ");
			
			checkResponse(response);
			kpiResponse = parseResponse (response);
			
		} catch (Exception e) {
			log.error("Error query", e);
			throw new RetryApiException(e);
		}
		
		return kpiResponse;
		
	}
	
	@Override
	public KpiResponse update(KpiUpdateRequest request) throws RetryApiException{
		
		KpiResponse kpiResponse = null;
		
		try{
			
			SSAPResource ssapUpdate = new SSAPResource();
			ssapUpdate.setSessionKey(request.getSessionKey());
			ssapUpdate.setOntology(request.getOntologyName());
			ssapUpdate.setData(request.getData());
			
			log.info("Sending UPDATE message to SIB:");
			
			Response response = api.update(ssapUpdate);		
				
			log.info("Receiving response from SIB: ");
			
			checkResponse(response);
			kpiResponse =new KpiResponse("OK");
						
		} catch (Exception e) {
			log.error("Error update", e);
			throw new RetryApiException(e);
		}
		return kpiResponse;
			    
	}
	
	@Override
	public KpiResponse insert(KpiInsertRequest request) throws RetryApiException {
		
		KpiResponse kpiResponse = null;
		
		try{
		
			SSAPResource ssapInsert = new SSAPResource();
			ssapInsert.setSessionKey(request.getSessionKey());
			ssapInsert.setOntology(request.getOntologyName());
			ssapInsert.setData(request.getData());
			
			log.info("Sending Create message to SIB:");
			
			Response response = api.insert(ssapInsert);		
				
			log.info("Receiving response from SIB: ");
			
			checkResponse(response);
			kpiResponse = parseResponse (response);
			
		} catch (Exception e) {
			log.error("Error insert", e);
			throw new RetryApiException(e);
		}
		return kpiResponse;
				
	}
	
	@Override
	public KpiResponse delete(KpiDeleteRequest request) throws RetryApiException{
		
		KpiResponse kpiResponse = null;
		
		try{
			
			SSAPResource ssapUpdate = new SSAPResource();
			ssapUpdate.setSessionKey(request.getSessionKey());
			ssapUpdate.setOntology(request.getOntologyName());
			ssapUpdate.setData(request.getData());
			
			log.info("Sending DELETE message to SIB:");
			
			Response response = api.delete(ssapUpdate);		
				
			log.info("Receiving response from SIB: ");
			
			checkResponse(response);
			kpiResponse = (isOkResponse(response)) ? new KpiResponse("OK"): new KpiResponse("KO");;
						
		} catch (Exception e) {
			log.error("Error update", e);
			throw new RetryApiException(e);
		}
		return kpiResponse;		
	    
	}

	private boolean isOkResponse (Response response) {
		
		boolean isOk = false;
		
		if (response != null) {			
			if (response.getStatus() == HttpServletResponse.SC_OK) {			
				isOk = true;
			}
		}
		
		return isOk;
	}

	private Response checkResponse(Response response) throws RetryApiException {
		
		log.info("response status " + response.getStatus());
		
		if (isOkResponse (response)) {
			  return response;
		} else {
			  throw new RetryApiException();
		}
	}
	
	private KpiResponse parseResponse (Response response) throws ResponseMapperException {
		SSAPResource bodyReturn = new SSAPResource();
		bodyReturn = api.responseAsSsap(response);
		return new KpiResponse(bodyReturn.getData());
	}
	

}
