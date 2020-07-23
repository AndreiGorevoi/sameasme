package com.tms.sameasme.dto.post;

import com.tms.sameasme.model.post.Post;
import com.tms.sameasme.model.tag.ETag;
import lombok.Data;

import java.util.Date;

@Data
public class UpdatePostDto {
    private Long id;
    private String title;
    private String description;
    private String img;
    private Date matchDate;
    private String contactNumber;
    private String location;
    private int amountOfPeople;
    private double price;
    private boolean showerPresent;
    private ETag tag;

    public Post updatePost(Post post){
        post.setTitle(this.title);
        post.setDescription(this.description);
        post.setImg(this.img);
        post.setMatchDate(this.matchDate);
        post.setContactNumber(this.contactNumber);
        post.setLocation(this.location);
        post.setAmountOfPeople(this.amountOfPeople);
        post.setPrice(this.price);
        post.setShowerPresent(this.showerPresent);
        return post;
    }
}
