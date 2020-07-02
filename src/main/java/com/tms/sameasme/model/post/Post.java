package com.tms.sameasme.model.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tms.sameasme.model.BaseModel;
import com.tms.sameasme.model.tag.Tag;
import com.tms.sameasme.model.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Post extends BaseModel {
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String img;
    @Column
    private Date createDate;
    @Column
    private Date matchDate;
    @Column
    private String contactNumber;
    @Column
    private String location;
    @Column
    private int amountOfPeople;
    @Column
    private double price;
    @Column
    private boolean showerPresent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Tag tag;

}
