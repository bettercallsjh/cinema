package com.pmsj.cinema.business.util;



import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author mhd
 * @className DaTE
 * @description TODO
 * @create 2020/7/7
 * @since 1.0.0
 */
public class DateUtil {
    public static String DateFormat(Date d){
        Date date = new Date(); //获取当前的系统时间。
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日 HH:mm") ; //使用了默认的格式创建了一个日期格式化对象。
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat dateFormat3 = new SimpleDateFormat("今天 MM月dd日 HH:mm");
        String time = dateFormat.format(d); //可以把日期转换转指定格式的字符串
        String time1 = dateFormat2.format(d);
        String time2 = dateFormat2.format(date);
        if (time1.equals(time2)){
            return dateFormat3.format(d);
        }else {
            return time;
        }
    }
}
