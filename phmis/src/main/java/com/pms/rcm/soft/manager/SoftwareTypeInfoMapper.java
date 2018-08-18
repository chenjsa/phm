package com.pms.rcm.soft.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.soft.vo.SoftwareTypeInfo; 
@Mapper
public interface SoftwareTypeInfoMapper {

    List<SoftwareTypeInfo> getAll();

    SoftwareTypeInfo get(String id);

    void insert(SoftwareTypeInfo entity);

    void update(SoftwareTypeInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<SoftwareTypeInfo> listPage(Page page);
}
