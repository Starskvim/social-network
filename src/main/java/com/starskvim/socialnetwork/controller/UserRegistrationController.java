package com.starskvim.socialnetwork.controller;

import com.starskvim.socialnetwork.controller.dto.UserDto;
import com.starskvim.socialnetwork.controller.dto.UserRegistrationDto;
import com.starskvim.socialnetwork.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
@Validated
public class UserRegistrationController {

    private final UserService userService;

    @Operation(summary = "Регистрация нового пользователя")
    @PostMapping
    public UserDto registerUserAccount(@Valid @RequestBody UserRegistrationDto registrationDto) {
        return userService.save(registrationDto);
    }

}
