package com.tcs.analyzer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

@Configuration
public class FacebookRestClient {
	
	@Value("${fb.api.accountKey}")
	private String accountKey;
	
	@Bean
	public FacebookClient facebookClient() {
		FacebookClient facebookClient = null;
	
		try {
			facebookClient = new DefaultFacebookClient(accountKey);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return facebookClient;
	}

}
