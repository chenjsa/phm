package com.pms.rcm.sys.vo;


import java.util.Set;

import com.pms.base.util.StrUtil;
 

/**
 * WebService entity. @author MyEclipse Persistence Tools
 */

public class DataRangeSearch implements java.io.Serializable {

	public static Dept LOCAL_DEPT = null; //本地机构
	public static final String INCLUDE_LOWER_LEVER = "1";    //包含下级
	
	/**
	 * 类型：long
	 */
	private static final long serialVersionUID = 1L;
	
	private String includeSea;    //查询用，是否包含下级:0不包含，1包含
	private String sysCodeSea;    //查询用，机构系统编码
	private String casCodeSea;    //查询用，机构级联编码
	
	/*用于ibatis传入分页用参数*/
	protected Integer pageSize = 20;//每页最大记录数量
	protected Integer pageIndex;    //页序号
	protected Integer startRow;     //开始行
	protected Integer endRow;       //结束行
	
	protected String orderBy;       //排序
	
	/*2013-6-26黄道勋加入*/
	protected String orderString;
	protected String casCode;//查询权限用
	protected String orgName;//查看
	
	public String getOrderString() {
		return orderString;
	}

	public void setOrderString(String orderString) {		
		this.orderString = orderString;
	}

	public String getCasCode() {
		return casCode;
	}


	public void setCasCode(String casCode) {
		this.casCode = casCode;
	}


	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}



	public Integer getPageIndex() {
		return pageIndex;
	}



	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}



	public Integer getStartRow() {
		return startRow;
	}



	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}



	public Integer getEndRow() {
		return endRow;
	}



	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
	}

	

	// Constructors

	public String getOrderBy() {
		if(StrUtil.isNotEmpty(this.orderBy)){
			String sb = "";
			sb = this.orderBy.trim();
			sb = " order by "+StrUtil.appendUnderline(sb.split(" ")[2])+" "+sb.split(" ")[3];
			return sb;
		}else{
			return this.orderBy;
		}
	}



	public void setOrderBy(String orderBy) {		
		this.orderBy = orderBy;
	}



	/** default constructor */
	public DataRangeSearch() {
		this.sysCodeSea = "";
		this.casCodeSea = "";
	}

	

	public String getIncludeSea() {
		return includeSea;
	}

	public void setIncludeSea(String includeSea) {
		this.includeSea = includeSea;
	}

	public String getSysCodeSea() {
		return sysCodeSea;
	}

	public void setSysCodeSea(String sysCodeSea) {
		this.sysCodeSea = sysCodeSea;
	}

	public String getCasCodeSea() {
		return casCodeSea;
	}

	public void setCasCodeSea(String casCodeSea) {
		this.casCodeSea = casCodeSea;
	}
	

	/**
	 * 
	 * <li>方法名：addManageDeptSearch
	 * <li>@param deptSet
	 * <li>返回类型：void
	 * <li>说明：将管理机构添加到查询条件中
	 * <li>创建人：曾明辉
	 * <li>创建日期：2010-8-5
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public void addManageDeptSearch(Set<Dept> deptSet){
		Dept[] deptArr = deptSet.toArray(new Dept[deptSet.size()]);
		for (int i = 0; i < deptArr.length; i++) {
			this.sysCodeSea += deptArr[i].getDeptNo();
			this.casCodeSea += deptArr[i].getCasCode();
			if(i != deptArr.length-1){
				this.sysCodeSea += ",";
				this.casCodeSea += ",";
			}
		}		
	}

	/**
	 * 
	 * <li>方法名：getCasCodeHql
	 * <li>@param alias 表别名
	 * <li>@param fieldName 持久化属性或字段名
	 * <li>@return
	 * <li>返回类型：String
	 * <li>说明：获得由级联编码组成的查询条件Hql,如有则包含了 and关键字
	 * <li>创建人：曾明辉
	 * <li>创建日期：2010-8-5
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public String getCasCodeHql(String alias, String fieldName){
		StringBuffer sb = new StringBuffer();
		if(StrUtil.isNotEmpty(this.casCodeSea)){
			sb.append(" and( ");
			String[] codeArr = this.casCodeSea.split(",");
			for (int i = 0; i < codeArr.length; i++) {
				if(StrUtil.isNotEmpty(alias)){
					sb.append(alias + ".");
				}
				sb.append(fieldName + " ");
				if(INCLUDE_LOWER_LEVER.equals(this.includeSea)){
					sb.append(" like '" + codeArr[i] + "%'");
				}
				else{
					sb.append(" = '" + codeArr[i] + "'");
				}
				if(i != codeArr.length -1){
					sb.append(" or ");
				}
			}
			
			sb.append(")");
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * <li>方法名：getCasCodeSqlForIbatis
	 * <li>@param alias 表别名
	 * <li>@param fieldName 持久化属性或字段名
	 * <li>@return
	 * <li>返回类型：String
	 * <li>说明：获得由级联编码组成的查询条件Sql
	 * <li>创建人：廖迎春
	 * <li>创建日期：2010-09-13
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public String getCasCodeSqlForIbatis(){
		StringBuffer sb = new StringBuffer();
		if(casCodeSea!=null && !casCodeSea.trim().equals("")){
			String[] casCodeArray = casCodeSea.split("\\,");
			if(StrUtil.isNotEmpty(this.getIncludeSea())){//包含下级
				if(casCodeArray.length == 1){
					sb.append("  br_Cas_Code like '"+casCodeSea+"%'");
				}else if(casCodeArray.length > 1){
					for(int i=0;i<casCodeArray.length;i++){
						if(i==0){
							sb.append("  (br_Cas_Code like '"+casCodeArray[i]+"%'");
						}else{
							sb.append(" or br_Cas_Code like '"+casCodeArray[i]+"%'");
						}
					}
					sb.append(")");
				}else{
					
				}
			}else{//不包含下级
				if(casCodeArray.length == 1){
					sb.append("  br_Cas_Code = '"+casCodeSea+"'");
				}else{
					for(int i=0;i<casCodeArray.length;i++){
						if(i==0){
							sb.append("  br_Cas_Code in ('"+casCodeArray[i]+"'");
						}else{
							sb.append(",'"+casCodeArray[i]+"'");
						}
					}
					sb.append(")");
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * <li>方法名：getCasCodeSqlForIbatis
	 * <li>@param alias 表别名
	 * <li>@param fieldName 持久化属性或字段名
	 * <li>@return
	 * <li>返回类型：String
	 * <li>说明：重写getCasCodeSqlForIbatis
	 * <li>创建人：黄道勋
	 * <li>创建日期：2013-06-26
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public String getCasCodeSqlForIbatis(String alias, String fieldName){
		StringBuffer sb = new StringBuffer();
		if(casCodeSea!=null && !casCodeSea.trim().equals("")){
			String[] casCodeArray = casCodeSea.split("\\,");
			if(StrUtil.isNotEmpty(this.getIncludeSea())){//包含下级
				if(casCodeArray.length == 1){
					sb.append(alias + "." + fieldName + " like '"+casCodeSea+"%'");
				}else if(casCodeArray.length > 1){
					for(int i=0;i<casCodeArray.length;i++){
						if(i==0){
							sb.append("  (" + alias + "." + fieldName + " like '"+casCodeArray[i]+"%'");
						}else{
							sb.append(" or " + alias + "." + fieldName + " like '"+casCodeArray[i]+"%'");
						}
					}
					sb.append(")");
				}else{
					
				}
			}else{//不包含下级
				if(casCodeArray.length == 1){
					sb.append(alias + "." + fieldName + "  = '"+casCodeSea+"'");
				}else{
					for(int i=0;i<casCodeArray.length;i++){
						if(i==0){
							sb.append(alias + "." + fieldName + " in ('"+casCodeArray[i]+"'");
						}else{
							sb.append(",'"+casCodeArray[i]+"'");
						}
					}
					sb.append(")");
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * <li>方法名：isNotSearchAll
	 * <li>@return
	 * <li>返回类型：boolean
	 * <li>说明：是否是查找全部
	 * <li>创建人：曾明辉
	 * <li>创建日期：2010-8-16
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public boolean isNotSearchAll(){
		if(! INCLUDE_LOWER_LEVER.equals(includeSea)){//没有包含下级
			return true;
		}
		if(LOCAL_DEPT == null){//没有设置本地机构
			return true;
		}
		if(StrUtil.isNotEmpty(this.casCodeSea)){
			String[] codeArr = this.casCodeSea.split(",");
			for (String code : codeArr) {
				if(code.equals(LOCAL_DEPT.getCasCode())){
					return false;
				}
			}
		}
		return true;
	}
	
}