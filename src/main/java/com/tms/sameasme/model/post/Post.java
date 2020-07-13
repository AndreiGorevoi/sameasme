package com.tms.sameasme.model.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tms.sameasme.model.BaseModel;
import com.tms.sameasme.model.tag.Tag;
import com.tms.sameasme.model.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Tag tag;

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
                ", createDate=" + createDate +
                ", matchDate=" + matchDate +
                ", contactNumber='" + contactNumber + '\'' +
                ", location='" + location + '\'' +
                ", amountOfPeople=" + amountOfPeople +
                ", price=" + price +
                ", showerPresent=" + showerPresent +
                ", tag=" + tag +
                '}';
    }
}
