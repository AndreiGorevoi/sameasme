package com.tms.sameasme.model.role;

import com.tms.sameasme.model.BaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Role extends BaseModel {

    @Column
    @Enumerated(EnumType.STRING)
    private ERole name;

}
