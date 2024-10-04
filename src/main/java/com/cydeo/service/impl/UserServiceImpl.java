package com.cydeo.service.impl;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.*;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.*;
import com.cydeo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final MapperUtil mapperUtil;
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           AddressRepository addressRepository,
                           MapperUtil mapperUtil,
                           CourseRepository courseRepository,
                           LessonRepository lessonRepository,
                           @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.mapperUtil = mapperUtil;
        this.courseRepository = courseRepository;
        this.lessonRepository = lessonRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> findAll() {

        return userRepository.findAll().stream()
                .map(user -> mapperUtil.convert(user,new UserDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        return userRepository.findById(id)
                .map(user -> mapperUtil.convert(user, new UserDTO()))
                .orElse(null);
    }

    @Override
    public void save(UserDTO userDTO) {

        Address address=mapperUtil.convert(userDTO.getAddress(),new Address());
        User user=mapperUtil.convert(userDTO,new User());

        user.setAddress(address);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userRepository.save(user);
    }

    @Override
    public List<UserDTO> findManagers() {
        return userRepository.findByRole_Description("Manager").stream()
                .map(user -> mapperUtil.convert(user,new UserDTO()))
                .toList();
    }


    @Override
    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new EntityNotFoundException("User with username: "+username+" does not exist");
        }
        return mapperUtil.convert(user, new UserDTO());
    }


    @Override
    public List<UserDTO> findInstructors() {
        return userRepository.findByRole_Description("Instructor").stream()
                .map(user -> mapperUtil.convert(user,new UserDTO()))
                .toList();
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
