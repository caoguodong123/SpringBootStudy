package com.cgd.springboot_study.util;

import com.cgd.springboot_study.model.DailyTransactionDetails;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil<T> {
    private Logger logger =  LoggerFactory.getLogger(StringUtil.class);
    @Value("${environment.type}")
    private String environmentType;


    public StringUtil() {
    }

    public static String trim(String source) {
        return source != null?source.trim():source;
    }

    public static boolean isEmpty(String source) {
        return source == null || source.length() == 0;
    }

    public static boolean isNotEmpty(String source) {
        return !isEmpty(source);
    }

    public static boolean isBlank(String source) {
        return source == null || source.length() <= 0 || source.trim().length() <= 0;
    }

    public static boolean isNotBlank(String source) {
        return !isBlank(source);
    }

    public static boolean isNumeric(String str) {
        return !isBlank(str) && str.trim().matches("\\d+");
    }
    public static boolean isNumber(String str) {
        return !isBlank(str) && str.trim().matches("([1-9][0-9]*.[0-9]*)|([1-9][0-9]*)");
    }
    public static boolean isBool(String str) {
        return !isBlank(str) && (str.trim().equals("true") || str.trim().equals("false"));
    }

    public static boolean isEquals(String src, String dest) {
        return src == null && dest == null?true:(src != null && dest != null?src.trim().equals(dest.trim()):false);
    }

    public static boolean isNotEquals(String src, String dest) {
        return !isEquals(src, dest);
    }



    public static boolean isInteger(String str) {
        byte begin = 0;
        if(isBlank(str)) {
            return false;
        } else {
            str = str.trim();
            if(str.startsWith("+") || str.startsWith("-")) {
                if(str.length() == 1) {
                    return false;
                }

                begin = 1;
            }

            for(int i = begin; i < str.length(); ++i) {
                if(!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    public static String buildUrl(String url, HashMap<String, String> params){
        if(params != null){
            StringBuffer buffer = new StringBuffer(url);
            if(buffer.indexOf("?") == -1){
                buffer.append("?");
            }

            buffer.append(buildParam(params));
            return buffer.toString();
        }else {
            return url;
        }
    }


    private static String buildParam(HashMap<String, String> params){
        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            try {
                buffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        buffer.deleteCharAt(buffer.length()-1);
        return buffer.toString();
    }
    //ascii转换为string
    public static String asciiToString(String text) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) <= '2' && i <= text.length() - 3) {
                int code = Integer.parseInt(text.substring(i, i + 3));
                builder.append((char) code);
                i += 2;
            } else {
                int code = Integer.parseInt(text.substring(i, i + 2));
                builder.append((char) code);
                i += 1;
            }
        }
        return builder.toString();
    }

    public static Long stringDateToTimeStap(String time) {
        DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
        try {
            if(!StringUtil.isEmpty(time)){
                Date parse = format1.parse(time);
                return  parse.getTime();
            }else{
                return  0L;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  0L;
    }
    public static String  removeSymbol(String str){
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n[\\\\]");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
            return dest;
        }
        return "";
    }

    /**
     * 如 1,2,3  字符串转为 list
     * @param str
     * @return
     */
    public static List<Integer> StringToList(String str){
         List<Integer> list=new ArrayList<Integer>() ;
         try {
             if (StringUtil.isNotEmpty(str)) {
                 String[] s = str.split(",");
                 System.out.println(s.length+":"+s.toString()) ;
                 for (int i = 0; i < s.length; i++) {
                     if (!s[i].equals("")) {
                         list.add(Integer.parseInt(s[i]));
                     }
                 }
             }
         }catch(Exception e){
             e.printStackTrace();
        }
         return list ;

    }

    public static String formatDate(String data)  {
        data= data.substring(0,4)+"-"+data.substring(4,6)+"-"+data.substring(6,8);
        System.out.println(data);
        return data;
    }

    public static String[] stringToArr(String str)  {
        String[] sArr = str.split(" ");
        return sArr;
    }

    // 首字母变大写
    public static String  toUpperCaseFirstOne(String str){
        char[] ch = str.toCharArray();
        ch[0] -= 32;
        return String.valueOf(ch);
    }

    public static String transToUpperCase(String str){

        String[] s=str.split("_") ;
        StringBuffer sb=new StringBuffer() ;
        for(int i=0 ;i<s.length;i++){
            s[i]=toUpperCaseFirstOne(s[i]) ;
        }
        for(int i=0 ;i<s.length;i++){
            sb.append(s[i]) ;
        }
        return sb.toString();
    }



    public static String toLowerCaseFirstOne(String str){
        char[] ch = str.toCharArray();
        ch[0] += 32;
        return String.valueOf(ch);
    }

    public static  String  getPath(String path,String name) {

        int length = path.length();
        int i = path.lastIndexOf("/");
        path = path.substring(0, i+1) ;
        path=path+name;
        System.out.println(path);
        return path;
    }
    //unicode转中文
    public static String unicodeToCN(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }

    public static String getESDate(){
        String FULL_FORMAT="yyyy-MM-dd\'T\'HH:mm:ss.SSS\'Z\'";
        Date now=new Date();

        return new SimpleDateFormat(FULL_FORMAT).format(now);
    }

    /*
     * @Author: zhanglin
     * @Date:  2019/9/27
     * @Description：业务相关
     */
//-----------------------------------------------------------------------------------------------------------

    public static String getcashEventContractTypeDetail(String eventType,String eventSubType){
        if(eventType.startsWith("COMM")){
            eventType= eventType.substring(0,4);
            return eventType+"_"+eventSubType;
        }
        if(eventType.startsWith("FX")){
            eventType= eventType.substring(0,2);
            return eventType+"_"+eventSubType;
        }

        return "";
    }


    public static String getUnit(String unit){
        try {
            String[] unitArr = unit.split("/");
            unit = unitArr[unitArr.length-1];
        } catch (Exception e) {
            e.printStackTrace();
            unit="";
        }
        return unit;
    }

    public static String getIntStr(String doubleStr){
        int i = doubleStr.indexOf(".");
        String intStr = doubleStr.substring(0, i);
        return intStr;

    }

     /*
     * @Author: zhanglin
     * @Date:  2019/10/16
     * @Description：
     */
    public static String getcontractTypeCn(String contractTypeDetail){
        Map<String,String> typeMap=new HashMap<>();
        typeMap.put("fxforward","外汇远期合约");
        typeMap.put("fxoption","外汇期权");

        typeMap.put("energyfuture","能源期货");
        typeMap.put("energyfutureoption","能源期货期权");

        typeMap.put("metalfuture","金属期货");
        typeMap.put("metalfutureoption","金属期货期权");

        typeMap.put("agriculturalfuture","农产品期货");
        typeMap.put("agriculturalfutureoption","农产品期货期权");
        String contractTypeCn = typeMap.get(contractTypeDetail);
        return contractTypeCn;
    }

    public static String  setMethod(String name){
        name=name.substring(0,1).toUpperCase()+name.substring(1);
        name="set"+name;
        return name;

    }

    public  T  setMethod(String name, Object value,T c){
        name=name.substring(0,1).toUpperCase()+name.substring(1);
        String setMethod="set"+name;
        String getMethod="get"+name;
        Method get = null;
        try {
            get = c.getClass().getMethod(getMethod);

           Method set= c.getClass().getMethod(setMethod, get.getReturnType());
            set.invoke(c,value);

            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public static String getMethod(String name){
        name=name.substring(0,1).toUpperCase()+name.substring(1);
        name="get"+name;
        return name;
    }

    public static void checkFileName(String fileName){
        String name = fileName.substring(0, fileName.lastIndexOf("."));
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        if (!suffixName.equals(".xlsx")) {
        }
    }

     /*
     * @Author: zhanglin
     * @Date:  2019/10/16
     * @Description：根据属性名，获得属性值
     */
    public  Object getMethodValue(String name, T c){
        name=name.substring(0,1).toUpperCase()+name.substring(1);
        String getMethod="get"+name;
        Method m = null;
        try {
            m = c.getClass().getMethod(getMethod);
            Object methodValue = m.invoke(c);
            return methodValue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public  String getUploadPringPath(int userId){
        String path="";
        String property = System.getProperty("os.name");
        try {
            if (property.equals("Linux")) {
                path ="/chenshi/"+environmentType+"/uploadPring/"+userId+"/";
            } else if ((property.equals("Windows XP") ||property.equals("Windows 7") || property.equals("Windows 8") || property.equals("Windows 10"))){
                path ="C:\\Users\\r1\\Desktop\\upload\\";
            }
            File dir = new File(path);
            if(!dir.exists()){
                dir.mkdirs();
            }
        } catch (Exception e) {
        }
        return path;
    }

    public  String getDownLoadPringPath(int userId){
        logger.info("环境名称--------:{}",environmentType);
        String path= null;
        try {
            path = "";
            String property = System.getProperty("os.name");
            if (property.equals("Linux")) {
                path ="/chenshi/"+environmentType+"/downLoadPring/"+userId+"/";
            } else if ((property.equals("Windows XP") ||property.equals("Windows 7") || property.equals("Windows 8") || property.equals("Windows 10"))){
                path ="D:\\";
            }
            File dir = new File(path);
            if(!dir.exists()){
                dir.mkdirs();
            }
        } catch (Exception e) {
        }
        return path;
    }

    public  InputStream getTemplate (String contractType){

        String  path="";

        if(contractType.equals("Commodity_Future")){
            path="template/Commodity_Future.xlsx";
        }else if(contractType.equals("Commodity_Future_Option")){
            path="template/Commodity_Future_Option.xlsx";
        }else if(contractType.equals("FX_Forward")){
            path="template/FX_Forward.xlsx";
        }else if(contractType.equals("FX_Option")){
            path="template/FX_Option.xlsx";
        }else if(contractType.equals("AssetLiability")){
            path="template/Assetliability.xlsx";
        }else if(contractType.equals("CashEvent")){
            path="template/CashEvent.xlsx";
        }else if(contractType.equals("inventoryReport")){
            path="template/inventoryReport.xlsx";
        }else if(contractType.equals("MTM")){
            path="template/MTM.xlsx";
        }
        InputStream inputStream = null;
        try {
            ClassPathResource classPathResource = new ClassPathResource(path);
             inputStream = classPathResource.getInputStream();
            return inputStream;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }
    public static Map<String,Object> setCondition(String downLoadPath,String templateType,Integer conditionRow,Integer listNameTypeRow){
        Map<String,Object> map=new HashMap<>();
        map.put("downLoadPath",downLoadPath);
        map.put("templateType",templateType);
        map.put("conditionRow",conditionRow);
        map.put("listNameTypeRow",listNameTypeRow);
        return map;
    }

    public String getFileName(T c){
        String fileName="";
        Object contractType = getMethodValue("contractType", c);
        if(contractType!=null){
         fileName=(String)contractType;
        }
        Object tradeId=(String)getMethodValue("tradeId", c);

        if(tradeId!=null){
            fileName+="_"+(String)tradeId;
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String dataStr = simpleDateFormat.format(date);
        fileName+="_"+dataStr+".xlsx";
        return fileName;

    }
    public static String inventoryReportMTMDownLoadDateFormat(Date date,String name){
        String farmatStr="yyyy-MM-dd";
/*        if(name.equals("createTime")){
            farmatStr="yyyy-MM-dd HH:mm:ss";
        }*/
        SimpleDateFormat sf = new SimpleDateFormat(farmatStr);
        String value = sf.format(date);
        return value;

    }
    public static  Map<String,Object> getNameColumn(XSSFRow row){
        Map<String,Object> map=new HashMap<>();
        Map<String,String> typeMap=new LinkedHashMap<>();
        Map<String,Integer> columnMap=new LinkedHashMap<>();

        for (int j = 0; j <row.getLastCellNum() ; j++) {
            XSSFCell cell = row.getCell(j);
            if(cell==null){
                continue;
            }
            switch ( cell.getCellTypeEnum()){
                case STRING:
                    String name = cell.getStringCellValue();
                    if(!name.contains("name")){
                        continue;
                    }
                    CellAddress address = cell.getAddress();
                    int column = address.getColumn();

                    String[] strings = name.split(" ");
                    if(strings.length<3){
                        continue;
                    }
                    name = strings[1];
                    String type = strings[2];
                    typeMap.put(name,type);
                    columnMap.put(name,column);

                    break;
                case NUMERIC:
                    break;
                case BOOLEAN:
                    break;
                case BLANK:
                    break;
                case ERROR:
                    break;
                default:
                    break;
            }

        }
        map.put("typeMap",typeMap);
        map.put("columnMap",columnMap);
        return map;
    }

    public static  Map<String,Object> getNameColumn(XSSFRow row,int startColumn,int endColumn){
        Map<String,Object> map=new HashMap<>();
        Map<String,String> typeMap=new LinkedHashMap<>();
        Map<String,Integer> columnMap=new LinkedHashMap<>();

        for (int j = startColumn; j <endColumn ; j++) {
            XSSFCell cell = row.getCell(j);
            if(cell==null){
                continue;
            }
            switch ( cell.getCellTypeEnum()){
                case STRING:
                    String name = cell.getStringCellValue();
                    if(!name.contains("name")){
                        continue;
                    }
                    CellAddress address = cell.getAddress();
                    int column = address.getColumn();

                    String[] strings = name.split(" ");
                    if(strings.length<3){
                        continue;
                    }
                    name = strings[1];
                    String type = strings[2];
                    typeMap.put(name,type);
                    columnMap.put(name,column);

                    break;
                case NUMERIC:
                    break;
                case BOOLEAN:
                    break;
                case BLANK:
                    break;
                case ERROR:
                    break;
                default:
                    break;
            }

        }
        map.put("typeMap",typeMap);
        map.put("columnMap",columnMap);
        return map;
    }

    public static Date stringToDate(String dateString){
        dateString= dateString.substring(0,4)+"-"+dateString.substring(4,6)+"-"+dateString.substring(6)+" 00:00:00";
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parseDate=new Date();
        try {
            parseDate = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }

    public static Date getDate(String dateStr){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parseDate = null;
        try {
            parseDate = simpleDateFormat.parse(dateStr);
            String s = parseDate.toString();
            System.out.println(parseDate.toString());
            return parseDate;
        } catch (ParseException e) {
        }
        return parseDate;
    }

    public static String stringToFormatDateString(String dateString){
        dateString= dateString.substring(0,4)+"-"+dateString.substring(4,6)+"-"+dateString.substring(6);

        return dateString;
    }

    public static void main(String[] args) {
        String dateString="2019-09-16";
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-ss");
        Date parseDate=new Date();
        try {
            parseDate = simpleDateFormat.parse(dateString);
            System.out.println(parseDate.toString());
            String format = simpleDateFormat.format(parseDate);
            System.out.println(format);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字符串大小不区分比较
     * @param str1
     * @param str2
     * @return
     */
    public static boolean ignoreCaseEquals(String str1,String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }


    private static Pattern linePattern = Pattern.compile("_(\\w)");
    /** 下划线转驼峰 */
    public static String lineToHump(String str) {

        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    private static Pattern humpPattern = Pattern.compile("[A-Z]");
    /** 驼峰转下划线 */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String  getSpotFutureType(DailyTransactionDetails record){
        String longShort = record.getLongShort();
        String openClose = record.getOpenClose();
        String purchaseSell = record.getPurchaseSell();

        //采购   买对应多，卖对应空
       /* if(longShort.equals("多") && openClose.equals("开")){
            return "2";
        }
        if(longShort.equals("空") && openClose.equals("平")){
            return "3";
        }
        //销售
        if(longShort.equals("空") && openClose.equals("开")){
            return "4";
        }
        if(longShort.equals("多") && openClose.equals("平")){
            return "5";
        }*/
        if(longShort.equals("买") && openClose.equals("开")){
            return "2";
        }
        if(longShort.equals("卖") && openClose.equals("平")){
            return "3";
        }
        //销售
        if(longShort.equals("卖") && openClose.equals("开")){
            return "4";
        }
        if(longShort.equals("买") && openClose.equals("平")){
            return "5";
        }
        //采购
        if(purchaseSell.equals("0")){
            return "2";
        }
        if(purchaseSell.equals("1")){
            return "4";
        }
        return "";
    }

    /**
     * 数字转字母 1-26 ： A-Z
     * @param num
     * @return
     */
    public  static String numberToLetter(int num) {
        if (num <= 0) {
            return null;
        }
        String letter = "";
        num--;
        do {
            if (letter.length() > 0) {
                num--;
            }
            letter = ((char) (num % 26 + (int) 'A')) + letter;
            num = (int) ((num - num % 26) / 26);
        } while (num > 0);

        return letter;
    }


    //将字符串分割成List（int类型）
    public static List divideString(String ids){
        List<Integer> idList=new ArrayList<>();

        if(StringUtil.isNotEmpty(ids)){
            String[]  id=ids.split(",");

            for(String s:id){
               idList.add(Integer.parseInt(s));
            }
        }

        return idList;
    }

    //将字符串分割成List（String类型）
    public static List divideStringToString(String ids){
        List<String> idList=new ArrayList<>();

        if(StringUtil.isNotEmpty(ids)){
            String[]  id=ids.split(",");

            for(String s:id){
                idList.add(s);
            }
        }

        return idList;
    }

}
