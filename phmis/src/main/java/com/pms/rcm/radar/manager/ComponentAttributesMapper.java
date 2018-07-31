package com.pms.rcm.radar.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.radar.vo.ComponentAttributes; 
@Mapper
public interface ComponentAttributesMapper {

    List<ComponentAttributes> getAll();

    ComponentAttributes get(String id);

    void insert(ComponentAttributes entity);

    void update(ComponentAttributes entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<ComponentAttributes> listPage(Page page);
}
