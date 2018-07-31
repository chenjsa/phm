package com.pms.base;

import java.math.BigDecimal;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;

/**
 * <br>
 * 
 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute()
 * 
 * @spring.bean id="jdbcHelper"
 * @spring.property name="dataSource" ref="dataSource"
 * 
 */
@Repository("jdbcHelper")
public class JdbcHelper{
	@Autowired
    private JdbcTemplate jdbcTemplate;
	public List SqlQuery(String sql) {
		List list = jdbcTemplate.queryForList(sql);
		return convertMapToObjectArray(list);
	}
	
	public List SqlQuery(String sql,Object[] args) {
		List list = jdbcTemplate.queryForList(sql, args);
		return convertMapToObjectArray(list);
	}
	
	public List SqlQueryPrim(String sql) { // ������ת��
		List list =jdbcTemplate.queryForList(sql);
		return list;
	}

	public List SqlQueryPrim(String sql,Object[] args) { // ������ת��
		List list = jdbcTemplate.queryForList(sql,args);
		return list;
	}
	
	public List convertMapToObjectArray(List resultList) {
		// ��MapתΪArray
		List arrayList = new ArrayList();
		int size = 0;
		if (resultList.size() > 0) {
			Map resultMap = (Map) resultList.get(0);
			Iterator keyIter = resultMap.keySet().iterator();
			while (keyIter.hasNext()) {
				size++;
				keyIter.next();
			}
		}

		for (int i = 0; i < resultList.size(); i++) {
			Object[] row = new Object[size];
			int j = 0;
			Map resultMap = (Map) resultList.get(i);
			Iterator keyIter = resultMap.keySet().iterator();
			while (keyIter.hasNext()) {
				String key = (String) keyIter.next();
				row[j] = resultMap.get(key);
				if (row[j] instanceof BigDecimal) {
					if (((BigDecimal) row[j]).scale() > 0)
						row[j] = new Double((((BigDecimal) row[j]).doubleValue()));
					else
						row[j] = new Long((((BigDecimal) row[j]).longValue()));
				}
				j++;
			}
			arrayList.add(row);
		}

		return arrayList;
	}

	public void execStoreProcedure(String spName, String param) {
		MyStoredProcedure sproc = new MyStoredProcedure(jdbcTemplate.getDataSource(), spName, param);
		sproc.execute(param);
	}

	private class MyStoredProcedure extends StoredProcedure {

		public MyStoredProcedure(DataSource ds, String spName, String param) {
			setDataSource(ds);
			setFunction(false);
			setSql(spName);
			declareParameter(new SqlParameter("g_param", Types.VARCHAR));
			compile();
		}

		public Map execute(String param) {

			Map inParams = new HashMap();
			inParams.put("g_param", param);
			Map out = execute(inParams);
			return out;
		}
	}

	public long getLong(String sql) {
		List list = SqlQuery(sql);
		if (list.size() == 0)
			return new Long(0);
		Object[] obj = (Object[]) list.get(0);
		return (Long) obj[0];
	}

	public long getLong(String sql,Object[] args) {
		List list = SqlQuery(sql,args);
		if (list.size() == 0)
			return new Long(0);
		Object[] obj = (Object[]) list.get(0);
		return (Long) obj[0];
	}
	
	public String getString(String sql) {
		List list = SqlQuery(sql);
		if(list.size()>0)
		{
			Object[] obj = (Object[]) list.get(0);
			if (obj[0] != null)
				return obj[0].toString();
			else
				return null;
		}
		else
			return null;
		
	}
	
	public String getString(String sql,Object[] args) {
		List list = SqlQuery(sql,args);
		Object[] obj = (Object[]) list.get(0);
		if (obj[0] != null)
			return obj[0].toString();
		else
			return null;
	}

	public void executeUpdate(String sql) {
		jdbcTemplate.execute(sql);
	}
	
	public void executeUpdate(String sql,Object[] args) {
		jdbcTemplate.update(sql, args);
	}
	
	public Double getDouble(String sql) {
		List list = SqlQuery(sql);
		if (list.size() == 0)
			return new Double(0);
		Object[] obj = (Object[]) list.get(0);
		Double b = Double.parseDouble(obj[0].toString());
		return b;
	}
	
	public Double getDouble(String sql,Object[] args) {
		List list = SqlQuery(sql,args);
		if (list.size() == 0)
			return new Double(0);
		Object[] obj = (Object[]) list.get(0);
		Double b = Double.parseDouble(obj[0].toString());
		return b;
	}
	 

	public Date getSystemDateTime(){
		String dateTimeStr = this.getString("SELECT TO_CHAR(SYSDATE,'YYYY-MM-DD HH24:MI:SS') FROM DUAL");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return dateFormat.parse(dateTimeStr);
		} catch (ParseException e) {
			throw new IllegalArgumentException("JdbcHelper Could not parse date: " + e.getMessage());
		}
	}
	
	public Date getSystemDate(){
		String dateStr = this.getString("SELECT TO_CHAR(SYSDATE,'YYYY-MM-DD HH24:MI:SS') FROM DUAL");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			throw new IllegalArgumentException("JdbcHelper Could not parse date: " + e.getMessage());
		}
	}
	public Long CmpSystemDate(String dateStr){
		
			return  this.getLong("SELECT to_date('"+dateStr+"','yyyy-MM-dd')-trunc(sysdate,'dd') FROM DUAL");
	}
	public Long CmpSystemDate(Date date){
		
		return  this.getLong("SELECT trunc('"+date+"','dd')-trunc(sysdate,'dd') FROM DUAL");
	}
	public Long CmpDate(String dateStr1,String dateStr2){
		
		return  this.getLong("SELECT to_date('"+dateStr1+"','yyyy-MM-dd')-to_date('"+dateStr2+"','yyyy-MM-dd') FROM DUAL");
}
public Long CmpDate(Date date1,Date date2){
	
	return  this.getLong("SELECT trunc("+date1+",'dd')-trunc("+date2+",'dd') FROM DUAL");
}
	public String addMonth(String rqStr,long addTag){
		String ls_cbyf_temp = this.getString("select to_char(add_months(to_date(?,'yyyy-mm-dd'),?),'yyyy-mm-dd') from dual", new Object[]{rqStr,addTag});
		return ls_cbyf_temp;
	}
	
	
	public void execStoreProcedure1(String spName, String param,String param1,String param2) {
		MyStoredProcedure1 sproc = new MyStoredProcedure1(jdbcTemplate.getDataSource(), spName);
		sproc.execute(param,param1,param2);
	}

	private class MyStoredProcedure1 extends StoredProcedure {

		public MyStoredProcedure1(DataSource ds, String spName) {
			setDataSource(ds);
			setFunction(false);
			setSql(spName);
			declareParameter(new SqlParameter("g_param", Types.VARCHAR));
			declareParameter(new SqlParameter("g_param1", Types.VARCHAR));
			declareParameter(new SqlParameter("g_param2", Types.VARCHAR));
			compile();
		}

		public Map execute(String param,String param1,String param2) {

			Map inParams = new HashMap();
			inParams.put("g_param", param);
			inParams.put("g_param1", param1);
			inParams.put("g_param2", param2);
			Map out = execute(inParams);
			return out;
		}
	}
	public int getInt(String ls_hql_count) {
		List list = SqlQuery(ls_hql_count);
		if (list.size() == 0)
			return 0;
		Object[] obj = (Object[]) list.get(0);
		Integer b = Integer.parseInt(obj[0].toString());
		return b;
	}
}