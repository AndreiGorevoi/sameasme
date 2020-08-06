package com.tms.sameasme.dto.post;

import com.tms.sameasme.model.post.Post;
import com.tms.sameasme.model.tag.ETag;
import lombok.Data;

import java.util.Date;

@Data
public class CreatePostDto {
    private String title;
    private String description;
    private String img;
    private Date createDate;
    private Date matchDate;
    private String contactNumber;
    private String location;
    private int amountOfPeople;
    private double price;
    private boolean showerPresent;
    private ETag tag;

    public Post updatePost(CreatePostDto dto){
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setDescription(dto.getDescription());
        post.setImg(dto.getImg());
        post.setCreateDate(dto.getCreateDate());
        post.setMatchDate(dto.getMatchDate());
        post.setContactNumber(dto.getContactNumber());
        post.setLocation(dto.getLocation());
        post.setAmountOfPeople(dto.getAmountOfPeople());
        post.setPrice(dto.getPrice());
        post.setShowerPresent(dto.showerPresent);
        return post;
    }

}
