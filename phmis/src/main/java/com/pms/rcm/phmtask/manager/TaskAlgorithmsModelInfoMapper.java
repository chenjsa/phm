package com.pms.rcm.phmtask.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.phmtask.vo.TaskAlgorithmsModelInfo; 
@Mapper
public interface TaskAlgorithmsModelInfoMapper {

    List<TaskAlgorithmsModelInfo> getAll();

    TaskAlgorithmsModelInfo get(String id);

    void insert(TaskAlgorithmsModelInfo entity);

    void update(TaskAlgorithmsModelInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<TaskAlgorithmsModelInfo> listPage(Page page);
}
