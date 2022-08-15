package com.practice.spring.proxy;

public class MiShu implements IQianZi{
    private DongShiZhang dongShiZhang;

    public MiShu(DongShiZhang dongShiZhang) {
        this.dongShiZhang = dongShiZhang;
    }

    @Override
    public void qianZi() {
        // 权限处理
        System.out.println("MiShu.qianZi before 开始事物");
        dongShiZhang.qianZi();
        System.out.println("MiShu.qianZi end 结束事物");
    }
}
