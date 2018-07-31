package com.pms.rcm.sys.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.sys.vo.PhysiclalStatus; 
@Mapper
public interface PhysiclalStatusMapper {

    List<PhysiclalStatus> getAll();

    PhysiclalStatus get(String id);

    void insert(PhysiclalStatus entity);

    void update(PhysiclalStatus entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<PhysiclalStatus> listPage(Page page);
}
