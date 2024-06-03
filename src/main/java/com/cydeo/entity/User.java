package com.cydeo.entity;

import com.cydeo.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity{


    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String userName;

    private String password;
    private String confirmPassword;

    @ManyToOne
    private Role role;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    private Address address;

}