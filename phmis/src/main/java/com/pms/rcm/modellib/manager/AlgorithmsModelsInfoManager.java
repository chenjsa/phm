package com.pms.rcm.modellib.manager;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.base.BaseManager;
import com.pms.base.common.BusinessException;
import com.pms.base.util.Page;
import com.pms.base.util.PageData;
import com.pms.base.util.StrUtil;
import com.pms.rcm.maintain.manager.SystemParametersInfoManager;
import com.pms.rcm.maintain.vo.SystemParametersInfo;
import com.pms.rcm.modellib.vo.AlgorithmsInfo;
import com.pms.rcm.modellib.vo.AlgorithmsModelsInfo;
import com.pms.rcm.modellib.vo.FunctionModelsInfo;

/**
 * Service object for domain model class AlgorithmsModelsInfo.
 * @see com.pms.rcm.modellib.vo.AlgorithmsModelsInfo
 * @author bao.zhou
 */
@Service("algorithmsModelsInfoManager")
public class AlgorithmsModelsInfoManager extends BaseManager<AlgorithmsModelsInfo>{ 
 	 @Autowired
     private AlgorithmsModelsInfoMapper algorithmsModelsInfoMapper;
 	@Autowired
    private AlgorithmsInfoManager algorithmsInfoManager;
 	@Autowired
    private FunctionModelsInfoManager functionModelsInfoManager;
 	
   	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<AlgorithmsModelsInfo>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    @Override
	public AlgorithmsModelsInfo get(Serializable id) throws BusinessException {
		AlgorithmsModelsInfo entity = super.get(id); 
		return entity;
	}
	public AlgorithmsModelsInfo getByMybatis(String id) throws BusinessException {
		AlgorithmsModelsInfo entity = algorithmsModelsInfoMapper.get(id); 
		return entity;
	} 
   	/**
	 * 
	 * <li>方法名：findByIds
	 * <li>@param ids
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<AlgorithmsModelsInfo>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<AlgorithmsModelsInfo> findByIds(String[] ids) throws BusinessException{
		if(ids.length ==1 && ids[0].indexOf(",") != -1){
			ids = ids[0].split(",");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("from AlgorithmsModelsInfo where id in(''");
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
	 * <li>返回类型：List<AlgorithmsModelsInfo>
	 * <li>说明：分页查询
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    public List<AlgorithmsModelsInfo> findPage(AlgorithmsModelsInfo entitySearch, Page page,
			boolean needSearchCount) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from AlgorithmsModelsInfo w where 1=1 ");   
		 
		if(entitySearch.getAid()!=null){
			sb.append(" and w.aid='" + entitySearch.getAid() + "'");
		}
		if(entitySearch.getMid()!=null){
			sb.append(" and w.mid='" + entitySearch.getMid() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getVersion())){
			sb.append(" and w.version='" + entitySearch.getVersion() + "'");
		}
		if(entitySearch.getGenerateTime()!=null){
			sb.append(" and w.generateTime='" + entitySearch.getGenerateTime() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getUrl())){
			sb.append(" and w.url='" + entitySearch.getUrl() + "'");
		}

		String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());		
		return super.findPage(hql, page, needSearchCount);
	}
	
	public List<AlgorithmsModelsInfo> listPage(AlgorithmsModelsInfo entity, Page page) throws BusinessException {
			PageData pd = new PageData();
			pd.put("entity", entity);
			page.setPd(pd);
			List<AlgorithmsModelsInfo> list=  algorithmsModelsInfoMapper.listPage(page); 
			return list;
	}
	  
	/**
	 * 
	 * <li>方法名：insert
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<AlgorithmsModelsInfo>
	 * <li>说明：新增、插入操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
  	@Override
  	@Transactional(rollbackFor = { Exception.class })
	public AlgorithmsModelsInfo insert(AlgorithmsModelsInfo entity) throws BusinessException {	
  		entity.setGenerateTime(new Date());
		super.insert(entity);
		return entity;
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public void insertMybatis(AlgorithmsModelsInfo entity) throws BusinessException {		
		//entity.setId(GUIDHexGenerator.getInstance().generateId());
		algorithmsModelsInfoMapper.insert(entity); 
	}
	
    @Transactional(rollbackFor = { Exception.class })
	public void  updateMybatis(AlgorithmsModelsInfo entity) throws BusinessException {
		algorithmsModelsInfoMapper.update(entity); 
	}

    /**
	 * 
	 * <li>方法名：update
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<AlgorithmsModelsInfo>
	 * <li>说明：修改操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
   @Override
   @Transactional(rollbackFor = { Exception.class })
	public AlgorithmsModelsInfo  update(AlgorithmsModelsInfo entity) throws BusinessException {
	    entity.setGenerateTime(new Date());
		super.update(entity);
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
	public void remove(AlgorithmsModelsInfo entity) {
		baseDao.delete(entity);
	}
	@Transactional(rollbackFor = { Exception.class })
	 public  void delete(Object entity,String id) throws BusinessException {
			entity=this.baseDao.get(id, entity.getClass());
			this.baseDao.delete(entity);
	 }
	 
	 @Transactional(rollbackFor = { Exception.class })
	 public  void deleteMybatis(Object entity,String id) throws BusinessException {
			algorithmsModelsInfoMapper.delete(id);
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
	public void checkUnique(AlgorithmsModelsInfo entity) throws BusinessException {
		
	}
	
	 public Map getSelect() throws BusinessException {
		 List<FunctionModelsInfo> functionModelsInfos=this.functionModelsInfoManager.findAll();
		 List<AlgorithmsInfo> algorithmsInfos=this.algorithmsInfoManager.findAll();
	
		 Map map=new HashMap();
		 map.put("functionModelsInfos", functionModelsInfos);
		 map.put("algorithmsInfos", algorithmsInfos);  
		 return map;
 }
	 
	 
		public List<AlgorithmsModelsInfo> getAllByPhmfunctionTypeId(String phmfunctionTypeId) throws BusinessException {
			 
			List<AlgorithmsModelsInfo> list= this.baseDao.find("from AlgorithmsModelsInfo t where t.functionModelsInfo.phmfunctionTypeId='"+phmfunctionTypeId+"'");
			return list;
	}

}

