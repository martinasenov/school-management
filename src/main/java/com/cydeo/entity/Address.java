package com.cydeo.entity;

import com.cydeo.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
public class Address extends BaseEntity {

    private String addressInfo;

    @Enumerated(EnumType.STRING)
    private State state;

    private String phoneNumber;


}
