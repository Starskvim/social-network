package com.starskvim.socialnetwork.controller;


import com.starskvim.socialnetwork.controller.dto.FriendDto;
import com.starskvim.socialnetwork.controller.dto.UserDto;
import com.starskvim.socialnetwork.model.User;
import com.starskvim.socialnetwork.service.SocialService;
import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@Validated
public class UserController {

    private final SocialService socialService;

    @GetMapping("/find")
    @Operation(summary = "Поиск пользователей", security = @SecurityRequirement(name = "basicAuth"))
    public List<UserDto> findUsers (@RequestParam(required = false) String searchRequest){
        return socialService.findUsers(searchRequest);
    }

    @GetMapping("/findByLogin")
    @Operation(summary = "Поиск пользователя по логину", security = @SecurityRequirement(name = "basicAuth"))
    public UserDto findUser (@RequestParam(required = false) String searchRequest){
        return socialService.findUserByLogin(searchRequest);
    }

    @GetMapping("/user/myFriends")
    @Operation(summary = "Получение своих друзей", security = @SecurityRequirement(name = "basicAuth"))
    public Set<FriendDto> getUserFriends (@ApiIgnore Principal principal){
        return socialService.getUserFriends(principal.getName());
    }

    @PostMapping("/user/addFriend")
    @Operation(summary = "Добавление друга по логину", security = @SecurityRequirement(name = "basicAuth"))
    public void addFriendToUser (@RequestParam String loginFriend, @ApiIgnore Principal principal){
        socialService.addFriendToUser(loginFriend, principal.getName());
    }

    @DeleteMapping ("/user/deleteFriend")
    @Operation(summary = "Удаление друга по логину", security = @SecurityRequirement(name = "basicAuth"))
    public void deleteFriendInUser (@RequestParam String loginFriend, @ApiIgnore Principal principal){
        socialService.deleteFriendInUser(loginFriend, principal.getName());
    }

}
