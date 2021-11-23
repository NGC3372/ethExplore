package com.rainbowguo.ethexplore.Utils;

import android.annotation.SuppressLint;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class TextUtils {
    public static boolean isNumber(String str){

        Pattern pattern = Pattern.compile("[0-9]*");

        return pattern.matcher(str).matches();

    }

    public static String to16(String value){
        return "0x" + new BigInteger(value).toString(16);
    }
    public static String to10(String value){
        if (value != null && !value.equals("Max rate limit reached")){
            value = value.replaceFirst("0x","");
            return new BigInteger(value,16).toString();
        }else
            return "-";

    }

    public static String timeStampFormat(String timeStamp){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time = Integer.toUnsignedLong(Integer.parseInt(timeStamp)) *1000;
        Date date = new Timestamp(time);
        return formatter.format(date);
    }
    public static String formatEther(String value,String pattern){
        if(pattern == null)
            pattern = "0.00000";
        BigDecimal big = new BigDecimal(value).divide(new BigDecimal("1000000000000000000"));
        DecimalFormat df2 = new DecimalFormat(pattern);
        String result = df2.format(big);
        return result + " Ether";
    }
    public static String KbToGb(String value){
        BigDecimal big = new BigDecimal(value).divide(new BigDecimal("1000000000"));
        DecimalFormat df2 = new DecimalFormat("0.00");
        return df2.format(big);
    }

    public static void main(String[] args) {
        String time = to10("0x3782dace9d900000");
        System.out.println(time);
        //System.out.println(timeStampFormat("1636686278"));
        System.out.println(to16("4000000000000000000"));
        System.out.println(new BigInteger("3782dace9d900000",16).toString(16));
        //System.out.println(formatEther("4000000000000000000"));
    }

}
