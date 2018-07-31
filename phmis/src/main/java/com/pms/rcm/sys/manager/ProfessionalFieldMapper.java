package com.pms.rcm.sys.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.sys.vo.ProfessionalField; 
@Mapper
public interface ProfessionalFieldMapper {

    List<ProfessionalField> getAll();

    ProfessionalField get(String id);

    void insert(ProfessionalField entity);

    void update(ProfessionalField entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<ProfessionalField> listPage(Page page);
}
