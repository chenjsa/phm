package com.pms.rcm.test.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.alibaba.dubbo.config.annotation.Service;
import com.pms.base.util.Page;
import com.pms.rcm.test.vo.Test; 
 
public interface TestMapper {

    List<Test> getAll();

    Test get(String id);

    void insert(Test entity);

    void update(Test entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<Test> listPage(Page page);
}
