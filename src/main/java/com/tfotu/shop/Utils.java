package com.tfotu.shop;


import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static Integer String2Integer(String intStr, Integer def) {
        Integer intRes = null;
        try {
            intRes = Integer.valueOf(intStr);
        } catch (NumberFormatException e) {  //  转换错误
            intRes = def;
        }
        return intRes;
    }

    /**
     * 得到时间戳，秒级别
     *
     * @return
     */
    public static BigInteger getTime() {
        Long time = (new Date()).getTime();
        return BigInteger.valueOf(time);
    }
    public static String getFormatDateTime(Date date){
        if( date == null ){
            date = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        sdf.setTimeZone( TimeZone.getTimeZone("UTC"));
        return sdf.format( date );
    }

    public static String createOrderNumber(String lastStr){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String number = sdf.format(d);
        return number+lastStr;
    }

    /**
     * 随机范围数
     *
     * @param min
     * @param max
     * @return
     */
    public static Integer randNum(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }

    //写一个md5加密的方法
    public static String md5(String plainText) {
        //定义一个字节数组
        byte[] secretBytes = null;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //对字符串进行加密
            md.update(plainText.getBytes());
            //获得加密后的数据
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        //将加密后的数据转换为16进制数字
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
    public static String md5Short(String plainText) {
        String md5 = md5(plainText);
        return md5.substring( 8, 24);
    }
    public static String md52(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String md5Str = base64Encoder.encode( md5.digest(str.getBytes("utf-8")) );
        return md5Str;
    }

    public static boolean isMobile(String str) {
        if (StringUtils.isEmpty(str) || str.length() < 10) {
            return false;
        }
        //  是否为 11位纯数字
        if (str.matches("[0-9]+") && str.length() == 11) {
            return true;
        }
//        //  正则判断
//        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
//        Pattern p = Pattern.compile(regex);
//        Matcher m = p.matcher(str);
//        if( m.matches() ){
//            return true;
//        }
        return false;
    }

    public static ServletRequestAttributes getRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    public static HttpSession getSession() {
        return getRequestAttributes().getRequest().getSession();
    }

}
