package com.tms.sameasme.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class User extends BaseModel {
    @Column
    private String login;
    @Column
    private String password;
    @Column
    private String name;

}
