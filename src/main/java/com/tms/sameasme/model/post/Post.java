package com.tms.sameasme.model.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tms.sameasme.model.BaseModel;
import com.tms.sameasme.model.tag.Tag;
import com.tms.sameasme.model.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private String createDate;

    @Column
    private String matchDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Tag tag;

}
