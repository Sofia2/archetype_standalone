package com.indra.sofia2.archetype.service;

import com.indra.sofia2.archetype.service.bean.kpi.request.KpiDeleteRequest;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiInsertRequest;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiJoinRequest;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiLeaveRequest;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiQueryRequest;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiUpdateRequest;
import com.indra.sofia2.archetype.service.bean.kpi.response.KpiJoinResponse;
import com.indra.sofia2.archetype.service.bean.kpi.response.KpiResponse;

public interface KpiService {
	
	KpiJoinResponse joinByToken(KpiJoinRequest request);
	
	KpiResponse leaveByToken(KpiLeaveRequest request);
	
	KpiResponse query (KpiQueryRequest request);
	
	KpiResponse update (KpiUpdateRequest request);
	
	KpiResponse insert (KpiInsertRequest request);
	
	KpiResponse delete (KpiDeleteRequest request);

}
