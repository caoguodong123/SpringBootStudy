package com.cgd.springboot_study;

import com.cgd.springboot_study.config.ExcelConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class SpringbootStudyApplicationTests {

    @Autowired
    private ExcelConfig excelConfig;
    @Test
    void contextLoads() {
        Map<String, String> map = excelConfig.getDailyTransactionDetails();
        System.out.println(map);
    }

}
