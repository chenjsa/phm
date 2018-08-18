package com.pms.rcm.phmtask.manager;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pms.base.BaseManager;
import com.pms.base.common.BusinessException;
import com.pms.base.util.GUIDHexGenerator;
import com.pms.base.util.Page;
import com.pms.base.util.PageData;
import com.pms.base.util.StrUtil;
import com.pms.rcm.modellib.manager.PhmfunctionTypeInfoManager;
import com.pms.rcm.modellib.vo.KnowledgeBaseInfo;
import com.pms.rcm.modellib.vo.PhmfunctionTypeInfo;
import com.pms.rcm.phmtask.vo.PhmtaskInfo;
import com.pms.rcm.phmtask.vo.TaskAlgorithmsModelInfo;
import com.pms.rcm.phmtask.vo.TaskKnowledgeInfo;
import com.pms.rcm.phmtask.vo.TaskRelevantData;
import com.pms.rcm.radar.manager.RadarDeviceInfoManager;
import com.pms.rcm.radar.vo.RadarDeviceInfo;
import com.pms.socket.client.PacketMsg;
import com.pms.socket.client.SocketTread;

/**
 * Service object for domain model class PhmtaskInfo.
 * @see com.pms.rcm.phmtask.vo.PhmtaskInfo
 * @author bao.zhou
 */
@Service("phmtaskInfoManager")
@CacheConfig(cacheNames = "weather")
public class PhmtaskInfoManager extends BaseManager<PhmtaskInfo>{ 
 	 @Autowired
     private PhmtaskInfoMapper phmtaskInfoMapper;
   	/**
	 * 
	 * <li>方法名：get
	 * <li>@param id
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<PhmtaskInfo>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
    @Override
	public PhmtaskInfo get(Serializable id) throws BusinessException {
		PhmtaskInfo entity = super.get(id); 
	    String hql="from TaskRelevantData where phmTaskId='"+entity.getId()+"'";
	    List<TaskRelevantData> taskRelevantDataSet=(List<TaskRelevantData>)this.baseDao.find(hql);
	    entity.getTaskRelevantDataSet().addAll(taskRelevantDataSet);
	    
	    hql="from  TaskAlgorithmsModelInfo where taskId='"+entity.getId()+"'";
	    List<TaskAlgorithmsModelInfo> taskAlgorithmsModelInfoSet=(List<TaskAlgorithmsModelInfo>)this.baseDao.find(hql);
	    entity.getTaskAlgorithmsModelInfoSet().addAll(taskAlgorithmsModelInfoSet);
	    
	    hql="from KnowledgeBaseInfo k where exists (select 1 from TaskKnowledgeInfo t where t.taskId='"+entity.getId()+"' and t.knowledgeId=k.id)";
	    List<KnowledgeBaseInfo> KnowledgeBaseInfoSet=(List<KnowledgeBaseInfo>)this.baseDao.find(hql);
	    entity.getKnowledgeBaseInfoSet().addAll(KnowledgeBaseInfoSet);
	     
		return entity;
	}
	public PhmtaskInfo getByMybatis(String id) throws BusinessException {
		PhmtaskInfo entity = phmtaskInfoMapper.get(id); 
		return entity;
	} 
   	/**
	 * 
	 * <li>方法名：findByIds
	 * <li>@param ids
	 * <li>@return
	 * <li>@throws BusinessException
	 * <li>返回类型：List<PhmtaskInfo>
	 * <li>说明：
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	@SuppressWarnings("unchecked")
	public List<PhmtaskInfo> findByIds(String[] ids) throws BusinessException{
		if(ids.length ==1 && ids[0].indexOf(",") != -1){
			ids = ids[0].split(",");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("from PhmtaskInfo where id in(''");
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
	 * <li>返回类型：List<PhmtaskInfo>
	 * <li>说明：分页查询
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */ 
	@Cacheable
    public List<PhmtaskInfo> findPage(PhmtaskInfo entitySearch, Page page,
			boolean needSearchCount) throws BusinessException {
		StringBuffer sb = new StringBuffer();		
		sb.append("from PhmtaskInfo w where 1=1 ");   
		 
		if(StrUtil.isNotEmpty(entitySearch.getPhmfunctionTypeId())){
			sb.append(" and w.phmfunctionTypeId='" + entitySearch.getPhmfunctionTypeId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getRadarId())){
			sb.append(" and w.radarId='" + entitySearch.getRadarId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getPhmsubsId())){
			sb.append(" and w.phmsubsId='" + entitySearch.getPhmsubsId() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getModuleId())){
			sb.append(" and w.moduleId='" + entitySearch.getModuleId() + "'");
		}
		if(entitySearch.getTaskDeadline()!=null){
			sb.append(" and w.taskDeadline='" + entitySearch.getTaskDeadline() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getTaskDescription())){
			sb.append(" and w.taskDescription='" + entitySearch.getTaskDescription() + "'");
		}
		if(StrUtil.isNotEmpty(entitySearch.getOther())){
			sb.append(" and w.other='" + entitySearch.getOther() + "'");
		}

		String hql = sb.toString();//this.baseDao.buildHql(entitySearch, "w", sb.toString());		
		return super.findPage(hql, page, needSearchCount);
	}
	
	public List<PhmtaskInfo> listPage(PhmtaskInfo entity, Page page) throws BusinessException {
			PageData pd = new PageData();
			pd.put("entity", entity);
			page.setPd(pd);
			List<PhmtaskInfo> list=  phmtaskInfoMapper.listPage(page); 
			return list;
	}
	  
	/**
	 * 
	 * <li>方法名：insert
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<PhmtaskInfo>
	 * <li>说明：新增、插入操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
  	@Override
  	@Transactional(rollbackFor = { Exception.class })
	public PhmtaskInfo insert(PhmtaskInfo entity) throws BusinessException {		
		entity =  super.insert(entity);
		for(TaskRelevantData taskData:entity.getTaskRelevantDatas()){
			taskData.setPhmTaskId(entity.getId());
			this.baseDao.insert(taskData);
		}
		for(TaskAlgorithmsModelInfo taskAl:entity.getTaskAlgorithmsModelInfos()){
			taskAl.setTaskId(entity.getId());
			this.baseDao.insert(taskAl);
		}
		for(TaskKnowledgeInfo taskKnow:entity.getTaskKnowledgeInfos()){
			taskKnow.setTaskId(entity.getId());
			this.baseDao.insert(taskKnow);
		}
		///采用线程发送避免卡顿
		SocketTread tre=new SocketTread();
		PacketMsg pkt=new PacketMsg();
	    pkt.cmd =3;// (byte)(3);
	    pkt.code=4;// (byte)(4);    	      	 
	    pkt.len =1;///(short) (pkt.data.length);///1 ///长度都是1，
	    pkt.sdata=2; 
    	tre.setPkt(pkt);
		Thread thread = new Thread(tre); 
		thread.start(); 
		return entity;
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public void insertMybatis(PhmtaskInfo entity) throws BusinessException {		
		entity.setId(GUIDHexGenerator.getInstance().generateId());
		phmtaskInfoMapper.insert(entity); 
	}
	
    @Transactional(rollbackFor = { Exception.class })
	public void  updateMybatis(PhmtaskInfo entity) throws BusinessException {
		phmtaskInfoMapper.update(entity); 
	}

    /**
	 * 
	 * <li>方法名：update
	 * <li>@param entity
	 * <li>@return entity
	 * <li>@throws BusinessException
	 * <li>返回类型：List<PhmtaskInfo>
	 * <li>说明：修改操作
	 * <li>创建人：周暴
	 * <li>创建日期：2018-04-19
	 * <li>修改人： 
	 * <li>修改日期：
	 */
   @Override
   @Transactional(rollbackFor = { Exception.class })
	public PhmtaskInfo  update(PhmtaskInfo entity) throws BusinessException {
	    entity = super.update(entity);
	    String hql="delete from TaskRelevantData where phmTaskId='"+entity.getId()+"'";
	    this.baseDao.execute(hql);
	    hql="delete from TaskAlgorithmsModelInfo where taskId='"+entity.getId()+"'";
	    this.baseDao.execute(hql);
	    hql="delete from TaskKnowledgeInfo where taskId='"+entity.getId()+"'";
	    this.baseDao.execute(hql);
		for(TaskRelevantData taskData:entity.getTaskRelevantDatas()){
			taskData.setPhmTaskId(entity.getId());
			this.baseDao.insert(taskData);
		}
		for(TaskAlgorithmsModelInfo taskAl:entity.getTaskAlgorithmsModelInfos()){
			taskAl.setTaskId(entity.getId());
			this.baseDao.insert(taskAl);
		}
		for(TaskKnowledgeInfo taskKnow:entity.getTaskKnowledgeInfos()){
			taskKnow.setTaskId(entity.getId());
			this.baseDao.insert(taskKnow);
		}		
		///采用线程发送避免卡顿 
		SocketTread tre=new SocketTread();
		PacketMsg pkt=new PacketMsg();
	    pkt.cmd =3;// (byte)(3);
	    pkt.code=4;// (byte)(4);    	      	 
	    pkt.len =1;///(short) (pkt.data.length);///1 ///长度都是1，
	    pkt.sdata=2; 
    	tre.setPkt(pkt);
		Thread thread = new Thread(tre); 
		thread.start(); 
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
	public void remove(PhmtaskInfo entity) {
		baseDao.delete(entity);
	}
	@Transactional(rollbackFor = { Exception.class })
	 public  void delete(Object entity,String id) throws BusinessException {
			entity=this.baseDao.get(id, entity.getClass());
			String hql="delete from TaskRelevantData where phmTaskId='"+id+"'";
		    this.baseDao.execute(hql);
		    hql="delete from TaskAlgorithmsModelInfo where taskId='"+id+"'";
		    this.baseDao.execute(hql);
		    hql="delete from TaskKnowledgeInfo where taskId='"+id+"'";
		    this.baseDao.execute(hql);
			this.baseDao.delete(entity);
	 }
	 
	 @Transactional(rollbackFor = { Exception.class })
	 public  void deleteMybatis(Object entity,String id) throws BusinessException {
			phmtaskInfoMapper.delete(id);
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
	public void checkUnique(PhmtaskInfo entity) throws BusinessException {
		
	}
	@Autowired
    private PhmfunctionTypeInfoManager phmfunctionTypeInfoManager;
	@Autowired
	private RadarDeviceInfoManager radarDeviceInfoManager;
	public Map getAll() throws BusinessException {
		Map map=new HashMap();
		List<PhmfunctionTypeInfo> phmfunctionTypeInfos=phmfunctionTypeInfoManager.findAll();
		map.put("phmfunctionTypeInfos", phmfunctionTypeInfos);
		List<RadarDeviceInfo> radarDeviceInfos=radarDeviceInfoManager.findAll();
		map.put("radarDeviceInfos", radarDeviceInfos);
		return map;
	}
	 

}

