package com.pms.rcm.radar.manager;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.base.BaseManager;
import com.pms.base.common.BusinessException;
import com.pms.base.util.GUIDHexGenerator;
import com.pms.base.util.Page;
import com.pms.base.util.PageData;
import com.pms.base.util.StrUtil;
import com.pms.rcm.radar.vo.ComponentAttributes;
import com.pms.rcm.radar.vo.ExtensionInfo;
import com.pms.rcm.radar.vo.KeyModulesInfo;
import com.pms.rcm.radar.vo.ModuleNumberInfo;
import com.pms.rcm.radar.vo.RadarSubsystemInfo; 

/**
 * Service object for domain model class KeyModulesInfo.
 * @see com.pms.rcm.radar.vo.KeyModulesInfo
 * @author bao.zhou
 */
@Service("keyModulesInfoManager")
public class KeyModulesInfoManager extends BaseManager<KeyModulesInfo>{ 
 	 @Autowired
     private KeyModulesInfoMapper keyModulesInfoMapper;
   	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<KeyModulesInfo>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    @Override
	public KeyModulesInfo get(Serializable id) throws BusinessException {
		KeyModulesInfo entity = super.get(id); 
		return entity;
	}
	public KeyModulesInfo getByMybatis(String id) throws BusinessException {
		KeyModulesInfo entity = keyModulesInfoMapper.get(id); 
		return entity;
	} 
   	/**
	 * 
	 * <li>方法名：findByIds
	 * <li>@param ids
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<KeyModulesInfo>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<KeyModulesInfo> findByIds(String[] ids) throws BusinessException{
		if(ids.length ==1 && ids[0].indexOf(",") != -1){
			ids = ids[0].split(",");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("from KeyModulesInfo where id in(''");
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
	 * <li>返回类型：List<KeyModulesInfo>
	 * <li>说明：分页查询
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    public List<KeyModulesInfo> findPage(KeyModulesInfo entitySearch, Page page,
			boolean needSearchCount) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from KeyModulesInfo w where 1=1 ");   
		 
		if(entitySearch.getExtensionId()!=null){
			sb.append(" and w.extensionId='" + entitySearch.getExtensionId() + "'");
		}
		if(entitySearch.getModuleNumberCode()!=null){
			sb.append(" and w.moduleNumberCode='" + entitySearch.getModuleNumberCode() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getKeyModulesName())){
			sb.append(" and w.keyModulesName='" + entitySearch.getKeyModulesName() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getModuleFunction())){
			sb.append(" and w.moduleFunction='" + entitySearch.getModuleFunction() + "'");
		}
		if(entitySearch.getBeginTime()!=null){
			sb.append(" and w.beginTime='" + entitySearch.getBeginTime() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getOther())){
			sb.append(" and w.other='" + entitySearch.getOther() + "'");
		}
		if(entitySearch.getComponentId()!=null){
			sb.append(" and w.componentId='" + entitySearch.getComponentId() + "'");
		}
		if(entitySearch.getPhmsubsId()!=null){
			sb.append(" and w.phmsubsId='" + entitySearch.getPhmsubsId() + "'");
		}

		String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());		
		return super.findPage(hql, page, needSearchCount);
	}
	
	public List<KeyModulesInfo> listPage(KeyModulesInfo entity, Page page) throws BusinessException {
			PageData pd = new PageData();
			pd.put("entity", entity);
			page.setPd(pd);
			List<KeyModulesInfo> list=  keyModulesInfoMapper.listPage(page); 
			return list;
	}
	  
	/**
	 * 
	 * <li>方法名：insert
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<KeyModulesInfo>
	 * <li>说明：新增、插入操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
  	@Override
  	@Transactional(rollbackFor = { Exception.class })
	public KeyModulesInfo insert(KeyModulesInfo entity) throws BusinessException {		
		entity =  super.insert(entity);
		return entity;
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public void insertMybatis(KeyModulesInfo entity) throws BusinessException {		
		entity.setId(GUIDHexGenerator.getInstance().generateId());
		keyModulesInfoMapper.insert(entity); 
	}
	
    @Transactional(rollbackFor = { Exception.class })
	public void  updateMybatis(KeyModulesInfo entity) throws BusinessException {
		keyModulesInfoMapper.update(entity); 
	}

    /**
	 * 
	 * <li>方法名：update
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<KeyModulesInfo>
	 * <li>说明：修改操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
   @Override
   @Transactional(rollbackFor = { Exception.class })
	public KeyModulesInfo  update(KeyModulesInfo entity) throws BusinessException {
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
	public void remove(KeyModulesInfo entity) {
		baseDao.delete(entity);
	}
	@Transactional(rollbackFor = { Exception.class })
	 public  void delete(Object entity,String id) throws BusinessException {
			entity=this.baseDao.get(id, entity.getClass());
			this.baseDao.delete(entity);
	 }
	 
	 @Transactional(rollbackFor = { Exception.class })
	 public  void deleteMybatis(Object entity,String id) throws BusinessException {
			keyModulesInfoMapper.delete(id);
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
	public void checkUnique(KeyModulesInfo entity) throws BusinessException {
		
	}
	 
	public Map findAllSelect(String phmsubsId) throws BusinessException{
		Map map=new HashMap();
		String hql="from RadarSubsystemInfo where id='"+phmsubsId+"'";
		RadarSubsystemInfo radarSubsystemInfo=(RadarSubsystemInfo)this.baseDao.findSingle(hql);
		hql="from ExtensionInfo where phmsubsId='"+radarSubsystemInfo.getSubsystemTypeId()+"'";
		List<ExtensionInfo> extensionInfos=this.baseDao.find(hql);
		hql="from ModuleNumberInfo";
		List<ModuleNumberInfo> moduleNumberInfos=this.baseDao.find(hql);
		hql="from ComponentAttributes";
		List<ComponentAttributes> componentAttributeses=this.baseDao.find(hql);
		map.put("extensionInfos", extensionInfos);
		map.put("moduleNumberInfos", moduleNumberInfos);
		map.put("componentAttributeses", componentAttributeses);
		return map;
	}
	
	public List<KeyModulesInfo> getListByphmsubsId(String phmsubsId) throws BusinessException { 
		List<KeyModulesInfo> list= this.baseDao.find("from KeyModulesInfo where phmsubsId='"+phmsubsId+"'");
		return list;
}
}

