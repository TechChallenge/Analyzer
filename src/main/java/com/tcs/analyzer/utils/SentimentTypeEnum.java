package com.tcs.analyzer.utils;

public enum SentimentTypeEnum {
	
	POSITIVE(1, "Postive"),
	NEGATIVE(2, "Negative"),
	NEUTRAL(3, "Neutral");
	
	SentimentTypeEnum(int type, String label) {
		this.type = type;
		this.label = label;
	}
	
	private int type;
	
	private String label;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public static SentimentTypeEnum getSentiment(String label) {
		if (POSITIVE.getLabel().equalsIgnoreCase(label)) {
			return POSITIVE;
		} else if (NEGATIVE.getLabel().equalsIgnoreCase(label)) {
			return NEGATIVE;
		} else {
			return NEUTRAL;
		}
	}
}