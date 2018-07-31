package com.pms.rcm.maintain.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.maintain.vo.HelpTypeInfo;

@Mapper
public interface HelpTypeInfoMapper {

    List<HelpTypeInfo> getAll();

    HelpTypeInfo get(String id);

    void insert(HelpTypeInfo entity);

    void update(HelpTypeInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<HelpTypeInfo> listPage(Page page);
}
