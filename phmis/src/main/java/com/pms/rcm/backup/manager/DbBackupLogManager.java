package com.pms.rcm.backup.manager;
import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.Connection;
import com.pms.base.BaseManager;
import com.pms.base.JdbcHelper;
import com.pms.base.common.BusinessException;
import com.pms.base.util.GUIDHexGenerator;
import com.pms.base.util.Page;
import com.pms.base.util.PageData;
import com.pms.base.util.StrUtil;
import com.pms.rcm.backup.vo.DbBackupLog;

/**
 * Service object for domain model class DbBackupLog.
 * @see com.pms.rcm.backup.vo.DbBackupLog
 * @author bao.zhou
 */
@Service("dbBackupLogManager")
public class DbBackupLogManager extends BaseManager<DbBackupLog>{ 
 	 @Autowired
     private DbBackupLogMapper dbBackupLogMapper;
 	 @Autowired
     private JdbcHelper jdbcHelper;
 	/**
      * @param sqlPath
 	 * @throws Exception 
      */
     public void runsqlBySpringUtils(String sqlPath) throws Exception {
         try { 
             java.sql.Connection conn =jdbcHelper.getJdbcTemplate().getDataSource().getConnection();
             
             FileSystemResource rc = new FileSystemResource(sqlPath);
             EncodedResource er = new EncodedResource(rc, "GBK");
             ScriptUtils.executeSqlScript(conn, er);
         } catch (Exception e) {
             e.printStackTrace();
             throw e;
         }
     }
   	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<DbBackupLog>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    @Override
	public DbBackupLog get(Serializable id) throws BusinessException {
		DbBackupLog entity = super.get(id); 
		return entity;
	}
	public DbBackupLog getByMybatis(String id) throws BusinessException {
		DbBackupLog entity = dbBackupLogMapper.get(id); 
		return entity;
	} 
   	/**
	 * 
	 * <li>方法名：findByIds
	 * <li>@param ids
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<DbBackupLog>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<DbBackupLog> findByIds(String[] ids) throws BusinessException{
		if(ids.length ==1 && ids[0].indexOf(",") != -1){
			ids = ids[0].split(",");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("from DbBackupLog where id in(''");
		for (String id : ids) {
			sb.append(",'" + id + "'");
		}
		sb.append(")");
		
		return this.baseDao.find(sb.toString());
	}
	/**
	 * 
	 * <li>方法名：findPage
	 * <li>@param entitySearch，page，needSearchCount
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<DbBackupLog>
	 * <li>说明：分页查询
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    public List<DbBackupLog> findPage(DbBackupLog entitySearch, Page page,
			boolean needSearchCount) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from DbBackupLog w where 1=1 ");   
		 
		if(entitySearch.getBackupTime()!=null){
			sb.append(" and w.backupTime='" + entitySearch.getBackupTime() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getBackupName())){
			sb.append(" and w.backupName='" + entitySearch.getBackupName() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getBackupType())){
			sb.append(" and w.backupType='" + entitySearch.getBackupType() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getBackupModel())){
			sb.append(" and w.backupModel='" + entitySearch.getBackupModel() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getFileSize())){
			sb.append(" and w.fileSize='" + entitySearch.getFileSize() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getStorePath())){
			sb.append(" and w.storePath='" + entitySearch.getStorePath() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getBackupStatus())){
			sb.append(" and w.backupStatus='" + entitySearch.getBackupStatus() + "'");
		}
		if(entitySearch.getBackupTimeB()!=null){
			sb.append(" and w.backupTime>='" + entitySearch.getBackupTimeB() + "'");
		}
		if(entitySearch.getBackupTimeE()!=null){
			sb.append(" and w.backupTime<='" + entitySearch.getBackupTimeE() + "'");
		}

		String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());		
		return super.findPage(hql, page, needSearchCount);
	}
	
	public List<DbBackupLog> listPage(DbBackupLog entity, Page page) throws BusinessException {
			PageData pd = new PageData();
			pd.put("entity", entity);
			page.setPd(pd);
			List<DbBackupLog> list=  dbBackupLogMapper.listPage(page); 
			return list;
	}
	  
	/**
	 * 
	 * <li>方法名：insert
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<DbBackupLog>
	 * <li>说明：新增、插入操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
  	@Override
  	@Transactional(rollbackFor = { Exception.class })
	public DbBackupLog insert(DbBackupLog entity) throws BusinessException {		
		entity =  super.insert(entity);
		return entity;
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public void insertMybatis(DbBackupLog entity) throws BusinessException {		
		entity.setId(GUIDHexGenerator.getInstance().generateId());
		dbBackupLogMapper.insert(entity); 
	}
	
    @Transactional(rollbackFor = { Exception.class })
	public void  updateMybatis(DbBackupLog entity) throws BusinessException {
		dbBackupLogMapper.update(entity); 
	}

    /**
	 * 
	 * <li>方法名：update
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<DbBackupLog>
	 * <li>说明：修改操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
   @Override
   @Transactional(rollbackFor = { Exception.class })
	public DbBackupLog  update(DbBackupLog entity) throws BusinessException {
		entity = super.update(entity);
		return entity;
	}
	 
    
    /**
	 * 
	 * <li>方法名：remove
	 * <li>@param obj 实体对象
	 * <li>返回类型：void
	 * <li>说明：删除指定的实体对象,删除级联情况视hibernate的配置情况。
	 * <li>创建人：周暴
	 * <li>创建日期：2008-10-14
	 * <li>修改人：
	 * <li>修改日期：
	 */
	@Transactional(rollbackFor = { Exception.class })
	public void remove(DbBackupLog entity) {
		baseDao.delete(entity);
	}
	@Transactional(rollbackFor = { Exception.class })
	 public  void delete(Object entity,String id) throws BusinessException {
			entity=this.baseDao.get(id, entity.getClass());
			this.baseDao.delete(entity);
	 }
	 
	 @Transactional(rollbackFor = { Exception.class })
	 public  void deleteMybatis(Object entity,String id) throws BusinessException {
			dbBackupLogMapper.delete(id);
	 }

	/**
	 * 
	 * <li>方法名：checkDelete
	 * <li>@param obj 实体对象
	 * <li>返回类型：void
	 * <li>说明：删除指定的实体对象校验
	 * <li>创建人：周暴
	 * <li>创建日期：2008-10-14
	 * <li>修改人：
	 * <li>修改日期：
	 */
	@Override
	public void checkDelete(Serializable id) throws BusinessException {
		
	}
	/**
	 * 
	 * <li>方法名：checkUnique
	 * <li>@param obj 实体对象
	 * <li>返回类型：void
	 * <li>说明： 判断数据库是是否已有数据该代码数据
	 * <li>创建人：周暴
	 * <li>创建日期：2008-10-14
	 * <li>修改人：
	 * <li>修改日期：
	 */
	@Override
	public void checkUnique(DbBackupLog entity) throws BusinessException {
		
	}
	 

}

