package com.pms.rcm.phmtask.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.phmtask.vo.TaskResult; 
@Mapper
public interface TaskResultMapper {

    List<TaskResult> getAll();

    TaskResult get(String id);

    void insert(TaskResult entity);

    void update(TaskResult entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<TaskResult> listPage(Page page);
}
