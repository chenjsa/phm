package com.pms.rcm.phmtask.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.phmtask.vo.ParameterCustomClass; 
@Mapper
public interface ParameterCustomClassMapper {

    List<ParameterCustomClass> getAll();

    ParameterCustomClass get(String id);

    void insert(ParameterCustomClass entity);

    void update(ParameterCustomClass entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<ParameterCustomClass> listPage(Page page);
}
