package com.pms.rcm.radar.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.radar.vo.RadarStateInfoValue; 
@Mapper
public interface RadarStateInfoValueMapper {

    List<RadarStateInfoValue> getAll();

    RadarStateInfoValue get(String id);

    void insert(RadarStateInfoValue entity);

    void update(RadarStateInfoValue entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<RadarStateInfoValue> listPage(Page page);
}
