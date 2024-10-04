package com.cydeo.service;

import com.cydeo.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SecurityService extends UserDetailsService {
    UserDTO getLoggedInUser();
}
