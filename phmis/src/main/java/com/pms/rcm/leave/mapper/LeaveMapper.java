package com.pms.rcm.leave.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pms.base.util.Page;
import com.pms.base.util.PageData;
import com.pms.rcm.leave.vo.TBizLeave; 

@Mapper
public interface LeaveMapper {
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	 
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> datalistPage(Page page)throws Exception;
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listAll(PageData pd)throws Exception;
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	 
}
