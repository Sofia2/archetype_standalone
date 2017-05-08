package com.indra.sofia2.archetype.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import com.indra.sofia2.archetype.exception.RetryApiException;
import com.indra.sofia2.archetype.service.ConnectionService;
import com.indra.sofia2.archetype.service.KpiService;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiDeleteRequest;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiInsertRequest;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiJoinRequest;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiLeaveRequest;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiQueryRequest;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiUpdateRequest;
import com.indra.sofia2.archetype.service.bean.kpi.response.KpiJoinResponse;
import com.indra.sofia2.archetype.service.bean.kpi.response.KpiResponse;


@Service
public class KpiServiceImpl implements KpiService {
	
	private static final Logger log = Logger.getLogger(KpiServiceImpl.class);
	
	@Autowired
	private ConnectionService connectionService;

	@Override
	public KpiJoinResponse joinByToken(KpiJoinRequest request) {
		
		KpiJoinResponse kpiResponse = null;
		
		try{
			
			kpiResponse = connectionService.joinByToken(request);
		
		} catch (RetryApiException e) {
			log.error("Error join", e);
		}
		
		return kpiResponse;
	}

	@Override
	public KpiResponse leaveByToken(KpiLeaveRequest request) {
		
		KpiResponse kpiResponse = null;
		
		try {
		
			kpiResponse = connectionService.leaveByToken(request);
			
		} catch (RetryApiException e) {
			log.error("Error leaving", e);
		}
		
		return kpiResponse;
	}

	@Override
	public KpiResponse query(KpiQueryRequest request) {
		
		RetryTemplate retryTemplate = new RetryTemplate();
	    retryTemplate.setRetryPolicy(getRetryPolicy());
	    
	    final KpiQueryRequest connRequest = request;
	    KpiResponse kpiResponse = null;
	    
	    try {
	    	
	    	kpiResponse = retryTemplate.execute(new RetryCallback<KpiResponse, RetryApiException>() {		
		    	  public KpiResponse doWithRetry(RetryContext context) throws RetryApiException {
		    		  log.debug("count " +context.getRetryCount());
		    		  return connectionService.query(connRequest);
				  }			
			});
	    	
	    } catch (RetryApiException e){
	    	log.error("Error query", e);
	    }
	    
	    return kpiResponse;
	}

	@Override
	public KpiResponse update(KpiUpdateRequest request) {
		
		RetryTemplate retryTemplate = new RetryTemplate();
	    retryTemplate.setRetryPolicy(getRetryPolicy());
	    final KpiUpdateRequest connRequest = request;
	    
	    KpiResponse kpiResponse = null;
	    
	    try {
	    	kpiResponse = retryTemplate.execute(new RetryCallback<KpiResponse, RetryApiException>() {		
		    	  public KpiResponse doWithRetry(RetryContext context) throws RetryApiException {
		    		  log.debug("count " +context.getRetryCount());
		    		  return connectionService.update(connRequest);
				  }			
			});
	    } catch (RetryApiException e){
	    	log.error("Error update", e);
	    }
	    
	    return kpiResponse;
	    
	}

	@Override
	public KpiResponse insert(KpiInsertRequest request) {
		
		RetryTemplate retryTemplate = new RetryTemplate();
	    retryTemplate.setRetryPolicy(getRetryPolicy());
	    final KpiInsertRequest connRequest = request;
	    
	    KpiResponse kpiResponse = null;
	    
	    try {
	    	kpiResponse = retryTemplate.execute(new RetryCallback<KpiResponse, RetryApiException>() {		
		    	  public KpiResponse doWithRetry(RetryContext context) throws RetryApiException {
		    		  log.debug("count " +context.getRetryCount());
		    		  return connectionService.insert(connRequest);
				  }			
			});
	    } catch (RetryApiException e){
	    	log.error("Error update", e);
	    }
	    
	    return kpiResponse;	   
	}

	@Override
	public KpiResponse delete(KpiDeleteRequest request) {
		
		RetryTemplate retryTemplate = new RetryTemplate();
	    retryTemplate.setRetryPolicy(getRetryPolicy());
	    final KpiDeleteRequest connRequest = request;
	    
	    KpiResponse kpiResponse = null;
	    
	    try {
	    	kpiResponse = retryTemplate.execute(new RetryCallback<KpiResponse, RetryApiException>() {		
		    	  public KpiResponse doWithRetry(RetryContext context) throws RetryApiException {
		    		  log.debug("count " +context.getRetryCount());
		    		  return connectionService.delete(connRequest);
				  }			
			});
	    } catch (RetryApiException e){
	    	log.error("Error delete", e);
	    }
	    
	    return kpiResponse;
	}
	
	private SimpleRetryPolicy getRetryPolicy () {
		SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
	    retryPolicy.setMaxAttempts(3);
		return retryPolicy;
	}

}
