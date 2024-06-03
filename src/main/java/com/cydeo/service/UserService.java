package com.cydeo.service;

import com.cydeo.dto.UserDTO;

import java.util.List;

public interface UserService {


    List<UserDTO> findAll();
    UserDTO findById(Long id);
    void save(UserDTO userDTO);

}
