package com.homework_mvc.homeworkmvc.service.impl;

import com.homework_mvc.homeworkmvc.dto.RegistrationDto;
import com.homework_mvc.homeworkmvc.dto.UserDto;
import com.homework_mvc.homeworkmvc.models.Role;
import com.homework_mvc.homeworkmvc.models.UserModel;
import com.homework_mvc.homeworkmvc.repo.RoleRepository;
import com.homework_mvc.homeworkmvc.repo.UserRepository;
import com.homework_mvc.homeworkmvc.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        UserModel user = new UserModel();
        user.setName(registrationDto.getName());
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role role = roleRepository.findByName("USER");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public UserModel findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserModel findByUsername(String username) {
        return null;
    }

    @Override
    public List<UserDto> findAll() {
        List<UserModel> users = userRepository.findAll();
        return users.stream().map(this::mapToUser).collect(Collectors.toList());
    }

    private UserDto mapToUser(UserModel user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
