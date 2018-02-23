package com.cx.exception;

/**
 * @author CX
 * @create 2018/1/12
 */
public class StorageException extends RuntimeException {
    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
