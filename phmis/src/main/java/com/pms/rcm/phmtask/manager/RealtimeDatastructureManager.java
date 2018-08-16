package com.pms.rcm.phmtask.manager;
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
import com.pms.rcm.phmtask.vo.DataClassInfo;
import com.pms.rcm.phmtask.vo.DataTypeInfo;
import com.pms.rcm.phmtask.vo.FileTypeInfo;
import com.pms.rcm.phmtask.vo.ParameterCustomClass;
import com.pms.rcm.phmtask.vo.RealtimeDatastructure;
import com.pms.rcm.radar.manager.RadarTypeInfoManager;
import com.pms.rcm.radar.vo.RadarTypeInfo;

/**
 * Service object for domain model class RealtimeDatastructure.
 * @see com.pms.rcm.phmtask.vo.RealtimeDatastructure
 * @author bao.zhou
 */
@Service("realtimeDatastructureManager")
public class RealtimeDatastructureManager extends BaseManager<RealtimeDatastructure>{ 
 	 @Autowired
     private RealtimeDatastructureMapper realtimeDatastructureMapper;
 	 @Autowired
     private RadarTypeInfoManager radarTypeInfoManager;
	 @Autowired
     private FileTypeInfoManager fileTypeInfoManager;
	 @Autowired
     private DataTypeInfoManager dataTypeInfoManager;
	 @Autowired
     private DataClassInfoManager dataClassInfoManager;
	 @Autowired
     private ParameterCustomClassManager parameterCustomClassManager;
   	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<RealtimeDatastructure>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    @Override
	public RealtimeDatastructure get(Serializable id) throws BusinessException {
		RealtimeDatastructure entity = super.get(id); 
		return entity;
	}
	public RealtimeDatastructure getByMybatis(String id) throws BusinessException {
		RealtimeDatastructure entity = realtimeDatastructureMapper.get(id); 
		return entity;
	} 
   	/**
	 * 
	 * <li>方法名：findByIds
	 * <li>@param ids
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<RealtimeDatastructure>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<RealtimeDatastructure> findByIds(String[] ids) throws BusinessException{
		if(ids.length ==1 && ids[0].indexOf(",") != -1){
			ids = ids[0].split(",");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("from RealtimeDatastructure where id in(''");
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
	 * <li>返回类型：List<RealtimeDatastructure>
	 * <li>说明：分页查询
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    public List<RealtimeDatastructure> findPage(RealtimeDatastructure entitySearch, Page page,
			boolean needSearchCount) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from RealtimeDatastructure w where 1=1 ");   
		 
		if(StrUtil.isNotEmpty(entitySearch.getDatafileTypeId())){
			sb.append(" and w.datafileTypeId='" + entitySearch.getDatafileTypeId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getOrder())){
			sb.append(" and w.order='" + entitySearch.getOrder() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getDataName())){
			sb.append(" and w.dataName='" + entitySearch.getDataName() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getDataType())){
			sb.append(" and w.dataType='" + entitySearch.getDataType() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getDataLength())){
			sb.append(" and w.dataLength='" + entitySearch.getDataLength() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getRadarTypeId())){
			sb.append(" and w.radarTypeId='" + entitySearch.getRadarTypeId() + "'");
		}

		String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());		
		return super.findPage(hql, page, needSearchCount);
	}
	
	public List<RealtimeDatastructure> listPage(RealtimeDatastructure entity, Page page) throws BusinessException {
			PageData pd = new PageData();
			pd.put("entity", entity);
			page.setPd(pd);
			List<RealtimeDatastructure> list=  realtimeDatastructureMapper.listPage(page); 
			return list;
	}
	  
	/**
	 * 
	 * <li>方法名：insert
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<RealtimeDatastructure>
	 * <li>说明：新增、插入操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
  	@Override
  	@Transactional(rollbackFor = { Exception.class })
	public RealtimeDatastructure insert(RealtimeDatastructure entity) throws BusinessException {		
		entity =  super.insert(entity);
		return entity;
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public void insertMybatis(RealtimeDatastructure entity) throws BusinessException {		
		entity.setId(GUIDHexGenerator.getInstance().generateId());
		realtimeDatastructureMapper.insert(entity); 
	}
	
    @Transactional(rollbackFor = { Exception.class })
	public void  updateMybatis(RealtimeDatastructure entity) throws BusinessException {
		realtimeDatastructureMapper.update(entity); 
	}

    /**
	 * 
	 * <li>方法名：update
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<RealtimeDatastructure>
	 * <li>说明：修改操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
   @Override
   @Transactional(rollbackFor = { Exception.class })
	public RealtimeDatastructure  update(RealtimeDatastructure entity) throws BusinessException {
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
	public void remove(RealtimeDatastructure entity) {
		baseDao.delete(entity);
	}
	@Transactional(rollbackFor = { Exception.class })
	 public  void delete(Object entity,String id) throws BusinessException {
			entity=this.baseDao.get(id, entity.getClass());
			this.baseDao.delete(entity);
	 }
	 
	 @Transactional(rollbackFor = { Exception.class })
	 public  void deleteMybatis(Object entity,String id) throws BusinessException {
			realtimeDatastructureMapper.delete(id);
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
	public void checkUnique(RealtimeDatastructure entity) throws BusinessException {
		
	}
	public Map getAll() throws BusinessException{		
		Map map=new HashMap();
		List<FileTypeInfo> fileTypeInfos=fileTypeInfoManager.findAll();
		List<RadarTypeInfo> radarTypeInfos=this.radarTypeInfoManager.findAll();
		List<DataTypeInfo> dataTypeInfos=dataTypeInfoManager.findAll();
		List<ParameterCustomClass> parameterCustomClasses=this.parameterCustomClassManager.findAll();
		List<DataClassInfo> dataClassInfos=this.dataClassInfoManager.findAll();
		map.put("fileTypeInfos", fileTypeInfos);
		map.put("radarTypeInfos", radarTypeInfos);
		map.put("dataTypeInfos", dataTypeInfos);
		map.put("parameterCustomClasses", parameterCustomClasses);
		map.put("dataClassInfos", dataClassInfos);
		return map;
	}

}

