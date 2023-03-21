package com.devsh.devsh.services.exceptions;

import java.io.Serial;

public class EmptyResultDataAccessException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public EmptyResultDataAccessException(String message) {
        super(message);
    }
}
