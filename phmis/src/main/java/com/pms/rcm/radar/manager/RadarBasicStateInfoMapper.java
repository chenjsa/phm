package com.pms.rcm.radar.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.radar.vo.RadarBasicStateInfo; 
@Mapper
public interface RadarBasicStateInfoMapper {

    List<RadarBasicStateInfo> getAll();

    RadarBasicStateInfo get(String id);

    void insert(RadarBasicStateInfo entity);

    void update(RadarBasicStateInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<RadarBasicStateInfo> listPage(Page page);
}
