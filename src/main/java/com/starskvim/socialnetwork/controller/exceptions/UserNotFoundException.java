package com.starskvim.socialnetwork.controller.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException (String searchLogin){
        super(String.format("User with Login %s not found", searchLogin));
    }

}
