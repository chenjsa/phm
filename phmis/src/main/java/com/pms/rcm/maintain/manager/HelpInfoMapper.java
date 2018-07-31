package com.pms.rcm.maintain.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.maintain.vo.HelpInfo;

@Mapper
public interface HelpInfoMapper {

    List<HelpInfo> getAll();

    HelpInfo get(String id);

    void insert(HelpInfo entity);

    void update(HelpInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<HelpInfo> listPage(Page page);
}
