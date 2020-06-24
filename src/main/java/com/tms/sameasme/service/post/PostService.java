package com.tms.sameasme.service.post;

import com.tms.sameasme.model.ETag;
import com.tms.sameasme.model.Post;

import java.util.List;

public interface PostService {
    List<Post> findAll();
    List<Post> findAllByTeg(ETag tag);
    Post findPostById(Long id);
    Post savePost(Post post);
}
