package com.pms.rcm.modellib.manager;
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
import com.pms.rcm.modellib.vo.KnowledgeBaseInfo;
import com.pms.rcm.modellib.vo.PhmfunctionTypeInfo;
import com.pms.rcm.radar.manager.ModuleTypeInfoManager;
import com.pms.rcm.radar.manager.RadarTypeInfoManager;
import com.pms.rcm.radar.manager.SubsystemInfoManager;
import com.pms.rcm.radar.vo.ModuleTypeInfo;
import com.pms.rcm.radar.vo.RadarTypeInfo;
import com.pms.rcm.radar.vo.SubsystemInfo; 

/**
 * Service object for domain model class KnowledgeBaseInfo.
 * @see com.pms.rcm.modellib.vo.KnowledgeBaseInfo
 * @author bao.zhou
 */
@Service("knowledgeBaseInfoManager")
public class KnowledgeBaseInfoManager extends BaseManager<KnowledgeBaseInfo>{ 
 	 @Autowired
     private KnowledgeBaseInfoMapper knowledgeBaseInfoMapper;
   	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<KnowledgeBaseInfo>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    @Override
	public KnowledgeBaseInfo get(Serializable id) throws BusinessException {
		KnowledgeBaseInfo entity = super.get(id); 
		return entity;
	}
	public KnowledgeBaseInfo getByMybatis(String id) throws BusinessException {
		KnowledgeBaseInfo entity = knowledgeBaseInfoMapper.get(id); 
		return entity;
	} 
   	/**
	 * 
	 * <li>方法名：findByIds
	 * <li>@param ids
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<KnowledgeBaseInfo>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<KnowledgeBaseInfo> findByIds(String[] ids) throws BusinessException{
		if(ids.length ==1 && ids[0].indexOf(",") != -1){
			ids = ids[0].split(",");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("from KnowledgeBaseInfo where id in(''");
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
	 * <li>返回类型：List<KnowledgeBaseInfo>
	 * <li>说明：分页查询
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    public List<KnowledgeBaseInfo> findPage(KnowledgeBaseInfo entitySearch, Page page,
			boolean needSearchCount) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from KnowledgeBaseInfo w where 1=1 ");   
		 
		if(StrUtil.isNotEmpty(entitySearch.getKnowledgeName())){
			sb.append(" and w.knowledgeName='" + entitySearch.getKnowledgeName() + "'");
		}
		if(entitySearch.getPhmfunctionTypeId()!=null){
			sb.append(" and w.phmfunctionTypeId='" + entitySearch.getPhmfunctionTypeId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getRadarTypeId())){
			sb.append(" and w.radarTypeId='" + entitySearch.getRadarTypeId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getSubsystemTypeId())){
			sb.append(" and w.subsystemTypeId='" + entitySearch.getSubsystemTypeId() + "'");
		}
		if(entitySearch.getModuleId()!=null){
			sb.append(" and w.moduleId='" + entitySearch.getModuleId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getKey())){
			sb.append(" and w.key='" + entitySearch.getKey() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getValue())){
			sb.append(" and w.value='" + entitySearch.getValue() + "'");
		}

		String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());		
		return super.findPage(hql, page, needSearchCount);
	}
	
	public List<KnowledgeBaseInfo> listPage(KnowledgeBaseInfo entity, Page page) throws BusinessException {
			PageData pd = new PageData();
			pd.put("entity", entity);
			page.setPd(pd);
			List<KnowledgeBaseInfo> list=  knowledgeBaseInfoMapper.listPage(page); 
			return list;
	}
	  
	/**
	 * 
	 * <li>方法名：insert
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<KnowledgeBaseInfo>
	 * <li>说明：新增、插入操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
  	@Override
  	@Transactional(rollbackFor = { Exception.class })
	public KnowledgeBaseInfo insert(KnowledgeBaseInfo entity) throws BusinessException {		
		entity =  super.insert(entity);
		return entity;
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public void insertMybatis(KnowledgeBaseInfo entity) throws BusinessException {		
		entity.setId(GUIDHexGenerator.getInstance().generateId());
		knowledgeBaseInfoMapper.insert(entity); 
	}
	
    @Transactional(rollbackFor = { Exception.class })
	public void  updateMybatis(KnowledgeBaseInfo entity) throws BusinessException {
		knowledgeBaseInfoMapper.update(entity); 
	}

    /**
	 * 
	 * <li>方法名：update
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<KnowledgeBaseInfo>
	 * <li>说明：修改操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
   @Override
   @Transactional(rollbackFor = { Exception.class })
	public KnowledgeBaseInfo  update(KnowledgeBaseInfo entity) throws BusinessException {
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
	public void remove(KnowledgeBaseInfo entity) {
		baseDao.delete(entity);
	}
	@Transactional(rollbackFor = { Exception.class })
	 public  void delete(Object entity,String id) throws BusinessException {
			entity=this.baseDao.get(id, entity.getClass());
			this.baseDao.delete(entity);
	 }
	 
	 @Transactional(rollbackFor = { Exception.class })
	 public  void deleteMybatis(Object entity,String id) throws BusinessException {
			knowledgeBaseInfoMapper.delete(id);
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
	public void checkUnique(KnowledgeBaseInfo entity) throws BusinessException {
		
	}
	@Autowired
    private PhmfunctionTypeInfoManager phmfunctionTypeInfoManager;
	@Autowired
    private RadarTypeInfoManager radarTypeInfoManager;
	@Autowired
    private SubsystemInfoManager subsystemInfoManager;
	@Autowired
    private ModuleTypeInfoManager moduleTypeInfoManager;
	public Map getAll() throws BusinessException {
		Map map=new HashMap();
		List<PhmfunctionTypeInfo> phmfunctionTypeInfos=phmfunctionTypeInfoManager.findAll();
		map.put("phmfunctionTypeInfos", phmfunctionTypeInfos);
		List<RadarTypeInfo> radarTypeInfos=radarTypeInfoManager.findAll();
		map.put("radarTypeInfos", radarTypeInfos);
		List<SubsystemInfo> subsystemInfos=subsystemInfoManager.findAll();
		map.put("subsystemInfos", subsystemInfos);
		List<ModuleTypeInfo> moduleTypeInfos=moduleTypeInfoManager.findAll();
		map.put("moduleTypeInfos", moduleTypeInfos);
		return map;
	}
	 

}

