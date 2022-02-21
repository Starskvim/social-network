package com.starskvim.socialnetwork.controller.exceptions;

public class UserRefersToHimselfException extends RuntimeException {
    public UserRefersToHimselfException (String login){
        super(String.format("User with Login %d tried to add myself as a friend", login));
    }
}
