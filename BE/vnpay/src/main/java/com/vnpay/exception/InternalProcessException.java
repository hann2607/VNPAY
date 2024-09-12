package com.vnpay.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.io.Serial;
import java.net.URI;

public class InternalProcessException extends AbstractThrowableProblem {

    @Serial
    private static final long serialVersionUID = 5017035103999685011L;

    public InternalProcessException(URI type, String messageCode) {
        super(type, messageCode, Status.INTERNAL_SERVER_ERROR, null, null, null, null);
    }

    public InternalProcessException(String messageCode) {
        this(URI.create("internal-server-error"), messageCode);
    }
}
