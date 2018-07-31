package com.pms.rcm.maintain.manager;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.maintain.vo.LogInfo;

@Mapper
public interface LogInfoMapper {

    List<LogInfo> getAll();

    LogInfo get(String id);

    void insert(LogInfo entity);

    void update(LogInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<LogInfo> listPage(Page page);
}
