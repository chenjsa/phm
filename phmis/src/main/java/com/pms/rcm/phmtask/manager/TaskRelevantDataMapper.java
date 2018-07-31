package com.pms.rcm.phmtask.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.phmtask.vo.TaskRelevantData; 
@Mapper
public interface TaskRelevantDataMapper {

    List<TaskRelevantData> getAll();

    TaskRelevantData get(String id);

    void insert(TaskRelevantData entity);

    void update(TaskRelevantData entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<TaskRelevantData> listPage(Page page);
}
