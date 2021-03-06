package com.starskvim.socialnetwork.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String login;
    private String mail;
    private String firstName;
    private String lastName;
    private List<LightUserDto> friends = new ArrayList<>();
}
