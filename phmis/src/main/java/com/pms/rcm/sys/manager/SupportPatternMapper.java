package com.pms.rcm.sys.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.sys.vo.SupportPattern; 
@Mapper
public interface SupportPatternMapper {

    List<SupportPattern> getAll();

    SupportPattern get(String id);

    void insert(SupportPattern entity);

    void update(SupportPattern entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<SupportPattern> listPage(Page page);
}
