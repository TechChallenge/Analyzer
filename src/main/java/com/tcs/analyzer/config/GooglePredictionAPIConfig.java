package com.tcs.analyzer.config;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.prediction.Prediction;
import com.google.api.services.prediction.PredictionScopes;

@Configuration
public class GooglePredictionAPIConfig {
	
	@Bean
	public Prediction predictionClient() throws Exception {
		HttpTransport httpTransport = new NetHttpTransport();

		Set<String> scopes = new HashSet<String>();
		scopes.add(PredictionScopes.DEVSTORAGE_FULL_CONTROL);
		scopes.add(PredictionScopes.DEVSTORAGE_READ_ONLY);
		scopes.add(PredictionScopes.DEVSTORAGE_READ_WRITE);
		scopes.add(PredictionScopes.PREDICTION);
		
		JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
		
		GoogleCredential credential = new GoogleCredential.Builder().setTransport(httpTransport)
	              .setJsonFactory(JSON_FACTORY)
	              .setServiceAccountId("9354185860-obninb6145u14dro26b5vcioimt9mmb6@developer.gserviceaccount.com")
	              .setServiceAccountScopes(scopes)
	              .setServiceAccountPrivateKeyFromP12File(new File(this.getClass().getResource("/key.p12").getFile()))
	              .build();
		
		Prediction client = new Prediction.Builder(httpTransport, JSON_FACTORY, credential).build();
		return client;
	}

}
