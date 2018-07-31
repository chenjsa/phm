package com.pms.socket.client;

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
		SendMsgManager sendMsgManager = (SendMsgManager)SpringContextUtil.getBean("sendMsgManager");
		sendMsgManager.sendMsg(msg);
	}

}
