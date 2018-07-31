package com.pms.rcm.people.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pms.base.util.Page;
import com.pms.rcm.people.vo.People;
 
public interface PeopleMapper {

    List<People> getAll();

    People getOne(String id);

    void insert(People user);

    void update(People user);

    void delete(String id); 
    /**
     * 分页查询数据
     * @return
     */
    List<People> listPage(Page page);
}
