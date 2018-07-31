package com.pms.rcm.phmtask.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.phmtask.vo.RadarDatafileTypeInfo; 
@Mapper
public interface RadarDatafileTypeInfoMapper {

    List<RadarDatafileTypeInfo> getAll();

    RadarDatafileTypeInfo get(String id);

    void insert(RadarDatafileTypeInfo entity);

    void update(RadarDatafileTypeInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<RadarDatafileTypeInfo> listPage(Page page);
}
