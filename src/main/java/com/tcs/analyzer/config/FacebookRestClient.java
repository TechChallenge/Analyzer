package com.tcs.analyzer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

@Configuration
public class FacebookRestClient {
	
	@Bean
	public FacebookClient facebookClient() {
		FacebookClient facebookClient = null;
	
		try {
			facebookClient = new DefaultFacebookClient("CAACEdEose0cBAEFiSr20Y1cWf6XWGdomr8zs7qKyR4s40a1o1IOLHihZCWu6ZCORqrmMuH1kYC2ZCx0KkzWl3zOtlXigbtD4mR9TboyIO4PQF0ZAl5MNZBl9a7IIIZBELjHiQXHgmwqynkeMOa36ngIi9ynxKybb3GSZA9WfZB4SOk668YWvttJinywwx7j7Dwhz0cdgJm2I5gZDZD");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return facebookClient;
	}

}
