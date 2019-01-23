package com.oms.commons.security.exception;

import java.text.MessageFormat;

public class NotLoginStatusException extends RuntimeException{

    public NotLoginStatusException(){

    }

    public NotLoginStatusException(String message){
        super(message);
    }

    public NotLoginStatusException(Throwable cause){
        super(cause);
    }

    public NotLoginStatusException(String message, Throwable cause){
        super(message, cause);
    }

    public NotLoginStatusException(String message, Object... args){
        this(MessageFormat.format(message, args));
    }

    public NotLoginStatusException(Enum clazz, Throwable cause, Object... args){
        this((String) MessageFormat.format(clazz.toString(), args), (Throwable) cause);
    }

    public NotLoginStatusException(Enum clazz, Object... args){
        super(MessageFormat.format(clazz.toString(), args));
    }
}
