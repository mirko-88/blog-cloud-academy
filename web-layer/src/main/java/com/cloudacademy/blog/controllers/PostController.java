package com.cloudacademy.blog.controllers;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudacademy.blog.AddPostUseCase;
import com.cloudacademy.blog.DeletePostUseCase;
import com.cloudacademy.blog.SearchPostUseCase;
import com.cloudacademy.blog.UpdatePostUseCase;
import com.cloudacademy.blog.model.Post;

@RestController
public class PostController {
	
	private final AddPostUseCase addPostUseCase;
	private final DeletePostUseCase deletePostUseCase;
	private final SearchPostUseCase searchPostUseCase;
	private final UpdatePostUseCase updatePostUseCase;
	

	public PostController(AddPostUseCase addPostUseCase, DeletePostUseCase deletePostUseCase,
			SearchPostUseCase searchPostUseCase, UpdatePostUseCase updatePostUseCase) {
		this.addPostUseCase = addPostUseCase;
		this.deletePostUseCase = deletePostUseCase;
		this.searchPostUseCase = searchPostUseCase;
		this.updatePostUseCase = updatePostUseCase;
	}

	@PostMapping("/post")
	public Post createPost(Post post) {
		validatePostForInsert(post);
		return addPostUseCase.addPost(post);
	}
	
	@GetMapping("/post")
	public Post getPost(Post id) {
		return null;
	}
	
	@PatchMapping("/post")
	public Post updatePost(Post post) {
		validatePostForUpdate(post);
		return null;
	}
	
	@DeleteMapping("/post")
	public void deletePost(UUID id) {
		
	}
	
	private void validatePostForUpdate(Post post) {
		if (post.getId() == null) {
			throw new IllegalArgumentException("Id cannot be empty");
		}
		
		validatePostForInsert(post);
	}
	
	private void validatePostForInsert(Post post) {
		if (StringUtils.isEmpty(post.getTitle())) {
			throw new IllegalArgumentException("Title cannot be empty");
		}
		
		if (StringUtils.isEmpty(post.getContent())) {
			throw new IllegalArgumentException("Content cannot be empty");
		}
		
		if (post.getContent().length() > 1024) {
			throw new IllegalArgumentException("Content cannot be longer than 1024 characters");
		}
		
		if (StringUtils.isEmpty(post.getAuthor())) {
			throw new IllegalArgumentException("Author cannot be empty");
		}
		
		if (StringUtils.isEmpty(post.getImageUrl())) {
			throw new IllegalArgumentException("Image URL cannot be empty");
		}
		
		if (StringUtils.isEmpty(post.getCategory())) {
			throw new IllegalArgumentException("Category cannot be empty");
		}
		
		if (post.getTags() == null || post.getTags().isEmpty()) {
			throw new IllegalArgumentException("At least one tag must be provided");
		}
	}
}
