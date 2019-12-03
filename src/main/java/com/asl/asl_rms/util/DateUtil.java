package com.asl.asl_rms.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateUtil
{
  private static Logger log = LogManager.getLogger(DateUtil.class.getName());

  private static String pattern = "yyyy-MM-dd";
  private static SimpleDateFormat sdf = new SimpleDateFormat(pattern);

  private static String defaultDatePattern = null;

  private static String timePattern = "HH:mm";
  public static final String TYPE_DATE = "D";
  public static final String TYPE_TIME = "T";
  public static final String TYPE_DATETIME = "DT";
  public static final String STYLE_XML = "X";
  public static final String STYLE_AD = "AD";
  public static final String STYLE_ROC = "R";
  public static final String STYLE_FORMAT = "F";
  public static final String STYLE_FORMAT_FOR_USER = "FU";
  public static final SimpleDateFormat DATE_FORMAT_FULL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public static final SimpleDateFormat DATE_FORMAT_MEDIUM = new SimpleDateFormat("yyyy-MM-dd HH:mm");

  public static final SimpleDateFormat DATE_FORMAT_SHORT = new SimpleDateFormat("yyyy-MM-dd");

  public static final SimpleDateFormat DATE_FORMAT_MONTH = new SimpleDateFormat("yyyy-MM");

  public static final SimpleDateFormat DATE_FORMAT_YEAR = new SimpleDateFormat("yyyy");

  public static final SimpleDateFormat DATE_FORMAT_MEDIUM_BBS = new SimpleDateFormat("MM-dd HH:mm");

  public static final SimpleDateFormat DATE_FORMAT_SHORT_BBS = new SimpleDateFormat("MM-dd");

  public static final SimpleDateFormat DATE_FORMAT_SHORT_BBSFEN = new SimpleDateFormat("HH:mm");

  public static final SimpleDateFormat DATE_FORMAT_FULL_ZH = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

  public static final SimpleDateFormat DATE_FORMAT_MEDIUM_ZH = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");

  public static final SimpleDateFormat DATE_FORMAT_SHORT_ZH = new SimpleDateFormat("yyyy年MM月dd日");

  public static final SimpleDateFormat DATE_FORMAT_DAY_ZH = new SimpleDateFormat("dd日");

  public static String getDatePattern()
  {
    try
    {
      defaultDatePattern = "yyyy-MM-dd";
    } catch (MissingResourceException mse) {
      defaultDatePattern = "MM/dd/yyyy";
    }
    return defaultDatePattern;
  }

  public static String getDateTimePattern() {
    return getDatePattern() + " HH:mm:ss.S";
  }

  public static final String getDate(java.util.Date aDate)
  {
    SimpleDateFormat df = null;
    String returnValue = "";
    if (aDate != null) {
      df = new SimpleDateFormat(getDatePattern());
      returnValue = df.format(aDate);
    }
    return returnValue;
  }

  public static final java.util.Date convertStringToDate(String aMask, String strDate)
    throws ParseException
  {
    SimpleDateFormat df = null;
    java.util.Date date = null;
    df = new SimpleDateFormat(aMask);
    if (log.isDebugEnabled())
      log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
    try
    {
      date = df.parse(strDate);
    } catch (ParseException pe) {
      throw new ParseException(pe.getMessage(), pe.getErrorOffset());
    }
    return date;
  }

  public static String getTimeNow(java.util.Date theTime)
  {
    return getDateTime(timePattern, theTime);
  }

  public static final String convertDateToString(java.util.Date aDate)
  {
    return getDateTime(getDatePattern(), aDate);
  }

  public static final String getDateTime(String aMask, java.util.Date aDate)
  {
    SimpleDateFormat df = null;
    String returnValue = "";

    if (aDate == null) {
      log.error("aDate is null!");
    } else {
      df = new SimpleDateFormat(aMask);
      returnValue = df.format(aDate);
    }

    return returnValue;
  }

  public static java.util.Date convertStringToDate(String strDate)
    throws ParseException
  {
    java.util.Date aDate = null;
    try
    {
      if (log.isDebugEnabled()) {
        log.debug("converting date with pattern: " + getDatePattern());
      }

      aDate = convertStringToDate(getDatePattern(), strDate);
    } catch (ParseException pe) {
      log.error("Could not convert '" + strDate + "' to a date, throwing exception");
      throw new ParseException(pe.getMessage(), pe.getErrorOffset());
    }

    return aDate;
  }

  public static synchronized String getDateTime(Calendar calendar, String type, String style)
  {
    String myDateTime = "";
    if ((type == null) || (type.equals(""))) {
      type = "DT";
    }
    if ((style == null) || (style.equals("")))
      style = "AD";
    String year;
    if (style.equals("R"))
      year = padding(calendar.get(1) - 1911, 2);
    else {
      year = padding(calendar.get(1), 4);
    }
    String month = padding(calendar.get(2) + 1, 2);
    String day = padding(calendar.get(5), 2);
    String hour = padding(calendar.get(11), 2);
    String min = padding(calendar.get(12), 2);
    String sec = padding(calendar.get(13), 2);

    if ((type.equals("D")) || (type.equals("DT"))) {
      myDateTime = year + month + day;
    }
    if ((type.equals("T")) || (type.equals("DT"))) {
      myDateTime = myDateTime + hour + min + sec;
    }
    if (style.equals("F")) {
      myDateTime = formateDateTime(myDateTime);
    }
    return myDateTime;
  }

  public static synchronized String padding(String srcString, int len)
  {
    String desString = null;
    srcString = cropping(srcString, len);
    int srcLen = srcString.getBytes().length;
    desString = srcString;
    for (int i = 0; i < len - srcLen; i++) {
      desString = desString + " ";
    }
    return desString;
  }

  public static synchronized String padding(long srcLong, int len)
  {
    String desString = null;
    String srcString = String.valueOf(srcLong);
    srcString = cropping(srcString, len);
    int srcLen = srcString.length();
    desString = srcString;
    for (int i = 0; i < len - srcLen; i++) {
      desString = "0" + desString;
    }
    return desString;
  }

  public static synchronized String cropping(String srcString, int maxLen)
  {
    String desString = null;
    byte[] desBytes = srcString.getBytes();
    if (desBytes.length > maxLen) {
      byte[] tmpBytes = cropping(desBytes, maxLen);
      desBytes = tmpBytes;
    }
    desString = new String(desBytes);
    return desString;
  }

  public static synchronized byte[] cropping(byte[] srcBytes, int maxLen)
  {
    byte[] desBytes = srcBytes;
    if (srcBytes.length > maxLen) {
      for (int i = 0; i < maxLen; i++) {
        if (srcBytes[i] < 0) {
          i++;
        }
        if (i == maxLen) {
          maxLen -= 1;
        }
      }
      byte[] tmpBytes = new byte[maxLen];
      System.arraycopy(srcBytes, 0, tmpBytes, 0, maxLen);
      desBytes = tmpBytes;
    }
    return desBytes;
  }

  public static synchronized String formateDateTime(String myDateTime)
  {
    String rtnDateTime = "";
    if ((myDateTime.length() == 8) || (myDateTime.length() == 14)) {
      rtnDateTime = myDateTime.substring(0, 4) + "-" + myDateTime.substring(4, 6) + "-" + myDateTime.substring(6, 8);
      if (myDateTime.length() == 14) {
        rtnDateTime = rtnDateTime + " ";
        myDateTime = myDateTime.substring(8);
      }
    }
    if (myDateTime.length() == 6) {
      rtnDateTime = rtnDateTime + myDateTime.substring(0, 2) + ":" + myDateTime.substring(2, 4) + ":" + myDateTime.substring(4, 6);
    }
    return rtnDateTime;
  }

  public static synchronized String formateFullDateTime(String myDateTime)
  {
    String rtnDateTime = "";
    if (myDateTime.length() == 14) {
      rtnDateTime = myDateTime.substring(0, 4) + "-" + myDateTime.substring(4, 6) + "-" + myDateTime.substring(6, 8) + " " + myDateTime.substring(8, 10) + ":" + myDateTime.substring(10, 12) + ":" + myDateTime.substring(12, 14);
    }
    return rtnDateTime;
  }

  public static synchronized String getCurrentTime(String type, String style)
  {
    Calendar calendar = Calendar.getInstance();
    return getDateTime(calendar, type, style);
  }

  public static java.util.Date dateRoler(java.util.Date source, int field, int num)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(source);
    c.add(field, num);
    return c.getTime();
  }

  public static java.util.Date addDate(java.util.Date date, int day)
  {
    java.util.Date result = null;
    if (date == null)
      return null;
    try {
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      int year = cal.get(1);
      int month = cal.get(2);
      int day1 = cal.get(5);
      GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month, day1);
      gregorianCalendar.add(5, day);
      result = new java.sql.Date(gregorianCalendar.getTime().getTime());
    } catch (Exception e) {
      result = null;
    }
    return result;
  }

  public static String[] getTime(String startday, String endday)
    throws ParseException
  {
    String[] returnArray = null;
    String[] hours = new String[24];
    for (int i = 0; i < hours.length; i++) {
      if (i < 10)
        hours[i] = ("0" + String.valueOf(i));
      else {
        hours[i] = String.valueOf(i);
      }
    }
    int days = getDate(startday, endday) + 1;
    returnArray = new String[days * 24];
    int k = 0;
    String day = startday;
    for (int i = 0; i < days; i++) {
      for (int j = 0; j < hours.length; j++) {
        returnArray[k] = (day + " " + hours[j]);
        k++;
      }
      day = getNextDay(day);
    }

    return returnArray;
  }

  public static String[] getTime96(String startday, String endday)
    throws ParseException
  {
    String[] returnArray = null;
    String[] hours = new String[24];
    String[] minute = { "00", "15", "30", "45" };

    for (int i = 0; i < hours.length; i++) {
      if (i < 10)
        hours[i] = ("0" + String.valueOf(i));
      else {
        hours[i] = String.valueOf(i);
      }
    }
    int days = getDate(startday, endday) + 1;
    returnArray = new String[days * 96];
    int k = 0;
    String day = startday;
    for (int i = 0; i < days; i++) {
      for (int j = 0; j < hours.length; j++) {
        for (int m = 0; m < minute.length; m++) {
          returnArray[k] = (day + " " + hours[j] + ":" + minute[m]);
          k++;
        }
      }
      day = getNextDay(day);
    }

    return returnArray;
  }

  public static String[] getDateArray(String startday, String endday)
    throws ParseException
  {
    String[] returnArray = null;
    int days = getDate(startday, endday) + 1;
    returnArray = new String[days];

    String day = startday;
    for (int i = 0; i < days; i++) {
      returnArray[i] = day;
      day = getNextDay(day);
    }

    return returnArray;
  }

  public static int getDate(String startday, String endday)
  {
    long ei = 0L;
    try
    {
      SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
      java.util.Date start = new java.util.Date(sf.parse(startday).getTime());
      java.util.Date end = new java.util.Date(sf.parse(endday).getTime());

      Calendar startcal = Calendar.getInstance();
      Calendar endcal = Calendar.getInstance();
      startcal.setTime(start);
      endcal.setTime(end);

      long sl = startcal.getTimeInMillis();
      long el = endcal.getTimeInMillis();

      ei = el - sl;
    }
    catch (ParseException e) {
      log.error(StringUtil.getExceptionDetailInfo(e));
    }

    return (int)(ei / 86400000L);
  }

  public static String getToday()
  {
    Calendar cal = Calendar.getInstance();
    return sdf.format(cal.getTime());
  }

  public static String getYesterday()
  {
    Calendar cal = Calendar.getInstance();
    cal.set(6, cal.get(6) - 1);
    return sdf.format(cal.getTime());
  }

  public static String getTomorrow()
  {
    Calendar cal = Calendar.getInstance();
    cal.set(6, cal.get(6) + 1);
    return sdf.format(cal.getTime());
  }

  public static String getYesterdayMonth()
  {
    Calendar cal = Calendar.getInstance();
    cal.set(6, cal.get(6) - 1);
    return DATE_FORMAT_MONTH.format(cal.getTime());
  }

  public static String getLastWeek()
  {
    Calendar cal = Calendar.getInstance();
    cal.set(6, cal.get(6) - 7);
    return sdf.format(cal.getTime());
  }

  public static String getNextWeek()
  {
    Calendar cal = Calendar.getInstance();
    cal.set(6, cal.get(6) + 7);
    return sdf.format(cal.getTime());
  }

  public static String getLastMonthDay()
  {
    Calendar cal = Calendar.getInstance();
    cal.set(2, cal.get(2) - 1);
    return sdf.format(cal.getTime());
  }

  public static String getLastMonthDay(String day)
  {
    String lastMonthDay = "";
    try {
      java.util.Date date = sdf.parse(day);
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.set(2, cal.get(2) - 1);
      lastMonthDay = sdf.format(cal.getTime());
    } catch (Exception e) {
      log.error(StringUtil.getExceptionDetailInfo(e));
    }
    return lastMonthDay;
  }

  public static String getLastMonth()
  {
    Calendar calendar = Calendar.getInstance();
    calendar.add(2, -1);
    return DATE_FORMAT_MONTH.format(calendar.getTime());
  }

  public static String getMonth(java.util.Date date, int day)
  {
    String re = "";
    try {
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      int year = cal.get(1);
      int month = cal.get(2);
      int day1 = cal.get(5);
      GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month, day1);
      gregorianCalendar.add(2, day);
      java.util.Date result = new java.sql.Date(gregorianCalendar.getTime().getTime());
      re = sdf.format(result);
    } catch (Exception e) {
      log.error(StringUtil.getExceptionDetailInfo(e));
    }
    return re;
  }

  public static String getNextMonth()
  {
    Calendar cal = Calendar.getInstance();
    cal.set(2, cal.get(2) + 1);
    return sdf.format(cal.getTime());
  }

  public static String getNextDay(String day)
    throws ParseException
  {
    String nextday = "";
    java.util.Date date = sdf.parse(day);
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(6, 1);
    nextday = sdf.format(cal.getTime());
    return nextday;
  }

  public static String getBeforeDay(String day)
    throws ParseException
  {
    String nextday = "";
    java.util.Date date = sdf.parse(day);
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(6, -1);
    nextday = sdf.format(cal.getTime());
    return nextday;
  }

  public static String getBeforeDay(String day, int span)
    throws ParseException
  {
    String nday = "";
    java.util.Date date = sdf.parse(day);
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(6, -span);
    nday = sdf.format(cal.getTime());
    return nday;
  }

  public static String getNextNDay(String day, int span)
  {
    String nday = "";
    java.util.Date date = null;
    try {
      date = sdf.parse(day);
    }
    catch (ParseException e) {
      log.error(StringUtil.getExceptionDetailInfo(e));
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(6, span);
    nday = sdf.format(cal.getTime());
    return nday;
  }

  public static String getBeforeMonth(String month)
  {
    if ((month == null) || ("".equals(month))) return "";
    String beforeMonth = "";
    try {
      java.util.Date date = DATE_FORMAT_MONTH.parse(month);
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.add(2, -1);
      beforeMonth = DATE_FORMAT_MONTH.format(cal.getTime());
    } catch (ParseException e) {
      log.error(StringUtil.getExceptionDetailInfo(e));
    }
    return beforeMonth;
  }

  public static String getBeforeMonth(String month, int span)
  {
    if ((month == null) || ("".equals(month))) return "";
    String beforeMonth = "";
    try {
      java.util.Date date = DATE_FORMAT_MONTH.parse(month);
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.add(2, -span);
      beforeMonth = DATE_FORMAT_MONTH.format(cal.getTime());
    } catch (ParseException e) {
      log.error(StringUtil.getExceptionDetailInfo(e));
    }
    return beforeMonth;
  }

  public static String getNextNMonth(String month, int span)
  {
    if ((month == null) || ("".equals(month))) return "";
    String beforeMonth = "";
    try {
      java.util.Date date = DATE_FORMAT_MONTH.parse(month);
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.add(2, span);
      beforeMonth = DATE_FORMAT_MONTH.format(cal.getTime());
    } catch (ParseException e) {
      log.error(StringUtil.getExceptionDetailInfo(e));
    }
    return beforeMonth;
  }

  public static String getBeforeYear(String year)
  {
    if ((year == null) || ("".equals(year))) return "";
    String beforeYear = "";
    try {
      java.util.Date date = DATE_FORMAT_YEAR.parse(year);
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.add(1, -1);
      beforeYear = DATE_FORMAT_YEAR.format(cal.getTime());
    } catch (ParseException e) {
      log.error(StringUtil.getExceptionDetailInfo(e));
    }
    return beforeYear;
  }

  public static String getBeforeYear(String year, int span)
  {
    if ((year == null) || ("".equals(year))) return "";
    String beforeYear = "";
    try {
      java.util.Date date = DATE_FORMAT_YEAR.parse(year);
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.add(1, -span);
      beforeYear = DATE_FORMAT_YEAR.format(cal.getTime());
    } catch (ParseException e) {
      log.error(StringUtil.getExceptionDetailInfo(e));
    }
    return beforeYear;
  }

  public static synchronized Calendar string2Calendar(String dateString)
  {
    int year = 0; int month = 0; int date = 0; int hour = 0; int min = 0; int sec = 0; int myLen = 0;
    if (dateString == null) {
      log.debug("string2Calendar():传入时间为null!");
      return null;
    }
    myLen = dateString.length();
    if ((myLen == 8) || (myLen == 14)) {
      year = Integer.parseInt(dateString.substring(0, 4));
      month = Integer.parseInt(dateString.substring(4, 6)) - 1;
      date = Integer.parseInt(dateString.substring(6, 8));
      if (myLen == 14) {
        dateString = dateString.substring(8);
      }
    }

    if (dateString.length() == 6) {
      hour = Integer.parseInt(dateString.substring(0, 2));
      min = Integer.parseInt(dateString.substring(2, 4));
      sec = Integer.parseInt(dateString.substring(4, 6));
    } else if (dateString.length() == 4) {
      hour = Integer.parseInt(dateString.substring(0, 2));
      min = Integer.parseInt(dateString.substring(2, 4));
    }

    Calendar calendarObj = Calendar.getInstance();
    if (myLen == 8) {
      calendarObj.set(year, month, date);
      if ((year != calendarObj.get(1)) || (month != calendarObj.get(2)) || (date != calendarObj.get(5)))
      {
        log.debug("日期格式错误!");
        return null;
      }
    } else if (myLen == 4) {
      calendarObj.set(11, hour);
      calendarObj.set(12, min);
      if ((hour < 0) || (hour >= 24) || (min < 0) || (min >= 60) || (sec < 0) || (sec >= 60))
      {
        log.debug("时间格式错误!");
        return null;
      }
    } else if (myLen == 6) {
      calendarObj.set(11, hour);
      calendarObj.set(12, min);
      calendarObj.set(13, sec);
      if ((hour < 0) || (hour >= 24) || (min < 0) || (min >= 60) || (sec < 0) || (sec >= 60))
      {
        log.debug("时间格式错误!");
        return null;
      }
    } else if (myLen == 14) {
      calendarObj.set(year, month, date, hour, min, sec);
      if ((year != calendarObj.get(1)) || (month != calendarObj.get(2)) || (date != calendarObj.get(5)) || (hour != calendarObj.get(11)) || (min != calendarObj.get(12)) || (sec != calendarObj.get(13)))
      {
        log.debug("日期或时间格式错误!");
        return null;
      }
    } else {
      log.debug("传入长度错误!");
      return null;
    }
    return calendarObj;
  }

  public static String getFulltime()
  {
    Calendar cal = Calendar.getInstance();
    return DATE_FORMAT_FULL.format(cal.getTime());
  }

  public static String DateToString(java.util.Date source)
  {
    String result = "";
    result = sdf.format(source);
    return result;
  }

  public static String getCurrentlyMonth()
  {
    StringBuffer date = new StringBuffer();
    Calendar calendar = Calendar.getInstance();
    date.append(calendar.get(1));
    date.append("-");
    if (calendar.get(2) + 1 < 10)
      date.append("0" + (calendar.get(2) + 1));
    else {
      date.append("" + (calendar.get(2) + 1));
    }
    return date.toString();
  }

  public static String getCurrentlyYear()
  {
    StringBuffer date = new StringBuffer();
    Calendar calendar = Calendar.getInstance();
    date.append(calendar.get(1));
    return date.toString();
  }

  public static String getFormerDate()
  {
    Calendar calendar = Calendar.getInstance();
    calendar.add(2, -1);
    return getCalendarStr(calendar);
  }

  public static String getCurrentlyDate()
  {
    Calendar calendar = Calendar.getInstance();
    return getCalendarStr(calendar);
  }

  private static String getCalendarStr(Calendar calendar)
  {
    StringBuffer date = new StringBuffer();
    date.append(calendar.get(1));
    date.append("-");
    if (calendar.get(2) + 1 < 10)
      date.append("0" + (calendar.get(2) + 1));
    else {
      date.append("" + (calendar.get(2) + 1));
    }
    date.append("-");
    if (calendar.get(5) < 10)
      date.append("0" + calendar.get(5));
    else {
      date.append("" + calendar.get(5));
    }
    return date.toString();
  }

  public static String[] getMonthFirsAndLast(String sj)
  {
    String[] dayResult = { "", "" };
    String strtmp = sj;
    if (sj.length() >= 7) {
      strtmp = strtmp.substring(0, 7);
    }
    int year = Integer.parseInt(strtmp.substring(0, 4));
    int month = Integer.parseInt(strtmp.substring(5, 7));
    Calendar cal = Calendar.getInstance();
    cal.set(1, year);
    cal.set(2, month - 1);

    dayResult[0] = (strtmp + "-" + String.valueOf(cal.getActualMinimum(5)));

    dayResult[1] = (strtmp + "-" + String.valueOf(cal.getActualMaximum(5)));
    return dayResult;
  }

  public static int getThisMonthDays()
  {
    String nextMonth = getNextMonth().substring(0, 7) + "-01";
    String thisMonth = getToday().substring(0, 7) + "-01";
    return getDate(thisMonth, nextMonth);
  }

  public static int getCountDays()
  {
    Calendar cld = Calendar.getInstance();
    return cld.getActualMaximum(5);
  }

  public static long getMinuteDiff(java.util.Date startDate, java.util.Date endDate)
  {
    long startTime = 0L;
    if (null != startDate) {
      startTime = startDate.getTime();
    }
    long endTime = 0L;
    if (null != endDate) {
      endTime = endDate.getTime();
    }
    long minute = (endTime - startTime) / 60000L;
    return minute;
  }

  public static int getDayDiff(java.util.Date startDate, java.util.Date endDate)
  {
    long minutes = getMinuteDiff(startDate, endDate);
    return (int)(minutes / 1440L);
  }

  public static String getDayBefore(String day, int diff)
  {
    Calendar calendar = Calendar.getInstance();
    try {
      calendar.setTime(DATE_FORMAT_SHORT.parse(day));
      calendar.add(5, -diff);
    } catch (ParseException e) {
      log.error(StringUtil.getExceptionDetailInfo(e));
    }
    return DATE_FORMAT_DAY_ZH.format(calendar.getTime());
  }

  public static boolean validTime(String time)
  {
    boolean flg = true;
    String[] tt = time.split(":");
    try {
      if (tt.length == 2) {
        int hh = Integer.parseInt(tt[0]);
        int mm = Integer.parseInt(tt[1]);
        if ((hh < 0) || (hh > 23)) {
          flg = false;
        }
        if ((mm < 0) || (hh > 59)) {
          flg = false;
        }
        if ((mm != 0) && (mm != 30))
        {
          if ((mm != 59) || (hh != 23))
          {
            flg = false;
          }
        }
      } else { flg = false; }
    }
    catch (Exception e) {
      flg = false;
    }
    return flg;
  }

  public static String getGwRwSbjzsj(String sbjgsjdw, int addMin)
  {
    String re = "";
    int key = Integer.valueOf(sbjgsjdw).intValue();
    switch (key) {
    case 2:{
      Calendar cal = Calendar.getInstance();
      cal.setTime(new java.util.Date());

      re = DATE_FORMAT_SHORT.format(cal.getTime());
      re = re + " " + minTools(addMin);
      break;}
    case 3:{
      Calendar cal = Calendar.getInstance();
      cal.setTime(new java.util.Date());

      re = DATE_FORMAT_SHORT.format(cal.getTime());
      re = re + " " + hourTools(addMin);
      break;}
    case 4:{
      Calendar cal = Calendar.getInstance();
      String day = sdf.format(cal.getTime());
      re = getNextNDay(day, addMin) + " 00:00:00";

      break;}
    case 5:{
      Calendar cal = Calendar.getInstance();
      String date = sdf.format(cal.getTime());
      String month = date.substring(0, 7);
      String day = date.substring(8, date.length());
      re = getNextNMonth(month, addMin) + "-" + day + " 00:00:00";

      break;}
    }

    return re;
  }

  private static String hourTools(int addHour)
  {
    String fm = "";
    if (addHour < 10)
      fm = "0" + String.valueOf(addHour) + ":00:00";
    else {
      fm = String.valueOf(addHour) + ":00:00";
    }
    return fm;
  }

  private static String minTools(int addmin)
  {
    String fm = "";
    if (addmin < 10)
      fm = "00:0" + String.valueOf(addmin) + ":00";
    else {
      fm = "00:" + String.valueOf(addmin) + ":00";
    }
    return fm;
  }

  public static int getSbjzsj(int sbjzsj, int sbsjz) {
    return sbjzsj + (int)(Math.random() * Double.valueOf(sbsjz).doubleValue());
  }

  public static Calendar getCalendr(String time, String format) {
    Calendar cld = Calendar.getInstance();
    try {
      SimpleDateFormat formatter = new SimpleDateFormat(format);

      java.util.Date date = formatter.parse(time);
      cld.setTime(date);
    }
    catch (ParseException e) {
      log.error(StringUtil.getExceptionDetailInfo(e));
    }
    return cld;
  }

  public static boolean isGregorianDateStr(String dateStr)
  {
    String datePattern = "[\\d]{4}-[0-1][0-9]-[0-3][0-9]";

    Pattern p = Pattern.compile(datePattern);
    Matcher m = p.matcher(dateStr);
    return m.matches();
  }

  public static boolean isIranDateStr(String dateStr)
  {
    String datePattern = "[\\d]{4}-[0-1][0-9]-[0-3][0-9]";
    Pattern p = Pattern.compile(datePattern);
    Matcher m = p.matcher(dateStr);
    return m.matches();
  }

  public static List<Object> showListByIranDate(List<Object> sourceList)
  {
    List rtnList = new ArrayList();
    for (int i = 0; i < sourceList.size(); i++) {
      Object tmpRow = sourceList.get(i);

      if ((tmpRow instanceof Map)) {
        Map rowMap = (Map)tmpRow;
        for (Iterator iterator = rowMap.keySet().iterator(); iterator.hasNext(); ) {
          Object key = iterator.next();
          Object value = rowMap.get(key);
          if ((value instanceof String)) {
            String objStr = String.valueOf(value);

            if ((!StringUtil.isEmptyString(objStr)) && (objStr.indexOf("-") != -1) && (objStr.length() >= 10)) {
              String dateStr1 = objStr.substring(0, 10);
              String dateStr2 = objStr.substring(10, objStr.length());
              if (isGregorianDateStr(dateStr1)) {
                String iranDate = DateConvert.gregorianToIran(dateStr1);
                rowMap.put(key, iranDate + dateStr2);
              }

            }

            if ((!StringUtil.isEmptyString(objStr)) && (objStr.indexOf("-") != -1) && (objStr.length() == 7))
            {
              String dateStr = objStr + "-01";
              if (isGregorianDateStr(dateStr)) {
                String iranDate = DateConvert.gregorianToIran(dateStr);
                iranDate = iranDate.substring(0, 7);
                rowMap.put(key, iranDate);
              }
            }
          }
        }
        rtnList.add(rowMap);
      }
      else {
        rtnList.add(tmpRow);
      }
    }
    sourceList = null;
    return rtnList;
  }

  public static Map<String, Object> showMapByIranDate(Map<String, Object> param)
  {
    for (Iterator iterator = param.keySet().iterator(); iterator.hasNext(); ) {
      String key = (String)iterator.next();
      Object value = param.get(key);
      if ((value instanceof String)) {
        String objStr = String.valueOf(value);

        if ((!StringUtil.isEmptyString(objStr)) && (objStr.indexOf("-") != -1) && (objStr.length() >= 10)) {
          String dateStr1 = objStr.substring(0, 10);
          String dateStr2 = objStr.substring(10, objStr.length());
          if (isIranDateStr(dateStr1)) {
            String iranDate = DateConvert.gregorianToIran(dateStr1);
            param.put(key, iranDate + dateStr2);
          }
        }
      }
    }
    return param;
  }



  public static Object changeMapByGregorianDate(Object obj)
  {
    if ((obj instanceof Map)) {
      Map param = (Map)obj;
      for (Iterator iterator = param.keySet().iterator(); iterator.hasNext(); ) {
        String key = (String)iterator.next();
        Object value = param.get(key);
        if ((value instanceof String)) {
          String objStr = String.valueOf(value);

          if ((!StringUtil.isEmptyString(objStr)) && (objStr.indexOf("-") != -1) && (objStr.length() >= 10)) {
            String dateStr1 = objStr.substring(0, 10);
            String dateStr2 = objStr.substring(10, objStr.length());
            if (isIranDateStr(dateStr1)) {
              String iranDate = DateConvert.gregorianToIran(dateStr1);
              param.put(key, iranDate + dateStr2);
            }
          }
        }
      }
      return param;
    }
    return obj;
  }



 
  public static Integer getYearDiff(java.util.Date startDate, java.util.Date endDate)
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
    String start = dateFormat.format(startDate);
    String end = dateFormat.format(endDate);
    int period = Integer.parseInt(end) - Integer.parseInt(start);
    return Integer.valueOf(period);
  }

  public static Calendar getCalendar(String month, int week) throws ParseException
  {
    java.util.Date newDate = convertStringToDate(month + "-01");
    Calendar caleNew = Calendar.getInstance();
    caleNew.setTime(newDate);
    caleNew.add(4, week - 1);
    return caleNew;
  }

  public static String getFirstOfWeek(String month, int week)
  {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Calendar cal = Calendar.getInstance();
    try {
      GregorianCalendar gc = (GregorianCalendar)getCalendar(month, week);
      cal.setTime(gc.getTime());
      cal.set(5, gc.get(5) - gc.get(7) + 2);
    }
    catch (Exception e) {
    }
    return df.format(cal.getTime());
  }

  public static String getLastOfWeek(String month, int week)
  {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Calendar myCale = Calendar.getInstance();
    try {
      GregorianCalendar gc = (GregorianCalendar)getCalendar(month, week);
      myCale.setTime(gc.getTime());
      myCale.set(5, gc.get(5) + 8 - gc.get(7));
    } catch (Exception e) {
    }
    return df.format(myCale.getTime());
  }

  public static int dayForWeek(String pTime)
  {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    int dayForWeek = 0;
    try {
      Calendar c = Calendar.getInstance();
      c.setTime(format.parse(pTime));
      if (c.get(7) == 1)
        dayForWeek = 7;
      else
        dayForWeek = c.get(7) - 1;
    }
    catch (Exception e) {
    }
    return dayForWeek;
  }
}