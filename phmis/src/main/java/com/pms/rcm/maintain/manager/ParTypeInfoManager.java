package com.pms.rcm.maintain.manager;

import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.base.BaseManager;
import com.pms.base.common.BusinessException;
import com.pms.base.util.Page;
import com.pms.base.util.StrUtil;
import com.pms.rcm.maintain.vo.ParTypeInfo;
import com.pms.rcm.sys.vo.UserTypeInfo;

@Service("parTypeInfoManager")
public class ParTypeInfoManager extends BaseManager<ParTypeInfo> {

	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ParTypeInfo>
	 * <li>说明：
	 * <li>创建人：zhangling
	 * <li>创建日期：2018-07-23
	 * <li>修改人：
	 * <li>修改日期：
	 */
	@Override
	public ParTypeInfo get(Serializable id) throws BusinessException {
		ParTypeInfo entity = super.get(id);
		return entity;
	}

	/**
	 * 
	 * <li>方法名：findPage
	 * <li>@param entitySearch，page，needSearchCount
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<ParTypeInfo>
	 * <li>说明：分页查询
	 * <li>创建人：zhangling
	 * <li>创建日期：2018-07-23
	 * <li>修改人：
	 * <li>修改日期：
	 */
	public List<ParTypeInfo> findPage(ParTypeInfo entitySearch, Page page, boolean needSearchCount)
			throws BusinessException {
		StringBuffer sb = new StringBuffer();
		sb.append("from ParTypeInfo w where 1=1 ");
		if(StrUtil.isNotEmpty(entitySearch.getTypeName())){
			sb.append(" and Parameters_type_name = '"+entitySearch.getTypeName()+"'");
		}
		String hql = sb.toString();
		return super.findPage(hql, page, needSearchCount);
	}

	/**
	 * 
	 * <li>方法名：insert
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<UserTypeInfo>
	 * <li>说明：新增、插入操作
	 * <li>创建人：张凌
	 * <li>创建日期：2018-04-19
	 * <li>修改人：
	 * <li>修改日期：
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public ParTypeInfo insert(ParTypeInfo entity) throws BusinessException {
		entity = super.insert(entity);
		return entity;
	}

	@Transactional(rollbackFor = { Exception.class })
	public ParTypeInfo update(ParTypeInfo entity) throws BusinessException {
		entity = super.update(entity);
		return entity;
	}

	@Transactional(rollbackFor = { Exception.class })
	public void delete(Object entity, String id) throws BusinessException {
		entity = this.baseDao.get(id, entity.getClass());
		this.baseDao.delete(entity);
	}

	@Override
	protected void checkDelete(Serializable id) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void checkUnique(ParTypeInfo entity) throws BusinessException {
		// TODO Auto-generated method stub

	}

}
