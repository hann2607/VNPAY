package com.vnpay.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.io.Serial;
import java.net.URI;

public class BadRequestException extends AbstractThrowableProblem {

    @Serial
    private static final long serialVersionUID = 2574553436449339264L;

    public BadRequestException(URI type, String messageCode) {
        super(type, messageCode, Status.BAD_REQUEST, null, null, null, null);
    }

    public BadRequestException(String messageCode) {
        this(URI.create("data-invalid"), messageCode);
    }
}
