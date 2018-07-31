package com.pms.rcm.radar.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.radar.vo.ModuleNumberInfo; 
@Mapper
public interface ModuleNumberInfoMapper {

    List<ModuleNumberInfo> getAll();

    ModuleNumberInfo get(String id);

    void insert(ModuleNumberInfo entity);

    void update(ModuleNumberInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<ModuleNumberInfo> listPage(Page page);
}
