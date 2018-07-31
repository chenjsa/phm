package com.pms.rcm.leave.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.base.util.Page;
import com.pms.base.util.PageData;
import com.pms.rcm.leave.mapper.LeaveMapper;
 

@Service("leaveManager")
public class LeaveManager {
	 @Autowired
     private LeaveMapper leaveMapper;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		leaveMapper.save(pd);
	}
	
 
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		//dao.update("LeaveMapper.edit", pd);
		leaveMapper.edit(pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */ 
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)leaveMapper.datalistPage(page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List listAll(PageData pd)throws Exception{
		return (List) leaveMapper.listAll(pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return leaveMapper.findById(pd);
	}
	
	 

}
