package com.cydeo.service;

import com.cydeo.dto.RoleDTO;

import java.util.List;

public interface RoleService {


    List<RoleDTO> findAll();

    RoleDTO findById(Long id);

}
