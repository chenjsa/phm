package com.pms.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class TimeUtil {
	//---当前日期的年，月，日，时，分，秒   
	public static Calendar now   = Calendar.getInstance();    
	int    year = now.get( Calendar.YEAR );   
	int    date = now.get( Calendar.DAY_OF_MONTH );   
	int    month = now.get( Calendar.MONTH ) + 1;   
	int    hour = now.get( Calendar.HOUR );   
	int    min   = now.get( Calendar.MINUTE );   
	int    sec   = now.get( Calendar.SECOND );   
	  
	//-------------------------------日期类型转换---------------------------------------------------------------------------   
	/**  
	* 字符型日期转化util.Date型日期  
	* @Param:p_strDate 字符型日期   
	* @param p_format 格式:"yyyy-MM-dd" / "yyyy-MM-dd hh:mm:ss"  
	 * @throws ParseException 
	* @Return:java.util.Date util.Date型日期  
	* @Throws: ParseException  
	* @Author: zhuqx  
	* @Date:   2006-10-31  
	*/  
	public static java.util.Date toUtilDateFromStrDateByFormat( String p_strDate, String p_format ) throws ParseException   
	    {   
	   java.util.Date l_date = null;   
	   java.text.DateFormat df = new java.text.SimpleDateFormat( p_format );   
	   if ( p_strDate != null && ( !"".equals( p_strDate ) ) && p_format != null && ( !"".equals( p_format ) ) ) {   
	    l_date = df.parse( p_strDate );   
	   }   
	   return l_date;   
	}   
	  
	  
	  
	/**  
	* 字符型日期转化成sql.Date型日期  
	* @param p_strDate    字符型日期  
	* @return java.sql.Date sql.Date型日期  
	* @throws ParseException   
	* @Author: zhuqx  
	* @Date:   2006-10-31  
	*/  
	public static java.sql.Date toSqlDateFromStrDate( String p_strDate ) throws ParseException {   
	   java.sql.Date returnDate = null;   
	   java.text.DateFormat sdf = new java.text.SimpleDateFormat();   
	   if ( p_strDate != null && ( !"".equals( p_strDate ) ) ) {   
	    returnDate = new java.sql.Date( sdf.parse( p_strDate ).getTime() );   
	   }   
	   return returnDate;   
	}   
	     
	/**   
	* util.Date型日期转化指定格式的字符串型日期  
	* @param   p_date    Date   
	* @param   p_format String   
	* 格式1:"yyyy-MM-dd"   
	* 格式2:"yyyy-MM-dd hh:mm:ss EE"  12小时 
	* 格式3:"yyyy-MM-dd HH:mm:ss EE"  24小时 
	* 格式4:"yyyy年MM月dd日 hh:mm:ss EE" 
	* 说明: 年-月-日 时:分:秒 星期 注意MM/mm大小写  
	* @return String   
	* @Author: zhuqx  
	* @Date:   2006-10-31  
	*/  
	public static String toStrDateFromUtilDateByFormat( java.util.Date p_utilDate, String p_format ) throws ParseException {   
	   String l_result = "";   
	   if ( p_utilDate != null ) {   
	    SimpleDateFormat sdf = new SimpleDateFormat( p_format );   
	    l_result = sdf.format( p_utilDate );   
	   }   
	   return l_result;   
	}   
	  
	/**  
	* util.Date型日期转化转化成Calendar日期  
	* @param p_utilDate Date  
	* @return Calendar  
	* @Author: zhuqx  
	* @Date: 2006-10-31  
	*/  
	public static Calendar toCalendarFromUtilDate(java.util.Date p_utilDate) {   
	   Calendar c = Calendar.getInstance();   
	   c.setTime(p_utilDate);   
	   return c;   
	}   
	  
	/**  
	* util.Date型日期转化sql.Date(年月日)型日期  
	* @Param: p_utilDate util.Date型日期  
	* @Return: java.sql.Date sql.Date型日期  
	* @Author: zhuqx  
	* @Date:   2006-10-31  
	*/  
	public static java.sql.Date toSqlDateFromUtilDate( java.util.Date p_utilDate ) {   
	   java.sql.Date returnDate = null;   
	   if ( p_utilDate != null ) {   
	    returnDate = new java.sql.Date( p_utilDate.getTime() );   
	   }   
	   return returnDate;   
	}   
	  
	/**  
	* util.Date型日期转化sql.Time(时分秒)型日期  
	* @Param: p_utilDate util.Date型日期  
	* @Return: java.sql.Time sql.Time型日期  
	* @Author: zhuqx  
	* @Date:   2006-10-31  
	*/  
	public static java.sql.Time toSqlTimeFromUtilDate( java.util.Date p_utilDate ) {   
	   java.sql.Time returnDate = null;   
	   if ( p_utilDate != null ) {   
	    returnDate = new java.sql.Time( p_utilDate.getTime() );   
	   }   
	   return returnDate;   
	}   
	  
	/**  
	* util.Date型日期转化sql.Date(时分秒)型日期  
	* @Param: p_utilDate util.Date型日期  
	* @Return: java.sql.Timestamp sql.Timestamp型日期  
	* @Author: zhuqx  
	* @Date:   2006-10-31  
	*/  
	public static java.sql.Timestamp toSqlTimestampFromUtilDate( java.util.Date p_utilDate ) {   
	   java.sql.Timestamp returnDate = null;   
	   if ( p_utilDate != null ) {   
	    returnDate = new java.sql.Timestamp( p_utilDate.getTime() );   
	   }   
	   return returnDate;   
	}   
	  
	/**  
	* sql.Date型日期转化util.Date型日期  
	* @Param: sqlDate sql.Date型日期  
	* @Return: java.util.Date util.Date型日期  
	* @Author: zhuqx  
	* @Date:   2006-10-31  
	*/  
	public static java.util.Date toUtilDateFromSqlDate( java.sql.Date p_sqlDate ) {   
	   java.util.Date returnDate = null;   
	   if ( p_sqlDate != null ) {   
	    returnDate = new java.util.Date( p_sqlDate.getTime() );   
	   }   
	   return returnDate;   
	}   
	  
	//-----------------获取指定日期的年份，月份，日份，小时，分，秒，毫秒----------------------------   
	/**   
	* 获取指定日期的年份   
	* @param p_date util.Date日期   
	* @return int   年份   
	* @author zhuqx  
	* @Date:   2006-10-31  
	*/  
	public static int getYearOfDate( java.util.Date p_date ) {   
	   java.util.Calendar c = java.util.Calendar.getInstance();   
	   c.setTime( p_date );   
	   return c.get( java.util.Calendar.YEAR );   
	}   
	     
	/**   
	* 获取指定日期的月份   
	* @param p_date util.Date日期   
	* @return int   月份   
	* @author zhuqx  
	* @Date:   2006-10-31  
	*/  
	public static int getMonthOfDate( java.util.Date p_date ) {   
	   java.util.Calendar c = java.util.Calendar.getInstance();   
	   c.setTime( p_date );   
	   return c.get( java.util.Calendar.MONTH ) + 1;   
	}   
	  
	/**   
	* 获取指定日期的日份   
	* @param p_date util.Date日期   
	* @return int   日份   
	* @author zhuqx  
	* @Date:   2006-10-31  
	*/  
	public static int getDayOfDate( java.util.Date p_date ) {   
	   java.util.Calendar c = java.util.Calendar.getInstance();   
	   c.setTime( p_date );   
	   return c.get( java.util.Calendar.DAY_OF_MONTH );   
	}   
	  
	/**   
	* 获取指定日期的小时   
	* @param p_date util.Date日期   
	* @return int   日份   
	* @author zhuqx  
	* @Date:   2006-10-31  
	*/  
	public static int getHourOfDate( java.util.Date p_date ) {   
	   java.util.Calendar c = java.util.Calendar.getInstance();   
	   c.setTime( p_date );   
	   return c.get( java.util.Calendar.HOUR_OF_DAY );   
	}   
	     
	/**   
	* 获取指定日期的分钟   
	* @param p_date util.Date日期   
	* @return int   分钟   
	* @author zhuqx  
	* @Date:   2006-10-31  
	*/  
	public static int getMinuteOfDate( java.util.Date p_date ) {   
	   java.util.Calendar c = java.util.Calendar.getInstance();   
	   c.setTime( p_date );   
	   return c.get( java.util.Calendar.MINUTE );   
	}   
	     
	/**   
	* 获取指定日期的秒钟   
	* @param p_date util.Date日期   
	* @return int   秒钟   
	* @author zhuqx  
	* @Date:   2006-10-31  
	*/  
	public static int getSecondOfDate( java.util.Date p_date ) {   
	   java.util.Calendar c = java.util.Calendar.getInstance();   
	   c.setTime( p_date );   
	   return c.get( java.util.Calendar.SECOND );   
	}   
	     
	/**   
	* 获取指定日期的毫秒     
	* @param p_date util.Date日期   
	* @return long   毫秒     
	* @author zhuqx  
	* @Date:   2006-10-31  
	*/  
	public static long getMillisOfDate( java.util.Date p_date ) {   
	   java.util.Calendar c = java.util.Calendar.getInstance();   
	   c.setTime( p_date );   
	   return c.getTimeInMillis();   
	}   
	  
	//-----------------获取当前/系统日期(指定日期格式)-----------------------------------------------------------------------------------   
	/**  
	* 获取指定日期格式当前日期的字符型日期  
	* @param p_format 日期格式  
	* 格式1:"yyyy-MM-dd"   
	* 格式2:"yyyy-MM-dd hh:mm:ss EE"   
	* 格式3:"yyyy年MM月dd日 hh:mm:ss EE"   
	* 说明: 年-月-日 时:分:秒 星期 注意MM/mm大小写  
	* @return String 当前时间字符串  
	* @author zhuqx  
	* @Date:   2006-10-31  
	*/  
	public static String getNowOfDateByFormat( String p_format ) {   
	   Date d = new Date();   
	   SimpleDateFormat sdf = new SimpleDateFormat( p_format );   
	   String dateStr = sdf.format( d );   
	   return dateStr;   
	}   
	  
	/**  
	* 获取指定日期格式系统日期的字符型日期  
	* @param p_format 日期格式  
	* 格式1:"yyyy-MM-dd"   
	* 格式2:"yyyy-MM-dd hh:mm:ss EE"   
	* 格式3:"yyyy年MM月dd日 hh:mm:ss EE"   
	* 说明: 年-月-日 时:分:秒 星期 注意MM/mm大小写  
	* @return String 系统时间字符串  
	* @author zhuqx  
	* @Date:   2006-10-31  
	*/  
	public static String getSystemOfDateByFormat( String p_format ) {   
	   long time = System.currentTimeMillis();   
	   Date d2 = new Date();   
	   Date d = new Date( time );   
	   SimpleDateFormat sdf = new SimpleDateFormat( p_format );   
	   String dateStr = sdf.format( d );   
	   return dateStr;   
	}   
	  
	/**  
	* 获取字符日期一个月的天数  
	* @param p_date  
	* @return 天数  
	* @author zhuqx  
	*/  
	public static long getDayOfMonth( Date p_date ) throws ParseException {   
	   int year = getYearOfDate(p_date);   
	   int month = getMonthOfDate( p_date )-1;   
	   int day = getDayOfDate( p_date );   
	   int hour = getHourOfDate( p_date );   
	   int minute = getMinuteOfDate( p_date );   
	   int second = getSecondOfDate( p_date );   
	   Calendar l_calendar = new GregorianCalendar(year,month,day,hour,minute,second);   
	   return l_calendar.getActualMaximum( l_calendar.DAY_OF_MONTH );   
	}   
	  
	// -----------------获取指定月份的第一天,最后一天 ---------------------------------------------------------------------------   
	/**   
	* 获取指定月份的第一天   
	* @param p_strDate 指定月份  
	* @param p_formate 日期格式  
	* @return String 时间字符串  
	* @author zhuqx  
	* @Date:   2006-10-31  
	*/  
	public static String getDateOfMonthBegin( String p_strDate, String p_format ) throws ParseException {   
	   java.util.Date date = toUtilDateFromStrDateByFormat( p_strDate,p_format );   
	   return toStrDateFromUtilDateByFormat( date,"yyyy-MM" ) + "-01";   
	}   
	     
	/**   
	* 获取指定月份的最后一天   
	* @param p_strDate 指定月份  
	* @param p_formate 日期格式  
	* @return String 时间字符串  
	* @author zhuqx  
	* @Date:   2006-10-31  
	*/  
	public static String getDateOfMonthEnd( String p_strDate, String p_format ) throws ParseException {   
	   java.util.Date date = toUtilDateFromStrDateByFormat( getDateOfMonthBegin( p_strDate,p_format ),p_format );   
	   Calendar calendar = Calendar.getInstance();   
	   calendar.setTime( date );   
	   calendar.add( Calendar.MONTH,1 );   
	   calendar.add( Calendar.DAY_OF_YEAR,-1 );   
	   return toStrDateFromUtilDateByFormat( calendar.getTime(),p_format );   
	}   

	/**
	 * 获取当前日期相关月份的第一天 
	 * @param month 哪个月,0表示本月,1表示下月,-1表示上月
	 * @return
	 */
	public static String getDateOfMonthBegin(int month) throws ParseException {  
		Date date = new Date();
		Calendar cal = Calendar.getInstance();   
		cal.setTime( date );
		cal.add(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return toStrDateFromUtilDateByFormat(cal.getTime(),"yyyy-MM-dd" );  		
	}
	
	/**
	 * 获取当前日期相关月份的最后一天 
	 * @param month 哪个月,0表示本月,1表示下月,-1表示上月
	 * @return
	 */
	public static String getDateOfMonthEnd(int month) throws ParseException {  
		Date date = new Date();
		Calendar cal = Calendar.getInstance();   
		cal.setTime( date ); 
		cal.add(Calendar.MONTH, month + 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_YEAR, -1 ); 
		return toStrDateFromUtilDateByFormat( cal.getTime(),"yyyy-MM-dd" );  		
	}
	
	/**
	 * 获得另一年的同一天
	 * @param strDate
	 * @param year
	 * @return
	 */
	public static String getDateOfOtherYear(String strDate, int year)  throws ParseException {
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(format.parse(strDate));
		cal.add(Calendar.YEAR, year);
		
		return toStrDateFromUtilDateByFormat( cal.getTime(),"yyyy-MM-dd" ); 
	}
	
	/**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }
    /**
     * 计算两个日期之间相差的小时
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static int hourBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*60*60);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }
    /**
     * 计算两个日期之间相差的小时
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static int minitusBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*60);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }
    /**
     * field传入的参数是年：calendar.YEAR，月：calendar.DAY_OF_MONTH，天：calendar.DATE，星期：calendar.WEEK_OF_MONTH，
     * @param date
     * @param field:
     * @param number
     * @return
     */
    public static java.util.Date dateAddMinus(java.util.Date date,int field,int number){
    	Calendar   calendar   =   new   GregorianCalendar(); 
    	calendar.setTime(date); 
    	calendar.add(field, 1);//把日期往后增加一年.整数往后推,负数往前移动
//        calendar.add(calendar.YEAR, 1);//把日期往后增加一年.整数往后推,负数往前移动
//        calendar.add(calendar.DAY_OF_MONTH, 1);//把日期往后增加一个月.整数往后推,负数往前移动
//        calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
//        calendar.add(calendar.WEEK_OF_MONTH, 1);//把日期往后增加一个月.整数往后推,负数往前移动
//        date=calendar.getTime();   //这个时间就是日期往后推一天的结果 
    	date=calendar.getTime();
    	return date;
    }
//    Date date=new   Date();//取时间
//    System.out.println(date.toString());

//        System.out.println(date.toString());
	
	public static void main(String[] args){
		Date startDate;
		try {
			startDate = TimeUtil.toUtilDateFromStrDateByFormat("2018-03-22 10:10:00", "yyyy-MM-dd hh:mm:ss");
		
			Date endDate=TimeUtil.toUtilDateFromStrDateByFormat("2018-03-22 11:00:00", "yyyy-MM-dd hh:mm:ss");
			System.out.println(hourBetween(startDate, endDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			System.out.println(getDateOfMonthBegin(-1));
//			System.out.println(getDateOfMonthBegin(0));
//			System.out.println(getDateOfMonthBegin(1));
//			
//
//			System.out.println(getDateOfMonthEnd(-1));
//			System.out.println(getDateOfMonthEnd(0));
//			System.out.println(getDateOfMonthEnd(1));
//			
//
//			System.out.println(getDateOfOtherYear("2009-12-12",-10));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
//		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
//		Calendar cal = Calendar.getInstance();
//		try {
//			cal.setTime(format.parse("2009-9-14"));
//			cal.add(Calendar.MONTH, 3);
//			if(cal.getTime().before(new Date())){
//				System.out.println("过期");
//			}
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	/**/
}
