package com.homework_mvc.homeworkmvc.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class RegistrationDto {
    private Long id;
    @NotNull
    private String username;

    @NotNull
    private String name;

    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
