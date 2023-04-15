package com.devsh.devsh.services.exceptions;

import java.io.Serial;

public class AuthorizationErrorException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public AuthorizationErrorException(String msg) {
        super(msg);
    }
}
