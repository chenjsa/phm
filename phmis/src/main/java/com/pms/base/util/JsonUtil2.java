package com.pms.base.util;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.ast.Var;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class JsonUtil2 {
	

	
   /**
    * 
    * <li>方法名：jsonStrToList
    * <li>@param jsonStr
    * <li>@param beanClass
    * <li>@return
    * <li>返回类型：List
    * <li>说明：
    * <li>创建人：张凌
    * <li>创建日期：2010-8-30
    * <li>修改人： 
    * <li>修改日期：
    */
	@SuppressWarnings("unchecked")
	public static List jsonStrToList(String jsonStr,Class beanClass){
		List list = new ArrayList();
		JSONObject jsonObj;
		Object object;
		JSONArray array = JSONArray.fromObject(jsonStr);
		if(array != null && array.size() > 0){
		   for(int i=0;i<array.size();i++){
			   jsonObj = array.getJSONObject(i);
			   object = JSONObject.toBean(jsonObj, beanClass);
			   list.add(object);
		   }
		}
		return list;
	}

	/**
	 * 
	 * <li>方法名：listToJsonStr
	 * <li>@param list
	 * <li>@param excludes
	 * <li>@return
	 * <li>返回类型：String
	 * <li>说明：
	 * <li>创建人：张凌
	 * <li>创建日期：2010-8-30
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public static String listToJsonStr(List list,String[] excludes){
		if(list == null || list.size() <= 0)
		    return "";
		JSONArray array = JSONArray.fromObject(list);
		String tmpStr = array.toString();
		if(excludes == null){
			return tmpStr;
		}else{
			JSONArray tmpArray = new JSONArray();
			for(int i=0;i<array.size();i++){
				JSONObject obj = array.getJSONObject(i);
				for(int j=0;j<excludes.length;j++){
					obj.remove(excludes[j]);
				}
				tmpArray.add(obj);
			}
			return tmpArray.toString();
		}
	}
	
	/**
	 * 
	 * <li>方法名：jsonStrToObject
	 * <li>@param jsonStr
	 * <li>@param beanClass
	 * <li>@return
	 * <li>返回类型：Object
	 * <li>说明：
	 * <li>创建人：张凌
	 * <li>创建日期：2010-8-30
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public static Object jsonStrToObject(String jsonStr,Class beanClass){
		if(jsonStr == null || jsonStr.equals(""))
			return null;
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		return JSONObject.toBean(jsonObj, beanClass);
	}
	
	
	/**
	 * 
	 * <li>方法名：objectToJsonStr
	 * <li>@param entity
	 * <li>@param excludes
	 * <li>@return
	 * <li>返回类型：String
	 * <li>说明：
	 * <li>创建人：张凌
	 * <li>创建日期：2010-8-30
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public static String objectToJsonStr(Object entity,String[] excludes){
		if(entity == null)
			return "";
		JSONObject jsonObj = JSONObject.fromObject(entity);
		if(excludes != null && excludes.length > 0){
			for(int i=0;i<excludes.length;i++){
				jsonObj.remove(excludes[i]);
			}
		}
		return jsonObj.toString();
	}
	
	
	
	public static String objListToJsonStr(List objList){
		if(objList == null || objList.size() <= 0){
			return "";
		}
		StringBuffer buf = new StringBuffer();
	
		for(int i=0;i<objList.size();i++){
			Object[] tmpObj = (Object[])objList.get(i);
			if(i > 0){
				buf.append(",");
			}
			buf.append("{");
			for(int j=0;j<tmpObj.length;j++){
				if(j == 0){
					buf.append("'"+j+"':'"+tmpObj[j]+"'");
				}else{
					buf.append(",'"+j+"':'"+tmpObj[j]+"'");
				}
			}
			buf.append("}");
		}
		
		return buf.toString();
	}
	
	
	
	
	
	/*
public static void main(String[] sdf){
		
		String jsonStr2 = "[{id:'12345',name:'测试0',sex:'男',age:26,aaaa:12392370,bbb:12.10215,child:{cid:'56565','cname':'子名字'}},{id:'12345',name:'测试2',sex:'男',age:26},{id:'12345',name:'测试3',sex:'男',age:26},{id:'12345',name:'测试4',sex:'男',age:26}]";
	    Object[] obj1 = new Object[]{"a1","a2","a3","a4","a5"};
	    Object[] obj2 = new Object[]{"b1","b2","b3","b4","b5"};
	    Object[] obj3 = new Object[]{"c1","c2","c3","c4","c5"};
	    Object[] obj4 = new Object[]{"a1","a2","a3","a4","a5"};
	    Object[] obj5 = new Object[]{"a1","a2","a3","a4","a5"};
	    Object[] obj6 = new Object[]{"a1","a2","a3","a4","a5"};
	    List list = new ArrayList();
	    list.add(obj1);
	    list.add(obj2);
	    list.add(obj3);
	    list.add(obj4);
	    list.add(obj5);
	    list.add(obj6);
	    
	    String x = JsonUtil2.objListToJsonStr(list);
//	    System.out.println(x);
	    JSONObject jsonObj = JSONObject.fromObject(x);
	    
	    
		
	}
	*/
	
}
