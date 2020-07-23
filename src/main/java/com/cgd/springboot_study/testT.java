package com.cgd.springboot_study;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class testT {
    public static void main(String[] args) {
        Double d = 4.6;
        BigDecimal bigDecimal = new BigDecimal(d.toString());
        System.out.println(bigDecimal);
        System.out.println(d);
        String str = "SELECT opdc.*, opd.plan_time_id, opd.plan_name, opd.employee_id, opd.department_id, e.NAME AS department_name, ui.user_name AS employee_name FROM order_plan_detail_contract opdc LEFT JOIN order_plan_detail opd ON opdc.plan_time_id = opd.plan_time_id LEFT JOIN entity e ON e.id = opd.department_id LEFT JOIN user_information ui ON ui.sys_user_id = opd.employee_id WHERE 1 = 1 AND opdc.STATUS = 1 AND opd.STATUS = 1 and opd.plan_time_id like '%278%' and opdc.account in (163494,12313) UNION SELECT opdc.*, NULL AS plan_time_id, NULL AS plan_name, NULL AS employee_id, NULL AS department_id, NULL AS department_name, NULL AS employee_name FROM order_plan_detail_contract opdc WHERE opdc.plan_time_id IS NULL order by create_time desc limit 0 , 15";
        System.out.println(str.substring(0,str.indexOf("order by")));
        System.out.println(str.substring(str.indexOf("order by")));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = null;
        try {
            parse = dateFormat.parse("2020-10-1 10:22:33");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(parse);
    }
}
