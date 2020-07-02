package com.tms.sameasme.service.post;

import com.tms.sameasme.model.tag.ETag;
import com.tms.sameasme.model.post.Post;
import com.tms.sameasme.repository.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findAllByTag(ETag tag) {
        return postRepository.findAllByTag(tag);
    }

    @Override
    public Post findPostById(Long id) {
        return postRepository.findPostById(id);
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> findAllOrOrderByMatchDate() {
        return postRepository.findAllOrOrderByMatchDate();
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }
}
