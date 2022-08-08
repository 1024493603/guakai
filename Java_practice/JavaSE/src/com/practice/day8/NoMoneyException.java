package com.practice.day8;

//定义异常
public class NoMoneyException extends Exception{
    public NoMoneyException(String message) {
        // 这个message会抛出这个异常时候会打印的信息
        super(message);
    }
}
