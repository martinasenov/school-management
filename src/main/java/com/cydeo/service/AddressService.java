package com.cydeo.service;

import com.cydeo.dto.AddressDTO;
import com.cydeo.entity.Address;

public interface AddressService {


    AddressDTO findById(Long id);
    void save(AddressDTO addressDTO);

}
