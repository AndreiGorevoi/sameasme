package com.tms.sameasme.service.post;

import com.tms.sameasme.dto.post.UpdatePostDto;
import com.tms.sameasme.model.tag.ETag;
import com.tms.sameasme.model.post.Post;

import java.util.Date;
import java.util.List;

public interface PostService {
    List<Post> getAll();
    List<Post> getAllByTag(ETag tag);
    Post getPostById(Long id);
    Post savePost(Post post);
    List<Post> getAllOrderedByMatchDate();
    List<Post> getAllOrderedByCreateDate();
    boolean deletePostById(Long id);
    List<Post> getAllFromToDate(Date fromDate, Date toDate);
    List<Post> getPostFromToDateByTag(ETag tag, Date fromDate, Date toDate);
    List<Post> getPostsByUserId(Long id);
    Post updatePost(UpdatePostDto updatedPostDto);
}
