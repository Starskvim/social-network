package com.starskvim.socialnetwork.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class LightUserDto {
    private Long id;
    private String login;
    private String mail;
    private String firstName;
    private String lastName;
}
