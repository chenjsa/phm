package com.pms.rcm.radar.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.radar.vo.RadarTypeInfo; 
@Mapper
public interface RadarTypeInfoMapper {

    List<RadarTypeInfo> getAll();

    RadarTypeInfo get(String id);

    void insert(RadarTypeInfo entity);

    void update(RadarTypeInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<RadarTypeInfo> listPage(Page page);
}
