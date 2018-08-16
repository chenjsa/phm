package com.pms.rcm.phmtask.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.phmtask.vo.DataClassInfo; 
@Mapper
public interface DataClassInfoMapper {

    List<DataClassInfo> getAll();

    DataClassInfo get(String id);

    void insert(DataClassInfo entity);

    void update(DataClassInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<DataClassInfo> listPage(Page page);
}
