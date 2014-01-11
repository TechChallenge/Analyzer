package com.tcs.analyzer.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.restfb.types.Comment;
import com.restfb.types.Post;
import com.tcs.analyzer.model.PostData;
import com.tcs.analyzer.service.FacebookRestService;
import com.tcs.analyzer.service.PostDataService;
import com.tcs.analyzer.service.PredictionService;
import com.tcs.analyzer.utils.FacebookMessageType;

@Controller
@RequestMapping("/analyze")
public class AnalyzeController {
	
	@Autowired
	PostDataService postDataService;
	
	@Autowired
	PredictionService predictionService;
	
	@Autowired
	FacebookRestService facebookRestService;
	
	@Value("${fb.page}")
	private String fbPage;

	@RequestMapping(value="getPostDetails", method = RequestMethod.GET)
	public String getFeeds(ModelMap model) {
		int limit = 0;
		List<PostData> postDatas = postDataService.getPosts();
		if (postDatas != null && postDatas.size() > 0) {
			limit = 10;
		}
		
		List<Post> facebookPosts = facebookRestService.getFacebookPosts(fbPage, limit);
		
		List<String> facebookFeeds = new ArrayList<String>();
		
		if (facebookPosts != null && facebookPosts.size() > 0) {
			PostData postData = null;
			for (Iterator<Post> iterator = facebookPosts.iterator(); iterator.hasNext();) {
				/*
				 * Capture posts
				 */
				Post post = (Post) iterator.next();
				facebookFeeds.add(post.getMessage());
				
				postData = new PostData();
				postData.setPostExternalId(post.getId());
				postData.setType(FacebookMessageType.POST.getType());
				postData.setMessage(post.getMessage());
				postData.setSentimentIndex(predictionService.predictPostSentiment(post.getMessage()));
				postDataService.savePost(postData);
				
				/*
				 * Capture comments
				 */
				List<Comment> comments = facebookRestService.getFacebookComments(post.getId());
				if (comments != null && comments.size() > 0) {
					for (Comment comment : comments) {
						facebookFeeds.add(comment.getMessage());
						
						postData = new PostData();
						postData.setPostExternalId(comment.getId());
						postData.setType(FacebookMessageType.COMMENT.getType());
						postData.setMessage(comment.getMessage());
						postData.setSentimentIndex(predictionService.predictPostSentiment(comment.getMessage()));
						postDataService.savePost(postData);
					}
				}
			}
		}
		
		postDatas = postDataService.getPosts();
		
		model.addAttribute("facebookFeeds", postDatas);
		
		return "facebookFeeds";
	}
}
