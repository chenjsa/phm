package com.pms.rcm.radar.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.radar.vo.RadarSubsystemInfo; 
@Mapper
public interface RadarSubsystemInfoMapper {

    List<RadarSubsystemInfo> getAll();

    RadarSubsystemInfo get(String id);

    void insert(RadarSubsystemInfo entity);

    void update(RadarSubsystemInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<RadarSubsystemInfo> listPage(Page page);
}
