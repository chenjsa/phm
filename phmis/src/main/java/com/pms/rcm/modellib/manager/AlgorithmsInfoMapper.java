package com.pms.rcm.modellib.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.modellib.vo.AlgorithmsInfo; 
@Mapper
public interface AlgorithmsInfoMapper {

    List<AlgorithmsInfo> getAll();

    AlgorithmsInfo get(String id);

    void insert(AlgorithmsInfo entity);

    void update(AlgorithmsInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<AlgorithmsInfo> listPage(Page page);
}
