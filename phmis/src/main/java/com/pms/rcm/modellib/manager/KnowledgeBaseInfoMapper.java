package com.pms.rcm.modellib.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.modellib.vo.KnowledgeBaseInfo; 
@Mapper
public interface KnowledgeBaseInfoMapper {

    List<KnowledgeBaseInfo> getAll();

    KnowledgeBaseInfo get(String id);

    void insert(KnowledgeBaseInfo entity);

    void update(KnowledgeBaseInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<KnowledgeBaseInfo> listPage(Page page);
}
