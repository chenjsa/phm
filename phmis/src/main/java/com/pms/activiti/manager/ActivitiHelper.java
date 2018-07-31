package com.pms.activiti.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.ExclusiveGateway;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.MultiInstanceLoopCharacteristics;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.javax.el.ExpressionFactory;
import org.activiti.engine.impl.javax.el.ValueExpression;
import org.activiti.engine.impl.juel.ExpressionFactoryImpl;
import org.activiti.engine.impl.juel.SimpleContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pms.rcm.task.manager.ActRuExecutionManager;
import com.pms.rcm.task.vo.ActRuExecution;
import com.pms.rcm.task.vo.ActUserTaskModel;
 
 

@Service("activitiHelper")
public class ActivitiHelper {
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
    private ActRuExecutionManager actRuExecutionManager;
    
    public List<FlowElement> getAllNodes(String processInstanceId) throws Exception {  
 
          //获取流程发布Id信息   
          String processDefinitionId = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult().getProcessDefinitionId();  

    	BpmnModel model = repositoryService.getBpmnModel(processDefinitionId);    
    	 List<FlowElement> list=new ArrayList<FlowElement>();
    	if(model != null) {    
    	    Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();      	   
    	    for(FlowElement e : flowElements) {    
    	      list.add(e);   
    	    }    
    	}    
    	return list;
    }

    	

    /** 
     * 获取下一个用户任务信息  
     * @param String taskId     任务Id信息  
     * @return  下一个用户任务用户组信息  
     * @throws Exception 
     */  
    public List<ActUserTaskModel> getNextTaskInfo(String taskId) throws Exception {  

        ProcessDefinitionEntity processDefinitionEntity = null;  
 

        Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();

        //获取流程实例Id信息   
        String processInstanceId = taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();  

        //获取流程发布Id信息   
        String definitionId = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult().getProcessDefinitionId();  

        processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)  
                .getDeployedProcessDefinition(definitionId);  
        ActRuExecution execution=this.actRuExecutionManager.findActiveExecutionByProcInstId(task.getProcessInstanceId());      	
         //当前流程节点Id信息   
        String activitiId = execution.getActId();    
        System.out.println("taskId="+taskId+" activitiId="+activitiId+" execution="+execution.getId());
        //获取流程所有节点信息   
        List<ActivityImpl> activitiList = processDefinitionEntity.getActivities();   
        List<ActUserTaskModel> acs=new ArrayList<ActUserTaskModel>();
        for(ActivityImpl activityImpl:activitiList){
        	String id = activityImpl.getId();
        	if(activitiId.equals(id)){ 
        	     List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();//获取从某个节点出来的所有线路
        	     BpmnModel bpmnModel =   
                         processEngine.getRepositoryService()  
                         .getBpmnModel(definitionId);  
                 Process mainProcess = bpmnModel.getMainProcess();  
                 Collection<FlowElement> flowElements = mainProcess.getFlowElements();  
                 FlowElement flowElement1 = null;  
                 FlowElement flowElement2 = null;  
	        	for(PvmTransition tr:outTransitions){
	        		PvmActivity ac = tr.getDestination(); //获取线路的终点节点 
	        	   for (FlowElement flowElement : flowElements) {  
	                    if (flowElement.getId().equals(ac.getId())) {  
	                        flowElement1 = flowElement;  
	                        
	                    }  
	                }  
	        	   String transName = (String) tr.getProperty("name");
	               String transId =tr.getId(); 
	               ActUserTaskModel model=new ActUserTaskModel();
	               model.setTransKey(transId);
	               model.setTransName(transName);
	               if(flowElement1 instanceof ExclusiveGateway){
	            	   ExclusiveGateway exclusiveGateway=(ExclusiveGateway) flowElement1;  
	            	   System.out.println("exclusiveGateway."+exclusiveGateway.getName()+"--"+exclusiveGateway.getDefaultFlow()+"--");
	            	   List<SequenceFlow> sequenceFlows=exclusiveGateway.getOutgoingFlows();
	            	   model.setId(exclusiveGateway.getId());
		        	   model.setName(exclusiveGateway.getName());
		        	   model.setType("exclusiveGateway");//排他网关
		        	   List<ActUserTaskModel> modelSonList=new ArrayList<ActUserTaskModel>();
	            	   for(SequenceFlow sf:sequenceFlows){ 
	    	               ActUserTaskModel modelson=new ActUserTaskModel();
	    	               modelson.setTransKey(sf.getId());
	    	               modelson.setTransName(sf.getName());
	            		   System.out.println("SequenceFlow:"+sf.getId()+"---"+sf.getName()+"--"+sf.getTargetRef());
	            		   FlowNode  sourceFlowElement = ( FlowNode) mainProcess.getFlowElement( sf.getTargetRef());
	            		   for (FlowElement flowElement : flowElements) {  
		   	                    if (flowElement.getId().equals(sourceFlowElement.getId())) {  
		   	                        flowElement2 = flowElement;  
		   	                        
		   	                    }  
	   	                   }  
	            		   if (flowElement2 instanceof UserTask) {  
	            			     UserTask userTask=(UserTask) flowElement2;  
	            			     modelson.setId(userTask.getId());
	            			     modelson.setName(userTask.getName());
	            			     modelson.setType("userTask");//用户任务
	            			     System.out.println("getCandidateGroups."+userTask.getCandidateGroups()+" getCandidateUsers."+userTask.getCandidateUsers());
	         	        	    
	            			     List<String> candidateUsers=userTask.getCandidateUsers();
			  	        	     if(!candidateUsers.isEmpty()){
			  	        	    	 for(String candidateUser:candidateUsers){
			  	        	    		modelson.setCandidateUsers(candidateUser);
			  	        	    	 }
			  	        	     }
			  	        	     if(userTask.getLoopCharacteristics()!=null){
			  	        	    	 modelson.setIsJointlySign("yes");//(true);
			  	        	     }else
			  	        	     {
			  	        	    	 modelson.setIsJointlySign("no");
			  	        	     }
			  	        	     if(userTask.getAssignee()!=null){
			  	        	    	 String replace = userTask.getAssignee().replace("${", "");  
			  	                     String replace2 = replace.replace("}", "");  
			  	                     modelson.setAssigneeVariable(replace2);
			  	        	     }	            			   
	            		   }	  
	            		   modelSonList.add(modelson);	            		    
		               }
                       model.setActUserTaskModels(modelSonList);
	               }
	             
	               
	        	 //如果是任务节点  
	        	   if (flowElement1 instanceof UserTask) {  
	        	     UserTask userTask=(UserTask) flowElement1;  
	        	  
	        	     model.setId(userTask.getId());
	        	     model.setName(userTask.getName());
	        	     model.setType("userTask");//用户任务
	        	   //  System.out.println("getCandidateGroups."+userTask.getCandidateGroups()+" getCandidateUsers."+userTask.getCandidateUsers());
	        	     List<String> candidateUsers=userTask.getCandidateUsers();
	        	     if(!candidateUsers.isEmpty()){
	        	    	 for(String candidateUser:candidateUsers){
	        	    		 model.setCandidateUsers(candidateUser);
	        	    	 }
	        	     }
	        	     if(userTask.getLoopCharacteristics()!=null){
	        	    	 model.setIsJointlySign("yes");//(true);
	        	     }else
	        	     {
	        	    	 model.setIsJointlySign("no");
	        	     }
	        	     if(userTask.getAssignee()!=null){
	        	    	 String replace = userTask.getAssignee().replace("${", "");  
	                     String replace2 = replace.replace("}", "");  
	        	    	 model.setAssigneeVariable(replace2);
	        	     }
	        	   
                    /// System.out.println("下一步任务任务："+userTask.getName()+"---"+userTask.getId()+"---"+userTask.getAssignee()+"--"+userTask.getLoopCharacteristics());
	        	   } 
	        	   acs.add(model);
	        	    
	        	 }
        	    break;
        	}
        }
    /*    //遍历所有节点信息   
        for(ActivityImpl activityImpl : activitiList){      
            id = activityImpl.getId();     
            if (activitiId.equals(id)) {
                //获取下一个节点信息   
                task = nextTaskDefinition(activityImpl, activityImpl.getId(), null, processInstanceId); 
                break;
            }
        }  */
        return acs;  
    }  
    
    private Map<String, Object> geta(String ProcessDefinitionId,String actId) {  
        
        BpmnModel bpmnModel =   
                 processEngine.getRepositoryService()  
                 .getBpmnModel(ProcessDefinitionId);  
         Process mainProcess = bpmnModel.getMainProcess();  
         Collection<FlowElement> flowElements = mainProcess.getFlowElements();  
         FlowElement flowElement1 = null;  
         for (FlowElement flowElement : flowElements) {  
            if (flowElement.getId().equals(actId)) {  
                flowElement1 = flowElement;  
            }  
        }  
           
          
         UserTask userTask= (UserTask)flowElement1;  
         MultiInstanceLoopCharacteristics loopCharacteristics = userTask.getLoopCharacteristics();  
         if(loopCharacteristics!=null){
        	 String loopCardinality = loopCharacteristics.getLoopCardinality();  
        	 if(loopCardinality!=null){
        		 String replace = loopCardinality.replace("${", "");  
                 String replace2 = replace.replace("}", "");  
                 Map<String, Object> map = new HashMap<String, Object>();  
                 map.put(replace2, replace2);  
                 return map;  
        	 }else{
            	 return null;
             }
            
         }else{
        	 return null;
         }
        
    }  
    /** 
     * 获取流程的下一个节点 且要经过规则引擎判断后的节点 
     * @param taskId 
     * @return 
     */  
    public List<FlowElement> getNextNode(String taskId) {  
          
        Task task = null;  
        task = taskService.createTaskQuery().taskId(taskId).singleResult();  
        if(task==null) {  
            return null;  
        }  
        List<FlowElement> list = new ArrayList<FlowElement>();  
          
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();  
          
        //当前活动节点  
        String activitiId = processInstance.getActivityId();  
          
        System.out.println("当前节点是【"+activitiId+"】");  
  
        //pmmnModel 遍历节点需要它  
        BpmnModel bpmnModel =  repositoryService.getBpmnModel(task.getProcessDefinitionId());  
  
        List<org.activiti.bpmn.model.Process> processList = bpmnModel.getProcesses();  
          
        //循环多个物理流程  
        for(Process process:processList) {  
              
            //返回该流程的所有任务，事件  
            Collection<FlowElement> cColl = process.getFlowElements();  
            //遍历节点  
            for(FlowElement f :cColl) {  
                  
                  
                //如果改节点是当前节点 者 输出该节点的下一个节点  
                if(f.getId().equals(activitiId)) {  
                  
                    List<SequenceFlow>  sequenceFlowList = new ArrayList<SequenceFlow>();  
                    //通过反射来判断是哪种类型  
                    if(f instanceof org.activiti.bpmn.model.StartEvent) {  
                        //开始事件的输出路由  
                        sequenceFlowList    = ((org.activiti.bpmn.model.StartEvent) f).getOutgoingFlows();  
                    }else if(f instanceof org.activiti.bpmn.model.UserTask) {  
                          
                        sequenceFlowList    = ((org.activiti.bpmn.model.UserTask) f).getOutgoingFlows();  
                          
                          
                        for(SequenceFlow sf :sequenceFlowList)  {  
                              
                            String targetRef = sf.getTargetRef();  
                            FlowElement ref = process.getFlowElement(targetRef);  
                              
                        //  nextActivitiIdList.add(ref.getId());  
                              
                            list.add(ref);  
                        }  
                          
                    }else if(f instanceof org.activiti.bpmn.model.SequenceFlow) {  
                      
                          
                    }else if(f instanceof org.activiti.bpmn.model.EndEvent) {  
                        sequenceFlowList    = ((org.activiti.bpmn.model.EndEvent) f).getOutgoingFlows();  
                    }  
                    break;  
                }   
                      
            }  
              
        }     
        return list;  
    }   

    /**  
     * 下一个任务节点信息,  
     *  
     * 如果下一个节点为用户任务则直接返回,  
     *  
     * 如果下一个节点为排他网关, 获取排他网关Id信息, 根据排他网关Id信息和execution获取流程实例排他网关Id为key的变量值,  
     * 根据变量值分别执行排他网关后线路中的el表达式, 并找到el表达式通过的线路后的用户任务
     * @param ActivityImpl activityImpl     流程节点信息  
     * @param String activityId             当前流程节点Id信息  
     * @param String elString               排他网关顺序流线段判断条件
     * @param String processInstanceId      流程实例Id信息  
     * @return  
     */    
    private TaskDefinition nextTaskDefinition(ActivityImpl activityImpl, String activityId, String elString, String processInstanceId){   

        PvmActivity ac = null;

        Object s = null;

        // 如果遍历节点为用户任务并且节点不是当前节点信息
        if ("userTask".equals(activityImpl.getProperty("type")) && !activityId.equals(activityImpl.getId())) {
            // 获取该节点下一个节点信息
            TaskDefinition taskDefinition = ((UserTaskActivityBehavior) activityImpl.getActivityBehavior())
                    .getTaskDefinition();
            return taskDefinition;
        } else if("exclusiveGateway".equals(activityImpl.getProperty("type"))){// 当前节点为exclusiveGateway
            List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();
            //outTransitionsTemp = ac.getOutgoingTransitions();

            // 如果网关路线判断条件为空信息
//          if (StringUtils.isEmpty(elString)) {
                // 获取流程启动时设置的网关判断条件信息
                elString = getGatewayCondition(activityImpl.getId(), processInstanceId);
//          }
            // 如果排他网关只有一条线路信息
            if (outTransitions.size() == 1) {
                return nextTaskDefinition((ActivityImpl) outTransitions.get(0).getDestination(), activityId,
                        elString, processInstanceId);
            } else if (outTransitions.size() > 1) { // 如果排他网关有多条线路信息
                for (PvmTransition tr1 : outTransitions) {
                    s = tr1.getProperty("conditionText"); // 获取排他网关线路判断条件信息
                    // 判断el表达式是否成立
                    if (isCondition(activityImpl.getId(), StringUtils.trimTrailingWhitespace(s.toString()), elString)) {
                        return nextTaskDefinition((ActivityImpl) tr1.getDestination(), activityId, elString,
                                processInstanceId);
                    }

                }
            }
        }else {
            // 获取节点所有流向线路信息
            List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();
            List<PvmTransition> outTransitionsTemp = null;
            for (PvmTransition tr : outTransitions) {
                ac = tr.getDestination(); // 获取线路的终点节点
                // 如果流向线路为排他网关
                if ("exclusiveGateway".equals(ac.getProperty("type"))) {
                    outTransitionsTemp = ac.getOutgoingTransitions();

                    // 如果网关路线判断条件为空信息
                    if (StringUtils.isEmpty(elString)) {
                        // 获取流程启动时设置的网关判断条件信息
                        elString = getGatewayCondition(ac.getId(), processInstanceId);
                    }

                    // 如果排他网关只有一条线路信息
                    if (outTransitionsTemp.size() == 1) {
                        return nextTaskDefinition((ActivityImpl) outTransitionsTemp.get(0).getDestination(), activityId,
                                elString, processInstanceId);
                    } else if (outTransitionsTemp.size() > 1) { // 如果排他网关有多条线路信息
                        for (PvmTransition tr1 : outTransitionsTemp) {
                            s = tr1.getProperty("conditionText"); // 获取排他网关线路判断条件信息
                            // 判断el表达式是否成立
                            if (isCondition(ac.getId(), StringUtils.trimTrailingWhitespace(s.toString()), elString)) {
                                return nextTaskDefinition((ActivityImpl) tr1.getDestination(), activityId, elString,
                                        processInstanceId);
                            }
                        }
                    }
                } else if ("userTask".equals(ac.getProperty("type"))) {
                    return ((UserTaskActivityBehavior) ((ActivityImpl) ac).getActivityBehavior()).getTaskDefinition();
                } else {
                }
            }
            return null;
        }
        return null;
    }  

    /** 
     * 查询流程启动时设置排他网关判断条件信息  
     * @param String gatewayId          排他网关Id信息, 流程启动时设置网关路线判断条件key为网关Id信息  
     * @param String processInstanceId  流程实例Id信息  
     * @return 
     */  
    public String getGatewayCondition(String gatewayId, String processInstanceId) {  
        Execution execution = runtimeService.createExecutionQuery().processInstanceId(processInstanceId).singleResult();
        Object object= runtimeService.getVariable(execution.getId(), gatewayId);
        return object==null? "":object.toString();  
    }  

    /** 
     * 根据key和value判断el表达式是否通过信息  
     * @param String key    el表达式key信息  
     * @param String el     el表达式信息  
     * @param String value  el表达式传入值信息  
     * @return 
     */  
    public boolean isCondition(String key, String el, String value) {  
        ExpressionFactory factory = new ExpressionFactoryImpl();    
        SimpleContext context = new SimpleContext();    
        context.setVariable(key, factory.createValueExpression(value, String.class));    
        ValueExpression e = factory.createValueExpression(context, el, boolean.class);    
        return (Boolean) e.getValue(context);  
    }  

}
