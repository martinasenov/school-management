package com.cydeo.service.impl;

import com.cydeo.dto.AddressDTO;
import com.cydeo.entity.Address;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.AddressRepository;
import com.cydeo.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl  implements AddressService {

    private final AddressRepository addressRepository;
    private final MapperUtil mapperUtil;

    public AddressServiceImpl(AddressRepository addressRepository, MapperUtil mapperUtil) {
        this.addressRepository = addressRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public AddressDTO findById(Long id) {
        return mapperUtil.convert(addressRepository.findById(id),new AddressDTO());
    }

    @Override
    public void save(AddressDTO addressDTO) {
        addressRepository.save(mapperUtil.convert(addressDTO,new Address()));
    }
}