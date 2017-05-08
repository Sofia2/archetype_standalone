package com.indra.sofia2.archetype.service;

import com.indra.sofia2.archetype.exception.RetryApiException;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiDeleteRequest;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiInsertRequest;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiJoinRequest;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiLeaveRequest;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiQueryRequest;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiUpdateRequest;
import com.indra.sofia2.archetype.service.bean.kpi.response.KpiJoinResponse;
import com.indra.sofia2.archetype.service.bean.kpi.response.KpiResponse;

public interface ConnectionService {
	
	public KpiJoinResponse joinByToken(KpiJoinRequest request) throws RetryApiException;
	
	public KpiResponse leaveByToken(KpiLeaveRequest request) throws RetryApiException;
	
	public KpiResponse query (KpiQueryRequest request) throws RetryApiException;
	
	public KpiResponse update (KpiUpdateRequest request) throws RetryApiException;
	
	public KpiResponse insert (KpiInsertRequest request) throws RetryApiException;
	
	public KpiResponse delete (KpiDeleteRequest request) throws RetryApiException;
}
