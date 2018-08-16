package com.pms.base.common;

public class JQgridBean {
	private String total;
	private String records;
	private Object rows;
	public JQgridBean(String total,String records,Object rows){
		this.total=total;
		this.records=records;
		this.rows=rows;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getRecords() {
		return records;
	}
	public void setRecords(String records) {
		this.records = records;
	}
	public Object getRows() {
		return rows;
	}
	public void setRows(Object rows) {
		this.rows = rows;
	}
	
}
