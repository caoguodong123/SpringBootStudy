package com.cgd.springboot_study.util;

import ch.qos.logback.core.util.TimeUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author kanglinan
 * @create 2019/10/14 17:37
 */
@Component
public class XmlUtil<T> {
    /**
     * 把xml模板信息放入到map里面
     * @param path
     * @return
     */
    public static Map LoadingXml(String path) {
        //导入xml文件，要注意xml文件的路径要正确
        Map map = new LinkedHashMap<Object, Object>();
        DocumentBuilder db;
        try {
            ClassPathResource classPathResource = new ClassPathResource(path);
            InputStream inputStream =classPathResource.getInputStream();
            /*实例化一个DocumentBuildFactory，一个document构造器构建的工厂*/
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //实例化一个DocumentBuilder，一个document构造器，用于生成Document文档
            db = dbf.newDocumentBuilder();
            //通过构造器的parse()方法，将一个File对象生成相应的Document文档
            Document document = db.parse(inputStream);
            /*根据字符串在document内查找相应的根，其返回值是一个节点链表*/
            NodeList nodeList = document.getElementsByTagName("Attribute");
            for (int j = 0; j < nodeList.getLength(); j++) {
                String name = document.getElementsByTagName("name").item(j).getFirstChild().getNodeValue();
                String position = document.getElementsByTagName("position").item(j).getFirstChild().getNodeValue();
                map.put(name, position);
            }
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return map;
    }
    /**
     * 把xml模板信息放入到map里面
     * @param path
     * @return
     */
    public static Map LoadingMateXML(String path) {
        //导入xml文件，要注意xml文件的路径要正确
        Map map = new LinkedHashMap<Object, Object>();
        DocumentBuilder db;
        try {
            ClassPathResource classPathResource = new ClassPathResource(path);
            InputStream inputStream =classPathResource.getInputStream();
            /*实例化一个DocumentBuildFactory，一个document构造器构建的工厂*/
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //实例化一个DocumentBuilder，一个document构造器，用于生成Document文档
            db = dbf.newDocumentBuilder();
            //通过构造器的parse()方法，将一个File对象生成相应的Document文档
            Document document = db.parse(inputStream);
            /*根据字符串在document内查找相应的根，其返回值是一个节点链表*/
            NodeList nodeList = document.getElementsByTagName("Attribute");
            for (int j = 0; j < nodeList.getLength(); j++) {
                String name = document.getElementsByTagName("name").item(j).getFirstChild().getNodeValue();
                String position = document.getElementsByTagName("position").item(j).getFirstChild().getNodeValue();
                map.put(position,name );
            }
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 把xml模板信息放入到map里面
     * @param path
     * @return
     */
    public static Map newLoadingMateXML(String path) {
        //导入xml文件，要注意xml文件的路径要正确
        Map<Object, Object> map = new LinkedHashMap();
        DocumentBuilder db;
        try {
            ClassPathResource classPathResource = new ClassPathResource(path);
            InputStream inputStream =classPathResource.getInputStream();
            /*实例化一个DocumentBuildFactory，一个document构造器构建的工厂*/
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //实例化一个DocumentBuilder，一个document构造器，用于生成Document文档
            db = dbf.newDocumentBuilder();
            //通过构造器的parse()方法，将一个File对象生成相应的Document文档
            Document document = db.parse(inputStream);
            /*根据字符串在document内查找相应的根，其返回值是一个节点链表*/
            NodeList nodeList = document.getElementsByTagName("Attribute");
            for (int j = 0; j < nodeList.getLength(); j++) {
                String value = document.getElementsByTagName("value").item(j).getFirstChild().getNodeValue();
                String position = document.getElementsByTagName("position").item(j).getFirstChild().getNodeValue();
                map.put(value,position);
            }
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 把List转换为一个二维数组
     * @param list
     * @param map
     * @param o
     * @return
     */
    public static <T> String[][] listT0Array(List<T> list, Map map, T o ){
        String[][] array=new String[1][1] ;
        try {
            //调整数组大小
            if (list != null && map != null && list.size() > 0 && map.size() > 0) {
                array = new String[list.size()][map.size()];
            }
            int j = 0;
            //双重for循环，遍历每一个值，放到array里面
            for (T t : list) {
                for (Object s : map.keySet()) {
                    Method get;
                    //sHedgingMarginRate和bHedgingMarginRate特殊，是因为getsHedgingMarginRate,getbHedgingMarginRate,其中s和b不大写（注意一下）
                    if(s.toString().equals("sHedgingMarginRate") || s.toString().equals("bHedgingMarginRate")){
                        get = o.getClass().getMethod("get" + s.toString());
                    }else {
                        get = o.getClass().getMethod("get" + StringUtil.transToUpperCase(s.toString()));
                    }
                    String value="" ;
                    int i=0 ;
                    String  str=(String)map.get(s) ;
                    if(t==null){continue;}
                    if(get.invoke(t)==null){continue;}
                    if (get.getReturnType().getName().equals( "java.util.Date") ) {
                        //把日期转换成String格式，并赋值
                       value= ((Date) get.invoke(t)).toString() ;
                    }else{
                        //直接赋值
                       value=get.invoke(t).toString() ;
                    }
                    if(StringUtil.isNotEmpty(str)){
                        i=Integer.parseInt(str) ;
                    }
                    array[j][i]=value ;
                }
                j++ ;
            }
        }catch(Exception e){
             e.printStackTrace();
        }
        return  array ;
    }

}
