package com.cydeo.service;

import com.cydeo.dto.UserDTO;

import java.util.List;

public interface UserService {


    List<UserDTO> findAll();
    UserDTO findById(Long id);
    void save(UserDTO userDTO);

    UserDTO findByUsername(String username);

    List<UserDTO>findManagers();
    List<UserDTO>findInstructors();
    void update(UserDTO userDTO);
    void delete(String username);



}
