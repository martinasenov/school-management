package com.cydeo.dto;

import com.cydeo.enums.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressDTO {

    private Long id;

    @NotEmpty(message = "{NotEmpty.user.address.addressInfo}")
    private String addressInfo;

    @NotNull(message = "{NotNull.user.address.state}")
    private State state;

    @NotEmpty(message = "{NotEmpty.user.address.phoneNumber}")
    private String phoneNumber;

}
