package com.lianglianglee.cloud.common.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

  public static Date getByDateAndSecond(int second) {
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
    Date date = new Date();
    calendar.setTime(date);
    calendar.add(Calendar.SECOND, second);
    return calendar.getTime();
  }
}
