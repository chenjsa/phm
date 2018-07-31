package com.pms.rcm.radar.manager;
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.base.BaseManager;
import com.pms.base.common.BusinessException;
import com.pms.base.util.GUIDHexGenerator;
import com.pms.base.util.Page;
import com.pms.base.util.PageData;
import com.pms.base.util.StrUtil;
import com.pms.rcm.radar.vo.RadarBasicStateInfo;
import com.pms.rcm.radar.vo.RadarDeviceInfo; 

/**
 * Service object for domain model class RadarDeviceInfo.
 * @see com.pms.rcm.radar.vo.RadarDeviceInfo
 * @author bao.zhou
 */
@Service("radarDeviceInfoManager")
public class RadarDeviceInfoManager extends BaseManager<RadarDeviceInfo>{ 
 	 @Autowired
     private RadarDeviceInfoMapper radarDeviceInfoMapper;
 	 @Autowired
     private RadarBasicStateInfoManager radarBasicStateInfoManager;
   	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<RadarDeviceInfo>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    @Override
	public RadarDeviceInfo get(Serializable id) throws BusinessException {
		RadarDeviceInfo entity = super.get(id); 
		return entity;
	}
	public RadarDeviceInfo getByMybatis(String id) throws BusinessException {
		RadarDeviceInfo entity = radarDeviceInfoMapper.get(id); 
		return entity;
	} 
   	/**
	 * 
	 * <li>方法名：findByIds
	 * <li>@param ids
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<RadarDeviceInfo>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<RadarDeviceInfo> findByIds(String[] ids) throws BusinessException{
		if(ids.length ==1 && ids[0].indexOf(",") != -1){
			ids = ids[0].split(",");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("from RadarDeviceInfo where id in(''");
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
	 * <li>返回类型：List<RadarDeviceInfo>
	 * <li>说明：分页查询
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    public List<RadarDeviceInfo> findPage(RadarDeviceInfo entitySearch, Page page,
			boolean needSearchCount) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from RadarDeviceInfo w where 1=1 ");   
		 
		if(StrUtil.isNotEmpty(entitySearch.getRadarName())){
			sb.append(" and w.radarName='" + entitySearch.getRadarName() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getTypeId())){
			sb.append(" and w.typeId='" + entitySearch.getTypeId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getDescription())){
			sb.append(" and w.description='" + entitySearch.getDescription() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getManufacturer())){
			sb.append(" and w.manufacturer='" + entitySearch.getManufacturer() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getStationId())){
			sb.append(" and w.stationId='" + entitySearch.getStationId() + "'");
		}
		if(entitySearch.getManufacturerDate()!=null){
			sb.append(" and w.manufacturerDate='" + entitySearch.getManufacturerDate() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getAddress())){
			sb.append(" and w.address='" + entitySearch.getAddress() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getWorkOrder())){
			sb.append(" and w.workOrder='" + entitySearch.getWorkOrder() + "'");
		}
		if(entitySearch.getErectionTime()!=null){
			sb.append(" and w.erectionTime='" + entitySearch.getErectionTime() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getInServiceState())){
			sb.append(" and w.inServiceState='" + entitySearch.getInServiceState() + "'");
		}
		if(entitySearch.getTaskAttributeId()!=null){
			sb.append(" and w.taskAttributeId='" + entitySearch.getTaskAttributeId() + "'");
		}

		String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());		
		return super.findPage(hql, page, needSearchCount);
	}
	
	public List<RadarDeviceInfo> listPage(RadarDeviceInfo entity, Page page) throws BusinessException {
			PageData pd = new PageData();
			pd.put("entity", entity);
			page.setPd(pd);
			List<RadarDeviceInfo> list=  radarDeviceInfoMapper.listPage(page); 
			return list;
	}
	  
	/**
	 * 
	 * <li>方法名：insert
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<RadarDeviceInfo>
	 * <li>说明：新增、插入操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
  	@Override
  	@Transactional(rollbackFor = { Exception.class })
	public RadarDeviceInfo insert(RadarDeviceInfo entity) throws BusinessException {		
		entity =  super.insert(entity);
		String hql="delete from RadarBasicStateInfo where radarId='"+entity.getId()+"'";
	    this.baseDao.execute(hql);
	    for(RadarBasicStateInfo radarBasicStateInfo: entity.getRadarBasicStateInfos()){
	    	radarBasicStateInfo.setRadarId(entity.getId());
 			this.radarBasicStateInfoManager.insert(radarBasicStateInfo);
 		 } 
		return entity;
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public void insertMybatis(RadarDeviceInfo entity) throws BusinessException {		
		entity.setId(GUIDHexGenerator.getInstance().generateId());
		radarDeviceInfoMapper.insert(entity); 
	}
	
    @Transactional(rollbackFor = { Exception.class })
	public void  updateMybatis(RadarDeviceInfo entity) throws BusinessException {
		radarDeviceInfoMapper.update(entity); 
	}

    /**
	 * 
	 * <li>方法名：update
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<RadarDeviceInfo>
	 * <li>说明：修改操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
   @Override
   @Transactional(rollbackFor = { Exception.class })
	public RadarDeviceInfo  update(RadarDeviceInfo entity) throws BusinessException {
		entity = super.update(entity);
		String hql="delete from RadarBasicStateInfo where radarId='"+entity.getId()+"'";
	    this.baseDao.execute(hql);
	    for(RadarBasicStateInfo radarBasicStateInfo: entity.getRadarBasicStateInfos()){
	    	radarBasicStateInfo.setRadarId(entity.getId());
 			this.radarBasicStateInfoManager.insert(radarBasicStateInfo);
 		 } 
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
	public void remove(RadarDeviceInfo entity) {
		baseDao.delete(entity);
	}
	@Transactional(rollbackFor = { Exception.class })
	 public  void delete(Object entity,String id) throws BusinessException {
			entity=this.baseDao.get(id, entity.getClass());
			this.baseDao.delete(entity);
	 }
	 
	 @Transactional(rollbackFor = { Exception.class })
	 public  void deleteMybatis(Object entity,String id) throws BusinessException {
			radarDeviceInfoMapper.delete(id);
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
	public void checkUnique(RadarDeviceInfo entity) throws BusinessException {
		
	}
	 

}

