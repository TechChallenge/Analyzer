package com.tcs.analyzer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

@Configuration
public class SpringMongoConfig {
	
	@Value("${mongo.db.url}")
	private String mongoDBURL;
	
	@Value("${mongo.db.port}")
	private int mongoDBPort;
	
	@Value("${mongo.db.schema}")
	private String mongoDBSchema;

	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(mongoDBURL, mongoDBPort), mongoDBSchema);
	}
	
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}
	
}
