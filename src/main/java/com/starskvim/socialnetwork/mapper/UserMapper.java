package com.starskvim.socialnetwork.mapper;

import com.starskvim.socialnetwork.controller.dto.UserDto;
import com.starskvim.socialnetwork.controller.dto.UserRegistrationDto;
import com.starskvim.socialnetwork.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper
public abstract class UserMapper {

    @Lazy
    @Autowired
    protected BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "friends", ignore = true)
    @Mapping(target = "password", expression = "java(bCryptPasswordEncoder.encode(userRegistrationDto.getPassword()))")
    public abstract User toUser (UserRegistrationDto userRegistrationDto);

    public abstract UserDto toUserDto(User user);
}
