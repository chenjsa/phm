package com.pms.rcm.radar.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.radar.vo.ExtensionInfo; 
@Mapper
public interface ExtensionInfoMapper {

    List<ExtensionInfo> getAll();

    ExtensionInfo get(String id);

    void insert(ExtensionInfo entity);

    void update(ExtensionInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<ExtensionInfo> listPage(Page page);
}
