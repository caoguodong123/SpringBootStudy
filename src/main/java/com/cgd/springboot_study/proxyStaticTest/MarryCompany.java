package com.cgd.springboot_study.proxyStaticTest;

/**
 * @Author: cgd
 * @Description:
 * @Date: Created in 9:19 2020/7/30
 */
public class MarryCompany implements Marry {
    private XMarry xMarry;
    public MarryCompany(XMarry xMarry) {
        this.xMarry = xMarry;
    }

    public MarryCompany() {
    }

    @Override
    public void happyMarry() {
        beforMarry();
        xMarry.happyMarry();
        afterMarry();
    }

    void beforMarry() {
        System.out.println("布置婚房");
    }

    void afterMarry() {
        System.out.println("收拾现场");
    }
}
