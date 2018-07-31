package com.pms.activiti.manager;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.rcm.task.manager.ActHiActinstManager;
import com.pms.rcm.task.manager.ActHiProcinstManager;
import com.pms.rcm.task.manager.ActHiTaskinstManager;
import com.pms.rcm.task.manager.ActHiVarinstManager;
import com.pms.rcm.task.manager.ActTaskExtendManager;
import com.pms.rcm.task.vo.ActHiActinst;
import com.pms.rcm.task.vo.ActHiProcinst;
import com.pms.rcm.task.vo.ActHiTaskinst;
import com.pms.rcm.task.vo.ActHiVarinst;
import com.pms.rcm.task.vo.ActTaskExtend;
@Service("activitiMonitorManager")
public class ActivitiMonitorManager {
	@Autowired    
    private RuntimeService runtimeService;    
    @Autowired    
    private TaskService taskService;    
    @Autowired    
    private HistoryService historyService;    
    @Autowired    
    private RepositoryService repositoryService;    
    @Autowired    
    private ProcessEngineConfigurationImpl processEngineConfiguration;    
    
    @Autowired    
    private ProcessEngine processEngine;   
    @Autowired    
    private ActHiTaskinstManager  actHiTaskinstManager;
    @Autowired   
    private ActTaskExtendManager actTaskExtendManager;
    @Autowired   
    private ActHiActinstManager actHiActinstManager;
    @Autowired   
    private ActHiProcinstManager actHiProcinstManager;
    @Autowired   
    private ActHiVarinstManager actHiVarinstManager;
      
    /** 
     * 启动流程 
     * @param bizId 业务id 
     */  
    public void startProcesses(String bizId) {   
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("demo5", bizId);//流程图id，业务表id  
        System.out.println("流程启动成功，流程id:"+pi.getId());  
    }    
      
    /** 
     *  
    * <p>描述: 根据用户id查询待办任务列表</p>     
    * @date 2018年2月25日 
     */  
    public List<Task> findTasksByUserId(String userId) {  
        List<Task> resultTask = taskService.createTaskQuery().processDefinitionKey("demo5").taskCandidateOrAssigned(userId).list();  
        return resultTask;  
    }  
      
    /** 
     *  
     * <p>描述:任务审批     （通过/拒接） </p>     
     * @param taskId 任务id 
     * @param userId 用户id 
     * @param result false OR true 
     */  
    public void completeTask(String taskId,String userId,String result) {  
        //获取流程实例  
        taskService.claim(taskId, userId);  
          
        Map<String,Object> vars = new HashMap<String,Object>();  
        vars.put("sign", "true");  
        taskService.complete(taskId, vars);  
    }  
      
    /** 
     * 更改业务流程状态#{ActivityDemoServiceImpl.updateBizStatus(execution,"tj")} 
     * @param execution 
     * @param status 
     */  
    public void updateBizStatus(DelegateExecution execution,String status) {  
        String bizId = execution.getProcessBusinessKey();  
        //根据业务id自行处理业务表  
        System.out.println("业务表["+bizId+"]状态更改成功，状态更改为："+status);  
    }  
      
      
    //流程节点权限用户列表${ActivityDemoServiceImpl.findUsers(execution,sign)}  
    public List<String> findUsersForSL(DelegateExecution execution){  
        return Arrays.asList("sly1","sly2");  
    }  
      
    //流程节点权限用户列表${ActivityDemoServiceImpl.findUsers(execution,sign)}  
    public List<String> findUsersForSP(DelegateExecution execution){  
        return Arrays.asList("spy1","uspy2");  
    }  
      
    /**
     * 某一次流程执行了多少步
     */ 
    public  List<HistoricActivityInstance> queryHistoricActivitiInstance(String processInstanceId) { 
        List<HistoricActivityInstance> list = processEngine.getHistoryService()
                .createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId)
                .list();
       /* if (list != null && list.size() > 0) {
            for (HistoricActivityInstance hai : list) {
                System.out.println(hai.getId());
                System.out.println("步骤ID：" + hai.getActivityId());
                System.out.println("步骤名称：" + hai.getActivityName());
                System.out.println("执行人：" + hai.getAssignee());
                System.out.println("====================================");
            }
        }*/
        return list;
    }
    
    /**
     * 某一次流程的执行经历的多少任务
     */ 
    public  List<HistoricTaskInstance>   queryHistoricTask(String processInstanceId) { 
        List<HistoricTaskInstance> list = processEngine.getHistoryService()
                .createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .list();        
        return list;
    }
    /**
     * 某一次流程的执行经历的多少任务
     */ 
    public  List<ActHiTaskinst>   queryActHiTaskinst(String processInstanceId) { 
        List<ActHiTaskinst> list=actHiTaskinstManager.find("from ActHiTaskinst where procInstId='"+processInstanceId+"' order by id" );
        for(ActHiTaskinst hiTask:list){
        	List<ActTaskExtend> e=this.actTaskExtendManager.find("from ActTaskExtend where taskId='"+hiTask.getId()+"'");
        	if(!e.isEmpty()){
        		ActTaskExtend ee=e.get(0);
        		hiTask.setActTaskExtend(ee);
        	}
        }
        return list;
    }
    
    
    /**
     * 获bskey
     */ 
    public String getBusinesskey(String processInstanceId)  throws Exception { 
    	/* //1,使用任务ID，查询对象task  
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();  
        //2.使用任务ID，获取实例ID  
        String processInstanceId = task.getProcessInstanceId();  */
        //3.使用流程实例，查询   
      //  ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();  
        //4.使用流程实例对象获取BusinessKey  
     //   String business_key = pi.getBusinessKey();  
        ActHiVarinst actHiVarinst=actHiVarinstManager.getActHiVarinstByBussinessKey(processInstanceId);
        System.out.println(actHiVarinst.getProcInstId()+" --"+actHiVarinst.getName()+"---"+actHiVarinst.getText());
        if(actHiVarinst!=null){
        	return actHiVarinst.getText();
        }else{
        	return "";
        } 
    }
    
    /**
     * 某一次流程的执行经历的多少任务
     */ 
    public  List<ActHiActinst>   queryActHiActinst(String processInstanceId) { 
        List<ActHiActinst> list=this.actHiActinstManager.find("from ActHiActinst where procInstId='"+processInstanceId+"' order by id" );
        for(ActHiActinst ac:list){
        	if(ac.getActType().equals("startEvent")){
        		ActHiProcinst ap=actHiProcinstManager.getActHiProcinstForStart(processInstanceId);
        		ac.setAssignee(ap.getStartUserId());
        		break;
        	}
        }
        return list;
    }
    
   
    /** 
     *  
    * <p>描述:  生成流程图 
     * 首先启动流程，获取processInstanceId，替换即可生成</p>     
    * @param processInstanceId 
    * @throws Exception 
     */  
    public void queryProImg(String processInstanceId) throws Exception {  
        //获取历史流程实例    
        HistoricProcessInstance processInstance =  historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();    
         
        //根据流程定义获取输入流  
        InputStream is = repositoryService.getProcessDiagram(processInstance.getProcessDefinitionId());  
        BufferedImage bi = ImageIO.read(is);  
        File file = new File("demo2.png");  
        if(!file.exists()) file.createNewFile();  
        FileOutputStream fos = new FileOutputStream(file);  
        ImageIO.write(bi, "png", fos);  
        fos.close();  
        is.close();  
        System.out.println("图片生成成功");  
  
        List<Task> tasks = taskService.createTaskQuery().taskCandidateUser("userId").list();  
        for(Task t : tasks) {  
            System.out.println(t.getName());  
        }  
    }  
      
      
    /** 
     * 流程图高亮显示 
     * 首先启动流程，获取processInstanceId，替换即可生成 
     * @throws Exception 
     */  
    public void queryProHighLighted(String processInstanceId,ByteArrayOutputStream output) throws Exception {  
        //获取历史流程实例    
        HistoricProcessInstance processInstance =  historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();    
        //获取流程图    
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());    
    
        DefaultProcessDiagramGenerator diagramGenerator = new DefaultProcessDiagramGenerator();///processEngineConfiguration.getProcessDiagramGenerator();    
        ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());    
    
        List<HistoricActivityInstance> highLightedActivitList =  historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();    
        //高亮环节id集合    
        List<String> highLightedActivitis = new ArrayList<String>();    
            
        //高亮线路id集合    
        List<String> highLightedFlows = getHighLightedFlows(definitionEntity,highLightedActivitList);    
    
        for(HistoricActivityInstance tempActivity : highLightedActivitList){    
            String activityId = tempActivity.getActivityId();    
            highLightedActivitis.add(activityId);    
        }    
        //配置字体  
        InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitis, highLightedFlows,"宋体","微软雅黑","黑体",null,2.0);  
        BufferedImage bi = ImageIO.read(imageStream);  
        File file = new File("demo2.png");  
        if(!file.exists()) file.createNewFile();  
       // FileOutputStream fos = new FileOutputStream(file);  
        ImageIO.write(bi, "png", output);  
      //  fos.close();  
        imageStream.close();  
        System.out.println("图片生成成功");  
    }    
     /**   
     * 获取需要高亮的线   
     * @param processDefinitionEntity   
     * @param historicActivityInstances   
     * @return   
     */    
    private List<String> getHighLightedFlows(    
            ProcessDefinitionEntity processDefinitionEntity,    
            List<HistoricActivityInstance> historicActivityInstances) {    
            
        List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId    
        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {// 对历史流程节点进行遍历    
            ActivityImpl activityImpl = processDefinitionEntity    
                    .findActivity(historicActivityInstances.get(i)    
                            .getActivityId());// 得到节点定义的详细信息    
            List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();// 用以保存后需开始时间相同的节点    
            ActivityImpl sameActivityImpl1 = processDefinitionEntity    
                    .findActivity(historicActivityInstances.get(i + 1)    
                            .getActivityId());    
            // 将后面第一个节点放在时间相同节点的集合里    
            sameStartTimeNodes.add(sameActivityImpl1);    
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {    
                HistoricActivityInstance activityImpl1 = historicActivityInstances    
                        .get(j);// 后续第一个节点    
                HistoricActivityInstance activityImpl2 = historicActivityInstances    
                        .get(j + 1);// 后续第二个节点    
                if (activityImpl1.getStartTime().equals(    
                        activityImpl2.getStartTime())) {    
                    // 如果第一个节点和第二个节点开始时间相同保存    
                    ActivityImpl sameActivityImpl2 = processDefinitionEntity    
                            .findActivity(activityImpl2.getActivityId());    
                    sameStartTimeNodes.add(sameActivityImpl2);    
                } else {    
                    // 有不相同跳出循环    
                    break;    
                }    
            }    
            List<PvmTransition> pvmTransitions = activityImpl    
                    .getOutgoingTransitions();// 取出节点的所有出去的线    
            for (PvmTransition pvmTransition : pvmTransitions) {    
                // 对所有的线进行遍历    
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition    
                        .getDestination();    
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示    
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {    
                    highFlows.add(pvmTransition.getId());    
                }    
            }    
        }    
        return highFlows;    
}    
}   
