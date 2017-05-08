package com.indra.sofia2.archetype.auth;

import com.indra.sofia2.archetype.auth.beans.AuthResponse;

public interface AuthenticationService {
	AuthResponse authenticate (String user, String password);
}
