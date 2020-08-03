package com.cgd.springboot_study.proxyDynamicTest.jdk.testCase;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This is a short description.
 *
 * @author Scott Smith 2019-12-02 14:13
 */
public class MyHandler implements InvocationHandler {
    private Dog target;

    public MyHandler(Dog target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {

        System.out.println("===========");
        //注意此处传值为target，是外部传入的类，不是填入proxy
        method.invoke(target, args);
        System.out.println("=====after======");

        return null;
    }
}
