package com.github.greenyears.core.exception;

/**
 * api exception.
 *
 * @author zhoumeiqin
 * @date 2019-02-21
 */
public class ApiException extends RuntimeException {
    private static final long serialVersionUID = -1701552639249164727L;
    private String code;


    public ApiException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
