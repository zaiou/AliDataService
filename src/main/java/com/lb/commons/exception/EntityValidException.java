package com.lb.commons.exception;

import com.lb.commons.utils.KeyValue;

/**
 * @author LB
 * @date 2017/7/31 18:03
 */
public class EntityValidException extends RuntimeException {
    private KeyValue keyValue;

    public EntityValidException() {
    }

    public EntityValidException(KeyValue keyValue) {
        this.keyValue = keyValue;
    }

    public EntityValidException(String message) {
        super(message);
    }

    public EntityValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityValidException(Throwable cause) {
        super(cause);
    }

    public EntityValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public KeyValue getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(KeyValue keyValue) {
        this.keyValue = keyValue;
    }
}
