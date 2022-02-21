package com.starskvim.socialnetwork.controller.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException (String friendLogin){
        super(String.format("User with Login %s already exists in friends", friendLogin));
    }

}
