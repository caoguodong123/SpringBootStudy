package com.cgd.springboot_study.proxyDynamicTest.cglib;

import com.cgd.springboot_study.proxyDynamicTest.jdk.testCase.Animal;
import com.cgd.springboot_study.proxyDynamicTest.jdk.testCase.Dog;
import com.cgd.springboot_study.proxyDynamicTest.jdk.testCase.MyHandler;
import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * @Author: cgd
 * @Description:
 * @Date: Created in 21:15 2020/8/2
 */
public class TestCgLibDynamicProxy {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dog.class);
        //传入一个MethodInterceptor
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            System.out.println("before");
            methodProxy.invokeSuper(o,args);
            System.out.println("after");
            return null;
        });
        Animal dog = new Dog();
        //传入一个InvocationHandler
        enhancer.setCallback((InvocationHandler) (o, method, objects) -> {
            System.out.println("before111");
            //注意这里传入的值
            method.invoke(dog,objects);
            System.out.println("after");
            return null;
        });
        Animal animal = (Animal) enhancer.create();
        animal.speak();
        animal.speakOne();
    }
}
