package com.starskvim.socialnetwork.controller.exceptions;


public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException (String mail){
        super(String.format("Email %s is Invalid", mail));
    }
}
