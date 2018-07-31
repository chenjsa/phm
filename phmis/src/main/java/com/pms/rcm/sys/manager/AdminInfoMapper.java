package com.pms.rcm.sys.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.sys.vo.AdminInfo; 
@Mapper
public interface AdminInfoMapper {

    List<AdminInfo> getAll();

    AdminInfo get(String id);

    void insert(AdminInfo entity);

    void update(AdminInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<AdminInfo> listPage(Page page);
}
