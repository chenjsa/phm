package com.pms.rcm.sys.manager;
import java.io.Serializable; 
import java.util.List; 
 
import com.pms.rcm.sys.vo.PostInfo; 
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
 * Service object for domain model class PostInfo.
 * @see com.pms.rcm.sys.vo.PostInfo
 * @author bao.zhou
 */
@Service("postInfoManager")
public class PostInfoManager extends BaseManager<PostInfo>{ 
 	 @Autowired
     private PostInfoMapper postInfoMapper;
   	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<PostInfo>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    @Override
	public PostInfo get(Serializable id) throws BusinessException {
		PostInfo entity = super.get(id); 
		return entity;
	}
	public PostInfo getByMybatis(String id) throws BusinessException {
		PostInfo entity = postInfoMapper.get(id); 
		return entity;
	} 
   	/**
	 * 
	 * <li>方法名：findByIds
	 * <li>@param ids
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<PostInfo>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<PostInfo> findByIds(String[] ids) throws BusinessException{
		if(ids.length ==1 && ids[0].indexOf(",") != -1){
			ids = ids[0].split(",");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("from PostInfo where id in(''");
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
	 * <li>返回类型：List<PostInfo>
	 * <li>说明：分页查询
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    public List<PostInfo> findPage(PostInfo entitySearch, Page page,
			boolean needSearchCount) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from PostInfo w where 1=1 ");   
		 
		if(StrUtil.isNotEmpty(entitySearch.getName())){
			sb.append(" and w.name='" + entitySearch.getName() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getDescription())){
			sb.append(" and w.description='" + entitySearch.getDescription() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getOther())){
			sb.append(" and w.other='" + entitySearch.getOther() + "'");
		}

		String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());		
		return super.findPage(hql, page, needSearchCount);
	}
	
	public List<PostInfo> listPage(PostInfo entity, Page page) throws BusinessException {
			PageData pd = new PageData();
			pd.put("entity", entity);
			page.setPd(pd);
			List<PostInfo> list=  postInfoMapper.listPage(page); 
			return list;
	}
	  
	/**
	 * 
	 * <li>方法名：insert
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<PostInfo>
	 * <li>说明：新增、插入操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
  	@Override
  	@Transactional(rollbackFor = { Exception.class })
	public PostInfo insert(PostInfo entity) throws BusinessException {		
		entity =  super.insert(entity);
		return entity;
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public void insertMybatis(PostInfo entity) throws BusinessException {		
		entity.setId(GUIDHexGenerator.getInstance().generateId());
		postInfoMapper.insert(entity); 
	}
	
    @Transactional(rollbackFor = { Exception.class })
	public void  updateMybatis(PostInfo entity) throws BusinessException {
		postInfoMapper.update(entity); 
	}

    /**
	 * 
	 * <li>方法名：update
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<PostInfo>
	 * <li>说明：修改操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
   @Override
   @Transactional(rollbackFor = { Exception.class })
	public PostInfo  update(PostInfo entity) throws BusinessException {
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
	public void remove(PostInfo entity) {
		baseDao.delete(entity);
	}
	@Transactional(rollbackFor = { Exception.class })
	 public  void delete(Object entity,String id) throws BusinessException {
			entity=this.baseDao.get(id, entity.getClass());
			this.baseDao.delete(entity);
	 }
	 
	 @Transactional(rollbackFor = { Exception.class })
	 public  void deleteMybatis(Object entity,String id) throws BusinessException {
			postInfoMapper.delete(id);
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
	public void checkUnique(PostInfo entity) throws BusinessException {
		
	}
	 

}

