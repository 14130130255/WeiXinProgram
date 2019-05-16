package com.cjr.WechatMessage.global;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {
    /**
     * date 转字符串
     * @param date
     * @return
     */
    public static String dateTimeToString(Date date)
    {
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeString = format.format(date);
        return timeString;
    }

    /**
     * 字符串转时间date
     * @param str
     * @return
     */
    public static Date StrToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
