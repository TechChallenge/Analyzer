package com.tcs.analyzer.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.tcs.analyzer.model.PostData;

@Repository
public class PostDataService {
	
	@Autowired
    private MongoTemplate mongoTemplate;
	
	public static final String COLLECTION_NAME = "postData";
	
	public void savePost(PostData postData) {
        if (!mongoTemplate.collectionExists(PostData.class)) {
            mongoTemplate.createCollection(PostData.class);
        }
        
        /*
         * Check if the same post already exists in the db
         */
        if (getPostForExternalId(postData.getPostExternalId()) == null) {
        	postData.setId(UUID.randomUUID().toString());
            mongoTemplate.insert(postData, COLLECTION_NAME);
        } else {
        	System.out.println("Found");
        }
    }
	
	public PostData getPostForExternalId(String externalId) {
		PostData postData = mongoTemplate.findOne(query(where("postExternalId").is(externalId)), PostData.class);
		return postData;
	}

}
