package com.tcs.analyzer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PostData {

	@Id
    private String id;
	
	private String postExternalId;
	
	private String type;
	
	private String message;
	
	private int sentimentIndex;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getSentimentIndex() {
		return sentimentIndex;
	}

	public void setSentimentIndex(int sentimentIndex) {
		this.sentimentIndex = sentimentIndex;
	}
	
	public String getPostExternalId() {
		return postExternalId;
	}

	public void setPostExternalId(String postExternalId) {
		this.postExternalId = postExternalId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}	
}
