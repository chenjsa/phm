package com.pms.rcm.radar.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.radar.vo.RadarServiceType; 
@Mapper
public interface RadarServiceTypeMapper {

    List<RadarServiceType> getAll();

    RadarServiceType get(String id);

    void insert(RadarServiceType entity);

    void update(RadarServiceType entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<RadarServiceType> listPage(Page page);
}
