package com.pms.rcm.phmtask.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.phmtask.vo.RealtimeDatastructure; 
@Mapper
public interface RealtimeDatastructureMapper {

    List<RealtimeDatastructure> getAll();

    RealtimeDatastructure get(String id);

    void insert(RealtimeDatastructure entity);

    void update(RealtimeDatastructure entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<RealtimeDatastructure> listPage(Page page);
}
