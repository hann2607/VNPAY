package com.vnpay.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.io.Serial;
import java.net.URI;

public class DataConflictException extends AbstractThrowableProblem {

    @Serial
    private static final long serialVersionUID = 2574553436449339264L;

    public DataConflictException(URI type, String messageCode) {
        super(type, messageCode, Status.CONFLICT, null, null, null, null);
    }

    public DataConflictException(String messageCode) {
        this(URI.create("data-conflict"), messageCode);
    }
}
