package com.pms.rcm.radar.manager;
import java.io.Serializable; 
import java.util.List; 
 
import com.pms.rcm.radar.vo.RadarSubsystemInfo; 
import java.io.Serializable;
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

/**
 * Service object for domain model class RadarSubsystemInfo.
 * @see com.pms.rcm.radar.vo.RadarSubsystemInfo
 * @author bao.zhou
 */
@Service("radarSubsystemInfoManager")
public class RadarSubsystemInfoManager extends BaseManager<RadarSubsystemInfo>{ 
 	 @Autowired
     private RadarSubsystemInfoMapper radarSubsystemInfoMapper;
   	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<RadarSubsystemInfo>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    @Override
	public RadarSubsystemInfo get(Serializable id) throws BusinessException {
		RadarSubsystemInfo entity = super.get(id); 
		return entity;
	}
	public RadarSubsystemInfo getByMybatis(String id) throws BusinessException {
		RadarSubsystemInfo entity = radarSubsystemInfoMapper.get(id); 
		return entity;
	} 
   	/**
	 * 
	 * <li>方法名：findByIds
	 * <li>@param ids
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<RadarSubsystemInfo>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<RadarSubsystemInfo> findByIds(String[] ids) throws BusinessException{
		if(ids.length ==1 && ids[0].indexOf(",") != -1){
			ids = ids[0].split(",");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("from RadarSubsystemInfo where id in(''");
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
	 * <li>返回类型：List<RadarSubsystemInfo>
	 * <li>说明：分页查询
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    public List<RadarSubsystemInfo> findPage(RadarSubsystemInfo entitySearch, Page page,
			boolean needSearchCount) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from RadarSubsystemInfo w where 1=1 ");   
		 
		if(StrUtil.isNotEmpty(entitySearch.getSubsystemTypeId())){
			sb.append(" and w.subsystemTypeId='" + entitySearch.getSubsystemTypeId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getRadarId())){
			sb.append(" and w.radarId='" + entitySearch.getRadarId() + "'");
		}
		if(entitySearch.getBeginTime()!=null){
			sb.append(" and w.beginTime='" + entitySearch.getBeginTime() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getOther())){
			sb.append(" and w.other='" + entitySearch.getOther() + "'");
		}

		String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());		
		return super.findPage(hql, page, needSearchCount);
	}
	
	public List<RadarSubsystemInfo> listPage(RadarSubsystemInfo entity, Page page) throws BusinessException {
			PageData pd = new PageData();
			pd.put("entity", entity);
			page.setPd(pd);
			List<RadarSubsystemInfo> list=  radarSubsystemInfoMapper.listPage(page); 
			return list;
	}
	  
	/**
	 * 
	 * <li>方法名：insert
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<RadarSubsystemInfo>
	 * <li>说明：新增、插入操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
  	@Override
  	@Transactional(rollbackFor = { Exception.class })
	public RadarSubsystemInfo insert(RadarSubsystemInfo entity) throws BusinessException {	
  		this.checkUnique(entity); 
		entity =  super.insert(entity);
		return entity;
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public void insertMybatis(RadarSubsystemInfo entity) throws BusinessException {		
		entity.setId(GUIDHexGenerator.getInstance().generateId());
		radarSubsystemInfoMapper.insert(entity); 
	}
	
    @Transactional(rollbackFor = { Exception.class })
	public void  updateMybatis(RadarSubsystemInfo entity) throws BusinessException {
		radarSubsystemInfoMapper.update(entity); 
	}

    /**
	 * 
	 * <li>方法名：update
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<RadarSubsystemInfo>
	 * <li>说明：修改操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
   @Override
   @Transactional(rollbackFor = { Exception.class })
	public RadarSubsystemInfo  update(RadarSubsystemInfo entity) throws BusinessException {
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
	public void remove(RadarSubsystemInfo entity) {
		baseDao.delete(entity);
	}
	@Transactional(rollbackFor = { Exception.class })
	 public  void delete(Object entity,String id) throws BusinessException {
			entity=this.baseDao.get(id, entity.getClass());
			this.baseDao.delete(entity);
	 }
	 
	 @Transactional(rollbackFor = { Exception.class })
	 public  void deleteMybatis(Object entity,String id) throws BusinessException {
			radarSubsystemInfoMapper.delete(id);
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
	public void checkUnique(RadarSubsystemInfo entity) throws BusinessException {
		String hql = "select d from RadarSubsystemInfo d where d.subsystemTypeId = '"+entity.getSubsystemTypeId()+"' and d.radarId='"+entity.getRadarId()+"'";
		entity=(RadarSubsystemInfo)this.baseDao.findSingle(hql);
		if(entity!=null){
			throw new BusinessException(entity.getRadarDeviceInfo().getRadarName() + "已创建分系统"+entity.getSubsystemInfo().getSubsystemName());
		}
	}
	
	public List<RadarSubsystemInfo> getListByRadarId(String radarId) throws BusinessException {
		 
		List<RadarSubsystemInfo> list=  this.baseDao.find("from RadarSubsystemInfo where radarId='"+radarId+"'"); 
		return list;
}
	 

}

