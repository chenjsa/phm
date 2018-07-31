package com.pms.rcm.sys.manager;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.base.BaseManager;
import com.pms.base.common.BusinessException;
import com.pms.base.util.GUIDHexGenerator;
import com.pms.base.util.Page;
import com.pms.base.util.PageData;
import com.pms.base.util.StrUtil;
import com.pms.rcm.radar.manager.RadarTypeInfoManager;
import com.pms.rcm.radar.manager.SubsystemInfoManager;
import com.pms.rcm.radar.vo.RadarTypeInfo;
import com.pms.rcm.radar.vo.SubsystemInfo;
import com.pms.rcm.sys.vo.ProfessionalField;
import com.pms.rcm.sys.vo.SupportPattern; 

/**
 * Service object for domain model class ProfessionalField.
 * @see com.pms.rcm.sys.vo.ProfessionalField
 * @author bao.zhou
 */
@Service("professionalFieldManager")
public class ProfessionalFieldManager extends BaseManager<ProfessionalField>{ 
 	 @Autowired
     private ProfessionalFieldMapper professionalFieldMapper;
 	@Resource(name="subsystemInfoManager")
 	private SubsystemInfoManager subsystemInfoManager;
 	@Resource(name="radarTypeInfoManager")
 	private RadarTypeInfoManager radarTypeInfoManager;
 	@Resource(name="supportPatternManager")
 	private SupportPatternManager supportPatternManager;
   	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ProfessionalField>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    @Override
	public ProfessionalField get(Serializable id) throws BusinessException {
		ProfessionalField entity = super.get(id); 
		return entity;
	}
	public ProfessionalField getByMybatis(String id) throws BusinessException {
		ProfessionalField entity = professionalFieldMapper.get(id); 
		return entity;
	} 
   	/**
	 * 
	 * <li>方法名：findByIds
	 * <li>@param ids
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ProfessionalField>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<ProfessionalField> findByIds(String[] ids) throws BusinessException{
		if(ids.length ==1 && ids[0].indexOf(",") != -1){
			ids = ids[0].split(",");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("from ProfessionalField where id in(''");
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
	 * <li>返回类型：List<ProfessionalField>
	 * <li>说明：分页查询
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    public List<ProfessionalField> findPage(ProfessionalField entitySearch, Page page,
			boolean needSearchCount) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from ProfessionalField w where 1=1 ");   
		 
		if(StrUtil.isNotEmpty(entitySearch.getSubsystemInfoId())){
			sb.append(" and w.subsystemInfoId='" + entitySearch.getSubsystemInfoId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getRadarTypeInfoId())){
			sb.append(" and w.radarTypeInfoId='" + entitySearch.getRadarTypeInfoId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getSupportPatternId())){
			sb.append(" and w.supportPatternId='" + entitySearch.getSupportPatternId() + "'");
		}

		String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());		
		return super.findPage(hql, page, needSearchCount);
	}
	
	public List<ProfessionalField> listPage(ProfessionalField entity, Page page) throws BusinessException {
			PageData pd = new PageData();
			pd.put("entity", entity);
			page.setPd(pd);
			List<ProfessionalField> list=  professionalFieldMapper.listPage(page); 
			return list;
	}
	  
	/**
	 * 
	 * <li>方法名：insert
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ProfessionalField>
	 * <li>说明：新增、插入操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
  	@Override
  	@Transactional(rollbackFor = { Exception.class })
	public ProfessionalField insert(ProfessionalField entity) throws BusinessException {		
		entity =  super.insert(entity);
		return entity;
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public void insertMybatis(ProfessionalField entity) throws BusinessException {		
		entity.setId(GUIDHexGenerator.getInstance().generateId());
		professionalFieldMapper.insert(entity); 
	}
	
    @Transactional(rollbackFor = { Exception.class })
	public void  updateMybatis(ProfessionalField entity) throws BusinessException {
		professionalFieldMapper.update(entity); 
	}

    /**
	 * 
	 * <li>方法名：update
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ProfessionalField>
	 * <li>说明：修改操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
   @Override
   @Transactional(rollbackFor = { Exception.class })
	public ProfessionalField  update(ProfessionalField entity) throws BusinessException {
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
	public void remove(ProfessionalField entity) {
		baseDao.delete(entity);
	}
	@Transactional(rollbackFor = { Exception.class })
	 public  void delete(Object entity,String id) throws BusinessException {
			entity=this.baseDao.get(id, entity.getClass());
			this.baseDao.delete(entity);
	 }
	 
	 @Transactional(rollbackFor = { Exception.class })
	 public  void deleteMybatis(Object entity,String id) throws BusinessException {
			professionalFieldMapper.delete(id);
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
	public void checkUnique(ProfessionalField entity) throws BusinessException {
		
	}
	 
	 public Map getSelect() throws BusinessException {
			 List<SubsystemInfo> subsystemInfos=this.subsystemInfoManager.find("from SubsystemInfo");
			 List<RadarTypeInfo> radarTypeInfos=this.radarTypeInfoManager.find("from RadarTypeInfo");
			 List<SupportPattern> supportPatterns=this.supportPatternManager.find("from SupportPattern");
			 Map map=new HashMap();
			 map.put("subsystemInfos", subsystemInfos);
			 map.put("radarTypeInfos", radarTypeInfos);
			 map.put("supportPatterns", supportPatterns);
			 return map;
	 }

}

