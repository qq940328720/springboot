package cold.face.others;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class DateUtils {
	
	public final static String DATE_FORMAT_ISO = "yyyy-MM-dd";

    public final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    public final static String DATE_FORMAT_DAY = "yyyy.MM.dd";
	
	private final static Map<String,SimpleDateFormat> map = new ConcurrentHashMap<String,SimpleDateFormat>();
	
	static {
		map.put(DATE_FORMAT_ISO, new SimpleDateFormat(DATE_FORMAT_ISO));
		map.put(DATE_FORMAT, new SimpleDateFormat(DATE_FORMAT));
		map.put(DATE_FORMAT_DAY, new SimpleDateFormat(DATE_FORMAT_DAY));
	}
	
	public final static SimpleDateFormat getSdf(String format) {
		SimpleDateFormat sdf = map.get(format);
		if(sdf != null) {
			return sdf;
		}else{
			synchronized(DateUtils.class) {
				sdf = map.get(format);
				if(sdf == null) {
					sdf = new SimpleDateFormat(format);
					map.put(format,sdf);
				}
				return sdf;
			}
		}
	}
	
	/**
	 * 获取当天最晚时间
	 * @param date
	 * @return
	 */
	public static Date convertTimeOfDate(Date date) {
		if (date == null) return null;
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(date.getTime());
		ca.set(Calendar.MINUTE, 59);
		ca.set(Calendar.SECOND, 59);
		ca.set(Calendar.MILLISECOND, 999);
		return ca.getTime();
	}

	/**
	 * 获取当天最晚时间
	 * @param date
	 * @return
	 */
	public static Date convertLastTimeOfDate(Date date) {
		if (date == null) return null;
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(date.getTime());
		ca.set(Calendar.HOUR_OF_DAY, 23);
		ca.set(Calendar.MINUTE, 59);
		ca.set(Calendar.SECOND, 59);
		ca.set(Calendar.MILLISECOND, 999);
		return ca.getTime();
	}
	
	/**
	 * 比较两个日期之间的天数差异，例如：如果left比right晚一天，返回1，如果相等返回0，
	 * 如果left比right早一天，返回-1
	 * @param left
	 * @param right
	 * @return int 差异天数
	 */
	public static int getDiffDays(Date left, Date right){
		GregorianCalendar leftCaldr = new GregorianCalendar();
		GregorianCalendar rightCaldr = new GregorianCalendar();
		leftCaldr.setTime(left);
		rightCaldr.setTime(right);
		
		leftCaldr.set(GregorianCalendar.HOUR_OF_DAY, 0);
		leftCaldr.set(GregorianCalendar.MINUTE, 0);
		leftCaldr.set(GregorianCalendar.SECOND, 0);
		leftCaldr.set(GregorianCalendar.MILLISECOND, 0);
		
		rightCaldr.set(GregorianCalendar.HOUR_OF_DAY, 0);
		rightCaldr.set(GregorianCalendar.MINUTE, 0);
		rightCaldr.set(GregorianCalendar.SECOND, 0);
		rightCaldr.set(GregorianCalendar.MILLISECOND, 0);
		
		long leftMilSec = leftCaldr.getTimeInMillis();
		long rightMilSec = rightCaldr.getTimeInMillis();
		
		long res = (leftMilSec - rightMilSec)/(24L * 60L *60L * 1000L);
		
		return (int)res;
	}
	
	/**
	 * 获取当天最早时间
	 * @param date
	 * @return
	 */
	public static Date convertStartTimeOfDate(Date date) {
		if (date == null) return null;
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(date.getTime());
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		ca.set(Calendar.MILLISECOND, 0);
		return ca.getTime();
	}
	
	/**
	 * 得到指定月的天数
	 */
	public static int getMonthLastDay(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		System.out.println(getSdf(DATE_FORMAT).format(a.getTime()));
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		System.out.println(getSdf(DATE_FORMAT).format(a.getTime()));

		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}
	
	/**
	 * 得到指定月的首天时间
	 */
	public static Date getMonthFirstDate(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		return a.getTime();
	}
	
	/**
	 *  根据时间戳得到指定月的首天时间
	 */
	public static Date getMonthFirstDate(long timeMillis) {
		Date date = new Date(timeMillis);
		Calendar a = Calendar.getInstance();
		a.setTime(date);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		return a.getTime();
	}
	
	/**
	 * 得到指定月的末天时间
	 */
	public static Date getMonthLastDate(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);
		return a.getTime();
	}
	
	/**
	 * 根据时间戳得到指定月的末天时间
	 */
	public static Date getMonthLastDate(long timeMillis) {
		Date date = new Date(timeMillis);
		Calendar a = Calendar.getInstance();
		a.setTime(date);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);
		return a.getTime();
	}
	
	/**
	 * 根据年月计算上一期的年月，用于计算环比
	 */
	public static Map<String, Object> getRingYearMonth(String beginYearMonth, String endYearMonth) {
		String lastBeginYearMonth = "";
		String lastEndYearMonth = "";
		int beginYear = Integer.parseInt(beginYearMonth.substring(0,4));
		int beginMonth = Integer.parseInt(beginYearMonth.substring(4,6));
		int endYear = Integer.parseInt(endYearMonth.substring(0,4));
		int endMonth = Integer.parseInt(endYearMonth.substring(4,6));
		int monthNum = (endYear - beginYear) * 12 + endMonth - beginMonth + 1;
		if(monthNum < 0) {
			System.out.println("error");
			return null;
		}
		int temp = 0;
		if(beginMonth - monthNum > 0) {
			temp = beginMonth - monthNum;
			lastBeginYearMonth = beginYear + "" + (temp < 10 ? "0" + temp : temp);
		} else {
			int times = (monthNum + 11) / 12;
			temp = times * 12 + beginMonth - monthNum;
			lastBeginYearMonth = (beginYear - times) + "" + (temp < 10 ? "0" + temp : temp);
		}
		if(endMonth - monthNum > 0) {
			temp = endMonth - monthNum;
			lastEndYearMonth = endYear + "" + (temp < 10 ? "0" + temp : temp);
		} else {
			int times = (monthNum + 11) / 12;
			temp = times * 12 + endMonth - monthNum;
			lastEndYearMonth = (endYear - times) + "" + (temp < 10 ? "0" + temp : temp);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginYearMonth", Integer.parseInt(beginYearMonth));
		map.put("endYearMonth", Integer.parseInt(endYearMonth));
		map.put("lastBeginYearMonth", Integer.parseInt(lastBeginYearMonth));
		map.put("lastEndYearMonth", Integer.parseInt(lastEndYearMonth));
		map.put("beginYear", beginYear);
		map.put("endYear", endYear);
		return map;
	}
	
	/**
	 * 根据年月计算去年同期的年月，用于计算同比
	 */
	public static Map<String, Object> getLastYearMonth(String beginYearMonth, String endYearMonth) {
		String lastBeginYearMonth = "";
		String lastEndYearMonth = "";
		int beginYear = Integer.parseInt(beginYearMonth.substring(0,4));
		int beginMonth = Integer.parseInt(beginYearMonth.substring(4,6));
		int endYear = Integer.parseInt(endYearMonth.substring(0,4));
		int endMonth = Integer.parseInt(endYearMonth.substring(4,6));
		int monthNum = (endYear - beginYear) * 12 + endMonth - beginMonth + 1;
		if(monthNum < 0) {
			System.out.println("error");
			return null;
		}
		lastBeginYearMonth = beginYear - 1 + "" + beginYearMonth.substring(4,6);
		lastEndYearMonth = endYear - 1 + "" + endYearMonth.substring(4,6);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginYearMonth", Integer.parseInt(beginYearMonth));
		map.put("endYearMonth", Integer.parseInt(endYearMonth));
		map.put("lastBeginYearMonth", Integer.parseInt(lastBeginYearMonth));
		map.put("lastEndYearMonth", Integer.parseInt(lastEndYearMonth));
		map.put("beginYear", beginYear);
		map.put("endYear", endYear);
		return map;
	}
	
	public static Date strToDate(String s) {
		try {
			if (s == null || "".equals(s)) return null;
			return getSdf(DATE_FORMAT).parse(s);
		} catch (ParseException e) {
			throw new RuntimeException("pasrse date error", e);
		}
	}

	/**
	 * 将指定格式的字符串转换成日期类型
	 * 
	 * @param date 待转换的日期字符串
	 * @param dateFormat 日期格式字符串
	 * @return Date
	 */
	public static Date parse(String dateStr, String dateFormat) {
		if (dateStr == null || dateStr.equals("")) {
			return null;
		}
		try {
			return getSdf(dateFormat).parse(dateStr);
		} catch (Exception e) {
			throw new RuntimeException("DateUtil.convertStrToDate():" + e.getMessage());
		}
	}
	
	/**
	 * 将日期类型转换成指定格式的日期字符串
	 * 
	 * @param date 待转换的日期
	 * @param dateFormat 日期格式字符串
	 * @return String
	 */
	public static String format(Date date) {
		if (date == null) {
			return "";
		}
		return getSdf(DATE_FORMAT).format(date);
	}
	
	/**
	 * 将日期类型转换成指定格式的日期字符串
	 * 
	 * @param date 待转换的日期
	 * @param dateFormat 日期格式字符串
	 * @return String
	 */
	public static String format(Date date, String dateFormat) {
		if (date == null) {
			return "";
		}
		return getSdf(dateFormat).format(date);
	}
	
	public final static Date setDate(Date date, int minute, int second, int millionSecond) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MILLISECOND, millionSecond);
		return calendar.getTime();
	}
	
	public final static Date setDate(Date date, int hour, int minute, int second, int millionSecond) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MILLISECOND, millionSecond);
		return calendar.getTime();
	}

	public static Date addMinute(Date date, int minute) {
		if (date == null) return null;
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(date.getTime());
		ca.add(Calendar.MINUTE, minute);
		return ca.getTime();
	}
	
	public final static Date currentTime() {
		return new Date(System.currentTimeMillis());
	}
	
	/**
	 * 防止new
	 */
	private DateUtils() {
		
	}
	
	public static void main(String[] args) {
		System.out.println(getSdf(DATE_FORMAT).format(setDate(new Date(), 10, 20, 999)));
		System.out.println(getSdf(DATE_FORMAT).format(setDate(new Date(),22, 10, 20, 999)));
		System.out.println(getMonthLastDay(2017,10));
		System.out.println("heb1506407396759702563".length());
		System.out.println(getSdf(DATE_FORMAT).format(addMinute(new Date(), 12)));
		
		Date firstDate1 = getMonthFirstDate(2017, 1);
		System.out.println(convertStartTimeOfDate(firstDate1));
		System.out.println(getSdf(DATE_FORMAT).format(convertStartTimeOfDate(firstDate1)));
		
		Date lastDate1 = getMonthLastDate(2017, 6);
		System.out.println(convertStartTimeOfDate(lastDate1));
		System.out.println(getSdf(DATE_FORMAT).format(convertLastTimeOfDate(lastDate1)));
		System.out.println("firstDate1:" + convertStartTimeOfDate(firstDate1).getTime());
		System.out.println("lastDate1:" + convertLastTimeOfDate(lastDate1).getTime());
		String number = "201702";
		int year = Integer.parseInt(number.substring(0, 4));
		int month = Integer.parseInt(number.substring(4, 6));
		String firstDateStr = DateUtils.getSdf(DateUtils.DATE_FORMAT).format(DateUtils.convertStartTimeOfDate(DateUtils.getMonthFirstDate(year, month)));
		String lastDateStr = DateUtils.getSdf(DateUtils.DATE_FORMAT).format(DateUtils.convertLastTimeOfDate(DateUtils.getMonthLastDate(year, month)));
		System.out.println("firstDate:" + firstDateStr + " lastDate:" + lastDateStr);
		long time = System.currentTimeMillis();
		System.out.println(time);
		System.out.println(new Date(time));
		System.out.println(firstDate1.getTime());
		System.out.println(lastDate1.getTime());
		System.out.println(DateUtils.getSdf(DateUtils.DATE_FORMAT).format(DateUtils.convertStartTimeOfDate(DateUtils.getMonthFirstDate(1325402717497L))));
		System.out.println(DateUtils.getSdf(DateUtils.DATE_FORMAT).format(DateUtils.convertLastTimeOfDate(DateUtils.getMonthLastDate(1330500317497L))));
		
		java.sql.Timestamp beginTime = new java.sql.Timestamp(1325402717497L);
		java.sql.Timestamp endTime = new java.sql.Timestamp(1330500317497L);
		System.out.println("beginDate:" + beginTime);
		System.out.println("endDate:" + endTime);
		
		java.sql.Time beginTime1 = new java.sql.Time(1325402717497L);
		java.sql.Time endTime1 = new java.sql.Time(1330500317497L);
		System.out.println("beginDate:" + beginTime1);
		System.out.println("endDate:" + endTime1);
		
		java.sql.Date beginTime2 = new java.sql.Date(1325402717497L);
		java.sql.Date endTime2 = new java.sql.Date(1330500317497L);
		System.out.println("beginDate:" + beginTime2);
		System.out.println("endDate:" + endTime2);
		
		Date beginTime3 = new Date(1325402700000l);
		Date endTime3 = new Date(1330500317497l);
		System.out.println("beginDate:" + beginTime3);
		System.out.println("endDate:" + endTime3);
		System.out.println(System.currentTimeMillis());
		System.out.println(new Date(1507695024842L));
		Date date4 = DateUtils.convertStartTimeOfDate(DateUtils.getMonthFirstDate(1325402717497L));
		Date date5 = DateUtils.convertLastTimeOfDate(DateUtils.getMonthLastDate(1330500317497L));
		System.out.println(date4.getTime());
		System.out.println(new Date(date4.getTime()));
		System.out.println(date5.getTime());
		System.out.println(new Date(date5.getTime()));
		
		Calendar a = Calendar.getInstance();
		a.setTime(date4);
		int month4 = a.get(Calendar.MONTH);
		
		Calendar a1 = Calendar.getInstance();
		a1.setTime(date5);
		int month5 = a1.get(Calendar.MONTH);
		
		int num = month5 - month4 + 1;
		a.set(Calendar.MONTH, month4-num);
		Date date6 = a.getTime();
		System.out.println(date6);
		a1.set(Calendar.MONTH, month5-num);
		a1.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a1.roll(Calendar.DATE, -1);
		Date date7 = a1.getTime();
		System.out.println(date7);
//		Date date = new Date(1325402717497L);
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		System.out.println(cal.get(Calendar.YEAR));
		double rate =  (96920 - 115085) / 115085 * 100;
		BigDecimal d = new BigDecimal(96920);
		BigDecimal d2 = new BigDecimal(115085);
		double d3 = (d.divide(d2,4,BigDecimal.ROUND_HALF_UP).doubleValue()-1) * 100;
		System.out.println(d3);
		
		
		
	}
	
}