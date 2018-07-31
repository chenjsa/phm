package com.pms.rcm.maintain.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.rcm.maintain.manager.SystemParametersInfoManager;
import com.pms.rcm.maintain.vo.SystemParametersInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/servicePlatControl")
@Api("远程操作服务平台程序")
public class ServicePlatController{

	
	
	@Resource(name="systemParametersInfoManager")
	private SystemParametersInfoManager systemParametersInfoManager;

	/**
	 * 获取系统参数中的command发向SocketServer
	 * 
	 * @项目名称
	 * @ProjectName Bank Operation Supervise System
	 * @author zhangling
	 * @date 2018-07-29
	 * @fileName ServicePlatController.java
	 */
	@ResponseBody
	@RequestMapping(value = "/postCommand/{serIPId}/{serPointId}/{commandId}",method=RequestMethod.GET)
	@ApiOperation(value = "信息发送", notes = "根据页面传入的id信息获取IP，端口号和命令内容", httpMethod = "GET")
	public Object postCommand(@PathVariable("serIPId") String serIPId,@PathVariable("serPointId") String serPointId,@PathVariable("commandId")  String commandId) throws Exception {
		
		String errInfo = "success";
		String msg = "";
		try {
			// 获取系统参数中配置的socketServer的IP地址
			SystemParametersInfo serIpEntity =  this.systemParametersInfoManager.get(serIPId);
			// 获取系统参数表中配置的socketServer的端口号
			SystemParametersInfo serPointEntity = this.systemParametersInfoManager.get(serPointId);
			Integer serPoint = Integer.valueOf(serPointEntity.getParametersValues());
			// 获取系统参数表中配置的socketServer的命令内容
			SystemParametersInfo commandCodeEntity = this.systemParametersInfoManager.get(commandId);
			//System.out.println("IP:"+serIpEntity.getParametersValues()+"|point:"+serPoint+"|value:"+commandCodeEntity.getParametersValues());
			this.socketClient(serIpEntity.getParametersValues(), serPoint, commandCodeEntity.getParametersValues());
			msg = "命令发送成功!";
			

		} catch (Exception e) {
			errInfo = "err";
			e.printStackTrace();
			msg = "操作出错：" + e.getLocalizedMessage();
			System.out.println(msg);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", errInfo);
		map.put("msg", msg);
		return map;

	}

	/**
	 * 创建一个socket Client
	 * 
	 * @项目名称
	 * @ProjectName Bank Operation Supervise System
	 * @author zhangling
	 * @date 2018-07-29
	 * @fileName ServicePlatController.java
	 */

	public void socketClient(String serverIp, Integer serverPoint, String command) {

		try {
			Socket sc = new Socket(serverIp, serverPoint);
			OutputStream os = sc.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write(command);
			pw.flush();
			// 关闭输出流
			sc.shutdownOutput();
			// 关闭资源
			pw.close();
			os.close();
			sc.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
