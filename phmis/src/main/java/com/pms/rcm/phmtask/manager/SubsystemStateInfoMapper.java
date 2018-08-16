package com.pms.rcm.phmtask.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.phmtask.vo.SubsystemStateInfo; 
@Mapper
public interface SubsystemStateInfoMapper {

    List<SubsystemStateInfo> getAll();

    SubsystemStateInfo get(String id);

    void insert(SubsystemStateInfo entity);

    void update(SubsystemStateInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<SubsystemStateInfo> listPage(Page page);
}
