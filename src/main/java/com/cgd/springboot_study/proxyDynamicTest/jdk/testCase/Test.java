package com.cgd.springboot_study.proxyDynamicTest.jdk.testCase;

import java.lang.reflect.Proxy;

/**
 * This is a short description.
 *
 * @author Scott Smith 2019-12-02 14:13
 */
public class Test {

    public static void main(String[] args) {
        Dog dog = new Dog();
        Animal proxy = (Animal) Proxy.newProxyInstance(dog.getClass().getClassLoader(),dog.getClass().getInterfaces(),new MyHandler(dog));
        proxy.speak();
        proxy.speakOne();
    }
}
