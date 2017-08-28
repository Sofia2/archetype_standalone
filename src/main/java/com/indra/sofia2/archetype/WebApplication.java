package com.indra.sofia2.archetype;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.indra.sofia2.proxy.Sofia2Initializer;


@SpringBootApplication(scanBasePackages="com.indra.sofia2")
public class WebApplication extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(WebApplication.class).initializers(new Sofia2Initializer()).run(args);

	}
}
