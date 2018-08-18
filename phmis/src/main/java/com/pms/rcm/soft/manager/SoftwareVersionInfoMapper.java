package com.pms.rcm.soft.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.soft.vo.SoftwareVersionInfo; 
@Mapper
public interface SoftwareVersionInfoMapper {

    List<SoftwareVersionInfo> getAll();

    SoftwareVersionInfo get(String id);

    void insert(SoftwareVersionInfo entity);

    void update(SoftwareVersionInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<SoftwareVersionInfo> listPage(Page page);
}
