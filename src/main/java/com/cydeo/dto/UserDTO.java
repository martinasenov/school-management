package com.cydeo.dto;

import com.cydeo.enums.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    private Long id;

    @NotEmpty(message = "{NotEmpty.user.firstName}")
    private String firstName;

    @NotEmpty(message = "{NotEmpty.user.lastName}")
    private String lastName;

    @NotEmpty(message = "{NotEmpty.user.username}")
    private String userName;

    @NotEmpty(message = "{NotEmpty.user.password}")
    private String password;

    @NotEmpty(message = "{NotEmpty.user.confirmPassword}")
    private String confirmPassword;

    @NotNull(message = "{NotNull.user.gender}")
    private Gender gender;

    @NotNull(message = "{NotNull.user.role}")
    private RoleDTO role;

    @Valid
    @NotNull(message = "{NotNull.user.address}")
    private AddressDTO address;

    public UserDTO(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
