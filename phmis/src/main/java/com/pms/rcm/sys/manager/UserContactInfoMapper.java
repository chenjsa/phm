package com.pms.rcm.sys.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.sys.vo.UserContactInfo; 
@Mapper
public interface UserContactInfoMapper {

    List<UserContactInfo> getAll();

    UserContactInfo get(String id);

    void insert(UserContactInfo entity);

    void update(UserContactInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<UserContactInfo> listPage(Page page);
}
