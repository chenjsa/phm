package com.pms.rcm.maintain.manager;
import java.io.Serializable;
import java.util.Date;
import java.util.List; 
 

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.pms.base.util.GUIDHexGenerator;
import com.pms.base.BaseManager;
import com.pms.base.common.BusinessException;
import com.pms.base.util.Page;
import com.pms.base.util.PageData;
import com.pms.base.util.StrUtil;
import com.pms.rcm.maintain.vo.SystemParametersInfo; 

/**
 * Service object for domain model class SystemParametersInfo.
 * @see com.northking.HelloWord.entity.SystemParametersInfo
 * @author bao.zhou
 */
@Service("systemParametersInfoManager")
public class SystemParametersInfoManager extends BaseManager<SystemParametersInfo>{ 
 	 @Autowired
     private SystemParametersInfoMapper systemParametersInfoMapper;
   	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<SystemParametersInfo>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    @Override
	public SystemParametersInfo get(Serializable id) throws BusinessException {
		SystemParametersInfo entity = super.get(id); 
		return entity;
	}
    
    public SystemParametersInfo getByProp(String code) throws BusinessException {
		SystemParametersInfo entity = (SystemParametersInfo)this.baseDao.findSingle("from SystemParametersInfo where systemParametersCode='"+code+"'");
		return entity;
	}
    
	public SystemParametersInfo getByMybatis(String id) throws BusinessException {
		SystemParametersInfo entity = systemParametersInfoMapper.get(id); 
		return entity;
	} 
   	/**
	 * 
	 * <li>方法名：findByIds
	 * <li>@param ids
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<SystemParametersInfo>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<SystemParametersInfo> findByIds(String[] ids) throws BusinessException{
		if(ids.length ==1 && ids[0].indexOf(",") != -1){
			ids = ids[0].split(",");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("from SystemParametersInfo where id in(''");
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
	 * <li>返回类型：List<SystemParametersInfo>
	 * <li>说明：分页查询
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    public List<SystemParametersInfo> findPage(SystemParametersInfo entitySearch, Page page,
			boolean needSearchCount) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from SystemParametersInfo w where 1=1 ");   
		 
		if(StrUtil.isNotEmpty(entitySearch.getSystemParametersCode())){
			sb.append(" and w.systemParametersCode like '%" + entitySearch.getSystemParametersCode() + "%'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getParametersTypeId())){
			sb.append(" and w.parametersTypeId='" + entitySearch.getParametersTypeId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getParametersValues())){
			sb.append(" and w.parametersValues='" + entitySearch.getParametersValues() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getStationId())){
			sb.append(" and w.stationId='" + entitySearch.getStationId() + "'");
		}

		String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());		
		return super.findPage(hql, page, needSearchCount);
	}
	
	public List<SystemParametersInfo> listPage(SystemParametersInfo entity, Page page) throws BusinessException {
			PageData pd = new PageData();
			pd.put("entity", entity);
			page.setPd(pd);
			List<SystemParametersInfo> list=  systemParametersInfoMapper.listPage(page); 
			return list;
	}
	  
	/**
	 * 
	 * <li>方法名：insert
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<SystemParametersInfo>
	 * <li>说明：新增、插入操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
  	@Override
  	@Transactional(rollbackFor = { Exception.class })
	public SystemParametersInfo insert(SystemParametersInfo entity) throws BusinessException {
  		entity =  super.insert(entity);
		return entity;
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public void insertMybatis(SystemParametersInfo entity) throws BusinessException {		
		//entity.setId(GUIDHexGenerator.getInstance().generateId());
		systemParametersInfoMapper.insert(entity); 
	}
	
    @Transactional(rollbackFor = { Exception.class })
	public void  updateMybatis(SystemParametersInfo entity) throws BusinessException {
		systemParametersInfoMapper.update(entity); 
	}

    /**
	 * 
	 * <li>方法名：update
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<SystemParametersInfo>
	 * <li>说明：修改操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
   @Override
   @Transactional(rollbackFor = { Exception.class })
	public SystemParametersInfo  update(SystemParametersInfo entity) throws BusinessException {
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
	public void remove(SystemParametersInfo entity) {
		baseDao.delete(entity);
	}
	@Transactional(rollbackFor = { Exception.class })
	 public  void delete(Object entity,String id) throws BusinessException {
			entity=this.baseDao.get(id, entity.getClass());
			this.baseDao.delete(entity);
	 }
	 
	 @Transactional(rollbackFor = { Exception.class })
	 public  void deleteMybatis(Object entity,String id) throws BusinessException {
			systemParametersInfoMapper.delete(id);
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
	public void checkUnique(SystemParametersInfo entity) throws BusinessException {
		
	}
	 

}

