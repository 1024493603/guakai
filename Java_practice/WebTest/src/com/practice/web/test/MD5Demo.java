package com.practice.web.test;

import com.practice.web.util.MD5Util;

public class MD5Demo {
    public static void main(String[] args) {
        String salt = "practice";
        String password = "123";
        password = MD5Util.MD5Encode(password + salt);
        System.out.println(MD5Util.MD5Encode("123"));
    }
}
