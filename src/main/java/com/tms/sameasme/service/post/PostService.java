package com.tms.sameasme.service.post;

import com.tms.sameasme.model.tag.ETag;
import com.tms.sameasme.model.post.Post;

import java.util.List;

public interface PostService {
    List<Post> findAll();
    List<Post> findAllByTag(ETag tag);
    Post findPostById(Long id);
    Post savePost(Post post);
    List<Post> findAllOrOrderByMatchDate();
    void deletePostById(Long id);
}
