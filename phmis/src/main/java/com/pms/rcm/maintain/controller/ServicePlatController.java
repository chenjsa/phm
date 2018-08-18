package com.pms.rcm.maintain.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
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

import com.pms.base.JdbcHelper;
import com.pms.base.util.SpringContextUtil;
import com.pms.rcm.maintain.manager.SystemParametersInfoManager;
import com.pms.socket.client.PacketMsg;
import com.pms.socket.client.SocketTread;
import com.pms.socket.client.UdpClient;

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
	@RequestMapping(value = "/postCommand/{type}",method=RequestMethod.GET)
	@ApiOperation(value = "信息发送", notes = "根据页面传入的id信息获取IP，端口号和命令内容", httpMethod = "GET")
	public Object postCommand(@PathVariable("type") String type) throws Exception {
		
		String errInfo = "success";
		String msg = "";
		try {
			/*// 获取系统参数中配置的socketServer的IP地址
			SystemParametersInfo serIpEntity =  this.systemParametersInfoManager.get(serIPId);
			// 获取系统参数表中配置的socketServer的端口号
			SystemParametersInfo serPointEntity = this.systemParametersInfoManager.get(serPointId);
			Integer serPoint = Integer.valueOf(serPointEntity.getParametersValues());
			// 获取系统参数表中配置的socketServer的命令内容
			SystemParametersInfo commandCodeEntity = this.systemParametersInfoManager.get(commandId);
			//System.out.println("IP:"+serIpEntity.getParametersValues()+"|point:"+serPoint+"|value:"+commandCodeEntity.getParametersValues());
			this.socketClient(serIpEntity.getParametersValues(), serPoint, commandCodeEntity.getParametersValues());*/
			
			///采用线程发送避免卡顿 
			SocketTread tre=new SocketTread();
			PacketMsg pkt=new PacketMsg();
			///启动服务
			if(type.equals("1")){
				pkt.cmd =1;// (byte)(3);
				pkt.code=2;// (byte)(4);    	      	 
				pkt.len =0;///(short) (pkt.data.length);///1 ///长度都是1，
				//pkt.sdata=2; 
			}else if(type.equals("2")){
				pkt.cmd =1;// (byte)(3);
				pkt.code=3;// (byte)(4);    	      	 
				pkt.len =0;///(short) (pkt.data.length);///1 ///长度都是1，
				//pkt.sdata=2; 
			}else  if(type.equals("3")){
				pkt.cmd =1;// (byte)(3);
				pkt.code=6;// (byte)(4);    	      	 
				pkt.len =0;///(short) (pkt.data.length);///1 ///长度都是1，
				//pkt.sdata=2; 
			}
		  
	    	tre.setPkt(pkt);
			Thread thread = new Thread(tre); 
			thread.start(); 
			
			if(type.equals("1")){
			
				///采用线程发送避免卡顿 
				SocketTread tre1=new SocketTread();
				PacketMsg pkt1=new PacketMsg();
				pkt1.cmd =3;// (byte)(3);
				pkt1.code=1;// (byte)(4);    
				pkt1.data="ABCDEF".getBytes();
				pkt1.len =6;///(short) (pkt.data.length);///1 ///长度都是1，
				//pkt.sdata=2; 
				tre1.setPkt(pkt1);
				Thread thread1 = new Thread(tre1); 
				thread1.sleep(2000);
				thread1.start();
			}
			
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
	
	private PacketMsg pkt;
	 

	public PacketMsg getPkt() {
		return pkt;
	}

	public void setPkt(PacketMsg pkt) {
		this.pkt = pkt;
	}
	@ResponseBody
	@RequestMapping(value = "/getServer",method=RequestMethod.GET)
	@ApiOperation(value = "信息发送", notes = "根据页面传入的id信息获取IP，端口号和命令内容", httpMethod = "GET")
	public Object init() throws Exception {
		 PacketMsg pkt1=new PacketMsg();
		// TODO Auto-generated method stub
				JdbcHelper jdbcHelper = (JdbcHelper)SpringContextUtil.getBean("jdbcHelper");
				try { 
					  PacketMsg pkt=new PacketMsg();
					  pkt.cmd =1;// (byte)(3);
			    	  pkt.code=1;// (byte)(4);    	      	 
			    	  pkt.len =0;///(short) (pkt.data.length);///1 ///长度都是1 
			    	  int DEST_PORT=5678;
			    	  String DEST_IP="127.0.0.1";
			    	  String port=jdbcHelper.getString("select Parameters_values from system_parameters_info where System_parameters_code='udpport'");
			    	  if(port!=null){
			    		  DEST_PORT=Integer.valueOf(port);
			    	  }
			    	  String ip=jdbcHelper.getString("select Parameters_values from system_parameters_info where System_parameters_code='udpip'");
			    	  if(ip!=null){
			    		  DEST_IP=ip;
			    	  }
			    	  UdpClient client= new UdpClient(pkt,DEST_IP,DEST_PORT);
			    	  client.init();
			    	 
			    	  DatagramPacket inPacket=client.getInPacket();
			    	  ByteArrayInputStream bais = new ByteArrayInputStream(inPacket.getData());
			  		  DataInputStream dis = new DataInputStream(bais);
			  	
			  		try { 
			  			pkt1.cmd = dis.readByte();
			  			pkt1.code = dis.readByte();
			  			pkt1.seq = dis.readShort();
			  			pkt1.len = dis.readShort() ;
			  			pkt1.sdata=dis.readByte();
			  			pkt1.address=inPacket.getAddress().toString();
			  			pkt1.port=inPacket.getPort();
			  		}catch(IOException e){
			  			e.printStackTrace(); 
			  		}			  		
			  		pkt1.len += 6; //add the head length
			  		System.out.println("recv proc: cmd=" + pkt1.cmd + ", code=" + pkt1.code +", seq="+pkt1.seq +", len="+pkt1.len+",sdata="+pkt1.sdata);
			  	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
		
				return pkt1;

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
