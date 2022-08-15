package com.practice.spring.proxy;

public class Client {

    //静态代理
    public static void main(String[] args) {
        DongShiZhang dongShiZhang = new DongShiZhang();
        IQianZi qianZi = new MiShu(dongShiZhang);
        qianZi.qianZi();
    }
}
