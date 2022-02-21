package com.starskvim.socialnetwork.controller.exceptions;

public class LoginIsAlreadyTakenException extends RuntimeException{
    public LoginIsAlreadyTakenException (String login){
        super(String.format("Login %s is already taken", login));
    }
}
