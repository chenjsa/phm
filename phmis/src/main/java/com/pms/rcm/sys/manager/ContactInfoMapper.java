package com.pms.rcm.sys.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.sys.vo.ContactInfo; 
@Mapper
public interface ContactInfoMapper {

    List<ContactInfo> getAll();

    ContactInfo get(String id);

    void insert(ContactInfo entity);

    void update(ContactInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<ContactInfo> listPage(Page page);
}
