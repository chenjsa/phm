package com.pms.base.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;


public class StrUtil {

	public static final Integer ASC = 0;
	public static final Integer DESC = 1;
	
    private final static String[] hexDigits = {
       "0", "1", "2", "3", "4", "5", "6", "7",
	   "8", "9", "a", "b", "c", "d", "e", "f"};
	/**
	 * 转换字节数组为16进制字串
	 * @param b 字节数组
	 * @return 16进制字串
	*/
    
    public static String addZeroForNum(String str,int strLength) {  
    	  int strLen =str.length();  
    	  if (strLen <strLength) {  
    	   while (strLen< strLength) {  
    	    StringBuffer sb = new StringBuffer();  
    	    sb.append("0").append(str);//左补0  
//    	    sb.append(str).append("0");//右补0  
    	    str= sb.toString();  
    	    strLen= str.length();  
    	   }  
    	  }  

    	  return str;  
    	 }  
	  
	public static String byteArrayToHexString(byte[] b) {
	    StringBuffer resultSb = new StringBuffer();
	    for (int i = 0; i < b.length; i++) {
	        resultSb.append(byteToHexString(b[i]));
	    }
	    return resultSb.toString();
	}
	  
	private static String byteToHexString(byte b) {
	    int n = b;
	    if (n < 0)
	       n = 256 + n;
	     int d1 = n / 16;
	     int d2 = n % 16;
	     return hexDigits[d1] + hexDigits[d2];
    }
	  
	public static String MD5Encode(String origin) {
	    String resultString = null;
	  
	    try {
	        resultString=new String(origin);
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        resultString=byteArrayToHexString(md.digest(resultString.getBytes()));
	    } catch (Exception ex) {
	    }
	    return resultString;
	}
	
	/**
	 * 分割字符串
	 * @param src    字符串
	 * @param split 分隔符
	 * @return
	 */
	public static List<String> splitString(String src, String split){
		List<String> strList = new ArrayList<String>();
		StringTokenizer toker = new StringTokenizer(src, split);
		while(toker.hasMoreElements()){
			strList.add(toker.nextElement().toString());
		}
		
		return strList;
	}
	
	/**获取32位UUID*****/
	public static String getUUID(){
		UUID temp = UUID.randomUUID();		
		StringBuffer sb = new StringBuffer(temp.toString());
		int idx = sb.lastIndexOf("-");
		while (idx > -1) {
			sb.deleteCharAt(idx);	
			idx = sb.lastIndexOf("-");
		}
		return sb.toString();
	}
	
	
	/**
	 * 显示中文年月日
	 * @return
	 */
	public static String getChineseDate(String strDate){
		if(strDate == null){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		if(strDate.length()>7){
			sb.append(strDate.substring(0, 4));
			sb.append("年");
			sb.append(strDate.substring(5, 6));
			sb.append("月");
			sb.append(strDate.substring(7));
			sb.append("日");
		}
		return sb.toString();
	}
	
	public static String toUTF8(String str){
		if(str == null){
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i=0; i< str.length(); i++) {
			char c = str.charAt(i);
			if(c >= 0 && c <= 256){
				sb.append(c);
			}
			else{
				try{
					byte[] b = Character.toString(c).getBytes("UTF-8");
					for (int j = 0; j < b.length; j++) {
						int k = b[j];
						if(k<0){
							k = k + 256;
						}
						sb.append("%" + Integer.toHexString(k).toUpperCase());
					}
				}
				catch (Exception e) {
//					System.out.println(e);
				}
			}
		}
		
		return sb.toString();
	}
	
	public static String decodeUTF8(String s) {
		if (s == null)
			return "";

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '+':
				sb.append(' ');
				break;
			case '%':
				try {
					// 将16进制的数转化为十进制
					sb.append((char) Integer.parseInt(
							s.substring(i + 1, i + 3), 16));
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException();
				}
				i += 2;
				break;
			default:
				sb.append(c);
				break;
			}
		}

		String result = sb.toString();
		// 将UTF-8转换为GBK java+%E7%BC%96%E7%A8%8B
		// byte[] inputBytes = result.getBytes("8859_1"); //UTF8
		// return new String(inputBytes,"GB2312");
		try {
			result = new String(result.getBytes("8859_1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 连接字符串，解决null
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static String connString(String s1, String s2){
		StringBuffer sb = new StringBuffer();
		if(s1 != null){
			sb.append(s1);
		}
		if(s2 != null){
			sb.append(s2);
		}
		return sb.toString();
	}
	
	/**
	 * 附加金额的逗号分隔符
	 * @param amount
	 * @return
	 */
	public static String appendAmountSeparator(String amount){
		if(amount == null || "".equals(amount)){
			return "";
		}
		if(amount.indexOf(",")>0){//已有分隔
			return amount;
		}
		
		StringBuffer sb = new StringBuffer();
		if(amount.startsWith("+") || amount.startsWith("-")){//有+-号
			sb.append(amount.substring(0, 1));
			amount = amount.substring(1);
		}
		int intLength = amount.length();//整数长度
		int pointIndex = amount.indexOf(".");//小数点位置
		String decimalPart = "";
		if(pointIndex > 0){
			intLength = pointIndex;
			decimalPart = amount.substring(pointIndex);
			if(decimalPart.length() > 3){
				decimalPart = decimalPart.substring(0, 3);
			}
		}
		if(intLength %3 != 0){
			sb.append(amount.substring(0, intLength %3));
			sb.append(",");
			amount = amount.substring(intLength %3);
		}
		for(int i=0; i<(intLength/3); i++){
			sb.append(amount.substring(i*3, (i+1)*3));
			sb.append(",");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append(decimalPart);
		
		return sb.toString();
	}
	
	/**
	 * 删除字符串后面的逗号
	 * @param str
	 * @return
	 */
	public static String deleteLastComma(String str){
		
		StringBuffer sb = new StringBuffer();
		sb.append(str.trim());
		int idx = sb.lastIndexOf(",");
		while (idx > -1) {
			sb.deleteCharAt(idx);	
			if(sb.toString().trim().lastIndexOf(",")==sb.toString().length()-1){
				idx = sb.toString().trim().lastIndexOf(",");
			}else{
				idx=-1;
			}
			
		}
				
		return sb.toString();
	}
	/**
	 * 
	 * @param s1
	 * @return
	 */
	public static HashMap<Integer,String> orderString(String s1){
		
		HashMap<Integer,String> hs = new HashMap<Integer,String>();
		if(!(s1.indexOf(",") <= -1)){
			hs.put(Integer.valueOf(-1),"");
			return hs;
		}
		
		int i = s1.toLowerCase().indexOf(" by ");

		String s = s1.substring(i+4,s1.length());

		int j = s.toLowerCase().lastIndexOf("asc");
		if(!(j <= -1)){
			s1 = s.substring(0,j-1);
			hs.put(Integer.valueOf(ASC),s1);
			return hs;
		}

		j = s.toLowerCase().lastIndexOf("desc");
			
		if(j <= -1){
			s1 = s;
			hs.put(Integer.valueOf(ASC),s1);
			return hs;
		}else{
			s1 = s.substring(0,j-1);
			hs.put(Integer.valueOf(DESC),s1);
			return hs;

		}
	}

	/**
	 * 判断是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		if(str == null){
			return false;
		}
		else{
			return ! "".equals(str.trim());
		}
	}
	/****
	 * 获取系统时间，格式用户自定义
	 * @param args
	 */
	public static String getDateString(String dateFormat){
		if(dateFormat == null || dateFormat.equals(""))
			return "";
		try{
			dateFormat = dateFormat.trim();
			String date = new SimpleDateFormat(dateFormat).format(Calendar.getInstance().getTime());
			return date;
		}catch(Exception e){
			return "";
		}
	}
	
	/**获取当月最后一天***/
	public static String lastDayOfMonth(){
		try{
			Calendar cal = Calendar.getInstance(); 
			SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");
			cal.set( Calendar.DATE, 1 );
			cal.roll(Calendar.DATE, - 1 );
			return date.format(cal.getTime());
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 
	 * <li>方法名：getYesterday
	 * <li>@param n
	 * <li>@return
	 * <li>返回类型：String
	 * <li>说明：获取当前日期 前N天或者后N天的日期
	 * <li>创建人：张凌
	 * <li>创建日期：2010-8-9
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public static String getYesterday(int n,String format){
		try{
			Calendar cal = Calendar.getInstance(); 
			SimpleDateFormat df = new SimpleDateFormat(format);
			cal.setTime(new Date());
			cal.add(Calendar.DATE, n);
			Date date = cal.getTime();
			return df.format(date);
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	
	/**
	 * 日期格式转换：由2009-01-01 转换为 20090101
	 * @param args
	 */
	 public static String parseDateString(String param){
		if(param == null || param.equals(""))
			return "";
		 try{
			 param = param.trim();
			 String[] tmp = param.split("-");
			 return tmp[0]+tmp[1]+tmp[2];
		 }catch(Exception e){
			 return "";
		 }
	 }
	
	 /**
	  * 
	  * <li>方法名：parseDateStringEx
	  * <li>@param param
	  * <li>@return
	  * <li>返回类型：String
	  * <li>说明：将20100101转换为2010-01-01格式
	  * <li>创建人：张凌
	  * <li>创建日期：2010-8-11
	  * <li>修改人： 
	  * <li>修改日期：
	  */
	 public static String parseDateStringEx(String param){
		 if(param == null || param.equals(""))
				return "";
		 try{
			 param = param.trim();
			 if(param.length() < 8){
				 return "";
			 }
			 return param.substring(0, 4)+"-"+param.substring(4, 6)+"-"+param.substring(6, 8);
		 }catch(Exception e){
			 return "";
		 }
	 }
	 
	 
	 /**
	 * 格式化数字
	 * @param obj
	 * @param format
	 * @return
	 */
	public static String formatNumber(Double obj,String format){
		DecimalFormat deciFmt = new DecimalFormat (format);
		return deciFmt.format(obj);
	}
	
	/**
	 * <li>方法名：appendUnderline
	 * <li>@param str
	 * <li>@return
	 * <li>返回类型：String
	 * <li>说明：将参数中的大写字母前加上‘_’,大写字母转换成小写字母。如strAppend,转化成str_append
	 * <li>创建人：王川
	 * <li>创建日期：2010-9-30
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public static String appendUnderline(String str){
		str = str.trim();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			if(Character.isUpperCase(c)){
				sb.append("_"+Character.toLowerCase(c));
			}else{
				sb.append(c);
			}
		}
		return sb.toString();
	}
	/**
	 * <li>方法名：appendUnderlineOrderBy
	 * <li>@param str
	 * <li>@return
	 * <li>返回类型：String
	 * <li>说明：将类似于 order by strAppend desc转化成 order by str_append desc;
	 * <li>创建人：王川
	 * <li>创建日期：2010-9-30
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public static String appendUnderlineOrderBy(String str){
		if (null != str && !"".equals(str)) {
			String old = str.trim().split(" ")[2];
			String newStr = StrUtil.appendUnderline(old);
			str = str.replaceAll(old, newStr);
		}
		return str;
	}
	
	public static String intToString(int number,int strLength){
		String numberStr=String.valueOf(number);
		String result=numberStr;
		if(strLength<=numberStr.length()){
			return numberStr;
		}else{
			for(int i=strLength-numberStr.length();i>0;i--){
				result="0"+result;
			}
		}
		return result;
	}
	
	/**
	 * 生成A-Z,0-9的随机数，随机数的位数由参数决定
	 * @param passwordLength
	 * @return
	 */
	public static String randomPassword(int passwordLength) {
		// 随机产生codeCount数字的验证码。
		char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
				'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		if (passwordLength < 1 || passwordLength > 10) {
			passwordLength = 6;// 位数
		}
		StringBuffer randomCode = new StringBuffer();
		java.util.Random random = new java.util.Random();
		for (int i = 0; i < passwordLength; i++) {
			// 得到随机产生的验证码数字。
			String code = String.valueOf(codeSequence[random.nextInt(36)]);

			// 将产生的随机数组合在一起。
			randomCode.append(code);
		}
		return randomCode.toString();
	}
	
	public static void main(String[] args){
		String getCasCode="001009010";
		String pre=getCasCode.substring(0, getCasCode.length()-3);
		String last=getCasCode.substring(getCasCode.length()-3,getCasCode.length());
		int addedList=Integer.parseInt(last)+1;
		
		String nextCasCode=pre+StrUtil.addZeroForNum(String.valueOf(addedList), 3);
		System.out.println(nextCasCode);
//		System.out.println(randomPassword(4));
		
//		System.out.println(intToString(5,3));
//	     System.err.println(MD5Encode("123"));
      System.err.println(MD5Encode("123456"));

//		System.out.println(appendAmountSeparator("-100122.00"));
//		System.out.println(appendAmountSeparator("2.00"));
//		System.out.println(appendAmountSeparator("999122.00"));
//		System.out.println(appendAmountSeparator("1999122.00"));
//		System.out.println(appendAmountSeparator("-11999122.00"));
//		
//		System.out.println(deleteAmountSeparator("-100,122.00"));
//		System.out.println(deleteAmountSeparator("2.00"));
//		System.out.println(deleteAmountSeparator("999,122.00"));
//		System.out.println(deleteAmountSeparator("1,999,122.00"));
//		System.out.println(deleteAmountSeparator("-11,999,122.00"));
//		System.out.println("AA:"+deleteAmountSeparator(""));

//		System.out.println(appendUnderline("StrAppendSBA"));
//		String orderString = "order by strAppend desc";
//		String old = orderString.trim().split(" ")[2];
//		String str = StrUtil.appendUnderline(old);
//		System.out.println(orderString.replaceAll(old, str));
//		
//		System.out.println("20090423".substring(6, 8));
//		
//		System.out.println(getDateString("yyyyMM01"));
//		System.out.println(lastDayOfMonth());
//		System.out.println(parseDateString("2009-01-01"));
//		System.out.println(getYesterday(-1, "yyyy-MM-dd"));

	
	} 	
	/**/
	
}
