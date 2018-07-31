package com.pms.rcm.radar.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.radar.vo.RadarStateInfo; 
@Mapper
public interface RadarStateInfoMapper {

    List<RadarStateInfo> getAll();

    RadarStateInfo get(String id);

    void insert(RadarStateInfo entity);

    void update(RadarStateInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<RadarStateInfo> listPage(Page page);
}
