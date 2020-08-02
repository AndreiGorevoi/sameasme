package com.tms.sameasme.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@MappedSuperclass
@Data
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean active = true;

}
