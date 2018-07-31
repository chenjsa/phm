package com.pms.rcm.radar.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.radar.vo.KeyModulesInfo; 
@Mapper
public interface KeyModulesInfoMapper {

    List<KeyModulesInfo> getAll();

    KeyModulesInfo get(String id);

    void insert(KeyModulesInfo entity);

    void update(KeyModulesInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<KeyModulesInfo> listPage(Page page);
}
