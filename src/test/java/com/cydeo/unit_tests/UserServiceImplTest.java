package com.cydeo.unit_tests;

import com.cydeo.dto.AddressDTO;
import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Address;
import com.cydeo.entity.Role;
import com.cydeo.entity.User;
import com.cydeo.enums.State;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.impl.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


    @Mock
    private UserRepository userRepository;


    @Spy
    private MapperUtil mapperUtilSpy = new MapperUtil(new ModelMapper());

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    public void testFindAll() {
        User user1 = new User("John", "Doe", "johndoe", "password", "confirmPassword", null, null, null);
        User user2 = new User("Jane", "Doe", "janedoe", "password", "confirmPassword", null, null, null);
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        when(userRepository.findAll()).thenReturn(userList);

        UserDTO userDTO1 = new UserDTO(1L, "John", "Doe");
        UserDTO userDTO2 = new UserDTO(2L, "Jane", "Doe");
        when(mapperUtilSpy.convert(user1, new UserDTO())).thenReturn(userDTO1);
        when(mapperUtilSpy.convert(user2, new UserDTO())).thenReturn(userDTO2);

        List<UserDTO> result = userService.findAll();

        verify(userRepository).findAll();
        verify(mapperUtilSpy).convert(user1, new UserDTO());
        verify(mapperUtilSpy).convert(user2, new UserDTO());

        assertEquals(2, result.size());
        assertEquals(userDTO1, result.get(0));
        assertEquals(userDTO2, result.get(1));
    }


    @Test
    public void findById_Test() {

        User user = new User("John", "Doe", "johndoe", "password", "confirmPassword", null, null, null);
        user.setId(1L);

        UserDTO userDTO1 = new UserDTO(1L, "John", "Doe");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        when(mapperUtilSpy.convert(user, new UserDTO())).thenReturn(userDTO1);

        UserDTO userDTO = userService.findById(1L);

        verify(userRepository).findById(1L);

        verify(mapperUtilSpy).convert(user, new UserDTO());

        assertEquals(userDTO1.getId(), userDTO.getId());
        assertEquals(userDTO1.getFirstName(), userDTO.getFirstName());
        assertEquals(userDTO1.getLastName(), userDTO.getLastName());
    }


    @Test
    public void save_Test() {

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(1L);
        addressDTO.setAddressInfo("Baker Street");
        addressDTO.setState(State.ALABAMA);
        addressDTO.setPhoneNumber("123456789");

        UserDTO userDTO = new UserDTO(1L, "Martin", "Asenov");
        userDTO.setAddress(addressDTO);

        User user = new User(1L, "Martin", "Asenov");
        Address address = new Address();
        address.setId(addressDTO.getId());
        address.setAddressInfo(addressDTO.getAddressInfo());
        address.setState(addressDTO.getState());
        address.setPhoneNumber(addressDTO.getPhoneNumber());
        user.setAddress(address);

        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.save(userDTO);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        User capturedUser = userCaptor.getValue();


        verify(mapperUtilSpy).convert(eq(userDTO.getAddress()), any(Address.class));
        verify(mapperUtilSpy).convert(eq(userDTO), any(User.class));

        assertEquals(userDTO.getId(), capturedUser.getId());
        assertEquals(userDTO.getFirstName(), capturedUser.getFirstName());
        assertEquals(userDTO.getLastName(), capturedUser.getLastName());
        assertEquals(userDTO.getAddress().getAddressInfo(), capturedUser.getAddress().getAddressInfo());
    }

    @Test
    public void findManagers_Test() {

        User user1 = new User();
        User user2 = new User();

        Role role = new Role("Manager");

        user1.setRole(role);
        user2.setRole(role);
        List<User> managers=List.of(user1,user2);

        when(userRepository.findByRole_Description("Manager")).thenReturn(managers);

        UserDTO userDTO1 = new UserDTO();
        UserDTO userDTO2 = new UserDTO();
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setDescription("Manager");
        userDTO1.setRole(roleDTO);
        userDTO2.setRole(roleDTO);

        when(mapperUtilSpy.convert(user1, new UserDTO())).thenReturn(userDTO1);
        when(mapperUtilSpy.convert(user2, new UserDTO())).thenReturn(userDTO2);


        List<UserDTO> result = userService.findManagers();

        verify(userRepository).findByRole_Description("Manager");

        assertEquals(2, result.size());
        assertEquals(user1.getRole().getDescription(), result.get(0).getRole().getDescription());
        assertEquals(user2.getRole().getDescription(), result.get(1).getRole().getDescription());

    }


    @Test
    public void findByUsername_Test(){
        User user = new User();
        user.setUserName("martin.asenov@gmail.com");

        when(userRepository.findByUserName("martin.asenov@gmail.com")).thenReturn(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("martin.asenov@gmail.com");
        when(mapperUtilSpy.convert(user, new UserDTO())).thenReturn(userDTO);

        userDTO = userService.findByUsername("martin.asenov@gmail.com");

        verify(userRepository).findByUserName("martin.asenov@gmail.com");
        verify(mapperUtilSpy).convert(user, new UserDTO());

        assertEquals(user.getUserName(), userDTO.getUserName());
    }


    @Test
    public void findByUsername_Should_Throw_Exception(){
        User user = new User();
        user.setUserName("martin.asenov@gmail.com");

        when(userRepository.findByUserName("martin.asenov@gmail.com")).thenReturn(null);

        userRepository.findByUserName(user.getUserName());

        verify(userRepository).findByUserName("martin.asenov@gmail.com");

        assertThrows(EntityNotFoundException.class, () -> userService.findByUsername("martin.asenov@gmail.com"));
    }

}