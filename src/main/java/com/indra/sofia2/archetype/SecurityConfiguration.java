package com.indra.sofia2.archetype;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import com.indra.sofia2.archetype.auth.impl.SofiaAuthenticationProvider;
import com.indra.sofia2.archetype.security.RestAuthenticationEntryPoint;
import com.indra.sofia2.archetype.security.JwtAuthenticationTokenFilter;
import com.indra.sofia2.archetype.security.handler.SofiaAuthenticationSuccessHandler;
import com.indra.sofia2.archetype.security.util.TokenExtractor;
import com.indra.sofia2.archetype.security.util.impl.SkipPathRequestMatcher;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
		public static final String LOGIN_ENTRY_POINT = "/auth/login";
		public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/**";
		
		@Value("${jwt.header}")
	    private String tokenHeader;
	
		@Autowired
    	private SofiaAuthenticationProvider authProvider;
		
		@Autowired private AuthenticationManager authenticationManager;
		
		@Autowired
	    private RestAuthenticationEntryPoint unauthorizedHandler;
		
		@Autowired private AuthenticationFailureHandler failureHandler;
		
		@Autowired
		private TokenExtractor tokenExtractor;
		
		@Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(this.authProvider).eraseCredentials(false);
	    }
		
		@Bean
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
		
		/*
		@Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(this.authProvider).eraseCredentials(false);
	    }*/
		
		//@Bean
	    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
			
			List<String> pathsToSkip = Arrays.asList(LOGIN_ENTRY_POINT);
	        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, TOKEN_BASED_AUTH_ENTRY_POINT);
	        
			JwtAuthenticationTokenFilter authenticationTokenFilter = new JwtAuthenticationTokenFilter(tokenHeader,failureHandler, 
																									  tokenExtractor, matcher);
	        
	        authenticationTokenFilter.setAuthenticationManager(this.authenticationManager);
	        authenticationTokenFilter.setAuthenticationSuccessHandler(new SofiaAuthenticationSuccessHandler());
	        return authenticationTokenFilter;
	        
	    }
		
		

		@Override
		protected void configure(HttpSecurity httpSecurity) throws Exception {
			
			httpSecurity
            // we don't need CSRF because our token is invulnerable
            .csrf().disable()

            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

            // don't create session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

            .authorizeRequests()
      
            // allow anonymous resource requests
            .antMatchers(LOGIN_ENTRY_POINT).permitAll()
            .anyRequest().authenticated();

		    // Custom JWT based security filter
		    httpSecurity
		            .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
		
		    // disable page caching
		    httpSecurity.headers().cacheControl();
		    
		    
		}
	
}
