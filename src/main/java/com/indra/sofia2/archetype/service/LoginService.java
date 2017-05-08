package com.indra.sofia2.archetype.service;

import com.indra.sofia2.archetype.security.model.LoginResponse;
import com.indra.sofia2.archetype.security.model.UserLogin;

public interface LoginService {
	
	LoginResponse login (UserLogin authLogin);

}
