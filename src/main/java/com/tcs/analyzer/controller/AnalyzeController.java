package com.tcs.analyzer.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.restfb.types.Post;
import com.tcs.analyzer.model.PostData;
import com.tcs.analyzer.service.FacebookRestService;
import com.tcs.analyzer.service.PostDataService;
import com.tcs.analyzer.service.PredictionService;

@Controller
@RequestMapping("/analyze")
public class AnalyzeController {
	
	@Autowired
	PostDataService postDataService;
	
	@Autowired
	PredictionService predictionService;
	
	@Autowired
	FacebookRestService facebookRestService;

	@RequestMapping(value="getPostDetails", method = RequestMethod.GET)
	public String getFeeds(ModelMap model) {
		List<Post> facebookPosts = facebookRestService.getFacebookPost("awesomeAspirants");
		
		List<String> facebookFeeds = new ArrayList<String>();
		
		if (facebookPosts != null && facebookPosts.size() > 0) {
			for (Iterator<Post> iterator = facebookPosts.iterator(); iterator.hasNext();) {
				Post post = (Post) iterator.next();
				facebookFeeds.add(post.getMessage());
				
				PostData postData = new PostData();
				postData.setPostExternalId(post.getId());
				postData.setMessage(post.getMessage());
				postData.setSentimentIndex(predictionService.predictPostSentiment(post.getMessage()));
				postDataService.savePost(postData);
			}
		}
		
		model.addAttribute("facebookFeeds", facebookFeeds);
		
		return "facebookFeeds";
	}
}
