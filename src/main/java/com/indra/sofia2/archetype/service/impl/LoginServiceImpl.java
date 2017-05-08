package com.indra.sofia2.archetype.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.indra.sofia2.archetype.auth.AuthenticationService;
import com.indra.sofia2.archetype.auth.CustomUser;
import com.indra.sofia2.archetype.auth.beans.AuthResponse;
import com.indra.sofia2.archetype.security.model.LoginResponse;
import com.indra.sofia2.archetype.security.model.UserLogin;
import com.indra.sofia2.archetype.security.util.JwtTokenService;
import com.indra.sofia2.archetype.service.KpiService;
import com.indra.sofia2.archetype.service.LoginService;
import com.indra.sofia2.archetype.service.bean.kpi.request.KpiJoinRequest;
import com.indra.sofia2.archetype.service.bean.kpi.response.KpiJoinResponse;

@Service
public class LoginServiceImpl implements LoginService {
	
	private static final Logger logger = Logger.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private AuthenticationService authService;
	
	@Autowired
	private KpiService kpiService;
	
	@Value("${web.kpi.token:}")
	private String webKpiToken;
	
	@Value("${web.kpi.instance:}")
	private String webKpiInstance;
	
	@Autowired
	JwtTokenService jwtTokenService;
	

	@Override
	public LoginResponse login(UserLogin authLogin) {
		
		logger.info("try to login user" + authLogin.getUsername());
		
		AuthResponse authResponse = authService.authenticate(authLogin.getUsername(), authLogin.getPassword());
		LoginResponse resp = new LoginResponse("",false);
		
		if (authResponse.isAuthenticated()) {
        	
        	KpiJoinResponse joinResponse = kpiService.joinByToken(new KpiJoinRequest(webKpiToken, webKpiInstance));
        		        	
        	List<String> roles = authResponse.getResponse().getUser().getVirtualRoles();
        	roles.add(authResponse.getResponse().getUser().getRol());
        	//In roles are the user role and the virtual roles
        	
        	String commaSepRoles = StringUtils.join(roles, ',');    	
        	List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(commaSepRoles);
        	
        	CustomUser user  = new CustomUser(authLogin.getUsername(), authLogin.getPassword(), 
        									  joinResponse.getSessionKey(), authorities);
        	
        	
        	String token = jwtTokenService.generateToken(user);
        	resp = new LoginResponse(token, true);
         } 
		
		return resp;
	}

}
