package com.starskvim.socialnetwork.controller.dto;


import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserRegistrationDto {
    @NotNull
    private String login;
    @NotNull
    private String password;
    @Email
    private String mail;
    @Length.List({
            @Length(min = 3, message = "The field must be at least 3 characters"),
            @Length(max = 25, message = "The field must be less than 25 characters")
    })
    private String firstName;
    @NotNull
    private String lastName;
}
