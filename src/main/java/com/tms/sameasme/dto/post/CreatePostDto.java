package com.tms.sameasme.dto.post;

import com.tms.sameasme.model.post.Post;
import lombok.Data;

@Data
public class CreatePostDto {
    private String title;

    private String description;

    private String img;

    private String createDate;

    private String matchDate;

    public Post updatePost(CreatePostDto dto){
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setDescription(dto.getDescription());
        post.setImg(dto.getImg());
        post.setMatchDate(dto.getMatchDate());
        return post;
    }

}
