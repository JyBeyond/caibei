package com.facebook.shuiai.project.util;

/**
 * Built in Eclipse
 * Created by ZZB on 2013/10/20.
 */

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 和String有关的工具类
 */
public class StringUtil {

    private StringUtil() {
    }

    /**
     * 判断是否是邮箱格式
     *
     * @param s String 要判断的字符串
     * @return boolean true表示是邮箱格式
     */
    public static boolean matchEmail(String s) {
        boolean flag = false;
        if (s != null) {
            Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");

            Matcher matcher = pattern.matcher(s);

            flag = matcher.matches();
        }
        return flag;
    }

    /**
     * 将某个位置的单个路径拼接成完整的文件路径,根目录为/
     *
     * @param paths String[]
     * @param start 要拼接的地址起始位置
     * @param end   结束位置,不包含
     * @return String 文件完整路径
     */
    public static String getFileAbsolutePath(String[] paths, int start, int end) {
        String absPath = "/";
        if (paths == null) {
            return absPath;
        }
        for (int i = start; i <= end; i++) {
            absPath = absPath + paths[i] + "/";
        }
        return absPath.substring(0, absPath.length() - 1);
    }

    /**
     * 两个数字字符串相减,前面的减去后面的
     *
     * @param s1 String
     * @param s2 String
     * @return int -1表示字符串非法
     */
    public static int subStr(String s1, String s2) {
        int ret;
        try {
            ret = Integer.parseInt(s1) - Integer.parseInt(s2);
        } catch (Exception e) {
            ret = -1;
        }
        return ret;
    }

    /**
     * 两个数字字符串相减,前面的减去后面的
     *
     * @param s1 String
     * @param s2 String
     * @return double -1表示字符串非法
     */
    public static double subStrDouble(String s1, String s2) {
        double ret;
        try {
            ret = Double.parseDouble(s1) - Double.parseDouble(s2);
        } catch (Exception e) {
            ret = -1;
        }
        return ret;
    }

    /**
     * 两个int格式的字符串相加
     *
     * @param str1 String
     * @param str2 String
     * @return int
     */
    public static int addIntegerStr(String str1, String str2) {
        int iOne = toInteger(str1);
        int iTwo = toInteger(str2);
        iOne = iOne < 0 ? 0 : iOne;
        iTwo = iTwo < 0 ? 0 : iTwo;
        return iOne + iTwo;
    }

    /**
     * 两个long格式的字符串相加
     *
     * @param str1 String
     * @param str2 String
     * @return long
     */
    public static long addLongStr(String str1, String str2) {
        long iOne = toLong(str1);
        long iTwo = toLong(str2);
        iOne = iOne < 0 ? 0 : iOne;
        iTwo = iTwo < 0 ? 0 : iTwo;
        return iOne + iTwo;
    }

    /**
     * 将String转为long
     *
     * @param str String
     * @return long 返回-1表示失败
     */
    public static long toLong(String str) {
        long ret = 0;
        try {
            ret = Long.parseLong(str);
        } catch (Exception e) {
            e.printStackTrace();
            ret = -1;
        }
        return ret;
    }

    /**
     * 两个数字字符串相减,前面的减去后面的
     *
     * @param s1 String
     * @param s2 String
     * @return double -1表示字符串非法
     */
    public static double subStrInt(String s1, String s2) {
        int ret;
        try {
            ret = Integer.parseInt(s1) - Integer.parseInt(s2);
        } catch (Exception e) {
            ret = -1;
        }
        return ret;
    }

    /**
     * 两个数字字符串相加
     *
     * @param str1 String
     * @param str2 String
     * @return int -1表示字符串非法
     */
    public static int addNumString(String str1, String str2) {
        int ret;
        try {
            ret = Integer.parseInt(str1) + Integer.parseInt(str2);
        } catch (Exception e) {
            ret = -1;
        }
        return ret;
    }

    /**
     * 将String转为Double
     *
     * @param str String
     * @return double 返回-1表示失败
     */
    public static double toDouble(String str) {
        double ret;
        try {
            ret = Double.parseDouble(str);
        } catch (Exception e) {
            ret = -1;
        }
        return ret;
    }

    /**
     * 将String转为Integer
     *
     * @param str String
     * @return int 返回-1表示失败
     */
    public static int toInteger(String str) {
        int ret;
        try {
            ret = Integer.parseInt(str);
        } catch (Exception e) {
            ret = -1;
        }
        return ret;
    }

    /**
     * 判断类型是整数还是小数还是都不是
     *
     * @param str String
     * @return int -1表示既不是是整数也不是小数, 0表示是整数, 1表示是小数
     */
    public static int isIntegerOrDecimal(String str) {
        int flag = -1;
        // 匹配整数
        if (str.matches("^[0-9]+$")) {
            flag = 0;
        }
        // 匹配小数
        if (str.matches("^[0-9]+.[0-9]*$")) {
            flag = 1;
        }
        return flag;
    }

    /**
     * 保留两位小数
     *
     * @param str String
     * @return String
     */
    public static String get2DoubleString(String str) {
        DecimalFormat df = new DecimalFormat("####0.00");
        return df.format(str);
    }

    /**
     * 保留一位小数
     *
     * @param str String
     * @return String
     */
    public static String getDoubleString(String str) {
        String s = "";
        double num;
        if ((num = toDouble(str)) != -1) {
            s = getDoubleString(num);
        }
        return s;
    }

    /**
     * 保留一位小数
     *
     * @param num double
     * @return String
     */
    public static String getDoubleString(double num) {
        DecimalFormat df = new DecimalFormat("####0.0");
        return df.format(num);
    }

    /**
     * 保留两位小数
     *
     * @param num double
     * @return String
     */
    public static String get2DoubleString(double num) {
        DecimalFormat df = new DecimalFormat("####0.00");
        return df.format(num);
    }

    public static float toFloat(String str) {
        float ret;
        try {
            ret = Float.parseFloat(str);
        } catch (Exception e) {
            ret = -1;
        }
        return ret;
    }

    /**
     * 获取当前的日期，格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String dateToString() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return sdf.format(date);
    }

    /**
     * 保留两位小数
     *
     * @param num
     * @return double
     */
    public static double get2Double(double num) {
        BigDecimal b = new BigDecimal(num);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 获取当前的日期
     *
     * @param dateFormat String 日期要转换的文本格式
     * @return String
     */
    public static String dateToString(String dateFormat) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.CHINA);
        return sdf.format(date);
    }

    /**
     * 获取当前的日期,只包含年月，格式为yyyy-MM
     *
     * @return String
     */
    public static String dateToStr() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM", Locale.CHINA);
        return sdf.format(date);
    }

    /**
     * 获取指定的日期的指定格式
     *
     * @return String
     */
    public static String dateToCustomStr(long time, String format) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
        return sdf.format(date);
    }

    /**
     * 获取当前的日期,只包含年，格式为yyyy
     *
     * @return String
     */
    public static String dateToYear() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy", Locale.CHINA);
        return sdf.format(date);
    }

    /**
     * 将数字改为某月,如 02->2月
     *
     * @return String, 默认1月
     */
    public static String numToMonth(String num) {
        String month = "1月";
        try {
            month = Integer.parseInt(num) + "月";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return month;
    }

    /**
     * 将yyyy-MM 改为 yyyy年MM月
     *
     * @return String, 默认2016年1月
     */
    public static String switchDateFormat(String date) {
        String yearMonth = "2016年1月";
        try {
            String[] arr = date.split("-");
            yearMonth = arr[0] + "年" + numToMonth(arr[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return yearMonth;
    }


    /**
     * 求比例值,最大100
     *
     * @param smallNum String
     * @param bigNum   String
     * @return int
     */
    public static int getScale(String smallNum, String bigNum) {
        int scale = 0;
        try {
            scale = (int) (Double.parseDouble(smallNum) / (Double.parseDouble(bigNum)) * 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scale;
    }

    /**
     * 判断数字字符串是否为空，如果为空则返回0
     *
     * @param str String
     * @return String
     */
    public static String numIsNull(String str) {
        if (str == null || str.equals("") || str.equals("null")) {
            return "0";
        }
        return str;
    }

    /**
     * 判断是否为空
     *
     * @param s
     * @return true为空
     */
    public static boolean isNull(String s) {
        boolean flag = false;
        if (s == null || s.equals("") || s.equals("null")) {
            flag = true;
        }
        return flag;
    }

    /**
     * 验证手机格式
     *
     * @return boolean true表示匹配正确
     */
    public static boolean isMobile(String mobileNums) {
        /*  
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188  
         * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）  
         * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9  
         */
        String telRegex = "^[1]\\d{10}$";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        return !TextUtils.isEmpty(mobileNums) && mobileNums.matches(telRegex);
    }

    /**
     * 检查手机号
     *
     * @param phone String
     * @return 返回手机号的检查结果，为null表示手机号格式正确
     */
    public static String checkMobilePhone(String phone) {
        String str = null;
        if (isNull(phone)) {
            str = "手机号不能为空";
        } else if (!isMobile(phone)) {
            str = "手机号格式不对";
        }
        return str;
    }

    /**
     * 获取带有格式的金额,如果小数部分全为0则只显示整数部分,10.0->10
     *
     * @param price double
     * @return String
     */
    public static String getPriceStr(double price) {
        // 前缀
        String prefix = "";
        if (price < 0) {
            prefix = "-";
        }
        price = Math.abs(price);
        String str = "";
        String zero = "";
        int ints = (int) price;

        // 取小数，去掉小数点
        String decimal = "";
        String[] parts = String.valueOf(price).split("\\.");
        if (parts.length == 2) {
            decimal = parts[1];
        }
        System.out.println("decimal=" + decimal);
        while (ints >= 1000) {
            if (ints % 1000 < 100) {
                zero = "0";
            }
            if (ints % 1000 < 10) {
                zero = zero + "0";
            }
            str = zero + ints % 1000 + "," + str;
            zero = "";
            ints = (ints - ints % 1000) / 1000;
        }
        str = ints + "," + str;
        str = str.substring(0, str.length() - 1);
        if (!decimal.equals("") && !decimal.equals("0")) {
            str = str + "." + decimal;
        }
        str = prefix + str;
        return str;
    }

    /**
     * 根据 url和参数 获得get请求地址
     *
     * @param baseUrl String
     * @param map     Map
     * @return String
     */
    public static String getUrlwithParms(String baseUrl, Map<String, Object> map) {
        if (map != null) {
            StringBuilder p = new StringBuilder();
            for (String param : map.keySet()) {
                try {
                    p.append("&").append(param).append("=").append(URLEncoder.encode(map.get(param) != null ? map.get(param).toString() : "", "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if (p.length() > 0) {
                baseUrl += "?" + p.substring(1);
            }
        }
        return baseUrl;
    }

    /**
     * 根据 url和参数 获得get请求地址（不進行encode）
     *
     * @param baseUrl String
     * @param map     Map
     * @return String
     */
    public static String getUrlwithParmsNoenCoder(String baseUrl, Map<String, Object> map) {
        if (map != null) {
            StringBuilder p = new StringBuilder();
            for (String param : map.keySet()) {
                p.append("&").append(param).append("=").append(map.get(param) != null ? map.get(param).toString() : "");
            }
            if (p.length() > 0) {
                baseUrl += "?" + p.substring(1);
            }
        }
        return baseUrl;
    }

    /**
     * 格式化double数据 保留指定的小数点后几位
     *
     * @param scan 保留的小数点位数
     * @param num  数字
     * @return String
     */
    public static String doubleFormat(int scan, String num) {
        return new BigDecimal(num).setScale(scan, RoundingMode.HALF_UP).toString();
    }

    /**
     * 格式化double数据 保留指定的小数点后几位
     *
     * @param num 数字
     * @return String
     */
    public static String double2Format(double num) {
        return new BigDecimal(num).setScale(2, RoundingMode.HALF_UP).toString();
    }

    /**
     * 得到类似于微信聊天消息展示时间格式的时间
     *
     * @param time     long,单位s
     * @param lastTime long，单位s
     * @return String
     */
    public static String getWeChatFormatTime(long time, long lastTime) {
        String sTime;
//        long timeGap = time - lastTime;
        if (isToady(time * 1000)) {
            // 如果是今天
//            if (lastTime == 0) {
//                timeGap = System.currentTimeMillis() / 1000 - time;
//            }
//            if (timeGap >= 12 * 60 * 60) {
//                // 两次时间间隔超过6小时，则显示晚上、早上、上午、下午等
//                sTime = getTimeState(time * 1000) + " " + getTimeFromTimeStamp(time * 1000);
//            } else {
//                sTime = getTimeFromTimeStamp(time * 1000);
//            }
            sTime = getTimeFromTimeStamp(time * 1000);
        } else if (isYesterday(time * 1000)) {
            // 如果是昨天
            sTime = "昨天" + " " + getTimeFromTimeStamp(time * 1000);
//        } else if (daysBetween(System.currentTimeMillis(), time * 1000) < getTodayWeekPosition() - 1) {
        } else if (daysBetween(System.currentTimeMillis(), time * 1000) < 7) {
            // 一周内
            sTime = getWeek(time * 1000) + " " + getTimeFromTimeStamp(time * 1000);
        } else if (isThisYear(time * 1000)) {
            // 今年
            sTime = getDateTimeNoYearFromTimeStamp(time * 1000);
        } else {
            sTime = getDateTimeFromTimeStamp(time * 1000);
        }
        return sTime;
    }

    /**
     * 得到消息展示时间格式的时间
     *
     * @param time long,单位ms
     * @return String
     */
    public static String getMessageFormatTime(long time) {
        String sTime;
        if (isToady(time)) {
            // 如果是今天
            sTime = getTimeFromTimeStamp(time);
        } else if (isYesterday(time)) {
            // 如果是昨天
            sTime = "昨天" + " " + getTimeFromTimeStamp(time);
//        } else if (daysBetween(System.currentTimeMillis(), time) < getTodayWeekPosition() - 1) {
        } else if (daysBetween(System.currentTimeMillis(), time) < 6) {
            // 一周内
            sTime = getWeek(time) + " " + getTimeFromTimeStamp(time);
        } else if (isThisYear(time)) {
            // 今年
            sTime = getDateTimeNoYearFromTimeStamp(time);
        } else {
            sTime = getDateTimeFromTimeStamp(time);
        }
        return sTime;
    }

    /**
     * 今日内则显示时分，昨天显示昨天，一周内显示星期，今年内显示月日，否则显示年月
     *
     * @param time long 单位s
     * @return String
     */
    public static String getWeChatListTime(long time) {
        String str;
        if (isToady(time * 1000)) {
            str = getTimeFromTimeStamp(time * 1000);
        } else if (isYesterday(time * 1000)) {
            // 如果是昨天
            str = "昨天";
//        } else if (daysBetween(System.currentTimeMillis(), time * 1000) < getTodayWeekPosition() - 1) {
        } else if (daysBetween(System.currentTimeMillis(), time * 1000) < 6) {
            // 一周内
            str = getWeek(time * 1000) + " ";
        } else if (isThisYear(time * 1000)) {
            str = getDateNoYearFromTimeStamp(time * 1000);
        } else {
            str = getDateFromTimeStamp(time * 1000);
        }
        return str;
    }

    /**
     * 得到时间所处的时刻
     *
     * @param time long，单位ms
     * @return String
     */
    public static String getTimeState(long time) {
        String s = "";
        String sTime = getTimeFromTimeStamp(time).substring(0, 2);
        int i = toInteger(sTime);
        if (i < 6) {
            s = "早上";
        } else if (i < 12) {
            s = "上午";
        } else if (i < 18) {
            s = "下午";
        } else if (i <= 24) {
            s = "晚上";
        }
        return s;
    }

    /**
     * 是否是今天
     *
     * @param time long，单位是ms
     * @return boolean true表示是今天
     */
    public static boolean isToady(long time) {
        String current = getDateFromTimeStamp(System.currentTimeMillis());
        String destTime = getDateFromTimeStamp(time);
        return current.equals(destTime);
    }

    /**
     * 是否是昨天
     *
     * @param time long，单位是ms
     * @return boolean true表示是昨天
     */
    public static boolean isYesterday(long time) {
        String current = getDateFromTimeStamp(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        String destTime = getDateFromTimeStamp(time);
        return current.equals(destTime);
    }

    /**
     * 是否是今年
     *
     * @param time long，单位是ms
     * @return boolean true表示是今年
     */
    public static boolean isThisYear(long time) {
        String current = getDateFromTimeStamp(System.currentTimeMillis()).substring(0, 4);
        String destTime = getDateFromTimeStamp(time).substring(0, 4);
        return current.equals(destTime);
    }

    /**
     * 得到今天星期几
     *
     * @return String
     */
    public static String getTodayWeek() {
        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE", Locale.CHINA);
        return dateFm.format(date);
    }

    /**
     * 得到今日在本周处于第几个位置
     *
     * @return int 7表示周日，1表示星期一
     */
    public static int getTodayWeekPosition() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w <= 0) {
            w = 7;
        }
        return w;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param time     long,ms
     * @param lastTime long,ms
     * @return 相差天数
     */
    public static int daysBetween(long time, long lastTime) {
        long i = time - lastTime;
        return (int) (i / (24 * 60 * 60 * 1000));
    }

    /**
     * 得到在所处周处于第几个位置
     *
     * @return int 7表示周日，1表示星期一
     */
    public static int getWeekPosition(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(time));
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w <= 0) {
            w = 7;
        }
        return w;
    }

    /**
     * 得到星期几
     *
     * @param time long，单位ms
     * @return String
     */
    public static String getWeek(long time) {
        Date date = new Date(time);
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE", Locale.CHINA);
        return dateFm.format(date);
    }

    /**
     * 是否是同一天
     *
     * @param time long，单位是ms
     * @return boolean true表示是同一天
     */
    public static boolean isSameDay(long time, long destTime) {
        String current = getDateFromTimeStamp(time);
        String dest = getDateFromTimeStamp(destTime);
        return current.equals(dest);
    }

    /**
     * 是否显示时间
     *
     * @param time     long，单位是s
     * @param lastTime long，单位是s
     * @return boolean true表示显示
     */
    public static boolean isShowTime(long time, long lastTime) {
        boolean isShow = true;
        if (lastTime == 0) {
            isShow = true;
        } else {
            boolean flag = isSameDay(time, lastTime);
            if (flag) {
                // 如果是同一天，则在五分钟之内，时间不显示
                if (time - lastTime < 5 * 60) {
                    isShow = false;
                }
            }
        }
        return isShow;
    }

    /**
     * 获取日期和时间
     *
     * @param time long 单位ms
     * @return String
     */
    public static String getDateTimeFromTimeStamp(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA);
        return sdf.format(time);
    }

    /**
     * 获取日期
     *
     * @param time long 单位ms
     * @return String
     */
    public static String getDateFromTimeStamp(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        return sdf.format(time);
    }

    /**
     * 获取日期
     *
     * @param time long 单位ms
     * @return String
     */
    public static String getDateNoYearFromTimeStamp(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日", Locale.CHINA);
        String s = sdf.format(time);
        if (s.startsWith("0")) {
            s = s.substring(1);
        }
        return s;
    }

    /**
     * 获取日期
     *
     * @param time long 单位ms
     * @return String
     */
    public static String getDateNoDayFromTimeStamp(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月", Locale.CHINA);
        return sdf.format(time);
    }

    /**
     * 获取月期和时分
     *
     * @param time long 单位ms
     * @return String
     */
    public static String getDateTimeNoYearFromTimeStamp(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm", Locale.CHINA);
        String s = sdf.format(time);
        if (s.startsWith("0")) {
            s = s.substring(1);
        }
        return s;
    }

    /**
     * 获取时分
     *
     * @param time long 单位ms
     * @return String
     */
    public static String getTimeFromTimeStamp(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CHINA);
        return sdf.format(time);
    }

    /**
     * @param satrt  开始位置
     * @param string 要截取的字符串
     * @param length 从最后一位倒数，要截取的长度
     * @return
     */
    public static String subString(int satrt, String string, int length) {
        String subString = string.substring(satrt, string.length() - length);
        return subString;
    }

    /**
     * 获取当前的日期，格式为yyyy-MM-dd
     *
     * @return String
     * 带参数的日期转化
     */
    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return sdf.format(date);
    }

    /**
     * 获取昨日时间
     *
     * @return String
     */
    public static String yesterdayDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat("yyyy.MM.dd ").format(cal.getTime());
        return yesterday;
    }

    /**
     * 获取当前本周的起始的时间
     *
     * @return
     */
    public static String getWeekDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        String startWeekDate = new SimpleDateFormat("yyyy.MM.dd ").format(cal.getTime());
        return startWeekDate;
    }

    /**
     * 获取本周的初始时间的月
     *
     * @param time
     * @return
     */
    public static String getWeekDateMonth(String time) {
        return time.substring(time.indexOf(".") + 1, time.lastIndexOf("."));
    }

    /**
     * 获取本周的时间的日
     *
     * @param time
     * @return
     */
    public static String getWeekDateDay(String time) {
        return time.substring(time.lastIndexOf(".") + 1);
    }

    /**
     * 获取本周日日期
     *
     * @return
     */
    public static String getEndWeek() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        String endWeekDate = new SimpleDateFormat("yyyy.MM.dd ").format(cal.getTime());
        return endWeekDate;
    }

    /**
     * 获取本月的起始日期
     *
     * @return
     */
    public static String getMonthDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String startMonthDate = new SimpleDateFormat("yyyy.MM.dd ").format(cal.getTime());
        return startMonthDate;
    }

    /**
     * 获取当前月末的时间
     *
     * @return
     */
    public static String getMonthEndDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 0);//设置为1号,当前日期既为本月第一天
        String endMonthDate = new SimpleDateFormat("yyyy.MM.dd ").format(cal.getTime());
        return endMonthDate;
    }

    /**
     * 通过传入的时间转换相应的格式
     *
     * @param date
     * @param dateFormat
     * @return
     */
    public static String dateToString(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.CHINA);
        return sdf.format(date);
    }

    /**
     * 将一种日期格式的字符串转化成另一种
     *
     * @param s
     * @param sFormat
     * @param toFormat
     * @return
     */
    public static String stringToStringDate(String s, String sFormat, String toFormat) {
        Date date = null;
        try {
            date = new SimpleDateFormat(sFormat).parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat(toFormat).format(date);
    }

    public static int getCurrentYear() {
        Calendar a = Calendar.getInstance();
        return a.get(Calendar.YEAR);
    }

    /**
     * 计算三个月前的时间
     *
     * @param endDate
     * @return
     */
    public static String getThreeMonthDate(String endDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String defaultStartDate = null;
        try {
            Date nowDate = simpleDateFormat.parse(endDate);
            Date dBefore = new Date();
            Calendar calendar = Calendar.getInstance(); //得到日历
            calendar.setTime(nowDate);//把当前时间赋给日历
            calendar.add(calendar.MONTH, -3);  //设置为前3月
            dBefore = calendar.getTime();   //得到前3月的时间

            defaultStartDate = simpleDateFormat.format(dBefore);    //格式化前3月的时间
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return defaultStartDate;
    }

    /**
     * 计算十二个月前的时间
     *
     * @param endDate
     * @return
     */
    public static String getSixMonthDate(String endDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String defaultStartDate = null;
        try {
            Date nowDate = simpleDateFormat.parse(endDate);
            Date dBefore = new Date();
            Calendar calendar = Calendar.getInstance(); //得到日历
            calendar.setTime(nowDate);//把当前时间赋给日历
            calendar.add(Calendar.YEAR, -1);  //设置为前12月
            dBefore = calendar.getTime();   //得到前12月的时间
            defaultStartDate = simpleDateFormat.format(dBefore);    //格式化前12月的时间
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return defaultStartDate;
    }

    /**
     * 计算个月前的时间
     *
     * @param endDate
     * @return
     */
    public static String getTwelveMonthDate(String endDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String defaultStartDate = null;
        try {
            Date nowDate = simpleDateFormat.parse(endDate);
            Date dBefore = new Date();
            Calendar calendar = Calendar.getInstance(); //得到日历
            calendar.setTime(nowDate);//把当前时间赋给日历
            calendar.add(calendar.MONTH, -6);  //设置为前6月
            dBefore = calendar.getTime();   //得到前6月的时间

            defaultStartDate = simpleDateFormat.format(dBefore);    //格式化前6月的时间
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return defaultStartDate;
    }

    /**
     * 以分为单位的金额 100表示1元
     *
     * @param num
     * @return 乘以100后的值
     */
    public static String enPrecise(String num) {
//		Float fNum = Float.valueOf(num);
//		return  fNum*100f+"";
        return new BigDecimal(num).multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP).toString();
    }

    /**
     * 以元为单位的金额 1表示100分
     *
     * @param num
     * @return 除以100后的值
     */
    public static String asePrecise(double num) {
        return new BigDecimal(num).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP).toString();
    }

    /**
     * 获取字符串中数字位置
     *
     * @param s 字符串
     * @return 返回位置列表
     */
    public static List<Integer> getPostionNum(String s) {
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            if (a >= '0' && a <= '9') {
                System.out.println("第一个数字出现的位置:" + i);
                positions.add(i);
            }
        }
        return positions;
    }

    /**
     * 判断字符串是否为纯数字
     *
     * @param s 字符
     * @return 是否为数字
     */
    public static boolean isNumeric(String s) {
        return Pattern.matches("^[0-9]*$", s);
    }

    /**
     * 判断两个字符数字是否相等
     *
     * @param str1 String
     * @param str2 String
     * @return boolean true相等
     */
    public static boolean isNumStrEqual(String str1, String str2) {
        boolean flag = false;
        try {
            if (str1.equals(str2)) {
                flag = true;
            } else {
                double d1 = Double.parseDouble(str1);
                double d2 = Double.parseDouble(str2);
                if (d1 == d2) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static Boolean toBoolean(String value) {
        Boolean flag = null;
        if (value != null) {
            flag = Boolean.parseBoolean(value);
        }
        return flag;
    }

    /**
     * 得到会员增量时间
     */
    public static String getMemberIncrement() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
        return sdf.format(date);
    }

    /**
     * 判断字符串第一位是否是字符
     *
     * @param s String
     * @return boolean
     */
    public static boolean isChar(String s) {
        boolean flag = false;
        char x = s.charAt(0);
        if ((x >= 'a' && x <= 'z') || (x >= 'A' && x <= 'Z')) {
            flag = true;
        }
        return flag;
    }

    public static Date StringToDate(String str, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = null;
        if (StringUtil.isNull(str)) {
            return date;
        }
        try {
            date = dateFormat.parse(str);
//            System.out.println(date.toLocaleString().split(" ")[0]);//切割掉不要的时分秒数据
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }



}
