package com.cydeo.dto;

import com.cydeo.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Long id;
    private String addressInfo;
    private State state;
    private String phoneNumber;

}
