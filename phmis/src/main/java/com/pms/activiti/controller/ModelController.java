package com.pms.activiti.controller;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pms.activiti.manager.ModelManager;
import com.pms.base.util.Jurisdiction;
import com.pms.base.util.ObjectExcelView;
import com.pms.base.util.Page;
import com.pms.base.util.PageData;
 

/** 
 * 说明：模型管理 
 */
@Controller
@RequestMapping(value="/model")
public class ModelController extends BaseController {
	
	String menuUrl = "model/list.do"; //菜单地址(权限用)
    @Autowired
    private ModelManager modelManager;
	@Autowired
    private RepositoryService repositoryService;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Model");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		
		int currentPage = 1;
		String strCP = pd.getString("page.currentPage");
		if(null != strCP && !"".equals(strCP))
		{currentPage = Integer.parseInt(strCP);}
		else{currentPage=1;}
		
		int showCount=10;
		String strSC = pd.getString("page.showCount");
		if(null != strSC && !"".equals(strSC))
		{showCount = Integer.parseInt(strSC);}
		else{showCount=10;}

		try {		
			logger.info("start model query,开始查询模型数据...");		
			ModelQuery modelQuery=repositoryService.createModelQuery();
			List<Model> list =new ArrayList<Model>();
			if(StringUtils.isNotBlank(keywords)){
				list = modelQuery.modelNameLike("%"+keywords+"%").orderByCreateTime().desc().listPage((currentPage-1)*showCount,showCount);
			}else{
				list = modelQuery.orderByCreateTime().desc().listPage((currentPage-1)*showCount,showCount);  //注意此接口参数第一个查询当前页从0开始
			}		
			
			SimpleDateFormat formatter; 
			formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");   
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<list.size();i++){
				PageData vpd = new PageData();
				vpd.put("ID", list.get(i).getId());			
				vpd.put("KEY", list.get(i).getKey());		
				vpd.put("NAME", list.get(i).getName());		
				vpd.put("VERSION", list.get(i).getVersion());		
				vpd.put("CREATE_TIME", formatter.format(list.get(i).getCreateTime()));	
				vpd.put("LAST_UPDATE_TIME", formatter.format(list.get(i).getLastUpdateTime()));	
				vpd.put("META_INFO", list.get(i).getMetaInfo());					
				vpd.put("STATUS", (list.get(i).getDeploymentId()==null)? 0 : 1); //1已部署，0已停止
				varList.add(vpd);
			}
			
			long count=modelQuery.count();			
			page.setTotalResult((int) count);  //设置分页总条数
			page.setCurrentPage(currentPage);
			page.getTotalPage();	//设置页数
			logger.info("end model query,结束查询模型数据.");
				
			mv.setViewName("jsp/activiti/model/model_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
			return mv;		
		} catch (Exception e) {
			logger.error("查询模型数据失败，error：" + e.toString(),e);
			mv.setViewName("jsp/activiti/model/model_list");
			mv.addObject("pd", pd);
			mv.addObject("QX",Jurisdiction.getHC()); 
			return mv;	
		}
		
	}
	
	/**去新增页面（创建新模型）
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("jsp/activiti/model/model_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	/**新增保存（创建新模型）
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Model"); 
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
	        ObjectNode editorNode = objectMapper.createObjectNode();
	        editorNode.put("id", "canvas");
	        editorNode.put("resourceId", "canvas");
	        ObjectNode stencilSetNode = objectMapper.createObjectNode();
	        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
	        editorNode.put("stencilset", stencilSetNode);
	       
	        ObjectNode modelObjectNode = objectMapper.createObjectNode();
	        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, pd.get("NAME").toString());
	        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
	        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, pd.get("DESCRIPTION").toString());
	        
	        Model modelData = repositoryService.newModel();
	        modelData.setMetaInfo(modelObjectNode.toString());
	        modelData.setName(pd.get("NAME").toString());
	        modelData.setKey(pd.get("KEY").toString());

	        repositoryService.saveModel(modelData);
	        repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
			
			mv.addObject("modelId",modelData.getId());
			mv.addObject("msg","success");
			mv.addObject("act","addModel");
			mv.setViewName("jsp/save_result");
			return mv;
		} catch (Exception e) {
			logger.error("创建模型失败，error：" + e.toString(),e);
			mv.addObject("msg","fail");
			mv.setViewName("jsp/save_result");
			return mv;	
		}
	}
	
	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除Model"); 
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			repositoryService.deleteModel(pd.getString("MODEL_ID"));
			logger.info("删除模型成功,MODEL_ID:"+pd.getString("MODEL_ID"));
			out.write("success");
		} catch (Exception e) {
			logger.error("删除模型失败，,MODEL_ID:"+pd.getString("MODEL_ID")+",error：" + e.toString(),e);
			out.write("fail");
		}		
		finally{
		    out.close();
		}
	}
	
	/**部署
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/deploy")
	public void deploy(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"部署Model"); 
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,String> map = new HashMap<String,String>();

		try {        	
			Model modelData = repositoryService.getModel(pd.getString("MODEL_ID"));
			ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
			byte[] bpmnBytes = null;
			BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
			bpmnBytes = new BpmnXMLConverter().convertToXML(model);
			String processName = modelData.getName() + ".bpmn20.xml";
			String strT = modelData.getName();
			Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(bpmnBytes,"UTF-8")).deploy();
			logger.info("模型部署流程成功，部署ID=" + deployment.getId()+"--"+strT);
			//部署成功后，deploymentID写入model表的deploymentID
			pd.put("DEPLOYMENT_ID", deployment.getId().toString().trim());
			modelManager.updateDeploymentID(pd);
			
			out.write("success");
			out.close();
    	} catch (Exception e) {
				logger.error("模型部署流程失败，error：" + e.toString(),e);
				out.write("fail");
				out.close();
    	}			
	}
	
	/**导出模型
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/exportModel", method = RequestMethod.GET)
	public void exportModel(PrintWriter out,HttpServletResponse response) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出Model");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,String> map = new HashMap<String,String>();

		try {        		
		    Model modelData = repositoryService.getModel(pd.getString("MODEL_ID"));
            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
            byte[] modelEditorSource = repositoryService.getModelEditorSource(modelData.getId());

            JsonNode editorNode = new ObjectMapper().readTree(modelEditorSource);
            BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);

            // 处理异常
            if (bpmnModel.getMainProcess() == null) {      
                out.write("no main process, can't export for type bpmn.");
				out.close();
				return;
            }
            byte[] exportBytes = null;	
            String mainProcessId = bpmnModel.getMainProcess().getId();
            
            BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
            exportBytes = xmlConverter.convertToXML(bpmnModel);
            String filename = mainProcessId + ".bpmn20.xml";
            
            ByteArrayInputStream in = new ByteArrayInputStream(exportBytes);
            response.reset();
            
            IOUtils.copy(in, response.getOutputStream());
            
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
            response.flushBuffer();
            
//          response.setContentType("application/vnd.ms-excel;charset=utf-8");                 
            out.close();
    	} catch (Exception e) {
				logger.error("导出模型失败，error：" + e.toString(),e);
				out.write("fail");
				out.close();
    	}			
	}
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出Model到excel");
	//	if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("名称");	//1
		dataMap.put("titles", titles);
//		List<PageData> varOList = modelService.listAll(pd);
//		List<PageData> varList = new ArrayList<PageData>();
//		for(int i=0;i<varOList.size();i++){
//			PageData vpd = new PageData();
//			vpd.put("var1", varOList.get(i).getString("NAME"));	//1
//			varList.add(vpd);
//		}
//		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
