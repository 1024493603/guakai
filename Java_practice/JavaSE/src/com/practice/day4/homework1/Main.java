package com.practice.day4.homework1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("第一个数");
        int num1 = input.nextInt();
        System.out.println("第二个数");
        int num2 = input.nextInt();

        Add sum = new Add(num1, num2);
        show(sum);

        Sub sub = new Sub(num1, num2);
        show(sub);

        Multi multi = new Multi(num1, num2);
        show(multi);

        Div div = new Div(num1, num2);
        show(div);
    }

    public static void show(Cal cal) {
        int result = cal.getResult();
        System.out.println(result);
    }
}