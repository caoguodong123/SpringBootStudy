package com.cgd.springboot_study.proxyStaticTest;

/**
 * @Author: cgd
 * @Description:
 * @Date: Created in 9:22 2020/7/30
 */
public class testStaticMarry {
    public static void main(String[] args) {
        XMarry xMarry = new XMarry();
        Marry marry = new MarryCompany(xMarry);

        marry.happyMarry();
    }
}
