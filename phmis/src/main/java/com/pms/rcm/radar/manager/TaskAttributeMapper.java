package com.pms.rcm.radar.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.radar.vo.TaskAttribute; 
@Mapper
public interface TaskAttributeMapper {

    List<TaskAttribute> getAll();

    TaskAttribute get(String id);

    void insert(TaskAttribute entity);

    void update(TaskAttribute entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<TaskAttribute> listPage(Page page);
}
