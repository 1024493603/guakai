package com.practice.day5.homework;

import org.junit.Test;

import java.util.Scanner;

public class Trim1 {
    public static String trim(String str) {
        if (str.length() == 0) {
            return "";
        }
        char[] chars = str.toCharArray();
        int start = 0;
        int end = 0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] != ' '){
                start = i;
                break;
            }
        }
        for (int i = chars.length-1; i > 0 ; i--) {
            if(chars[i] != ' '){
                end = i;
                break;
            }
        }
        if (start == end && chars[start] == ' ') {
            return "";
        }
        return str.substring(start,end+1);
    }

    @Test
    public void homework2() {
        String str = "I Love Java！";
        String result = str.toLowerCase();
        System.out.println(result);
    }

    public static void main(String[] args) {
        System.out.println("输入字符串");
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        String result = trim(str);
        if (result.length() == 0) {
            System.out.println("结果为空");
        } else {
            System.out.println(result);
        }
    }
}
