package com.docfee.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: Sed.Li(李朝)
 * Date: 14-3-24
 * Time: 下午1:27
 */
public class DateUtil {
   private static SimpleDateFormat sdf = new SimpleDateFormat();

    public static Date parse(String str,String fmt){
        sdf.applyPattern(fmt);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(parse("2012-11","yyyy-MM"));
    }

    public static java.sql.Date toSqlDate(Date date){
        return new java.sql.Date(date.getTime());
    }
}
