package com.jasonpeng.demo.util.common;


public enum CodeEnum {
    SUCCESS(200, "SUCCESS"),
    FAIL(400, "FAIL"),
    NOT_FOUND(404, "NOT_FOUND"),
    SERVER_ERROR(500, "SERVER_ERROR"),
    BAD_REQUEST(400, "BAD_REQUEST"),
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    FORBIDDEN(403, "FORBIDDEN"),
    METHOD_NOT_ALLOWED(405, "METHOD_NOT_ALLOWED"),
    TIMEOUT(408, "TIMEOUT"),
    TOO_MANY_REQUESTS(429, "TOO_MANY_REQUESTS"),
    CUSTOM_ERROR(1000, "CUSTOM_ERROR");

    CodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code;
    public String message;
}
