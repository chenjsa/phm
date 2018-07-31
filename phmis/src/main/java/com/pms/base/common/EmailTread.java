package com.pms.base.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pms.base.BaseDao;
import com.pms.base.util.SpringContextUtil;
import com.pms.rcm.sys.manager.EmailManager; 
public class EmailTread implements Runnable {  
	private Email email;
	
	public Email getEmail() {
		return email;
	}

	public void setEmail(com.pms.base.common.Email email) {
		this.email = email;
	}

	public EmailTread() { 
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		EmailManager emailManager = (EmailManager)SpringContextUtil.getBean("emailManager");
		emailManager.sendSimpleEmail(email.getEmailTo(), email.getTitle(), email.getContent());

	}

}
