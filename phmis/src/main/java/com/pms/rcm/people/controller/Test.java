package com.pms.rcm.people.controller;

import com.github.pagehelper.parser.SqlServer;

import net.sf.jsqlparser.JSQLParserException;

public class Test{
	public static final SqlServer sqlServer = new SqlServer();//初始化
	 
	public static  void main(String args[]) throws JSQLParserException {
		String originalSql = "select distinct countrycode,countryname from country order by countrycode";
		System.out.println(sqlServer.convertToPageSql(originalSql, 1, 10));
	}  


}
