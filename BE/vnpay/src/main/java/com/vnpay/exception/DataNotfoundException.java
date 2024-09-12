package com.vnpay.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.io.Serial;
import java.net.URI;

public class DataNotfoundException extends AbstractThrowableProblem {

    @Serial
    private static final long serialVersionUID = 2574553436449339264L;

    public DataNotfoundException(URI type, String messageCode) {
        super(type, messageCode, Status.NOT_FOUND, null, null, null, null);
    }

    public DataNotfoundException(String messageCode) {
        this(URI.create("data-notfound"), messageCode);
    }
}
