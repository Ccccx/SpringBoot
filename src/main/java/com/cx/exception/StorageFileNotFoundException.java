package com.cx.exception;

/**
 * @author CX
 * @create 2018/1/12
 */
public class StorageFileNotFoundException extends StorageException {
    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
