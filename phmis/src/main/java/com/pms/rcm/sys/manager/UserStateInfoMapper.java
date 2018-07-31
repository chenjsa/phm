package com.pms.rcm.sys.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.sys.vo.UserStateInfo; 
 
public interface UserStateInfoMapper {

    List<UserStateInfo> getAll();

    UserStateInfo get(String id);

    void insert(UserStateInfo entity);

    void update(UserStateInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<UserStateInfo> listPage(Page page);
}
