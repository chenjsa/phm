package com.pms.rcm.phmtask.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.phmtask.vo.PhmtaskInfo; 
@Mapper
public interface PhmtaskInfoMapper {

    List<PhmtaskInfo> getAll();

    PhmtaskInfo get(String id);

    void insert(PhmtaskInfo entity);

    void update(PhmtaskInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<PhmtaskInfo> listPage(Page page);
}
