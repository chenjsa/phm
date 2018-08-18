package com.pms.rcm.maintain.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pms.rcm.maintain.manager.PlatResourceMonitorManager;
import com.pms.rcm.maintain.vo.PlatResourceInfo;

import io.swagger.annotations.Api;

/**
 * help_info表HelpInfo维护
 * 
 * @项目名称　 
 * @ProjectName Bank Operation Supervise System
 * @author bao.zhou
 * @date 2018-03-07
 * @fileName HelpInfoController.java
 */
@Controller
@RequestMapping(value="/monitor")
@Api("platResourceInfo操作controller")
public class PlatResourceMonitorController{
	
	private static final long serialVersionUID = 376679610738L;  
	
	@Resource(name="platResourceMonitorManager")
	private PlatResourceMonitorManager platResourceMonitorManager;
	
	
	private static String osName = System.getProperty("os.name");
	@ResponseBody
	@RequestMapping(value="/list")
	public PlatResourceInfo getPlatResourceInfo() throws Exception{
		PlatResourceInfo entity = new PlatResourceInfo();
		try{
			System.out.println("OS:"+osName);
			entity.setCpuRate(this.platResourceMonitorManager.cpuRate());
			entity.setDiskRate(this.platResourceMonitorManager.diskRate());
			entity.setMemoryRate(this.platResourceMonitorManager.memoryRate());
			entity.setNetRate(this.platResourceMonitorManager.netRate());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return entity;
	}
	
	
}
