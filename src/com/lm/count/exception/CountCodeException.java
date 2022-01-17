package com.lm.count.exception;

/**
 * 代码检测异常
 *
 * @author limin
 * @since 2021-12-28
 */
public class CountCodeException extends RuntimeException {
    public CountCodeException(String msg) {
        super(msg);
    }
}
