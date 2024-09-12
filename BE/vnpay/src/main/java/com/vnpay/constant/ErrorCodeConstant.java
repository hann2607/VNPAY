package com.vnpay.constant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.MessageFormat;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorCodeConstant {
    public static final String UNAUTHORIZED = "error.authentication.unauthorize";
    public static final String INTERNAL_SERVER = "error.server.internal";
    public static final String ERROR_NOT_FOUND = "error.not-found";
    public static final String ERROR_CONFLICT = "error.conflict";
    public static final String NOT_FOUND = "error.{0}.not-found";
    public static final String REQUIRED_VALIDATE = "error.required";
    public static final String INVALID_VALIDATE = "error.invalid";
    public static final String FIELD_INVALID_VALIDATE = "error.{0}.invalid";
    public static final String INVALID_LENGTH = "error.length";
    public static final String INVALID_EXISTED = "error.{0}.existed";

    public static String getErrorCode(String code, Object... args) {
        return MessageFormat.format(code, args);
    }
}
