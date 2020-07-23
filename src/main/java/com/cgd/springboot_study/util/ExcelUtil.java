package com.cgd.springboot_study.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ExcelUtil<T> {




    /**
     * 导出Excel(.xsl)
     * @param sheetName sheet名称
     * @param title 标题
     * @param values 内容
     * @param wb HSSFWorkbook对象
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName,String []title,String [][]values, HSSFWorkbook wb){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new HSSFWorkbook();
        }
        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        // 声明列对象
        HSSFCell cell = null;
        //  创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        //创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wb;
    }

    /**
     * 导出Excel(.xslx)
     * @param sheetName sheet名称
     * @param title 标题
     * @param values 内容
     * @param wb XSSFWorkbook对象
     * @return
     */
    public static XSSFWorkbook getXSSFWorkbook(String sheetName, String []title, String [][]values, XSSFWorkbook wb){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new XSSFWorkbook();
        }
        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = wb.createSheet(sheetName);
        // 第三步，sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        XSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        //声明列对象
        XSSFCell cell = null;
        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        //创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wb;
    }

    /**
     * 根据二维数组，生成excel对象
     * @param values
     * @param index  行偏移量
     * @param wb
     * @return
     */
    public static XSSFWorkbook getXSSFWorkbook(String [][]values,int index, XSSFWorkbook wb){
        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new XSSFWorkbook();
        }
        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet =wb.getSheetAt(0);
        // 第三步，初始化row
        XSSFRow row = sheet.getRow(0);
        //创建内容
       for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + index);
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return wb;
    }

    /**
     * 根据二维数组，生成excel对象
     * @param values
     * @param index  行偏移量
     * @param shift  列偏移量
     * @param wb
     * @return
     */
    public static XSSFWorkbook getXSSFWorkbook(String [][]values,int index,int shift,XSSFWorkbook wb){
        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new XSSFWorkbook();
        }
        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet =wb.getSheetAt(0);
        // 第三步，初始化row
        XSSFRow row = sheet.getRow(0);
        //创建内容
        for(int i=0;i<values.length;i++){
            if(sheet.getRow(i + index)!=null){
                row=sheet.getRow(i + index) ;
            }else{
                row = sheet.createRow(i + index);
            }
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j+shift).setCellValue(values[i][j]);
            }
        }
        return wb;
    }

    /**
     * 生成excel文件
     * @param path
     * @param wb
     */
    public  static  void createExcelFile(String path,XSSFWorkbook wb) throws  IOException{
        File file = new File (path);
        if (!file.getParentFile().exists()){
            if (!file.getParentFile().mkdirs()) {
            }
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream out = new FileOutputStream(path);
        wb.write(out);
        out.close();
    }

}

