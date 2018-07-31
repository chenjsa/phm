package com.pms.activiti.manager;

import org.apache.ibatis.annotations.Mapper;

import com.pms.base.util.PageData;
 
public interface ModelManager{
	
	/**部署成功后更新DeploymentID
	 * @param pd
	 * @throws Exception
	 */
	public void updateDeploymentID(PageData pd)throws Exception;
	
}

