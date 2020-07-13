package com.tms.sameasme.controller.rest;

import com.tms.sameasme.controller.BaseController;
import com.tms.sameasme.dto.post.CreatePostDto;
import com.tms.sameasme.model.post.Post;
import com.tms.sameasme.model.tag.ETag;
import com.tms.sameasme.repository.tag.TagRepository;
import com.tms.sameasme.service.post.PostService;
import com.tms.sameasme.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("post")
public class PostRestController extends BaseController {

    private final PostService postService;
    private final UserService userService;
    private final TagRepository tagRepository;


    @Autowired
    public PostRestController(PostService postService, UserService userService, TagRepository tagRepository) {
        this.postService = postService;
        this.userService = userService;
        this.tagRepository = tagRepository;
    }

    @PostMapping(value = "/addPost")
    public Post addPost(@RequestBody CreatePostDto dto){
        Post post = dto.updatePost(dto);
        post.setUser(userService.findUserById(getUserId()));
        post.setTag(tagRepository.findTagByName(dto.getTag()));
        return postService.savePost(post);
    }
    @GetMapping(value = "/getAllPosts")
   public List<Post> getAllPosts(){
        return postService.getAllOrOrderByMatchDate();
    }

    @GetMapping(value = "/getAllOrderByMatchDate")
    public List<Post> getAllPostsOrderByMatchDate(){
        return postService.getAllOrOrderByMatchDate();
    }

    @PostMapping(value = "/getAllPostsByTeg")
    public List<Post> getAllByTeg(@RequestParam String tag){
        return (tag.equals("ALL")) ? postService.getAll() : postService.getAllByTag(ETag.valueOf(tag));
    }

    @PostMapping(value = "/dateFilter")
    public List<Post> getPostsFilterByDate(@RequestParam Date fromDate, @RequestParam Date toDate){
        return postService.getAllFromToDate(fromDate,toDate);
    }
}
