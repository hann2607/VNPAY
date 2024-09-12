package com.vnpay.enumeration;

import lombok.Getter;

import java.net.URI;

@Getter
public enum ResponseType {
    INTERNAL_SERVER_ERROR(500, "error.internal-server-error", "Internal Server Error", null),
    UNAUTHORIZED(401, "error.unauthorized", "Unauthorized", null),
    BAD_REQUEST(400, "error.bad-request", "Bad Request", null),
    NOT_FOUND(404, "error.not-found", "Not Found", null),
    CONFLICT(409, "error.conflict", "Conflict", null),

    REQUEST_BODY_MISSING(400, "error.request-body-missing", "Request Body Missing", null),
    FORBIDDEN(403, "error.forbidden", "Forbidden", null),
    METHOD_NOT_ALLOWED(405, "error.method-not-allowed", "Method Not Allowed", null),
    NOT_ACCEPTABLE(406, "error.not-acceptable", "Not Acceptable", null),
    UNSUPPORTED_MEDIA_TYPE(415, "error.unsupported-media-type", "Unsupported Media Type", null),
    ;

    private final int status;
    private final String messageCode;
    private final String message;
    private final URI type;

    ResponseType(int status, String messageCode, String message, URI type) {
        this.status = status;
        this.messageCode = messageCode;
        this.message = message;
        this.type = type;
    }
}
