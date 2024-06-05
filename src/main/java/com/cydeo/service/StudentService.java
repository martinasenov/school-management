package com.cydeo.service;


import com.cydeo.dto.StudentDTO;

import java.util.List;

public interface StudentService {

   StudentDTO findById(Long id);
   List<StudentDTO> findALl();
   void save(StudentDTO studentDTO);
   StudentDTO findByEmail(String email);

}
