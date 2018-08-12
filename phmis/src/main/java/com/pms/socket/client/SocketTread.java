package com.pms.socket.client;

import java.io.IOException;

import com.pms.base.util.SpringContextUtil;
import com.pms.rcm.sys.manager.EmailManager;

public class SocketTread  implements Runnable {  
	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public SocketTread() { 
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
/*		SendMsgManager sendMsgManager = (SendMsgManager)SpringContextUtil.getBean("sendMsgManager");
		sendMsgManager.sendMsg(msg);*/
		 try {
			new UdpClient().init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

}
