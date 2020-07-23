package com.cgd.springboot_study;

import com.cgd.springboot_study.model.DailyTransactionDetails;
import com.cgd.springboot_study.util.ExcelUtil;
import com.cgd.springboot_study.util.FileUtil;
import com.cgd.springboot_study.util.XmlUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: cgd
 * @Description:
 * @Date: Created in 17:33 2020/7/22
 */
public class testExcel {
    public static void main(String[] args) throws IOException {
        export("D:"+ "DailyTransactionDetails.xlsx");
    }
    static void export(String exportExcelPath) throws IOException {
        List<DailyTransactionDetails> list= new ArrayList<>();
        DailyTransactionDetails dailyTransactionDetails = new DailyTransactionDetails();
        dailyTransactionDetails.setAccount("1");
        dailyTransactionDetails.setAmount(56.3);
        list.add(dailyTransactionDetails);
        // 根据模板和模板描述信息生成二维数组
        // 根据模板描述信息生成对应的Map
        String path = "template/DailyTransactionDetailsList.xml";
        Map m = XmlUtil.LoadingXml(path);
        // 根据excel模板生成 wb对象
        String filepath = "template/DailyTransactionDetailsList.xlsx";
        XSSFWorkbook wb = new XSSFWorkbook(FileUtil.getInputStreamByClassPath(filepath));

        //把list 生成二维数组
        String[][] array = XmlUtil.listT0Array(list, m, new DailyTransactionDetails());

        //根据二维数组生成excel文件
        //index表示从第几行开始放记录（一般0放表头，1放行标题，从2开始放list记录）
        ExcelUtil.createExcelFile(exportExcelPath, ExcelUtil.getXSSFWorkbook(array, 2, wb));
    }
}
