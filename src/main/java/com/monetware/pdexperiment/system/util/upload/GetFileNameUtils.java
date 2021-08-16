package com.monetware.pdexperiment.system.util.upload;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: GetFileNameUtils
 * @author: 彭于晏
 * @create: 2020-10-16 11:54
 **/

public class GetFileNameUtils {

    /**
     * 获取图片名字
     * @return
     */
    public static String getImgName(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMDDhhmmss");
        String format = simpleDateFormat.format(date);
        return format+".jpg";
    }

    public static String getWordName(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMDDhhmmss");
        String format = simpleDateFormat.format(date);
        return format+".docx";
    }

    /**
     * 年月日
     * @return
     */
    public static String yearAndMonthAndDay(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
        String format = simpleDateFormat.format(date);
        return format;
    }
}
