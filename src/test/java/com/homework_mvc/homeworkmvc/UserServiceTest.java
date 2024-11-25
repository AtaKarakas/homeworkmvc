package com.homework_mvc.homeworkmvc;

import com.homework_mvc.homeworkmvc.dto.RegistrationDto;
import com.homework_mvc.homeworkmvc.dto.UserDto;
import com.homework_mvc.homeworkmvc.models.Role;
import com.homework_mvc.homeworkmvc.models.UserModel;
import com.homework_mvc.homeworkmvc.repo.RoleRepository;
import com.homework_mvc.homeworkmvc.repo.UserRepository;
import com.homework_mvc.homeworkmvc.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testSaveUserSuccess() {
        // Arrange
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setName("John Doe");
        registrationDto.setUsername("johndoe");
        registrationDto.setEmail("johndoe@example.com");
        registrationDto.setPassword("password123");

        Role role = new Role();
        role.setName("USER");

        UserModel userModel = new UserModel();
        userModel.setName("John Doe");
        userModel.setUsername("johndoe");
        userModel.setEmail("johndoe@example.com");

        Mockito.when(roleRepository.findByName("USER")).thenReturn(role);
        Mockito.when(passwordEncoder.encode("password123")).thenReturn("encryptedPassword");
        Mockito.when(userRepository.save(Mockito.any(UserModel.class))).thenReturn(userModel);

        // Act
        userService.saveUser(registrationDto);

        // Assert
        ArgumentCaptor<UserModel> userCaptor = ArgumentCaptor.forClass(UserModel.class);
        Mockito.verify(userRepository).save(userCaptor.capture());
        UserModel savedUser = userCaptor.getValue();

        Assertions.assertEquals("John Doe", savedUser.getName());
        Assertions.assertEquals("johndoe", savedUser.getUsername());
        Assertions.assertEquals("johndoe@example.com", savedUser.getEmail());
        Assertions.assertEquals("encryptedPassword", savedUser.getPassword());
        Assertions.assertEquals(1, savedUser.getRoles().size());
        Assertions.assertEquals("USER", savedUser.getRoles().get(0).getName());
    }

    @Test
    void testFindByEmailSuccess() {
        // Arrange
        String email = "johndoe@example.com";
        UserModel userModel = new UserModel();
        userModel.setEmail(email);
        Mockito.when(userRepository.findByEmail(email)).thenReturn(userModel);

        // Act
        UserModel result = userService.findByEmail(email);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(email, result.getEmail());
    }

    @Test
    void testFindAllUsersSuccess() {
        // Arrange
        UserModel user1 = new UserModel();
        user1.setId(1L);
        user1.setName("John Doe");
        user1.setEmail("johndoe@example.com");

        UserModel user2 = new UserModel();
        user2.setId(2L);
        user2.setName("Jane Doe");
        user2.setEmail("janedoe@example.com");

        List<UserModel> users = Arrays.asList(user1, user2);
        Mockito.when(userRepository.findAll()).thenReturn(users);

        // Act
        List<UserDto> result = userService.findAll();

        // Assert
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("John Doe", result.get(0).getName());
        Assertions.assertEquals("janedoe@example.com", result.get(1).getEmail());
    }
}
