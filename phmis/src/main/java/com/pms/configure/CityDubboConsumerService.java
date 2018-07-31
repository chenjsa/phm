package com.pms.configure;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pms.rcm.test.manager.TestManager;
import com.pms.rcm.test.vo.Test;

@Component
public class CityDubboConsumerService {
	@Reference(version = "1.0.0")
	private TestManager testManager;
	public Test getById(String id){
		Test test=this.testManager.get(id);
		return test;
	}

}
