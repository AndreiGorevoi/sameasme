package com.tms.sameasme.service.post;

import com.tms.sameasme.model.tag.ETag;
import com.tms.sameasme.model.post.Post;
import com.tms.sameasme.repository.post.PostRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Transactional
@Service
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAll() {
        log.debug("searching all posts");
        return postRepository.findAll();
    }

    @Override
    public List<Post> getAllByTag(ETag tag) {
        return postRepository.findAllByTag(tag);
    }

    @Override
    public Post getPostById(Long id) {
        log.debug("searching post by id: " + id);
        return postRepository.findPostById(id);
    }

    @Override
    public Post savePost(Post post) {
        log.debug("saving post with id: " + post.getId());
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
        log.debug("deleting post by id: " + id);
        Post post = postRepository.findPostById(id);
        post.setActive(false);
        postRepository.save(post);
        return true;
    }

    @Override
    public List<Post> getAllFromToDate(Date fromDate, Date toDate) {
        log.debug("searching all posts by filters:[ tag: ALL, from date: "
                + fromDate.toString() + ", to date: "+ toDate.toString());
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
        log.debug("searching all posts by filters:[ tag:  " + tag.toString()+", from date: "
                + fromDate.toString() + ", to date: "+ toDate.toString());
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

    @Override
    public List<Post> getPostsByUserId(Long id) {
        log.debug("searching all posts by user id:" + id);
        return postRepository.findPostsByUserId(id);
    }
}
