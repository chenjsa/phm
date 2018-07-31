package com.pms.rcm.doc.manager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.base.BaseManager;
import com.pms.base.common.BusinessException;
import com.pms.base.util.Page;
import com.pms.base.util.StrUtil;
import com.pms.rcm.doc.vo.DocType;
import com.pms.rcm.doc.vo.DocTypeInputElements; 
/**
 * Service object for domain model class DocType.
 * @see com.pms.rcm.doc.vo.DocType
 * @author bao.zhou
 */
@Service("docTypeManager")
public class DocTypeManager extends BaseManager<DocType>{ 
	@Autowired
	private DocTypeInputElementsManager docTypeInputElementsManager;
   	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<DocType>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    @Override
	public DocType get(Serializable id) throws BusinessException {
		DocType entity = super.get(id);
		
		if(entity==null){
			entity=new DocType();
			String hql="from DocType  order by serialNumber desc";
	  		DocType docType=(DocType)this.baseDao.findSingle(hql); 
	  		if(docType!=null){
	  			entity.setCode(StrUtil.addZeroForNum(String.valueOf(docType.getSerialNumber()+1), 5));
	  		}else{
	  			entity.setCode(StrUtil.addZeroForNum(String.valueOf(1), 5));
	  			entity.setSerialNumber(1);
	  		}
	  		DocTypeInputElements doc=new DocTypeInputElements();
	  		List<DocTypeInputElements> docTypeInputElementses=new ArrayList<DocTypeInputElements>();
	  		docTypeInputElementses.add(doc);
	  		entity.setDocTypeInputElementses(docTypeInputElementses);
		}else{			
			String hql="from DocTypeInputElements where docTypeId='"+id+"' order by serialNumber";
			List<DocTypeInputElements> docTypeInputElementses=this.baseDao.find(hql);
			entity.setDocTypeInputElementses(docTypeInputElementses);
		}
		return entity;
	}
   	/**
	 * 
	 * <li>方法名：findByIds
	 * <li>@param ids
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<DocType>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<DocType> findByIds(String[] ids) throws BusinessException{
		if(ids.length ==1 && ids[0].indexOf(",") != -1){
			ids = ids[0].split(",");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("from DocType where id in(''");
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
	 * <li>返回类型：List<DocType>
	 * <li>说明：分页查询
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    public List<DocType> findPage(DocType entitySearch, Page page,
			boolean needSearchCount) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from DocType w where 1=1 ");   
		 
		if(StrUtil.isNotEmpty(entitySearch.getCode())){
			sb.append(" and w.code='" + entitySearch.getCode() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getName())){
			sb.append(" and w.name='" + entitySearch.getName() + "'");
		}

		String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());		
		return super.findPage(hql, page, needSearchCount);
	}
    
    public List<DocType> findAll() throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from DocType order by serialNumber ");    
		String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());		
		List<DocType> docTypeList=this.baseDao.find(hql);
		for(DocType docType:docTypeList){
			hql="from DocTypeInputElements where docTypeId='"+docType.getId()+"'  order by serialNumber ";
			List<DocTypeInputElements> list=this.docTypeInputElementsManager.find(hql);
			docType.setDocTypeInputElementses(list);
		}
		return docTypeList;
	}
	  
	/**
	 * 
	 * <li>方法名：insert
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<DocType>
	 * <li>说明：新增、插入操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
  	@Override
  	@Transactional(rollbackFor = { Exception.class })
	public DocType insert(DocType entity) throws BusinessException {	  	
  		String hql="from DocType  order by serialNumber desc";
  		DocType docType=(DocType)this.baseDao.findSingle(hql); 
  		if(docType!=null){
  			entity.setSerialNumber(docType.getSerialNumber()+1);
  		}else{
  			entity.setSerialNumber(1);
  		}
		entity =  super.insert(entity);
		for(DocTypeInputElements docTypeInputElements:entity.getDocTypeInputElementses()){
			docTypeInputElements.setDocTypeId(entity.getId());
  			docTypeInputElementsManager.insert(docTypeInputElements);
  		}
		return entity;
	}

    /**
	 * 
	 * <li>方法名：update
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<DocType>
	 * <li>说明：修改操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
   @Override
   @Transactional(rollbackFor = { Exception.class })
	public DocType  update(DocType entity) throws BusinessException {
	    String hql="delete from DocTypeInputElements where docTypeId='"+entity.getId()+"' ";
	    this.baseDao.execute(hql);
		entity = super.update(entity); 
		for(DocTypeInputElements docTypeInputElements:entity.getDocTypeInputElementses()){
			docTypeInputElements.setDocTypeId(entity.getId());
  			docTypeInputElementsManager.insert(docTypeInputElements);
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
	public void remove(DocType entity) {
		baseDao.delete(entity);
	}
	@Transactional(rollbackFor = { Exception.class })
	 public  void delete(Object entity,String id) throws BusinessException {
		    String hql="delete from DocTypeInputElements where docTypeId='"+id+"' ";
		    this.baseDao.execute(hql);
			entity=this.baseDao.get(id, entity.getClass());
			this.baseDao.delete(entity);
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
	public void checkUnique(DocType entity) throws BusinessException {
		
	}
	 

}

