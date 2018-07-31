package com.pms.rcm.task.vo;

import java.util.List;

import org.activiti.bpmn.model.SequenceFlow;

public class ActUserTaskModel {
   private String id;
   private String name;
   private String isJointlySign;
   ///委托人变量
   /***
    * 處理人變量
    */
   private String assigneeVariable;
   
   private String transName;
   private String transKey;
   
   
	 
	public String getAssigneeVariable() {
		return assigneeVariable;
	}
	public void setAssigneeVariable(String assigneeVariable) {
		this.assigneeVariable = assigneeVariable;
	}
	public String getTransName() {
		return transName;
	}
	public void setTransName(String transName) {
		this.transName = transName;
	}
	public String getTransKey() {
		return transKey;
	}
	public void setTransKey(String transKey) {
		this.transKey = transKey;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsJointlySign() {
		return isJointlySign;
	}
	public void setIsJointlySign(String isJointlySign) {
		this.isJointlySign = isJointlySign;
	}
	private String candidateUsers;



	public String getCandidateUsers() {
		return candidateUsers;
	}
	public void setCandidateUsers(String candidateUsers) {
		this.candidateUsers = candidateUsers;
	}
	
	private String type;



	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	private List<ActUserTaskModel> actUserTaskModels;



	public List<ActUserTaskModel> getActUserTaskModels() {
		return actUserTaskModels;
	}
	public void setActUserTaskModels(List<ActUserTaskModel> actUserTaskModels) {
		this.actUserTaskModels = actUserTaskModels;
	}


 
	
	
	 
   
   

}
