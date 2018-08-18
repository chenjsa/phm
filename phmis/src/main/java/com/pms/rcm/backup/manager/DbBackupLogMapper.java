package com.pms.rcm.backup.manager;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pms.base.util.Page;
import com.pms.rcm.backup.vo.DbBackupLog; 
@Mapper
public interface DbBackupLogMapper {

    List<DbBackupLog> getAll();

    DbBackupLog get(String id);

    void insert(DbBackupLog entity);

    void update(DbBackupLog entity);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<DbBackupLog> listPage(Page page);
}
