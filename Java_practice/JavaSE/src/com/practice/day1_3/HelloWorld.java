package com.practice.day1_3;

import org.junit.Test;
import java.util.Scanner;

public class HelloWorld {

    public static void main(String[] args) {
        int result = Day2_Work5(9);
        System.out.println("9的阶乘结果为:" + result);
        int[] arr = {4, 5, 6, 7};
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i]);
        }
    }

    @Test
    public void Day1_Work1() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入年份:");
        int year = input.nextInt();
        if (year < 0) {
            System.out.println("请输入正确的年份。");
            return;
        }
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            System.out.println(year + "是闰年");
        } else {
            System.out.println(year + "不是闰年");
        }
    }

    @Test
    public void Day1_Work2() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入第一个数:");
        int num1 = input.nextInt();
        System.out.println("请输入第二个数:");
        int num2 = input.nextInt();
        System.out.println("两个数为" + num1 + "和" + num2);
        int temp;
        temp = num1;
        num1 = num2;
        num2 = temp;
        System.out.println("交换之后两个数为" + num1 + "和" + num2);
    }

    @Test
    public void Day1_Work3() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入单价:");
        double prise = input.nextDouble();
        System.out.println("请输入数量:");
        int count = input.nextInt();
        System.out.println("请输入支付金额:");
        double givePrise = input.nextDouble();
        double totalPrise = prise * count;
        if (givePrise < totalPrise) {
            System.out.println("支付金额不足。");
            return;
        }
        if (totalPrise >= 500) {
            totalPrise *= 0.8;
            System.out.println("超过500打八折\t折扣价:" + totalPrise + "\t应找" + (givePrise - totalPrise));
        } else {
            System.out.println("总价:" + totalPrise + "\t应找" + (givePrise - totalPrise));
        }

    }

    @Test
    public void Day2_Work1() {
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    @Test
    public void Day2_Work2() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入行数:");
        int line = input.nextInt();
        // 正三角
        for (int i = 0; i < line; i++) {
            jinZiTa(line, i);
        }

        System.out.println();
        System.out.println();

        // 倒三角
        for (int i = line - 1; i >= 0; i--) {
            jinZiTa(line, i);
        }
    }

    private void jinZiTa(int line, int i) {
        for (int j = 0; j < line - i; j++) {
            System.out.print(" ");
        }
        for (int j = 0; j < 2 * i + 1; j++) {
            System.out.print("*");
        }
        System.out.println();
    }

    @Test
    public void Day2_Work3() {
        int oddSum = 0;
        int evenSum = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                evenSum += i;
            } else {
                oddSum += i;
            }
        }
        System.out.println("奇数和为" + oddSum + "\t" + "偶数和为" + evenSum);
    }

    @Test
    public void Day2_Work4() {
        int count = 0;
        for (int i = 1; i <= 1000; i++) {
            if (i % 5 == 0) {
                System.out.print(i + " ");
                count++;
            }
            if (count == 3) {
                System.out.println();
                count = 0;
            }
        }
    }

    public static int Day2_Work5(int num) {
        if (num == 1) {
            return 1;
        }
        return num * Day2_Work5(num - 1);
    }

    @Test
    public void Day2_Work6() {
        int j;
        for (int i = 100; i <= 200; i++) {
            for (j = 2; j <= i / 2; j++) {
                if (i % j == 0) {
                    break;
                }
            }
            if (j > i / 2) {
                System.out.print(i + " ");
            }
        }
    }

    @Test
    public void Exercise01() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入年份:");
        int year = input.nextInt();
        if (year < 0) {
            System.out.println("请输入正确的年份。");
            return;
        }

        System.out.println("请输入月份");
        int month = input.nextInt();
        if (month <= 0 || month > 12) {
            System.out.println("请输入正确的月份。");
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                System.out.println(year + "年" + month + "月有31天");
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                System.out.println(year + "年" + month + "月有30天");
                break;
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    System.out.println(year + "年" + month + "月有29天");
                } else {
                    System.out.println(year + "年" + month + "月有28天");
                }
                break;
            default:
                break;
        }
    }

    //    int[] scores={0,0,1,2,3,5,4,5,2,8,7,6,9,5,4,8,3,1,0,2,4,8,7,9,5,2,1,2,3,9};
//    求出上面数组中0-9分别出现的次数
    @Test
    public void Day3_Work3() {
        int[] counts = new int[10];
        int[] scores = {0, 0, 1, 2, 3, 5, 4, 5, 2, 8, 7, 6, 9, 5, 4, 8, 3, 1, 0, 2, 4, 8, 7, 9, 5, 2, 1, 2, 3, 9};
        for (int score : scores) {
            counts[score]++;
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(i + "出现了" + counts[i] + "次");
        }
    }

    //    int[] scores={0,0,1,2,3,5,4,5,2,8,7,6,9,5,4,8,3,1,0,2,4,8,7,9,5,2,1,2,3,9};
//    要求求出其中的奇数个数和偶数个数。
    @Test
    public void Day3_Work2() {
        int[] scores = {0, 0, 1, 2, 3, 5, 4, 5, 2, 8, 7, 6, 9, 5, 4, 8, 3, 1, 0, 2, 4, 8, 7, 9, 5, 2, 1, 2, 3, 9};
        int countOdd = 0;
        int countEven = 0;
        for (int score : scores) {
            if (score % 2 == 0) {
                countEven++;
            } else {
                countOdd++;
            }
        }
        System.out.println("奇数个数为:" + countOdd + "\t偶数个数为:" + countEven);
    }

    //选择答案: 5.B 6.D 7.A 8.B 9.A 10.C 11.B 13.C 14.B

    @Test
    public void Day3_Work16() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入一个字符串:");
        String str = input.next();
        char[] chars = str.toCharArray();
        int i;
        int j = chars.length - 1;
        for (i = 0; i < j; i++, j--) {
            if (chars[i] != chars[j]) {
                break;
            }
        }
        if (i < j) {
            System.out.println("不是回文");
        } else {
            System.out.println("是回文");
        }
    }

    @Test
    public void Day3_Work17() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入一个字符串:");
        String str = input.nextLine();
        char[] chars = str.toCharArray();
        int countLetter = 0, countSpace = 0, countNUM = 0, countAnother = 0;
        for (char aChar : chars) {
            if ((aChar >= 'a' && aChar <= 'z') || (aChar >= 'A' && aChar <= 'Z')) {
                countLetter++;
            } else if (aChar == ' ') {
                countSpace++;
            } else if (aChar >= '0' && aChar <= '9') {
                countNUM++;
            } else {
                countAnother++;
            }

        }
        System.out.println(str);
        System.out.println("字母有" + countLetter
                + "个\t空格有" + countSpace
                + "个\t数字有" + countNUM
                + "个\t其他字符有"
                + countAnother + "个");
    }
//-------------------------------------------------------------------------
// これから本番だ
// ------------------------------------------------------------------------
}