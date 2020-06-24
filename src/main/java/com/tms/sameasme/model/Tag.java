package com.tms.sameasme.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
@NoArgsConstructor
public class Tag  extends BaseModel{
    @Column
    @Enumerated(value = EnumType.STRING)
    private ETag name;
}
