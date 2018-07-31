package com.pms.rcm.radar.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.radar.vo.SubsystemInfo; 
@Mapper
public interface SubsystemInfoMapper {

    List<SubsystemInfo> getAll();

    SubsystemInfo get(String id);

    void insert(SubsystemInfo entity);

    void update(SubsystemInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<SubsystemInfo> listPage(Page page);
}
