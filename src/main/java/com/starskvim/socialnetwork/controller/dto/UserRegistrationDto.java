package com.starskvim.socialnetwork.controller.dto;


import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserRegistrationDto {
    @Length.List({
            @Length(min = 4, message = "The login must be at least 3 characters"),
            @Length(max = 15, message = "The login must be less than 25 characters")
    })
    @NotNull
    private String login;
    @Length.List({
            @Length(min = 6, message = "The password must be at least 3 characters"),
            @Length(max = 32, message = "The password must be less than 25 characters")
    })
    @NotNull
    private String password;
    @Email
    @NotNull
    private String mail;
    @NotNull
    @Length.List({
            @Length(min = 2, message = "The first name must be at least 3 characters"),
            @Length(max = 25, message = "The first name be less than 25 characters")
    })
    private String firstName;
    @NotNull
    @Length.List({
            @Length(min = 2, message = "The last name must be at least 3 characters"),
            @Length(max = 25, message = "The last name be less than 25 characters")
    })
    private String lastName;
}
