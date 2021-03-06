package com.pms.rcm.radar.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.radar.vo.RadarDeviceInfo; 
@Mapper
public interface RadarDeviceInfoMapper {

    List<RadarDeviceInfo> getAll();

    RadarDeviceInfo get(String id);

    void insert(RadarDeviceInfo entity);

    void update(RadarDeviceInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<RadarDeviceInfo> listPage(Page page);
}
