package com.pms.rcm.modellib.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.modellib.vo.AlgorithmsModelsInfo; 
@Mapper
public interface AlgorithmsModelsInfoMapper {

    List<AlgorithmsModelsInfo> getAll();

    AlgorithmsModelsInfo get(String id);

    void insert(AlgorithmsModelsInfo entity);

    void update(AlgorithmsModelsInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<AlgorithmsModelsInfo> listPage(Page page);
}
