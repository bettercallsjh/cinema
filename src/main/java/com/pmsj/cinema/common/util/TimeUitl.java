package com.pmsj.cinema.common.util;

import java.util.Calendar;
import java.util.Date;

/*
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/7/9 22:14
 **/
public class TimeUitl {

    /**
     * 获取每天的开始时间 00:00:00:00
     *
     * @param date
     * @return
     */
    public static Date getStartTime(Date date) {
        Calendar dateStart = Calendar.getInstance();
        dateStart.setTime(date);
        dateStart.set(Calendar.HOUR_OF_DAY, 0);
        dateStart.set(Calendar.MINUTE, 0);
        dateStart.set(Calendar.SECOND, 0);
        return dateStart.getTime();
    }

    /**
     * 获取每天的开始时间 23:59:59:999
     *
     * @param date
     * @return
     */
    public static Date getEndTime(Date date) {
        Calendar dateEnd = Calendar.getInstance();
        dateEnd.setTime(date);
        dateEnd.set(Calendar.HOUR_OF_DAY, 23);
        dateEnd.set(Calendar.MINUTE, 59);
        dateEnd.set(Calendar.SECOND, 59);
        return dateEnd.getTime();
    }
}
