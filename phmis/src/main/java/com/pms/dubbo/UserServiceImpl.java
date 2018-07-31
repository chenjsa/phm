package com.pms.dubbo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.pms.rcm.test.manager.TestMapper;
import com.pms.rcm.test.vo.Test;

@Service(version = "1.0.0")
public class UserServiceImpl implements UserService { 
	@Autowired
    private TestMapper testMapper;
    public List<Test>  getUserName() {
    	List<Test> list=testMapper.getAll(); 
        return list;
    }
}
