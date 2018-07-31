package com.pms.rcm.maintain.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.maintain.vo.SystemParametersInfo;

@Mapper
public interface SystemParametersInfoMapper {

    List<SystemParametersInfo> getAll();

    SystemParametersInfo get(String id);

    void insert(SystemParametersInfo entity);

    void update(SystemParametersInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<SystemParametersInfo> listPage(Page page);
}
