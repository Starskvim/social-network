package com.starskvim.socialnetwork.service;

import com.starskvim.socialnetwork.controller.dto.UserDto;
import com.starskvim.socialnetwork.controller.dto.UserRegistrationDto;
import com.starskvim.socialnetwork.controller.exceptions.InvalidEmailException;
import com.starskvim.socialnetwork.controller.exceptions.LoginIsAlreadyTakenException;
import com.starskvim.socialnetwork.mapper.UserMapper;
import com.starskvim.socialnetwork.model.User;
import com.starskvim.socialnetwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto save(UserRegistrationDto registrationDto) {

        User user = new User();

        try {
            user = userRepository.save(userMapper.toUser(registrationDto));
        } catch (DataIntegrityViolationException e){
            if(e.getCause().getCause().getMessage().contains("повторяющееся значение ключа")){
                throw new LoginIsAlreadyTakenException(registrationDto.getLogin());
            }
        }
        return userMapper.toUserDto(user);
    }

    @Override
    public void deleteByLogin(String login) {
        userRepository.deleteByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), new ArrayList<>()); // TODO ?
    }




}
