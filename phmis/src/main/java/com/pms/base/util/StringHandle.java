package com.pms.base.util;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

public class StringHandle {

	public static String getSuffix(String str, String c) {
		String result = null;
		if(str!=null)
		{
			int loc = str.lastIndexOf(c);
			if (loc == -1) {
				result = str;
			} else {
				result = str.substring(loc + 1, str.length());
			}
		}
//		System.out.println("str:"+str);
//		System.out.println("result:"+result);
		return result;
	}
	public static String getPrefix(String str, String c) {
		String result = null;
		if(str!=null)
		{
		int loc = str.lastIndexOf(c);
		if (loc == -1) {
			result = str;
		} else {
			result = str.substring(0, loc);
		}
		}
		return result;
	}
	public static String getValueByPropertyName(
			String propertiesFileNameWithoutPostfix, String propertyName) {
		String s = "";
		// �������ļ���test.properties���Ǵ�ʱpropertiesFileNameWithoutPostfix��ֵ����test
		if (propertiesFileNameWithoutPostfix == null
				|| propertiesFileNameWithoutPostfix.equals("")) {
			propertiesFileNameWithoutPostfix = "ApplicationResources";
		}
		ResourceBundle bundel = ResourceBundle
				.getBundle(propertiesFileNameWithoutPostfix);
		s = bundel.getString(propertyName);
		return s;
	}
	public static String getValueByPropertyName(String propertyName) {
		String s = "";
		// �������ļ���test.properties���Ǵ�ʱpropertiesFileNameWithoutPostfix��ֵ����test
		
		String	propertiesFileNameWithoutPostfix = "ApplicationResources";
		ResourceBundle bundel = ResourceBundle
				.getBundle(propertiesFileNameWithoutPostfix);
		s = bundel.getString(propertyName);
		return s;
	}
	public static String xTrim(String str) {
		String result;
		if (str == null) {
			result = "";
		} else {
			result = str.trim();
		}
		return result;
	}

	public static String xTrim(String str, String df) {
		String result;
		if (str == null ||str.equals("")) {
			result = df;
		} else {
			result = str.trim();
		}
		return result;
	}

	public static String escape(String src) {
		int i;
		char j;
		src=xTrim(src);
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	public static String unescape(String src) {
		//System.out.println("unescpae:"+src);
		StringBuffer tmp = new StringBuffer();
		
		if(src!=null)
		{
			tmp.ensureCapacity(src.length());
			int lastPos = 0, pos = 0;
			char ch;
			while (lastPos < src.length()) {
				pos = src.indexOf("%", lastPos);
				if (pos == lastPos) {
					if (src.charAt(pos + 1) == 'u') {
						ch = (char) Integer.parseInt(src
								.substring(pos + 2, pos + 6), 16);
						tmp.append(ch);
						lastPos = pos + 6;
					} else {
						ch = (char) Integer.parseInt(src
								.substring(pos + 1, pos + 3), 16);
						tmp.append(ch);
						lastPos = pos + 3;
					}
				} else {
					if (pos == -1) {
						tmp.append(src.substring(lastPos));
						lastPos = src.length();
					} else {
						tmp.append(src.substring(lastPos, pos));
						lastPos = pos;
					}
				}
			}
		}
		return tmp.toString();
	}
	public static String iso2utf8(Object obj)
	{	
		String str = "";
		if(null !=obj)
		{
			try
			{
				str = new String(((String)obj).getBytes("iso8859-1"),"utf-8");
			}
			catch(UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
		}
		
		return str;
	}
}
