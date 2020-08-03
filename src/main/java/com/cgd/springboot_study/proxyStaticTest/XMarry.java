package com.cgd.springboot_study.proxyStaticTest;

/**
 * @Author: cgd
 * @Description:
 * @Date: Created in 9:18 2020/7/30
 */
public class XMarry implements Marry {
    @Override
    public void happyMarry() {
        System.out.println("x marry ... happy");
    }
}
