package com.practice.spring.proxy;

import com.practice.spring.service.ICourseService;
import com.practice.spring.service.Impl.CourseServiceImpl;
import org.junit.Test;

public class Client {

    //静态代理
    public static void main(String[] args) {
        DongShiZhang dongShiZhang = new DongShiZhang();
        IQianZi qianZi = new MiShu(dongShiZhang);
        qianZi.qianZi();
    }

    @Test
    public void test1() {

        //动态代理
        DongShiZhang dongShiZhang = new DongShiZhang();
        IQianZi qianZi = (IQianZi) ProxyFactory.getProxyInstance(dongShiZhang);
        qianZi.qianZi();


        //动态代理可以只写一次就让别的类的方法前后全加上开始、关闭事物
        ICourseService courseService = new CourseServiceImpl();
        ICourseService proxy = (ICourseService) ProxyFactory.getProxyInstance(courseService);
        proxy.deleteById(1);
    }
}
