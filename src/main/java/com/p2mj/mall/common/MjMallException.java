package com.p2mj.mall.common;

public class MjMallException extends RuntimeException {

    public MjMallException() {
    }

    public MjMallException(String message) {
        super(message);
    }

    /**
     * 丢出一个异常
     *
     * @param message
     */
    public static void fail(String message) {
        throw new MjMallException(message);
    }

}
