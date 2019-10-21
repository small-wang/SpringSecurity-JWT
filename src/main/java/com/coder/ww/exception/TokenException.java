package com.coder.ww.exception;

public class TokenException extends BusinessException {

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenException(String message) {
        super(message);
    }
}
