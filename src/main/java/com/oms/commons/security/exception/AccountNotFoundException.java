package com.oms.commons.security.exception;

import java.text.MessageFormat;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(){

    }

    public AccountNotFoundException(String message){
        super(message);
    }

    public AccountNotFoundException(Throwable cause){
        super(cause);
    }

    public AccountNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public AccountNotFoundException(String message, Object... args){
        this(MessageFormat.format(message, args));
    }

    public AccountNotFoundException(Enum clazz, Throwable cause, Object... args){
        this((String) MessageFormat.format(clazz.toString(), args), (Throwable) cause);
    }

    public AccountNotFoundException(Enum clazz, Object... args){
        super(MessageFormat.format(clazz.toString(), args));
    }
}
