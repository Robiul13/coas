package com.asl.asl_rms.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StringUtil
{
	
  private static Logger logger = LogManager.getLogger(StringUtil.class.getName());

  public static String[] getStrArrayBySpit(String srcStr, String delimiters)
  {
    String[] result = null;
    int index = 0;
    if (srcStr != null) {
      StringTokenizer stk = new StringTokenizer(srcStr, delimiters);

      if (stk.hasMoreTokens())
        result = new String[stk.countTokens()];
      while (stk.hasMoreTokens()) {
        result[index] = stk.nextToken();
        index++;
      }
    }
    return result;
  }

  public static final String replace(String line, String oldString, String newString)
  {
    if (line == null) {
      return null;
    }
    int i = 0;
    i = line.indexOf(oldString, i);
    if (i >= 0) {
      char[] line2 = line.toCharArray();
      char[] newString2 = newString.toCharArray();
      int oLength = oldString.length();
      StringBuffer buf = new StringBuffer(line2.length);
      buf.append(line2, 0, i).append(newString2);
      i += oLength;
      int j = i;
      while ((i = line.indexOf(oldString, i)) > 0) {
        buf.append(line2, j, i - j).append(newString2);
        i += oLength;
        j = i;
      }
      buf.append(line2, j, line2.length - j);
      return buf.toString();
    }
    return line;
  }

  public static final boolean isEmptyString(String s)
  {
    return (s == null) || (s.trim().equals("")) || (s.equals("null")) || (s.equals("NULL")) || (s.trim().equals("undefined"));
  }

  public static byte[] getUTF8Bytes(String src)
  {
    try
    {
      return src.getBytes("UTF-8"); } catch (UnsupportedEncodingException e) {
    }
    return new byte[0];
  }

  public static String escape(String src)
  {
    StringBuffer tmp = new StringBuffer();
    tmp.ensureCapacity(src.length() * 6);
    for (int i = 0; i < src.length(); i++) {
      char j = src.charAt(i);
      if ((Character.isDigit(j)) || (Character.isLowerCase(j)) || (Character.isUpperCase(j)))
      {
        tmp.append(j);
      } else if (j < 'Ā') {
        tmp.append("%");
        if (j < '\020')
          tmp.append("0");
        tmp.append(Integer.toString(j, 16));
      } else {
        tmp.append("%u");
        tmp.append(Integer.toString(j, 16));
      }
    }
    return tmp.toString();
  }

  public static String unescape(String src)
  {
    StringBuffer tmp = new StringBuffer();
    tmp.ensureCapacity(src.length());
    int lastPos = 0; int pos = 0;

    while (lastPos < src.length()) {
      pos = src.indexOf("%", lastPos);
      if (pos == lastPos) {
        if (src.charAt(pos + 1) == 'u') {
          char ch = (char)Integer.parseInt(src.substring(pos + 2, pos + 6), 16);

          tmp.append(ch);
          lastPos = pos + 6;
        } else {
          char ch = (char)Integer.parseInt(src.substring(pos + 1, pos + 3), 16);

          tmp.append(ch);
          lastPos = pos + 3;
        }
      }
      else if (pos == -1) {
        tmp.append(src.substring(lastPos));
        lastPos = src.length();
      } else {
        tmp.append(src.substring(lastPos, pos));
        lastPos = pos;
      }
    }

    return tmp.toString();
  }

  public static String doubleFormat(double number, int decimalDigits)
  {
    if (number == 0.0D) {
      number = 0.0D;
    }

    boolean flag = false;
    if (decimalDigits < 0)
    {
      return "";
    }

    String pattern = "##################";
    if (decimalDigits > 0) {
      flag = true;
      pattern = pattern + ".";
      for (int i = 0; i < decimalDigits; i++) {
        pattern = pattern + "0";
      }
    }

    DecimalFormat df = new DecimalFormat(pattern);
    if (number <= -1.0D)
      return df.format(number);
    if ((number > -1.0D) && (number < 0.0D))
      return "-0" + df.format(number).substring(1);
    if ((number >= 0.0D) && (number < 1.0D)) {
      if (flag) {
        return "0" + df.format(number);
      }
      return df.format(number);
    }

    return df.format(number);
  }

  public static byte[] zipString(String s)
  {
    if (s == null)
      return null;
    BufferedInputStream in = null;
    ByteArrayOutputStream baos = null;
    BufferedOutputStream out = null;
    try {
      byte[] bytes = s.getBytes("GBK");
      in = new BufferedInputStream(new ByteArrayInputStream(bytes));
      baos = new ByteArrayOutputStream();
      out = new BufferedOutputStream(new GZIPOutputStream(baos));
      byte[] buffer = new byte[1024];
      int len = 0;
      while ((len = in.read(buffer)) != -1)
        out.write(buffer, 0, len);
    }
    catch (Exception e)
    {
      byte[] buffer;
      logger.error(getExceptionDetailInfo(e));
      return null;
    } finally {
      try {
        in.close();
      }
      catch (IOException e) {
        logger.error(getExceptionDetailInfo(e));
      }
      try {
        out.close();
      }
      catch (IOException e) {
        logger.error(getExceptionDetailInfo(e));
      }
    }
    if (baos != null) {
      return baos.toByteArray();
    }
    return null;
  }

  public static String unzipString(byte[] bytes)
  {
    if ((bytes == null) || (bytes.length == 0))
      return null;
    BufferedInputStream in = null;
    ByteArrayOutputStream baos = null;
    BufferedOutputStream out = null;
    try {
      in = new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(bytes)));

      baos = new ByteArrayOutputStream();
      out = new BufferedOutputStream(baos);
      byte[] buffer = new byte[1024];
      int len = 0;
      while ((len = in.read(buffer)) != -1) {
        out.write(buffer, 0, len);
      }
      bytes = baos.toByteArray();
    }
    catch (Exception e)
    {
      int len;
      logger.error(getExceptionDetailInfo(e));
      return null;
    } finally {
      try {
        in.close();
      }
      catch (IOException e) {
        logger.error(getExceptionDetailInfo(e));
      }
      try {
        out.close();
      }
      catch (IOException e) {
        logger.error(getExceptionDetailInfo(e));
      }
    }
    if (bytes != null) {
      try {
        return new String(bytes, "GBK");
      }
      catch (UnsupportedEncodingException e) {
        logger.error(getExceptionDetailInfo(e));
        return null;
      }
    }
    return null;
  }

  public static String stringToHtmlFormat(String s)
  {
    if (s == null)
      return null;
    String temp = replace(s, "\n", "<br>");
    temp = replace(temp, "\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
    return replace(temp, " ", "&nbsp;");
  }

  public static String leftTrimZero(String str)
  {
    int len = str.length();
    int st = 0;
    char[] val = str.toCharArray();
    while ((st < len) && (val[st] == '0')) {
      st++;
    }
    return st < len + 1 ? str.substring(st) : str;
  }

  public static String leftTrimZeroS(String str)
  {
    if (("0".equals(str)) || (isEmptyString(str))) return str;
    String result = leftTrimZero(str);
    if ((result.length() == 0) || (result.charAt(0) == '.')) result = "0" + result;
    return result;
  }

  public static String getSubStrByToken(String srcStr, String headToken, String tailToken)
  {
    String result = "";
    int startPos = srcStr.indexOf(headToken);
    int endPos = srcStr.indexOf(tailToken);
    if ((startPos >= 0) && (endPos > 0) && (startPos < endPos)) {
      result = srcStr.substring(startPos + headToken.length(), endPos);
    }
    return result;
  }

 
  public static String iPToHexStr(String ip)
  {
    String hexStr = null;
    String[] ips = getStrArrayBySpit(ip, ".");
    for (int i = 0; i < ips.length; i++) {
      hexStr = hexStr.concat(str2Hex(ips[i]));
    }
    return hexStr;
  }

  public static String hexStrToIP(String hexStr)
  {
    String ip = null;
    String subIP = "";
    if (hexStr.length() == 8) {
      for (int i = 0; i < 4; i++) {
        subIP = hexStr.substring(i * 2, (i + 1) * 2);
        if (i == 0)
          ip = String.valueOf(Integer.parseInt(subIP, 16));
        else
          ip = ip + "." + hex2Str(subIP);
      }
    }
    return ip;
  }

  public static boolean isSub(String str, String[] aStr)
  {
    boolean isSub = false;
    if (aStr != null) {
      for (int i = 0; i < aStr.length; i++) {
        if (str.equalsIgnoreCase(aStr[i])) {
          isSub = true;
          break;
        }
      }
    }
    return isSub;
  }

  public static String array2String(String[] strArray)
  {
    String result = "";
    if ((strArray != null) && (strArray.length > 0)) {
      for (int i = 0; i < strArray.length; i++) {
        if (i > 0)
          result = result + "," + strArray[i];
        else {
          result = strArray[i];
        }
      }
    }
    return result;
  }

  public static String quoted(String s)
  {
    if (s != null) {
      return "'" + s + "'";
    }
    return null;
  }

  public static String randomNumber(int scale)
  {
    int max = new Double(Math.pow(10.0D, scale)).intValue();
    int min = new Double(Math.pow(10.0D, scale - 1)).intValue();
    Random r = new Random();
    int result;
    do
      result = r.nextInt(max) % max;
    while (result <= min);
    return String.valueOf(result);
  }

  public static String removeSubstring(String main, String sub)
  {
    int pos = main.indexOf(sub);

    return pos != -1 ? main.substring(0, pos) + main.substring(pos + sub.length()) : main;
  }

  public static String getValue(Object obj)
  {
    if (obj == null)
      return "";
    if ((obj instanceof BigDecimal)) {
      return String.valueOf(obj.toString());
    }
    if ((obj instanceof Integer)) {
      return String.valueOf(obj.toString());
    }
    if ((obj instanceof String)) {
      if (isEmptyString(obj.toString())) {
        return "";
      }
      return obj.toString();
    }

    return "";
  }

  public static String hex2Str(String hexStr)
  {
    String str = null;
    str = String.valueOf(Integer.parseInt(hexStr, 16));
    return str;
  }

  public static String str2Hex(String str)
  {
    String hexStr = "";
    hexStr = Integer.toHexString(Integer.parseInt(str));
    if (hexStr.length() < 2)
      hexStr = "0" + hexStr;
    return hexStr;
  }

  public static String bin2Hex(String str, int len)
  {
    long i = Long.valueOf(str, 2).longValue();
    return leftZero(Long.toHexString(i).toUpperCase(), len);
  }

  public static String hex2Bin(String hex)
  {
    long i = Long.valueOf(hex, 16).longValue();
    return Long.toBinaryString(i);
  }

  public static String leftZero(String instr, int len)
  {
    String str = "";
    if (isEmptyString(instr)) {
      for (int j = 0; j < len; j++) {
        str = "0" + str;
      }
      return str;
    }
    if (instr.length() < len) {
      for (int j = instr.length(); j < len; j++) {
        instr = "0" + instr;
      }
    }
    return instr;
  }

  public static String rightZero(String instr, int len)
  {
    String str = "";
    if (isEmptyString(instr)) {
      for (int j = 0; j < len; j++) {
        str = str + "0";
      }
      return str;
    }
    if (instr.length() < len) {
      for (int j = instr.length(); j < len; j++) {
        instr = instr + "0";
      }
    }
    return instr;
  }

  public static String bigDecimal2String(BigDecimal bg) {
    String result = "";
    if (bg != null)
      result = bg.toString();
    return result;
  }

  public static String binStrToStr(String str, int start, int end)
  {
    return Integer.toString(Integer.parseInt(str.substring(start, end), 2));
  }

  public static String strToBinStr(String instr, int len)
  {
    String str = "";
    if (isEmptyString(instr)) {
      for (int j = 0; j < len; j++) {
        str = "0" + str;
      }
      return str;
    }
    int i = Integer.parseInt(instr);
    str = Integer.toBinaryString(i);
    if (str.length() < len) {
      for (int j = str.length(); j < len; j++) {
        str = "0" + str;
      }
    }
    return str;
  }

  public static String convertStr(String str)
  {
    String res = "";
    int i = str.length() - 1;
    while (i > -1) {
      res = res + str.charAt(i);
      i--;
    }
    return res;
  }

  public static String convertObisToHex(String classId, String obis, String attr)
  {
    String res = "";
    String[] obisItems = obis.split("\\.");
    res = res + "00" + str2Hex(classId);
    for (int i = 0; i < obisItems.length; i++) {
      res = res + str2Hex(obisItems[i]);
    }
    res = res + str2Hex(attr);
    return res;
  }

  public static String[] strDateToGrid(String[] zdjhs, int count, int size)
  {
    List result = new ArrayList();
    int counts = 0;
    for (int i = count * size; i < zdjhs.length; i++) {
      counts++;
      if (counts > size) {
        break;
      }
      result.add(0, zdjhs[i]);
    }

    String[] newString = new String[result.size()];
    for (int j = 0; j < result.size(); j++) {
      newString[j] = ((String)result.get(j));
    }
    return newString;
  }

  public static String[] strDateToGridForString(String zdjh, int count, int size)
  {
    List result = new ArrayList();

    String[] zdjhs = zdjh.split(",");
    int counts = 0;
    for (int i = count * size; i < zdjhs.length; i++) {
      counts++;
      if (counts > size) {
        break;
      }
      result.add(0, zdjhs[i]);
    }

    String[] newString = new String[result.size()];
    for (int j = 0; j < result.size(); j++) {
      newString[j] = ((String)result.get(j));
    }

    return newString;
  }

  public static int[] strToIntArray(String[] strArr)
  {
    if ((null == strArr) || (0 == strArr.length)) return null;
    if (isEmptyString(strArr[0])) return null;
    int[] intArr = null;
    try {
      intArr = new int[strArr.length];
      for (int i = 0; i < strArr.length; i++)
        intArr[i] = Integer.parseInt(strArr[i]);
    }
    catch (Exception e) {
      logger.error(getExceptionDetailInfo(e));
    }
    return intArr;
  }

  public static int indexOfArray(String[] strArray, String compString)
  {
    int result = -1;

    for (int i = 0; (strArray != null) && (i < strArray.length); i++) {
      if ((!isEmptyString(strArray[i])) && (strArray[i].equals(compString))) {
        result = i;
        break;
      }
    }

    return result;
  }

  public static String ConvertUTF(String s)
    throws Exception
  {
    byte[] temp = s.getBytes("iso-8859-1");
    String result = new String(temp, "UTF-8");

    return result;
  }

  public static String getString(Object s)
  {
    return s == null ? "" : String.valueOf(s);
  }

  public static String removeEndChar(String str, int n)
  {
    return isEmptyString(str) ? str : str.substring(0, str.length() - n);
  }

  public static String[] getInterval(int num)
  {
    String[] sjrq = new String[num];
    for (int i = 0; i < num; i++) {
      if (i < 10)
        sjrq[i] = ("0" + String.valueOf(i));
      else {
        sjrq[i] = String.valueOf(i);
      }
    }
    return sjrq;
  }

  public static String getExceptionDetailInfo(Exception ex)
  {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    ex.printStackTrace(pw);
    return sw.toString();
  }

  public static String convertString(String s, String character_s, String character_d)
  {
    s = s == null ? "" : s;
    String s_unicode = "";
    try {
      s = s.trim();
      byte[] bytes = s.getBytes(character_s);
      s_unicode = new String(bytes, character_d);
    } catch (Exception e) {
      logger.error(getExceptionDetailInfo(e));
    }
    return s_unicode;
  }

  public static String ISO8859_1ToGBK(String s)
  {
    return convertString(s, "ISO8859_1", "GBK");
  }

  public static String GBKToISO8859_1(String s) {
    return convertString(s, "GBK", "ISO8859_1");
  }

  public static String UTF8ToGBK(String s)
  {
    return convertString(s, "UTF-8", "GBK");
  }

  public static String GBKToUTF8(String s) {
    return convertString(s, "GBK", "UTF-8");
  }

  public static String replaceStr(String source, String oldString, String newString)
  {
    if ((oldString == null) || (oldString.length() == 0)) {
      return source;
    }
    if (source == null) {
      return "";
    }
    if (newString == null) {
      newString = "";
    }

    StringBuffer output = new StringBuffer();
    int lengthOfSource = source.length();
    int lengthOfOld = oldString.length();
    int posStart = 0;
    int pos;
    while ((pos = source.indexOf(oldString, posStart)) >= 0) {
      output.append(source.substring(posStart, pos));
      output.append(newString);
      posStart = pos + lengthOfOld;
    }
    if (posStart < lengthOfSource) {
      output.append(source.substring(posStart));
    }
    return output.toString();
  }
}