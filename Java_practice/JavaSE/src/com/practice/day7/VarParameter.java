//package的作用是声明当前类所在的包，必须放在class的最上面，一个类最多只有一个
package com.practice.day7;

import java.util.Scanner;

public class VarParameter {
    //可以计算2、3、4、...个数的和
    //int...表示接收为可变参数，类型是int
    //使用可变参数时可以当作数组来使用

    public int sum(int... nums) {
        System.out.println("接受的参数个数为" + nums.length);
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result += nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        VarParameter var = new VarParameter();
        int result = var.sum(3, 4, 5);
        System.out.println(result);
        result = var.sum(3, 4, 5, 6, 7);
        System.out.println(result);
    }
}
