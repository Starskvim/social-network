package com.starskvim.socialnetwork.service;


import com.starskvim.socialnetwork.controller.dto.UserDto;
import com.starskvim.socialnetwork.controller.exceptions.UserAlreadyExistsException;
import com.starskvim.socialnetwork.controller.exceptions.UserNotFoundException;
import com.starskvim.socialnetwork.controller.exceptions.UserRefersToHimselfException;
import com.starskvim.socialnetwork.mapper.UserMapper;
import com.starskvim.socialnetwork.model.User;
import com.starskvim.socialnetwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SocialService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> findUsers (String searchRequest){
        if(StringUtils.isEmpty(searchRequest)){
            List<User> users = userRepository.findAll();
            return users.stream().map(userMapper::toUserDto).collect(Collectors.toList());
        }
        List<User> users = userRepository.findAllByFullName(searchRequest);
        return users.stream().map(userMapper::toUserDto).collect(Collectors.toList());
    }

    public UserDto findUserByLogin(String searchRequest){
        if(StringUtils.isEmpty(searchRequest)){
            throw new UserNotFoundException(searchRequest);
        }
        User user = userRepository.findByLogin(searchRequest);
        return userMapper.toUserDto(user);
    }

    public Set<UserDto> getUserFriends(String name) {
        User user = userRepository.getUserWithFriends(name);
        return user.getFriends().stream().map(userMapper::toUserDto).collect(Collectors.toSet());
    }

    @Transactional
    public void addFriendToUser(String loginFriend, String loginUser) {
        if (loginUser.equals(loginFriend)){
            throw new UserRefersToHimselfException(loginUser);
        }
        User user = userRepository.getUserWithFriends(loginUser);
        User friend = userRepository.getUserWithFriends(loginFriend);
        Set<User> userFriendsList = user.getFriends();
        Set<User> friendFriendsList = friend.getFriends();
        if(userFriendsList.contains(friend)){
            throw new UserAlreadyExistsException(loginFriend);
        }
        userFriendsList.add(friend);
        friendFriendsList.add(user);
        userRepository.save(user);
        userRepository.save(friend);
    }

    @Transactional
    public void deleteFriendInUser(String loginFriend, String loginUser) {
        User user = userRepository.getUserWithFriends(loginUser);
        User friend = userRepository.getUserWithFriends(loginFriend);
        if (friend == null){
            throw new UserNotFoundException(loginFriend);
        }
        Set<User> userFriendsList = user.getFriends();
        Set<User> friendFriendsList = friend.getFriends();
        userFriendsList.remove(friend);
        friendFriendsList.remove(user);
        userRepository.save(user);
        userRepository.save(friend);
    }
}
