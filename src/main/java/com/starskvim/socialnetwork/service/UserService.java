package com.starskvim.socialnetwork.service;

import com.starskvim.socialnetwork.controller.dto.UserDto;
import com.starskvim.socialnetwork.controller.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto save(UserRegistrationDto registrationDto);
    void deleteByLogin(String login);

}
