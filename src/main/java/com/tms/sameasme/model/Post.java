package com.tms.sameasme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private  User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Tag tag;

}
