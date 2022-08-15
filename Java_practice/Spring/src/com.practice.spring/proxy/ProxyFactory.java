package com.practice.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    /**
     * 得到代理对象
     * @param target 目标对象
     * @return 代理对象
     */
    public static Object getProxyInstance(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(), //目标对象的类加载器
                target.getClass().getInterfaces(),  //目标对象实现的接口
                new InvocationHandler() {// 事件处理器
                    // Object proxy 代理对象
                    // Method method调用的方法
                    //Object[] args 调用的方法的参数
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("ProxyFactory.invoke 开启事物");
                        long begin = System.currentTimeMillis();

                        Object returnValue = method.invoke(target, args);

                        long end = System.currentTimeMillis();
                        long takeTime = end - begin;
                        System.out.println("takeTime: " + takeTime);
                        System.out.println("ProxyFactory.invoke 关闭事物");

                        return returnValue;
                    }
                });
    }
}
