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

    @PostMapping(value = "/")
    public Post addPost(@RequestBody CreatePostDto dto){
        Post post = dto.updatePost(dto);
        post.setUser(userService.findUserById(getUserId()));
        post.setTag(tagRepository.findTagByName(dto.getTag()));
        return postService.savePost(post);
    }
    @GetMapping(value = "/all")
   public List<Post> getAllPosts(){
        return postService.getAllOrderedByCreateDate();
    }

    @GetMapping(value = "/user")
    public List<Post> getPostForUser(){
        return postService.getPostsByUserId(getUserId());
    }

    @PostMapping(value = "/filtered")
    public List<Post> getPostsByAllFilters(@RequestParam String tag,
                                           @RequestParam Date fromDate,
                                           @RequestParam Date toDate
                                           )
    {
        if(tag.equalsIgnoreCase("ALL")){
            return postService.getAllFromToDate(fromDate,toDate);
        }else {
            return postService.getPostFromToDateByTag(ETag.valueOf(tag),fromDate,toDate);
        }
    }

    @DeleteMapping(value = "/post")
    public String deleteUserPost(@RequestParam Long id){
        Post postToDelete = postService.getPostById(id);
        if(postToDelete.getUser().getId().equals(getUserId())){
            postToDelete.setActive(false);
            postService.savePost(postToDelete);
            return "fine!";
        }else {
            return "there is not your post MAN!";
        }

    }

    @PostMapping(value = "/{id}")
    public Post getPostById(@PathVariable Long id){
        return postService.getPostById(id);
    }
}
