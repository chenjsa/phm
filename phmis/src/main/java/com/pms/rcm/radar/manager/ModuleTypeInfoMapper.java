package com.pms.rcm.radar.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.radar.vo.ModuleTypeInfo; 
@Mapper
public interface ModuleTypeInfoMapper {

    List<ModuleTypeInfo> getAll();

    ModuleTypeInfo get(String id);

    void insert(ModuleTypeInfo entity);

    void update(ModuleTypeInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<ModuleTypeInfo> listPage(Page page);
}
