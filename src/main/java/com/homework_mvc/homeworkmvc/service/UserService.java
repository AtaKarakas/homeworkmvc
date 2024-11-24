package com.homework_mvc.homeworkmvc.service;

import com.homework_mvc.homeworkmvc.dto.RegistrationDto;
import com.homework_mvc.homeworkmvc.dto.UserDto;
import com.homework_mvc.homeworkmvc.models.UserModel;

import java.util.List;

public interface UserService {

    void saveUser(RegistrationDto registrationDto);
    UserModel findByEmail(String email);
    UserModel findByUsername(String username);
    List<UserDto> findAll();

}
