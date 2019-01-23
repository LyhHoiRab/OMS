package com.oms.commons.security.exception;

import java.text.MessageFormat;

public class AccountStatusException extends RuntimeException{

    public AccountStatusException(){

    }

    public AccountStatusException(String message){
        super(message);
    }

    public AccountStatusException(Throwable cause){
        super(cause);
    }

    public AccountStatusException(String message, Throwable cause){
        super(message, cause);
    }

    public AccountStatusException(String message, Object... args){
        this(MessageFormat.format(message, args));
    }

    public AccountStatusException(Enum clazz, Throwable cause, Object... args){
        this((String) MessageFormat.format(clazz.toString(), args), (Throwable) cause);
    }

    public AccountStatusException(Enum clazz, Object... args){
        super(MessageFormat.format(clazz.toString(), args));
    }
}
