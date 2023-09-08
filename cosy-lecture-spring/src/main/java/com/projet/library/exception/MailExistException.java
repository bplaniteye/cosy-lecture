package com.projet.library.exception;

public class MailExistException extends RuntimeException {

    public MailExistException(String message) {
        super(message);
    }    
}
