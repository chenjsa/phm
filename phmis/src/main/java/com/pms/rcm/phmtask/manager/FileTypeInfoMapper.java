package com.pms.rcm.phmtask.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.phmtask.vo.FileTypeInfo; 
@Mapper
public interface FileTypeInfoMapper {

    List<FileTypeInfo> getAll();

    FileTypeInfo get(String id);

    void insert(FileTypeInfo entity);

    void update(FileTypeInfo entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<FileTypeInfo> listPage(Page page);
}
