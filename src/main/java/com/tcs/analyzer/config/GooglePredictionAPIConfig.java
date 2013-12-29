package com.tcs.analyzer.config;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${google.api.accountId}")
	private String accountId;
	
	@Value("${google.api.keyPath}")
	private String keyPath;
	
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
	              .setServiceAccountId(accountId)
	              .setServiceAccountScopes(scopes)
	              .setServiceAccountPrivateKeyFromP12File(new File(this.getClass().getResource(keyPath).getFile()))
	              .build();
		
		Prediction client = new Prediction.Builder(httpTransport, JSON_FACTORY, credential).build();
		return client;
	}

}
