package com.pms.rcm.phmtask.manager;
import java.io.Serializable; 
import java.util.List; 
 
import com.pms.rcm.phmtask.vo.SubsystemStateInfo; 
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
 * Service object for domain model class SubsystemStateInfo.
 * @see com.pms.rcm.phmtask.vo.SubsystemStateInfo
 * @author bao.zhou
 */
@Service("subsystemStateInfoManager")
public class SubsystemStateInfoManager extends BaseManager<SubsystemStateInfo>{ 
 	 @Autowired
     private SubsystemStateInfoMapper subsystemStateInfoMapper;
   	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<SubsystemStateInfo>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    @Override
	public SubsystemStateInfo get(Serializable id) throws BusinessException {
		SubsystemStateInfo entity = super.get(id); 
		return entity;
	}
	public SubsystemStateInfo getByMybatis(String id) throws BusinessException {
		SubsystemStateInfo entity = subsystemStateInfoMapper.get(id); 
		return entity;
	} 
   	/**
	 * 
	 * <li>方法名：findByIds
	 * <li>@param ids
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<SubsystemStateInfo>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<SubsystemStateInfo> findByIds(String[] ids) throws BusinessException{
		if(ids.length ==1 && ids[0].indexOf(",") != -1){
			ids = ids[0].split(",");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("from SubsystemStateInfo where id in(''");
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
	 * <li>返回类型：List<SubsystemStateInfo>
	 * <li>说明：分页查询
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    public List<SubsystemStateInfo> findPage(SubsystemStateInfo entitySearch, Page page,
			boolean needSearchCount) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from SubsystemStateInfo w where 1=1 ");   
		 
		if(StrUtil.isNotEmpty(entitySearch.getDataClassInfoId())){
			sb.append(" and w.dataClassInfoId='" + entitySearch.getDataClassInfoId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getStatusEnName())){
			sb.append(" and w.statusEnName='" + entitySearch.getStatusEnName() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getStatusChName())){
			sb.append(" and w.statusChName='" + entitySearch.getStatusChName() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getStatusValue())){
			sb.append(" and w.statusValue='" + entitySearch.getStatusValue() + "'");
		}
		if(entitySearch.getDataLength()!=null){
			sb.append(" and w.dataLength='" + entitySearch.getDataLength() + "'");
		}
		if(entitySearch.getStatusBitNumber()!=null){
			sb.append(" and w.statusBitNumber='" + entitySearch.getStatusBitNumber() + "'");
		}

		String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());		
		return super.findPage(hql, page, needSearchCount);
	}
	
	public List<SubsystemStateInfo> listPage(SubsystemStateInfo entity, Page page) throws BusinessException {
			PageData pd = new PageData();
			pd.put("entity", entity);
			page.setPd(pd);
			List<SubsystemStateInfo> list=  subsystemStateInfoMapper.listPage(page); 
			return list;
	}
	  
	/**
	 * 
	 * <li>方法名：insert
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<SubsystemStateInfo>
	 * <li>说明：新增、插入操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
  	@Override
  	@Transactional(rollbackFor = { Exception.class })
	public SubsystemStateInfo insert(SubsystemStateInfo entity) throws BusinessException {		
		entity =  super.insert(entity);
		return entity;
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public void insertMybatis(SubsystemStateInfo entity) throws BusinessException {		
		entity.setId(GUIDHexGenerator.getInstance().generateId());
		subsystemStateInfoMapper.insert(entity); 
	}
	
    @Transactional(rollbackFor = { Exception.class })
	public void  updateMybatis(SubsystemStateInfo entity) throws BusinessException {
		subsystemStateInfoMapper.update(entity); 
	}

    /**
	 * 
	 * <li>方法名：update
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<SubsystemStateInfo>
	 * <li>说明：修改操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
   @Override
   @Transactional(rollbackFor = { Exception.class })
	public SubsystemStateInfo  update(SubsystemStateInfo entity) throws BusinessException {
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
	public void remove(SubsystemStateInfo entity) {
		baseDao.delete(entity);
	}
	@Transactional(rollbackFor = { Exception.class })
	 public  void delete(Object entity,String id) throws BusinessException {
			entity=this.baseDao.get(id, entity.getClass());
			this.baseDao.delete(entity);
	 }
	 
	 @Transactional(rollbackFor = { Exception.class })
	 public  void deleteMybatis(Object entity,String id) throws BusinessException {
			subsystemStateInfoMapper.delete(id);
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
	public void checkUnique(SubsystemStateInfo entity) throws BusinessException {
		
	}
	 

}

