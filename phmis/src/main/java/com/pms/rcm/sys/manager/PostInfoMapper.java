package com.pms.rcm.sys.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.sys.vo.PostInfo; 
@Mapper
public interface PostInfoMapper {

    List<PostInfo> getAll();

    PostInfo get(String id);

    void insert(PostInfo entity);

    void update(PostInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<PostInfo> listPage(Page page);
}
