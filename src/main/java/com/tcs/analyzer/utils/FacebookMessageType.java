package com.tcs.analyzer.utils;

public enum FacebookMessageType {
	
	POST("POST", "Postive"),
	COMMENT("COMMENT", "Negative");
	
	FacebookMessageType(String type, String label) {
		this.type = type;
		this.label = label;
	}
	
	private String type;
	
	private String label;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
