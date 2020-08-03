package com.cgd.springboot_study.proxyDynamicTest.jdk.testCase;

/**
 * This is a short description.
 *
 * @author Scott Smith 2019-12-02 13:57
 */
public class Dog implements Animal {

    @Override
    public void speak() {
        System.out.println("wang wang wang ...");
    }

    @Override
    public void speakOne() {
        System.out.println("aaaa");
    }
}
