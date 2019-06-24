package com.github.greenyears.core.exception;

/**
 * api exception.
 *
 * @author zhoumeiqin
 * @date 2019-06-20
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

    /**
     *
     * @param code code
     * @param message message
     * @param cause api exception
     */
    public ApiException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
