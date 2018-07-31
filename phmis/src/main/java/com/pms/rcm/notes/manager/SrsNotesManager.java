package com.pms.rcm.notes.manager;
import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.base.BaseManager;
import com.pms.base.common.BusinessException;
import com.pms.base.util.Page;
import com.pms.base.util.StrUtil;
import com.pms.rcm.notes.vo.SrsNotes; 
/**
 * Service object for domain model class SrsNotes.
 * @see com.santai.rcm.vo.SrsNotes
 * @author bao.zhou
 */
@Service("srsNotesManager")
public class SrsNotesManager extends BaseManager<SrsNotes>{ 
   	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<SrsNotes>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2013-09-18
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    @Override
	public SrsNotes get(Serializable id) throws BusinessException {
		SrsNotes entity = super.get(id); 
		return entity;
	}
   	/**
	 * 
	 * <li>方法名：findByIds
	 * <li>@param ids
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<SrsNotes>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2013-09-18
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<SrsNotes> findByIds(String[] ids) throws BusinessException{
		if(ids.length ==1 && ids[0].indexOf(",") != -1){
			ids = ids[0].split(",");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("from SrsNotes where id in(''");
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
	 * <li>返回类型：List<SrsNotes>
	 * <li>说明：分页查询
	 * <li>创建人：周暴
	 * <li>创建日期：2013-09-18
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    public List<SrsNotes> findPage(SrsNotes entitySearch, Page page,
			boolean needSearchCount) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from SrsNotes w where 1=1 ");   
		 
		if(StrUtil.isNotEmpty(entitySearch.getNotesCode())){
			sb.append(" and w.notesCode='" + entitySearch.getNotesCode() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getNotesType())){
			sb.append(" and w.notesType='" + entitySearch.getNotesType() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getNotesContent())){
			sb.append(" and w.notesContent like '%" + entitySearch.getNotesContent() + "%'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getNotesIsRead())){
			sb.append(" and w.notesIsRead='" + entitySearch.getNotesIsRead() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getNotesReceiverId())){
			sb.append(" and w.notesReceiverId='" + entitySearch.getNotesReceiverId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getNotesReceiverName())){
			sb.append(" and w.notesReceiverName='" + entitySearch.getNotesReceiverName() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getNotesSenderId())){
			sb.append(" and w.notesSenderId='" + entitySearch.getNotesSenderId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getNotesSenderName())){
			sb.append(" and w.notesSenderName='" + entitySearch.getNotesSenderName() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getNotesGenTime())){
			sb.append(" and w.notesGenTime='" + entitySearch.getNotesGenTime() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getNotesReadTime())){
			sb.append(" and w.notesReadTime='" + entitySearch.getNotesReadTime() + "'");
		}
		if(null==page.getSidx() || page.getSidx().equals("")){
			sb.append(" order by w.notesGenTime DESC");
		}

		String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());		
		return super.findPage(hql, page, needSearchCount);
	}
	  
	/**
	 * 
	 * <li>方法名：insert
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<SrsNotes>
	 * <li>说明：新增、插入操作
	 * <li>创建人：周暴
	 * <li>创建日期：2013-09-18
	 * <li>修改人： 
	 * <li>修改日期：
	 */
  	@Override
  	@Transactional(rollbackFor = { Exception.class })
	public SrsNotes insert(SrsNotes entity) throws BusinessException {		
		entity =  super.insert(entity);
		return entity;
	}

    /**
	 * 
	 * <li>方法名：update
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<SrsNotes>
	 * <li>说明：修改操作
	 * <li>创建人：周暴
	 * <li>创建日期：2013-09-18
	 * <li>修改人： 
	 * <li>修改日期：
	 */
   @Override
   @Transactional(rollbackFor = { Exception.class })
	public SrsNotes  update(SrsNotes entity) throws BusinessException {
		entity = super.update(entity);
		return entity;
	}
   
   @Transactional(rollbackFor = { Exception.class })
	public void  updateNotesIsRead(List<SrsNotes> srsNotesList,String notesIsRead) throws BusinessException {
	   for(SrsNotes srsNotes:srsNotesList){
			srsNotes.setNotesIsRead(notesIsRead);
			srsNotes.setNotesReadTime(StrUtil.getDateString("yyyy-MM-dd HH:mm:ss"));
			this.update(srsNotes); 
		}
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
	public void remove(SrsNotes entity) {
		baseDao.delete(entity);
	}
	@Transactional(rollbackFor = { Exception.class })
	 public  void delete(Object entity,String id) throws BusinessException {
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
	public void checkUnique(SrsNotes entity) throws BusinessException {
		
	}
	 

}

