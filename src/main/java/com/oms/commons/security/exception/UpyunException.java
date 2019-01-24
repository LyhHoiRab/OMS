package com.oms.commons.security.exception;

import java.text.MessageFormat;

public class UpyunException extends RuntimeException{

    public UpyunException(){

    }

    public UpyunException(String message){
        super(message);
    }

    public UpyunException(Throwable cause){
        super(cause);
    }

    public UpyunException(String message, Throwable cause){
        super(message, cause);
    }

    public UpyunException(String message, Object... args){
        this(MessageFormat.format(message, args));
    }

    public UpyunException(Enum clazz, Throwable cause, Object... args){
        this((String) MessageFormat.format(clazz.toString(), args), (Throwable) cause);
    }

    public UpyunException(Enum clazz, Object... args){
        super(MessageFormat.format(clazz.toString(), args));
    }
}
