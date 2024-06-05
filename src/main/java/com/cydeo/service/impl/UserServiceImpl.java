package com.cydeo.service.impl;

import com.cydeo.dto.AddressDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Address;
import com.cydeo.entity.User;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.AddressRepository;
import com.cydeo.repository.RoleRepository;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.AddressService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final RoleRepository roleRepository;
    private final MapperUtil mapperUtil;

    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository, RoleRepository roleRepository, MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.roleRepository = roleRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<UserDTO> findAll() {
         List<User> users=userRepository.findAll();
        return users.stream()
                .map(user -> {
                    UserDTO userDTO=mapperUtil.convert(user,new UserDTO());
                    if (user.getAddress() != null){
                        AddressDTO addressDTO=mapperUtil.convert(user.getAddress(),new AddressDTO());
                        userDTO.setAddress(addressDTO);
                    }
                    return userDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        return mapperUtil.convert(userRepository.findById(id),new UserDTO());
    }

    @Override
    public void save(UserDTO userDTO) {

        Address address=mapperUtil.convert(userDTO.getAddress(),new Address());
        User user=mapperUtil.convert(userDTO,new User());

        addressRepository.save(address);

        user.setAddress(address);

        userRepository.save(user);
    }

    @Override
    public List<UserDTO> findManagers() {
        return userRepository.findAll().stream()
                .filter(user -> user.getRole().getDescription().equals("Manager"))
                .map(user -> mapperUtil.convert(user,new UserDTO()))
                .collect(Collectors.toList());
    }


    @Override
    public UserDTO findByUsername(String username) {
        return mapperUtil.convert(userRepository.findByUserName(username),new UserDTO());
    }


    @Override
    public List<UserDTO> findInstructors() {
        return userRepository.findAll().stream()
                .filter(user -> user.getRole().getDescription().equals("Instructor"))
                .map(user -> mapperUtil.convert(user,new UserDTO()))
                .collect(Collectors.toList());
    }
}
