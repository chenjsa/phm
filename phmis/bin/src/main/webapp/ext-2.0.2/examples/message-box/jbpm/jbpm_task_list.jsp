<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org" %>
<%@ taglib prefix="yd" uri="http://www.santai.com/santai"%>

<html>
	<head>
		<title>待办任务</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<%@ include file="/jsp/common/pub_js_css.jsp"%>
	</head>
	<body>
		<table style="width:100%">
			<tr>
				<td>
					<div id="dataListTitle"  class="Noprint">
						<img src="${pageContext.request.contextPath}/images/icons/prompt.gif">&nbsp;待办任务
					</div>
					<div id="dataListInner" search="true"  class="Noprint">
						
					</div>
					<label id="info">
						<s:actionerror />
						<s:actionmessage />
					</label>
					<div id="dataArea">
					<fieldset>
						<legend class="Noprint">分组待办任务</legend>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<ec:table tableId="groupec" items="groupTaskList" var="record" width="100%"
										autoIncludeParameters="true" retrieveRowsCallback="limit"
										sortRowsCallback="limit" classic="true" toolbarLocation="top"
										useAjax="true" doPreload="false" showHeader="true"
										pageSizeList="5,10,20,40"
										action="${pageContext.request.contextPath}/jbpmTask!groupSearch.action"
										toolbarContent="navigation|pagejump|pagesize|status|refresh|export|extend"
										showPrint="true" resizeColWidth="true" xlsFileName="分组待办任务列表.xls"
										sortable="true" listHeight="100%" listWidth="100%"
										filterable="true">
										<ec:extend>
											<div align="right">
											</div>
										</ec:extend>
										<ec:row highlightRow="true" selectlightRow="true">																				
											<ec:column property="name" title="任务名称" width="10%"/>
											<ec:column property="description" title="描述" width="60%" sortable="false"/>
											<ec:column property="createTime" title="创建时间" width="20%"/>	
											
											<ec:column width="110" property="hh" title="操作"
												viewsDenied="xls,csv,pdf,print" sortable="false">
												<yd:purv keyWord="jbpmTask!complete">
													<img src="${pageContext.request.contextPath}/images/icons/task_group.gif"
														onClick="takeTask('${record.id}');" alt="接受任务"/>
												</yd:purv>
												<img src="${pageContext.request.contextPath}/images/icons/view.png"
													onClick="toView('${record.id}','${record.executionId}','${record.formResourceName}');" alt="数据详情"/>
											</ec:column>
										</ec:row>
									</ec:table>
								</td>
							</tr>
						</table>
					</fieldset>
					<fieldset>
						<legend class="Noprint">个人待办任务</legend>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<ec:table tableId="personec" items="personTaskList" var="record" width="100%"
										autoIncludeParameters="true" retrieveRowsCallback="limit"
										sortRowsCallback="limit" classic="true" toolbarLocation="top"
										useAjax="true" doPreload="false" showHeader="true"
										pageSizeList="5,10,20,40"
										action="${pageContext.request.contextPath}/jbpmTask!personSearch.action"
										toolbarContent="navigation|pagejump|pagesize|status|refresh|export|extend"
										showPrint="true" resizeColWidth="true" xlsFileName="个人待办任务列表.xls"
										sortable="true" listHeight="100%" listWidth="100%"
										filterable="true">
										<ec:extend>
											<div align="right">
											</div>
										</ec:extend>
										<ec:row highlightRow="true" selectlightRow="true">				
											<ec:column property="name" title="任务名称" width="10%"/>
											<ec:column property="description" title="描述" width="60%" sortable="false"/>
											<ec:column property="createTime" title="创建时间" width="20%"/>	
											
											<ec:column width="110" property="hh" title="操作"
												viewsDenied="xls,csv,pdf,print" sortable="false">
												<yd:purv keyWord="jbpmTask!complete">
													<img src="${pageContext.request.contextPath}/images/icons/task_person.gif"
														onClick="completeTask('${record.id}','${record.executionId}','${record.formResourceName}');" alt="${record.name}"/>
												</yd:purv>
												<img src="${pageContext.request.contextPath}/images/icons/view.png"
													onClick="toView('${record.id}','${record.executionId}','${record.formResourceName}');" alt="数据详情"/>
											</ec:column>
										</ec:row>
									</ec:table>
								</td>
							</tr>
						</table>
					</fieldset>
					</div>
				</td>
			</tr>
		</table>

		<script type="text/javascript">
		
			markUiElement();			
			
			//查询
			function search(){
				searchForm.submit();
			}
			
						
			//接受任务
			function takeTask(tId){
				var url = '${pageContext.request.contextPath}/jbpmTask!takeTask.action?id='+tId;
				ajaxSend(url,'确定接受该任务吗?');
			}
			
			//完成任务
			function completeTask(taskId, executionId, formResourceName){
				var keyArr = executionId.split(".");
				if(formResourceName.indexOf("?") > -1){
					formResourceName = formResourceName.substring(0, formResourceName.indexOf("?"));
				}
				var url = '${pageContext.request.contextPath}'+formResourceName + '?id='+keyArr[1] + "&taskId="+taskId;
				linkWindow(url);		
			}
			
			//查看
			function toView(taskId, executionId, formResourceName){
				var keyArr = executionId.split(".");
				if(formResourceName.indexOf("?") > -1){
					formResourceName = formResourceName.substring(0, formResourceName.indexOf("?"));
				}
				if(formResourceName.indexOf("!") > -1){
					formResourceName = formResourceName.substring(0, formResourceName.indexOf("!"));
				}
				var url = '${pageContext.request.contextPath}'+formResourceName + '!toView.action?id='+keyArr[1] + "&taskId="+taskId;
				linkWindow(url);
			}
			
			//确认、审核、回复成功
			function riskEventSaveSucc(){
				refresh();
			}	
								
			//刷新 必须命名为refresh()
			function refresh(){				
				ECSideUtil.gotoPageByInput(groupec.groupec_pg,'groupec');
				ECSideUtil.gotoPageByInput(personec.personec_pg,'personec');
			}
			
			//刷新
			if('refresh' == '${refreshFlag}'){
				refresh();
			}
			
			
		</script>

	</body>
</html>
