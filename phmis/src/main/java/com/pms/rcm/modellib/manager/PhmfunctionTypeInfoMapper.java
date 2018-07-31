package com.pms.rcm.modellib.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.modellib.vo.PhmfunctionTypeInfo; 
@Mapper
public interface PhmfunctionTypeInfoMapper {

    List<PhmfunctionTypeInfo> getAll();

    PhmfunctionTypeInfo get(String id);

    void insert(PhmfunctionTypeInfo entity);

    void update(PhmfunctionTypeInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<PhmfunctionTypeInfo> listPage(Page page);
}
