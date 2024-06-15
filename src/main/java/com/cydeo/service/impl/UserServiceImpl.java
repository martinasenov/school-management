package com.cydeo.service.impl;

import com.cydeo.dto.AddressDTO;
import com.cydeo.dto.LessonDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.*;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.*;
import com.cydeo.service.AddressService;
import com.cydeo.service.CourseService;
import com.cydeo.service.LessonService;
import com.cydeo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final MapperUtil mapperUtil;
    private final CourseRepository courseRepository;
    private final LessonService lessonService;
    private final CourseService courseService;
    private final LessonRepository lessonRepository;

    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository, MapperUtil mapperUtil, CourseRepository courseRepository, LessonService lessonService, CourseService courseService, LessonRepository lessonRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.mapperUtil = mapperUtil;
        this.courseRepository = courseRepository;
        this.lessonService = lessonService;
        this.courseService = courseService;
        this.lessonRepository = lessonRepository;
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


    @Override
    public void update(UserDTO userDTO) {
        User user = userRepository.findByUserName(userDTO.getUserName());

        Long addressId = user.getAddress().getId();

        if (addressId == null) {
            throw new IllegalArgumentException("Address ID must not be null");
        }

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        user.setConfirmPassword(userDTO.getConfirmPassword());
        user.setRole(mapperUtil.convert(userDTO.getRole(),new Role()));
        user.setGender(userDTO.getGender());


        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));

        address = mapperUtil.convert(userDTO.getAddress(), address);

        addressRepository.save(address);

        user.setAddress(address);

        userRepository.save(user);


    }

    @Override
    public void delete(String username) {
        User user = userRepository.findByUserName(username);

        boolean hasAssignedLessons = !lessonRepository.findAllByInstructor(user).isEmpty();
        boolean hasAssignedCourses = !courseRepository.findByCourseManager(user).isEmpty();

        if (hasAssignedLessons) {
            throw new IllegalStateException("Cannot be deleted: assigned lesson(s)");
        } else if (hasAssignedCourses) {
            throw new IllegalStateException("Cannot be deleted: assigned course(s)");
        }

        userRepository.delete(user);
    }
}
