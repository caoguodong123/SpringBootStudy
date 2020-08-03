package com.cgd.springboot_study.util;

/**
 * @Author: cgd
 * @Description:
 * @Date: Created in 15:53 2020/7/29
 */
public class TestAES {
    public static void main(String[] args) {
        String str = AESUtil.AESEncode("123","str");
        System.out.println(str);
        String str1 = AESUtil.AESEncode("123","str1");
        System.out.println(str1);
        System.out.println(str.equals(str1));

        System.out.println("=================");
        str = "U10OuMObYhxQGB4esyAJ9w==";
        str1 = "U10OuMObYhxQGB4esyAJ9w==";
        System.out.println(str);
        String decode = AESUtil.AESDecode("163494",str1);
        System.out.println(decode);

        String en = AESUtil.AESEncode("163494","123456");
        System.out.println(en);

        System.out.println("============");
        String encode = "0xdZNXNxDZBt3e7CUTQWKQ==";
        String de = AESUtil.AESDecode("163494",encode);
        System.out.println(de);
    }
}
