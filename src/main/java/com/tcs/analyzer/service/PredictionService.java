package com.tcs.analyzer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.api.services.prediction.Prediction;
import com.google.api.services.prediction.model.Input;
import com.google.api.services.prediction.model.Input.InputInput;
import com.google.api.services.prediction.model.Output;
import com.tcs.analyzer.utils.SentimentTypeEnum;

@Service
public class PredictionService {
	
	@Autowired
	Prediction client;
	
	@Value("${google.api.projectId}")
	private String projectId;
	
	@Value("${google.api.model}")
	private String model;
	
	public int predictPostSentiment(String post) {
		int sentimentType = -1;
		
		try {
			List<Object> params = new ArrayList<Object>();
			params.add(post);

			Input input = new Input();
			InputInput inputInput = new InputInput();
			inputInput.setCsvInstance(params);
			input.setInput(inputInput);
			
			//Output output = client.hostedmodels().predict("414649711441", "sample.sentiment", input).execute();
			Output output = client.trainedmodels().predict(projectId, model, input).execute();
		    
			SentimentTypeEnum sentimentTypeEnum = SentimentTypeEnum.getSentiment(output.getOutputLabel());
			if (sentimentTypeEnum != null) {
				sentimentType = sentimentTypeEnum.getType();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return sentimentType;
	}

}
