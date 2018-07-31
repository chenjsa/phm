package com.pms.rcm.maintain.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.maintain.vo.LogTypeInfo;
@Mapper
public interface LogTypeInfoMapper {

    List<LogTypeInfo> getAll();

    LogTypeInfo get(String id);

    void insert(LogTypeInfo entity);

    void update(LogTypeInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<LogTypeInfo> listPage(Page page);
}
