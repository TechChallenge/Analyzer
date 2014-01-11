package com.tcs.analyzer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Comment;
import com.restfb.types.Page;
import com.restfb.types.Post;

@Service
public class FacebookRestService {
	
	@Autowired
	FacebookClient facebookClient;

	public List<Post> getFacebookPosts(String pageId) {
		/*
		 * Get the required page to fetch post / comments
		 */
		Page page = facebookClient.fetchObject(pageId, Page.class);

		/*
		 * Fetch Posts
		 */
		//Connection<Post> myFeed = facebookClient.fetchConnection(page.getId() + "/feed", Post.class, Parameter.with("limit", 10), Parameter.with("offset", 0));
		Connection<Post> myFeed = facebookClient.fetchConnection(page.getId() + "/feed", Post.class, Parameter.with("offset", 0));

		List<Post> posts = myFeed.getData();
		return posts;
	}
	
	public List<Comment> getFacebookComments(String postId) {
		/*
		 * Fetch Posts
		 */
		Connection<Comment> commentsConn = facebookClient.fetchConnection(postId + "/comments", Comment.class);

		List<Comment> comments = commentsConn.getData();
		return comments;
	}
}
