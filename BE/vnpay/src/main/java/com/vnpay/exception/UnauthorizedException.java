package com.vnpay.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.io.Serial;
import java.net.URI;

public class UnauthorizedException extends AbstractThrowableProblem {

    @Serial
    private static final long serialVersionUID = -6810134007413964408L;

    public UnauthorizedException(URI type, String messageCode) {
        super(type, messageCode, Status.UNAUTHORIZED, null, null, null, null);
    }

    public UnauthorizedException(String messageCode) {
        this(URI.create("unauthorized"), messageCode);
    }
}
