package com.pms.rcm.modellib.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.modellib.vo.FunctionModelsInfo; 
@Mapper
public interface FunctionModelsInfoMapper {

    List<FunctionModelsInfo> getAll();

    FunctionModelsInfo get(String id);

    void insert(FunctionModelsInfo entity);

    void update(FunctionModelsInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<FunctionModelsInfo> listPage(Page page);
}
