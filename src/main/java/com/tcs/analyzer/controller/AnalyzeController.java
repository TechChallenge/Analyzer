package com.tcs.analyzer.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.tcs.analyzer.model.PostData;
import com.tcs.analyzer.service.PostDataService;
import com.tcs.analyzer.service.PredictionService;

@Controller
@RequestMapping("/analyze")
public class AnalyzeController {
	
	@Autowired
	PostDataService postDataService;
	
	@Autowired
	PredictionService predictionService;

	@RequestMapping(value="getPostDetails", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		/*
		 * This is just good for testing purpose, need to implement Facebook Login feature to receive Access Token for long term use
		 */
		FacebookClient facebookClient = new DefaultFacebookClient("CAACEdEose0cBAEFiSr20Y1cWf6XWGdomr8zs7qKyR4s40a1o1IOLHihZCWu6ZCORqrmMuH1kYC2ZCx0KkzWl3zOtlXigbtD4mR9TboyIO4PQF0ZAl5MNZBl9a7IIIZBELjHiQXHgmwqynkeMOa36ngIi9ynxKybb3GSZA9WfZB4SOk668YWvttJinywwx7j7Dwhz0cdgJm2I5gZDZD");
		
		/*
		 * Get the required page to fetch post / comments
		 */
		Page page = facebookClient.fetchObject("awesomeAspirants", Page.class);
		
		/*
		 * Fetch Posts
		 */
		Connection<Post> myFeed = facebookClient.fetchConnection(page.getId() + "/feed", Post.class, 
																	Parameter.with("limit", 10), Parameter.with("offset", 0));
		
		List<String> facebookFeeds = new ArrayList<String>();
		
		if (myFeed != null && myFeed.getData() != null && myFeed.getData().size() > 0) {
			for (Iterator<Post> iterator = myFeed.getData().iterator(); iterator.hasNext();) {
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
