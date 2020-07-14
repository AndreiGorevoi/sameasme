package com.tms.sameasme.service.post;

import com.tms.sameasme.model.tag.ETag;
import com.tms.sameasme.model.post.Post;
import com.tms.sameasme.repository.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Transactional
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getAllByTag(ETag tag) {
        return postRepository.findAllByTag(tag);
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findPostById(id);
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllOrderedByMatchDate() {
        return postRepository.findAllOrderByMatchDate();
    }

    @Override
    public List<Post> getAllOrderedByCreateDate() {
        return postRepository.findAllOrderByCreateDate();
    }

    @Override
    public boolean deletePostById(Long id) {
        postRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Post> getAllFromToDate(Date fromDate, Date toDate) {
        //        set date time from 00:00 to 23:59
        Date fromDateModify = new Date();
        Date toDateModify = new Date();
        fromDateModify.setTime(fromDate.getTime()-10799999);
        toDateModify.setTime(toDate.getTime()+75599999);
        List<Post> postList = postRepository.findAllByMatchTime(fromDateModify,toDateModify);
        //Sort by date
         postList.sort(Comparator.comparing(Post::getCreateDate));
        Collections.reverse(postList);
         return postList;
    }

    @Override
    public List<Post> getPostFromToDateByTag(ETag tag, Date fromDate, Date toDate) {
        //        set date time from 00:00 to 23:59
        Date fromDateModify = new Date();
        Date toDateModify = new Date();
        fromDateModify.setTime(fromDate.getTime()-10799999);
        toDateModify.setTime(toDate.getTime()+75599999);
        List<Post> postList = postRepository.findPostsByMatchTimeAndTag(tag, fromDateModify, toDateModify);
        //Sort by date
        postList.sort(Comparator.comparing(Post::getCreateDate));
        Collections.reverse(postList);
        return postList;
    }
}
