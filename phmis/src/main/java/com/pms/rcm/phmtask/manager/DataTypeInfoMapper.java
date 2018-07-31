package com.pms.rcm.phmtask.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.phmtask.vo.DataTypeInfo; 
@Mapper
public interface DataTypeInfoMapper {

    List<DataTypeInfo> getAll();

    DataTypeInfo get(String id);

    void insert(DataTypeInfo entity);

    void update(DataTypeInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<DataTypeInfo> listPage(Page page);
}
