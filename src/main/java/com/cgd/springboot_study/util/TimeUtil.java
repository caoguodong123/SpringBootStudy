package com.cgd.springboot_study.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    private final static Logger logger = LoggerFactory.getLogger(TimeUtil.class);

    public  static Timestamp getTimesTamp(){
        Date date= new java.sql.Date(new Date().getTime());
        long longTime = date.getTime();
        Timestamp timestamp = new Timestamp(longTime);
        return timestamp;
        
    }

    /**
     * 把日期转为20190710 字符串格式
     * @param date
     * @return
     */
    public  static String turnTimeToString(Date date){
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd") ;
        return sDateFormat.format(date) ;
    }

    /**
     * 转换日期格式
     * @param date
     * @return
     */
    public static Date transferDate(Date date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(date);
            return formatter.parse(dateString);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null ;
    }
    /**
     * 转换日期格式
     * @param date
     * @return
     */
    public  static String transferDateToString(Date date) {
        try {
            SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sDateFormat.format(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null ;
    }
    /**
     * 转换日期格式
     * @param ds
     * @return
     */
    public  static Date transferStringToDate(String ds) {
        try {
            SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sDateFormat.parse(ds);
            return  date ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null ;
    }
    /**
     * 转换日期格式
     * @param ds
     * @return
     */
    public  static Date transferStringToDateNo(String ds) {
        try {
            SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyyMMdd");
            Date date = sDateFormat.parse(ds);
            return  date ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null ;
    }
    /**
     * 获取以当前时间为基准，跨度为num月的时间
     * @param num
     * @return
     */
    public  static Date  getSpanTime(int num){
         Date now = new Date();   //当前时间
         Date  date = new Date();
         Calendar calendar = Calendar.getInstance(); //得到日历
         calendar.setTime(now);//把当前时间赋给日历
         calendar.add(Calendar.MONTH, num);  //设置为前3月
         date = calendar.getTime();   //得到前3月的时间
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
         return  date ;
    }
    /**
     * 获取以当前时间为基准，跨度为days 天的时间
     * @return
     */
    public  static Date  getSpanTimeByDay(int days){
        Date now = new Date();   //当前时间
        Date  date = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(now);//把当前时间赋给日历
        calendar.add(Calendar.DATE, days);  //设置为前3月
        date = calendar.getTime();   //得到前3月的时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
        logger.info("当前时间:{},跨度为:{}天,的时间:{}",sdf.format(now),days,sdf.format(date));
        return  date ;
    }
    /**
     * 获取以当前时间为基准，跨度为num月的时间
     * @param num
     * @return
     */
    public  static String  getTheDayBefore(int num){
        Date now = new Date();   //当前时间
        Date  date = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(now);//把当前时间赋给日历
        calendar.add(Calendar.DATE, num);  //设置为前3月
        date = calendar.getTime();   //得到前3月的时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        logger.info("当前时间:{},跨度为:{},的时间:{}",sdf.format(now),num,sdf.format(date));
        String stringDate = sdf.format(date);
        return  stringDate ;
    }

    /**
     *  根据elasticSearch输出的日志格式转换为日期类型
     * @param dateS
     * @return
     */
    public static Date turnStringToDate(String dateS){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = formatter.parse(dateS);
            return  date ;
        }catch(Exception e){
            logger.error("字符串日期{}转换为日期类型的过程失败",dateS);
        }
        return  null ;
    }
    /**
     *  根据elasticSearch输出的日志格式转换为日期类型
     * @param dateS
     * @return
     */
    public static Date turnString(String dateS){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = formatter.parse(dateS);
            return  date ;
        }catch(Exception e){
            logger.error("字符串日期{}转换为日期类型的过程失败",dateS);
        }
        return  null ;
    }
    public static String longToDate(String timeLong){
         if(isValidLong(timeLong)){
             long time=Long.parseLong(timeLong) ;
             Date date = new Date(time);
             SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             return sd.format(date);
         }else{
             return   timeLong ;
         }

    }

    public static boolean isValidLong(String str){
        try{
            long _v = Long.parseLong(str);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
}
