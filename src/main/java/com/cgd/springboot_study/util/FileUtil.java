package com.cgd.springboot_study.util;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.export.ExcelBatchExportServer;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ClassPathResource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public class FileUtil {

    public static InputStream getInputStreamByPath(String path) throws IOException {
        File file = new File (path);
        if (!file.getParentFile().exists()){
            if (!file.getParentFile().mkdirs()) {
            }
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        InputStream inputStream =new FileInputStream(path)  ;
        return  inputStream ;
    }

    /**
     * 从class path读取文件
     * @param path
     * @return
     * @throws IOException
     */
    public static InputStream getInputStreamByClassPath(String path) {
        File file = new File (path);
        if (!file.getParentFile().exists()){
            if (!file.getParentFile().mkdirs()) {
            }
        }
        InputStream inputStream = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            ClassPathResource classPathResource = new ClassPathResource(path);
            inputStream=classPathResource.getInputStream();
        } catch (Exception e) {

        }

        return  inputStream ;
    }







    /**
     * @param entity
     *            表格标题属性
     * @param pojoClass
     *            Excel对象Class
     * @param dataSet
     *            Excel对象数据List
     */
    public static Workbook exportBigExcel(ExportParams entity, Class<?> pojoClass,
                                          Collection<?> dataSet) {
        ExcelBatchExportServer batachServer = ExcelBatchExportServer
                .getExcelBatchExportServer(entity, pojoClass);
        return batachServer.appendData(dataSet);
    }

    public static void closeExportBigExcel() {
        ExcelBatchExportServer batachServer = ExcelBatchExportServer.getExcelBatchExportServer(null,
                null);
        batachServer.closeExportBigExcel();
    }


}
