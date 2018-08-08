package cold.face.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: leron
 * Date: 13-8-21
 * Time: 下午12:09
 * 日期辅助类
 */
public class DateUtils {

    /**
     * Base ISO 8601 Date format yyyyMMdd i.e., 20021225 for the 25th day of
     * December in the year 2002
     */
    public static final String ISO_DATE_FORMAT = "yyyyMMdd";

    /**
     * Expanded ISO 8601 Date format yyyy-MM-dd i.e., 2002-12-25 for the 25th
     * day of December in the year 2002
     */
    public static final String ISO_EXPANDED_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * yyyy-MM-dd hh:mm:ss
     */
    public static String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * Default lenient setting for getDate.
     */
    private static boolean LENIENT_DATE = false;

    public static String[] TIME_RANGE_NAMES = {"今天", "本周", "上周", "本月", "上月",
            "今年", "全部"};
    public static String[] TIME_RANGE_VALUES = {"today", "thisweek",
            "lastweek", "thismonth", "lastmonth", "thisyear", "all"};

    /**
     * 暂时不用
     *
     * @param JD
     * @return
     */
    protected static final float normalizedJulian(float JD) {

        float f = Math.round(JD + 0.5f) - 0.5f;

        return f;
    }

    /**
     * 浮点值转换成日期格式<br>
     * 暂时不用 Returns the Date from a julian. The Julian date will be converted to
     * noon GMT, such that it matches the nearest half-integer (i.e., a julian
     * date of 1.4 gets changed to 1.5, and 0.9 gets changed to 0.5.)
     *
     * @param JD the Julian date
     * @return the Gregorian date
     */
    public static final Date toDate(float JD) {

		/*
         * To convert a Julian Day Number to a Gregorian date, assume that it is
		 * for 0 hours, Greenwich time (so that it ends in 0.5). Do the
		 * following calculations, again dropping the fractional part of all
		 * multiplicatons and divisions. Note: This method will not give dates
		 * accurately on the Gregorian Proleptic Calendar, i.e., the calendar
		 * you get by extending the Gregorian calendar backwards to years
		 * earlier than 1582. using the Gregorian leap year rules. In
		 * particular, the method fails if Y<400.
		 */
        float Z = (normalizedJulian(JD)) + 0.5f;
        float W = (int) ((Z - 1867216.25f) / 36524.25f);
        float X = (int) (W / 4f);
        float A = Z + 1 + W - X;
        float B = A + 1524;
        float C = (int) ((B - 122.1) / 365.25);
        float D = (int) (365.25f * C);
        float E = (int) ((B - D) / 30.6001);
        float F = (int) (30.6001f * E);
        int day = (int) (B - D - F);
        int month = (int) (E - 1);

        if (month > 12) {
            month = month - 12;
        }

        int year = (int) (C - 4715); // (if Month is January or February) or
        // C-4716 (otherwise)

        if (month > 2) {
            year--;
        }

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1); // damn 0 offsets
        c.set(Calendar.DATE, day);

        return c.getTime();
    }

    /**
     * Returns the days between two dates. Positive values indicate that the
     * second date is after the first, and negative values indicate, well, the
     * opposite. Relying on specific times is problematic.
     *
     * @param early the "first date"
     * @param late  the "second date"
     * @return the days between the two dates
     */
    public static final int daysBetween(Date early, Date late) {

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(early);
        c2.setTime(late);

        return daysBetween(c1, c2);
    }

    /**
     * Returns the days between two dates. Positive values indicate that the
     * second date is after the first, and negative values indicate, well, the
     * opposite.
     *
     * @param early
     * @param late
     * @return the days between two dates.
     */
    public static final int daysBetween(Calendar early, Calendar late) {

        return (int) (toJulian(late) - toJulian(early));
    }

    /**
     * Return a Julian date based on the input parameter. This is based from
     * calculations found at <a
     * href="http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html">Julian Day
     * Calculations (Gregorian Calendar)</a>, provided by Bill Jeffrys.
     *
     * @param c a calendar instance
     * @return the julian day number
     */
    public static final float toJulian(Calendar c) {

        int Y = c.get(Calendar.YEAR);
        int M = c.get(Calendar.MONTH);
        int D = c.get(Calendar.DATE);
        int A = Y / 100;
        int B = A / 4;
        int C = 2 - A + B;
        float E = (int) (365.25f * (Y + 4716));
        float F = (int) (30.6001f * (M + 1));
        float JD = C + D + E + F - 1524.5f;

        return JD;
    }

    /**
     * 暂时不用 Return a Julian date based on the input parameter. This is based
     * from calculations found at <a
     * href="http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html">Julian Day
     * Calculations (Gregorian Calendar)</a>, provided by Bill Jeffrys.
     *
     * @param date
     * @return the julian day number
     */
    public static final float toJulian(Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return toJulian(c);
    }

    /**
     * 日期增加
     *
     * @param isoString 日期字符串
     * @param fmt       格式
     * @param field     年/月/日 Calendar.YEAR/Calendar.MONTH/Calendar.DATE
     * @param amount    增加数量
     * @return
     */
    public static final String dateIncrease(String isoString, String fmt,
                                            int field, int amount) {

        try {
            Calendar cal = GregorianCalendar.getInstance(TimeZone
                    .getTimeZone("GMT"));
            cal.setTime(stringToDate(isoString, fmt, true));
            cal.add(field, amount);

            return dateToString(cal.getTime(), fmt);

        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Time Field Rolling function. Rolls (up/down) a single unit of time on the
     * given time field.
     *
     * @param isoString
     * @param field     the time field.
     * @param up        Indicates if rolling up or rolling down the field value.
     *                  use formating char's
     * @throws ParseException if an unknown field value is given.
     */
    public static final String roll(String isoString, String fmt, int field,
                                    boolean up) throws ParseException {

        Calendar cal = GregorianCalendar.getInstance(TimeZone
                .getTimeZone("GMT"));
        cal.setTime(stringToDate(isoString, fmt));
        cal.roll(field, up);

        return dateToString(cal.getTime(), fmt);
    }

    /**
     * Time Field Rolling function. Rolls (up/down) a single unit of time on the
     * given time field.
     *
     * @param isoString
     * @param field     the time field.
     * @param up        Indicates if rolling up or rolling down the field value.
     * @throws ParseException if an unknown field value is given.
     */
    public static final String roll(String isoString, int field, boolean up)
            throws ParseException {

        return roll(isoString, DATETIME_PATTERN, field, up);
    }

    /**
     * 字符串转换为日期java.util.Date
     *
     * @param dateText 字符串
     * @param format   日期格式
     * @param lenient  日期越界标志
     * @return
     */
    public static Date stringToDate(String dateText, String format,
                                    boolean lenient) {

        if (dateText == null) {

            return null;
        }

        if (dateText.length() == 10) {
            dateText = dateText + " 00:00:00";
        }

        DateFormat df = null;

        try {

            if (format == null) {
                df = new SimpleDateFormat();
            } else {
                df = new SimpleDateFormat(format);
            }

            // setLenient avoids allowing dates like 9/32/2001
            // which would otherwise parse to 10/2/2001
//            df.setLenient(false);

            return df.parse(dateText);
        } catch (ParseException e) {

            return null;
        }
    }

    /**
     * 字符串转换为日期java.util.Date
     *
     * @param dateString 字符串
     * @param format     日期格式
     * @return
     */
    public static Date stringToDate(String dateString, String format) {

        return stringToDate(dateString, format, LENIENT_DATE);
    }

    /**
     * 字符串转换为日期java.util.Date
     *
     * @param dateString 字符串
     */
    public static Date stringToDate2(String dateString) {

        return stringToDate(dateString, DATETIME_PATTERN, LENIENT_DATE);
    }

    /**
     * 字符串转换为日期java.util.Date
     *
     * @param dateString 字符串
     */
    public static Date stringToDate(String dateString) {

        return stringToDate(dateString, ISO_EXPANDED_DATE_FORMAT, LENIENT_DATE);
    }

    /**
     * 根据时间变量返回时间字符串
     *
     * @param dateText 时间变量
     * @return 返回时间字符串
     */
    public static String stringToDateToString(String dateText) {
        if (dateText == null) {
            return null;
        }
        if (dateText.length() == 10) {
            dateText = dateText + " 00:00:00";
        }
        DateFormat df = null;
        try {
            df = new SimpleDateFormat(DATETIME_PATTERN);
            // setLenient avoids allowing dates like 9/32/2001
            // which would otherwise parse to 10/2/2001
            df.setLenient(false);
            return dateToString(df.parse(dateText), DATETIME_PATTERN);
        } catch (ParseException e) {

            return null;
        }

    }

    /**
     * 根据时间变量返回时间字符串
     *
     * @param pattern 时间字符串样式
     * @param date    时间变量
     * @return 返回时间字符串
     */
    public static String dateToString(Date date, String pattern) {

        if (date == null) {

            return null;
        }

        try {

            SimpleDateFormat sfDate = new SimpleDateFormat(pattern);
            sfDate.setLenient(false);

            return sfDate.format(date);
        } catch (Exception e) {

            return null;
        }
    }

    /**
     * 根据时间变量返回时间字符串 yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        return dateToString(date, ISO_EXPANDED_DATE_FORMAT);
    }

    /**
     * 根据时间变量返回时间字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String dateTimeToString(Date date) {
        return dateToString(date, DATETIME_PATTERN);
    }

    /**
     * 返回当前时间
     *
     * @return 返回当前时间
     */
    public static Date getCurrentDateTime() {
        Calendar calNow = Calendar.getInstance();
        Date dtNow = calNow.getTime();

        return dtNow;
    }


    /**
     * 返回当前日期字符串
     *
     * @param pattern 日期字符串样式
     * @return
     */
    public static String getCurrentDateString(String pattern) {
        return dateToString(getCurrentDateTime(), pattern);
    }

    /**
     * 返回当前日期字符串 yyyy-MM-dd
     *
     * @return
     */
    public static String getCurrentDateString() {
        return dateToString(getCurrentDateTime(), ISO_EXPANDED_DATE_FORMAT);
    }

    /**
     * 返回当前日期+时间字符串 yyyy-MM-dd hh:mm:ss
     *
     * @param date
     * @return
     */
    public static String dateToStringWithTime(Date date) {

        return dateToString(date, DATETIME_PATTERN);
    }

    /**
     * 日期增加-按日增加
     *
     * @param date
     * @param days
     * @return java.util.Date
     */
    public static Date dateIncreaseByDay(Date date, int days) {

        Calendar cal = GregorianCalendar.getInstance(TimeZone
                .getTimeZone("GMT+8"));
        cal.setTime(date);
        cal.add(Calendar.DATE, days);

        return cal.getTime();
    }

    /**
     * 日期增加-按月增加
     *
     * @param date
     * @param mnt
     * @return java.util.Date
     */
    public static Date dateIncreaseByMonth(Date date, int mnt) {

        Calendar cal = GregorianCalendar.getInstance(TimeZone
                .getTimeZone("GMT+8"));
        cal.setTime(date);
        cal.add(Calendar.MONTH, mnt);

        return cal.getTime();
    }

    /**
     * 日期增加-按年增加
     *
     * @param date
     * @param mnt
     * @return java.util.Date
     */
    public static Date dateIncreaseByYear(Date date, int mnt) {

        Calendar cal = GregorianCalendar.getInstance(TimeZone
                .getTimeZone("GMT+8"));
        cal.setTime(date);
        cal.add(Calendar.YEAR, mnt);

        return cal.getTime();
    }

    /**
     * 日期增加
     *
     * @param date 日期字符串 yyyy-MM-dd
     * @param days
     * @return 日期字符串 yyyy-MM-dd
     */
    public static String dateIncreaseByDay(String date, int days) {
        return dateIncreaseByDay(date, ISO_DATE_FORMAT, days);
    }

    /**
     * 日期增加
     *
     * @param date 日期字符串
     * @param fmt  日期格式
     * @param days
     * @return
     */
    public static String dateIncreaseByDay(String date, String fmt, int days) {
        return dateIncrease(date, fmt, Calendar.DATE, days);
    }

    /**
     * 日期字符串格式转换
     *
     * @param src    日期字符串
     * @param srcfmt 源日期格式
     * @param desfmt 目标日期格式
     * @return
     */
    public static String stringToString(String src, String srcfmt, String desfmt) {
        return dateToString(stringToDate(src, srcfmt), desfmt);
    }

    /**
     * 日期范围函数组 getBeginOfThisWeek 本周开始日期 getEndOfThisWeek 本周结束日期
     * getBeginOfLastWeek 上周开始日期 getEndOfLastWeek 上周结束日期 getBeginOfThisMonth
     * 本月开始日期 getEndOfThisMonth 本月结束日期 getBeginOfLastMonth 上月开始日期
     * getEndOfLastMonth 上月结束日期 getBeginOfThisYear 今年开始日期 getEndOfThisYear
     * 今年结束日期
     *
     * @return
     */
    public static Date getBeginOfThisWeek(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        int todayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (todayOfWeek >= Calendar.MONDAY && todayOfWeek <= Calendar.SATURDAY) {
            calendar.add(Calendar.DATE, 2 - todayOfWeek);
        } else if (todayOfWeek == Calendar.SUNDAY) {
            calendar.add(Calendar.DATE, -6);
        }

        return calendar.getTime();
    }

    public static Date getEndOfThisWeek(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        int todayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (todayOfWeek >= Calendar.MONDAY && todayOfWeek <= Calendar.SATURDAY) {
            calendar.add(Calendar.DATE, 8 - todayOfWeek);
        } else if (todayOfWeek == Calendar.SUNDAY) {
            calendar.add(Calendar.DATE, 0);
        }

        return calendar.getTime();
    }

    public static Date getBeginOfLastWeek(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, -7);

        return getBeginOfThisWeek(calendar.getTime());
    }

    public static Date getEndOfLastWeek(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, -7);

        return getEndOfThisWeek(calendar.getTime());
    }

    public static Date getBeginOfThisMonth(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        int todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, 1 - todayOfMonth);

        return calendar.getTime();
    }

    public static Date getEndOfThisMonth(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        int todayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int maxOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DAY_OF_MONTH, maxOfMonth - todayOfMonth);

        return calendar.getTime();
    }

    public static Date getBeginOfLastMonth(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.MONTH, -1);

        return getBeginOfThisMonth(calendar.getTime());
    }

    public static Date getEndOfLastMonth(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.MONTH, -1);

        return getEndOfThisMonth(calendar.getTime());
    }

    public static Date getBeginOfThisYear() {
        Calendar calendar = Calendar.getInstance();
        int thisYear = calendar.get(Calendar.YEAR);

        return stringToDate(thisYear + "-1-1");
    }

    public static Date getEndOfThisYear() {
        Calendar calendar = Calendar.getInstance();
        int thisYear = calendar.get(Calendar.YEAR);

        return stringToDate(thisYear + "-12-31");
    }

    public static String getEndDate(String range) {
        if (range.trim().equals(TIME_RANGE_VALUES[0])) {// today
            return dateToString(new Date()) + " 23:59:59 ";

        } else if (range.trim().equals(TIME_RANGE_VALUES[1])) {// this week
            return dateToString(getEndOfThisWeek(new Date())) + " 23:59:59 ";

        } else if (range.trim().equals(TIME_RANGE_VALUES[2])) {// last week
            return dateToString(getEndOfLastWeek(new Date())) + " 23:59:59 ";

        } else if (range.trim().equals(TIME_RANGE_VALUES[3])) {// this month
            return dateToString(getEndOfThisMonth(new Date())) + " 23:59:59 ";

        } else if (range.trim().equals(TIME_RANGE_VALUES[4])) {// last month
            return dateToString(getEndOfLastMonth(new Date())) + " 23:59:59 ";

        } else if (range.trim().equals(TIME_RANGE_VALUES[5])) {// this year
            return dateToString(getEndOfThisYear()) + " 23:59:59 ";

        } else if (range.trim().equals(TIME_RANGE_VALUES[6])) {// all
            return "";
        }

        return "";
    }

    public static String getBeginDate(String range) {
        if (range.trim().equals(TIME_RANGE_VALUES[0])) {// today
            return dateToString(new Date()) + " 00:00:00";

        } else if (range.trim().equals(TIME_RANGE_VALUES[1])) {// this week
            return dateToString(getBeginOfThisWeek(new Date())) + " 00:00:00";

        } else if (range.trim().equals(TIME_RANGE_VALUES[2])) {// last week
            return dateToString(getBeginOfLastWeek(new Date())) + " 00:00:00";

        } else if (range.trim().equals(TIME_RANGE_VALUES[3])) {// this month
            return dateToString(getBeginOfThisMonth(new Date())) + " 00:00:00";

        } else if (range.trim().equals(TIME_RANGE_VALUES[4])) {// last month
            return dateToString(getBeginOfLastMonth(new Date())) + " 00:00:00";

        } else if (range.trim().equals(TIME_RANGE_VALUES[5])) {// this year
            return dateToString(getBeginOfThisYear()) + " 00:00:00";

        } else if (range.trim().equals(TIME_RANGE_VALUES[6])) {// all
            return "";
        }

        return "";
    }

    public static String getFormatDate(String formatDate) {
        SimpleDateFormat format = new SimpleDateFormat(DATETIME_PATTERN);
        String date = format.format(stringToDate(formatDate,
                DATETIME_PATTERN));
        return date;
    }

    /**
     * YYYYMMDDHHMMSS
     */
    public static String parseLongTime(Date date) {
        String fmt = "yyyyMMddHHmmss";
        DateFormat df = new SimpleDateFormat(fmt);
        String str = df.format(date);
        return str;
    }

    /**
     * YYYYMM
     */
    public static String getShortParseDate(Date date) {
        String fmt = "yyyyMM";
        DateFormat df = new SimpleDateFormat(fmt);
        String str = df.format(date);
        return str;
    }

    /**
     * 返回年份
     *
     * @param date 日期
     * @return 返回年份
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 返回月份
     *
     * @param date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回日份
     *
     * @param date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回小时
     *
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 返回分钟
     *
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     *
     * @param date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }

    /**
     * 返回毫秒
     *
     * @param date 日期
     * @return 返回毫秒
     */
    public static long getMillis(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    /**
     * 对当前日期增加月份
     *
     * @param month
     * @return
     */
    public static Date addMonth(Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    public static long fromDateStringToLong(String inVal) { //此方法计算时间毫秒
        Date date = null;   //定义时间类型
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-mm-dd hh:ss");
        try {
            date = inputFormat.parse(inVal); //将字符型转换成日期型
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date.getTime();   //返回毫秒数
    }


    /**
     * 输入两个日期中区间的所有点
     */

    static String dateFormat = "yyyy-MM-dd";
    static SimpleDateFormat format = new SimpleDateFormat(dateFormat);

    public static List<Date> process(String date1, String date2) {
        List<Date> dateList = new ArrayList<Date>();

        if (date1.equals(date2)) {
//			System.out.println("两个日期相等!");
            dateList.add(DateUtils.stringToDate(date2, "yyyy-MM-dd"));
            return dateList;
        }

        String tmp;
        if (date1.compareTo(date2) > 0) { // 确保 date1的日期不晚于date2
            tmp = date1;
            date1 = date2;
            date2 = tmp;
        }

        tmp = format.format(str2Date(date1).getTime() + 3600 * 24 * 1000);

        int num = 0;
        while (tmp.compareTo(date2) < 0) {
//			System.out.println(tmp);
            dateList.add(DateUtils.stringToDate(tmp, "yyyy-MM-dd"));
            num++;
            tmp = format.format(str2Date(tmp).getTime() + 3600 * 24 * 1000);
        }

//		if (num == 0)
//			System.out.println("两个日期相邻!");
        return dateList;
    }

    private static Date str2Date(String str) {
        if (str == null) {
            return null;
        }

        try {
            return format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * EEE MMM dd HH:mm:ss zzz yyyy
     *
     * @param cts
     * @return
     */
    public static Date CTSStringToDate(String cts) {
        DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        try {
            Date date = df.parse(cts);
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String str = df.format(date);// 获得格式化后的日期字符串
            return str2Date(str);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }// 那天是周一
        return null;
    }

    /**
     * 两个日期之间的月数
     *
     * @param date1 <Date> 2016-4-6 00:00:00
     * @param date2 <Date> 2014-4-6 00:00:00
     * @return int 24
     * @throws ParseException
     */
    public static int calculateMonthIn(Date date1, Date date2) {
        Calendar cal1 = new GregorianCalendar();
        cal1.setTime(date1);
        Calendar cal2 = new GregorianCalendar();
        cal2.setTime(date2);
        int c =
                (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + cal1.get(Calendar.MONTH)
                        - cal2.get(Calendar.MONTH);
        return c;
    }

    public static void main(String[] args) throws ParseException {

//			System.out.println(calculateMonthIn(str2Date("2016-4-6 00:00:00"),str2Date("2014-4-6 00:00:00")));

//		  DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
//		  try {
//			Date date = df.parse("Mon Dec 17 00:00:00 CST 2012");
//			df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		      String str = df.format(date);// 获得格式化后的日期字符串
//		      System.err.println(str);// 打印最终结果
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}// 那天是周一
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
        String dstr = "2017-05-01 00:00:00";
        for (int i = 1; i <= 6; i++) {
            String date = sdf.format(dateIncreaseByMonth(sdf.parse(dstr), i));
            System.out.println(date);
        }

    }


    /**
     * 获取后几个月相同的一天
     *
     * @param installments 贷款期数
     * @param minDate      第一期还款日
     * @param date         薪资发放日
     * @return
     */
    public static List<Date> getDateOfMonthBySet(int installments, Date minDate, Date date) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        List<Date> list = new ArrayList<Date>();
        if (installments > 0) {
            list.add(minDate);
            int DAY_OF_MONTH = 1;
            if (date == null) {
                DAY_OF_MONTH = minDate.getDate();
            } else {
                DAY_OF_MONTH = date.getDate();
            }
            for (int i = 0; i < installments - 1; i++) {
                Calendar lastDate = Calendar.getInstance();
                lastDate.set(Calendar.MONTH, minDate.getMonth() + (i + 1));
                lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
                lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
//	                System.out.println("====>"+lastDate.getTime());

                if (lastDate.getTime().getDate() <= DAY_OF_MONTH) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(lastDate.getTime());
                    calendar.set(Calendar.DAY_OF_MONTH, lastDate.getTime().getDate());
                    //calendar.add(Calendar.MONTH, i );
                    Date d = calendar.getTime();
                    list.add(d);
                } else {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(minDate);
                    calendar.set(Calendar.DAY_OF_MONTH, DAY_OF_MONTH);
                    calendar.add(Calendar.MONTH, i + 1);
                    Date d = calendar.getTime();
                    list.add(d);
                }

            }
        }
        return list;
    }

    //将当前时间戳转当天00:00:00时间戳
    public static long getCurDayTime(Long curTime) {
        Date d = new Date(curTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(d);
        Long ret = 0L;
        try {
            ret = string2Long(dateNowStr, "yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ret;
    }

    // 获取两位随机数
    public static String getRandomNum() {
        Random random = new Random();
        Integer num1 = random.nextInt(10);
        Integer num2 = random.nextInt(10);
        return num1.toString() + num2.toString();
    }

    // date类型转换为String类型
    // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    // data Date类型的时间
    public static String date2String(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    // long类型转换为String类型
    // currentTime要转换的long类型的时间
    // formatType要转换的string类型的时间格式
    public static String long2String(long currentTime, String formatType) {
        Date date = long2Date(currentTime, formatType); // long类型转成Date类型
        String strTime = date2String(date, formatType); // date类型转成String
        return strTime;
    }

    // string类型转换为date类型
    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date string2Date(String strTime, String formatType) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        try {
            date = formatter.parse(strTime);
        } catch (ParseException e) {
            System.out.println("string类型转换为date类型异常" + e.getMessage());
        }
        return date;
    }

    // long转换为Date类型
    // currentTime要转换的long类型的时间
    // formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static Date long2Date(long currentTime, String formatType) {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = date2String(dateOld, formatType); // 把date类型的时间转换为string
        Date date = string2Date(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    // string类型转换为long类型
    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long string2Long(String strTime, String formatType)
            throws ParseException {
        Date date = string2Date(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = date2Long(date); // date类型转成long类型
            return currentTime;
        }
    }

    // date类型转换为long类型
    // date要转换的date类型的时间
    public static long date2Long(Date date) {
        if (date == null) {
            return 0;
        }
        return date.getTime();
    }

}
