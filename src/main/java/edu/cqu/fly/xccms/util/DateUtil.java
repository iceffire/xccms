package edu.cqu.fly.xccms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理类
 * @author miaoxiaolong
 *
 */
public class DateUtil {
	/**
	 * 日期变换
	 * @param MMddyyyy
	 * @return
	 */
	public static Date strToDate(String str){
		if(null!=str&&!str.equals("")){
			Date date = null;
			str = str.replaceAll("-", "/");
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			try {
				date =format.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}else{
			return null;
		}
	}
	/**
	 * 日期变换
	 * @param MMddyyyy HH:MM:ss
	 * @return
	 */
	public static Date strToDateMMDDYYYYHHMMSS(String str){
		if(null!=str&&!str.equals("")){
			Date date = null;
			str = str.replaceAll("-", "/");
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			try {
				date =format.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}else{
			return null;
		}
	}
	/**
	 * 获取一小时之前时间
	 * @return
	 */
	public static Date get1HourAgo(){
		Date dNow = new Date();
	    Date dBefore = new Date();
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(dNow);
	    calendar.add(calendar.HOUR, -1);
	    dBefore = calendar.getTime();
		return dBefore;
	}
	/**
	 * 获取四小时之前时间
	 * @return
	 */
	public static Date get4HourAgo(){
		Date dNow = new Date();
	    Date dBefore = new Date();
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(dNow);
	    calendar.add(calendar.HOUR, -4);
	    dBefore = calendar.getTime();
		return dBefore;
	}
	/**
	 * 获取八小时之前时间
	 * @return
	 */
	public static Date get8HourAgo(){
		Date dNow = new Date();
	    Date dBefore = new Date();
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(dNow);
	    calendar.add(calendar.HOUR, -8);
	    dBefore = calendar.getTime();
		return dBefore;
	}
	/**
	 * 获取一天之前时间
	 * @return
	 */
	public static Date get1DayAgo(){
		Date dNow = new Date();
	    Date dBefore = new Date();
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(dNow);
	    calendar.add(calendar.HOUR,-24);
	    dBefore = calendar.getTime();
		return dBefore;
	}
	/**
	 * 获取两天之前时间
	 * @return
	 */
	public static Date get2DayAgo(){
		Date dNow = new Date();
	    Date dBefore = new Date();
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(dNow);
	    calendar.add(calendar.HOUR,-48);
	    dBefore = calendar.getTime();
		return dBefore;
	}
	/**
	 * 获取一周之前时间
	 * @return
	 */
	public static Date get1WeekAgo(){
		Date dNow = new Date();
	    Date dBefore = new Date();
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(dNow);
	    calendar.add(calendar.WEEK_OF_MONTH,-1);
	    dBefore = calendar.getTime();
		return dBefore;
	}
	/**
	 * 获取一月之前时间
	 * @return
	 */
	public static Date get1MonthAgo(){
		Date dNow = new Date();
	    Date dBefore = new Date();
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(dNow);
	    calendar.add(calendar.MONTH,-1);
	    dBefore = calendar.getTime();
		return dBefore;
	}
	/**
	 * 获取半年之前时间
	 * @return
	 */
	public static Date get6MonthAgo(){
		Date dNow = new Date();
	    Date dBefore = new Date();
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(dNow);
	    calendar.add(calendar.MONTH,-6);
	    dBefore = calendar.getTime();
		return dBefore;
	}
	/**
	 * 获取一年之前时间
	 * @return
	 */
	public static Date get1YearAgo(){
		Date dNow = new Date();
	    Date dBefore = new Date();
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(dNow);
	    calendar.add(calendar.YEAR,-1);
	    dBefore = calendar.getTime();
		return dBefore;
	}
}
