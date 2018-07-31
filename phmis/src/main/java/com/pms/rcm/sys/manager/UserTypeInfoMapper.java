package com.pms.rcm.sys.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.sys.vo.UserTypeInfo; 
 
public interface UserTypeInfoMapper {

    List<UserTypeInfo> getAll();

    UserTypeInfo get(String id);

    void insert(UserTypeInfo entity);

    void update(UserTypeInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<UserTypeInfo> listPage(Page page);
}
