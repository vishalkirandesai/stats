package com.n26.stats.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,
        reason = "One or more required parameters are null.")
public class MissingInformationException extends Exception{
    static final long serialVersionUID = -884551835111683516L;

    public MissingInformationException(String message) {
        super(message);
    }
}
