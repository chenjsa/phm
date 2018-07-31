package com.pms.rcm.modellib.manager;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.base.BaseManager;
import com.pms.base.common.BusinessException;
import com.pms.base.util.Page;
import com.pms.base.util.PageData;
import com.pms.base.util.StrUtil;
import com.pms.rcm.modellib.vo.AlgorithmsInfo; 

/**
 * Service object for domain model class AlgorithmsInfo.
 * @see com.pms.rcm.modellib.vo.AlgorithmsInfo
 * @author bao.zhou
 */
@Service("algorithmsInfoManager")
public class AlgorithmsInfoManager extends BaseManager<AlgorithmsInfo>{ 
 	 @Autowired
     private AlgorithmsInfoMapper algorithmsInfoMapper;
   	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<AlgorithmsInfo>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    @Override
	public AlgorithmsInfo get(Serializable id) throws BusinessException {
		AlgorithmsInfo entity = super.get(id); 
		return entity;
	}
	public AlgorithmsInfo getByMybatis(String id) throws BusinessException {
		AlgorithmsInfo entity = algorithmsInfoMapper.get(id); 
		return entity;
	} 
   	/**
	 * 
	 * <li>方法名：findByIds
	 * <li>@param ids
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<AlgorithmsInfo>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<AlgorithmsInfo> findByIds(String[] ids) throws BusinessException{
		if(ids.length ==1 && ids[0].indexOf(",") != -1){
			ids = ids[0].split(",");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("from AlgorithmsInfo where id in(''");
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
	 * <li>返回类型：List<AlgorithmsInfo>
	 * <li>说明：分页查询
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    public List<AlgorithmsInfo> findPage(AlgorithmsInfo entitySearch, Page page,
			boolean needSearchCount) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from AlgorithmsInfo w where 1=1 ");   
		 
		if(StrUtil.isNotEmpty(entitySearch.getAname())){
			sb.append(" and w.aname='" + entitySearch.getAname() + "'");
		}
		if(entitySearch.getCreationDate()!=null){
			sb.append(" and w.creationDate='" + entitySearch.getCreationDate() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getDynamiclibraryUrl())){
			sb.append(" and w.dynamiclibraryUrl='" + entitySearch.getDynamiclibraryUrl() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getCodeUrl())){
			sb.append(" and w.codeUrl='" + entitySearch.getCodeUrl() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getAlgorithmDescriptions())){
			sb.append(" and w.algorithmDescriptions='" + entitySearch.getAlgorithmDescriptions() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getInputParameterDescription())){
			sb.append(" and w.inputParameterDescription='" + entitySearch.getInputParameterDescription() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getOutputParameterDescription())){
			sb.append(" and w.outputParameterDescription='" + entitySearch.getOutputParameterDescription() + "'");
		}

		String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());		
		return super.findPage(hql, page, needSearchCount);
	}
	
	public List<AlgorithmsInfo> listPage(AlgorithmsInfo entity, Page page) throws BusinessException {
			PageData pd = new PageData();
			pd.put("entity", entity);
			page.setPd(pd);
			List<AlgorithmsInfo> list=  algorithmsInfoMapper.listPage(page); 
			return list;
	}
	  
	/**
	 * 
	 * <li>方法名：insert
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<AlgorithmsInfo>
	 * <li>说明：新增、插入操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
  	@Override
  	@Transactional(rollbackFor = { Exception.class })
	public AlgorithmsInfo insert(AlgorithmsInfo entity) throws BusinessException {	
  		entity.setCreationDate(new Date());
		entity =  super.insert(entity);
		return entity;
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public void insertMybatis(AlgorithmsInfo entity) throws BusinessException {		
		//entity.setId(GUIDHexGenerator.getInstance().generateId());
		algorithmsInfoMapper.insert(entity); 
	}
	
    @Transactional(rollbackFor = { Exception.class })
	public void  updateMybatis(AlgorithmsInfo entity) throws BusinessException {
		algorithmsInfoMapper.update(entity); 
	}

    /**
	 * 
	 * <li>方法名：update
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<AlgorithmsInfo>
	 * <li>说明：修改操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
   @Override
   @Transactional(rollbackFor = { Exception.class })
	public AlgorithmsInfo  update(AlgorithmsInfo entity) throws BusinessException {
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
	public void remove(AlgorithmsInfo entity) {
		baseDao.delete(entity);
	}
	@Transactional(rollbackFor = { Exception.class })
	 public  void delete(Object entity,String id) throws BusinessException {
			entity=this.baseDao.get(id, entity.getClass());
			this.baseDao.delete(entity);
	 }
	 
	 @Transactional(rollbackFor = { Exception.class })
	 public  void deleteMybatis(Object entity,String id) throws BusinessException {
			algorithmsInfoMapper.delete(id);
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
	public void checkUnique(AlgorithmsInfo entity) throws BusinessException {
		
	}
	 

}

