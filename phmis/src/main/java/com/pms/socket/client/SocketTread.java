package com.pms.socket.client;

import java.io.IOException;

import com.pms.base.JdbcHelper;
import com.pms.base.util.SpringContextUtil;
import com.pms.rcm.maintain.manager.SystemParametersInfoManager;
import com.pms.rcm.maintain.vo.SystemParametersInfo;

public class SocketTread  implements Runnable {  
	private PacketMsg pkt;
	 

	public PacketMsg getPkt() {
		return pkt;
	}

	public void setPkt(PacketMsg pkt) {
		this.pkt = pkt;
	}

	public SocketTread() { 
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		JdbcHelper jdbcHelper = (JdbcHelper)SpringContextUtil.getBean("jdbcHelper");
		try { 
			/*  pkt.cmd =3;// (byte)(3);
	    	  pkt.code=4;// (byte)(4);    	      	 
	    	  pkt.len =1;///(short) (pkt.data.length);///1 ///长度都是1，
	    	  pkt.sdata=2;*/
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
			  new UdpClient(pkt,DEST_IP,DEST_PORT).init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

}
