<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org" %>
<%@ taglib prefix="yd" uri="http://www.santai.com/santai"%>
<%@ page import="org.jbpm.api.model.ActivityCoordinates" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<head>
		<title>历史信息</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<%@ include file="/jsp/common/pub_js_css.jsp"%>
	</head>
	<body>
		<table id="tb" style="width:100%;">
			<tr>
				<td>
					<div id="dataListTitle">
						<img src="${pageContext.request.contextPath}/images/icons/prompt.gif">&nbsp;流程详情
					</div>
					<div><center><button onClick="closeWin();window.close();">关闭</button></center></div>
					<label id="info">
						<s:actionerror />
						<s:actionmessage />
					</label>
						<s:property value="HTAList"/>
						<table width="100%" border="0" cellpadding="2" cellspacing="2" id="dataArea">
							<tr>
								<td>
									<ec:table tableId="hisTaskEC" items="taList" var="record" width="100%"
										autoIncludeParameters="true" retrieveRowsCallback="limit"
										sortRowsCallback="limit" classic="true" toolbarLocation="top"
										useAjax="true" doPreload="false" showHeader="true"
										pageSizeList="5,10,20,40"
										action="${pageContext.request.contextPath}/jbpmHistTask!getHistoryTask.action"
										toolbarContent="navigation|pagejump|pagesize|status|refresh|export|extend"
										showPrint="false" resizeColWidth="true" xlsFileName="历史信息列表.xls"
										sortable="true" listHeight="100%" listWidth="100%"
										filterable="true">
										<ec:extend>
											<div align="right">
											</div>
										</ec:extend>
										<ec:row highlightRow="true" selectlightRow="true">				
										<c:if test="${record.ha_ACTIVITY_NAME=='exclusive1'}">
										  <ec:column property="ha_ACTIVITY_NAME" title="名称" width="20%" sortable="false">
													系统判定
											</ec:column>
										</c:if>
										<c:if test="${record.ha_ACTIVITY_NAME=='exclusive2'}">
										   <ec:column property="ha_ACTIVITY_NAME" title="名称" width="20%" sortable="false">
													系统判定
											</ec:column>
										</c:if>
										<c:if test="${record.ha_ACTIVITY_NAME!='exclusive1'}">
										   <c:if test="${record.ha_ACTIVITY_NAME!='exclusive2'}">
										    <ec:column property="ha_ACTIVITY_NAME" title="名称" width="15%" sortable="true"/>
										  </c:if>
										</c:if>
											
											<ec:column property="ha_TRANSITION" title="去向" width="15%" sortable="true"/>
											<c:if test="${record.ha_ACTIVITY_NAME=='exclusive1'}">
										 <ec:column property="ht_ASSIGNEE" title="处理人" width="20%" sortable="false">
													系统 
											</ec:column>
										</c:if>
										<c:if test="${record.ha_ACTIVITY_NAME=='exclusive2'}">
										   <ec:column property="ht_ASSIGNEE" title="处理人" width="20%" sortable="false">
													系统 
											</ec:column>
										</c:if>
										<c:if test="${record.ha_ACTIVITY_NAME!='exclusive1'}">
										   <c:if test="${record.ha_ACTIVITY_NAME!='exclusive2'}">
										    <ec:column property="ht_ASSIGNEE" title="处理人" width="15%"/>	
										  </c:if>
										</c:if>
												<c:if test="${record.ha_ACTIVITY_NAME=='exclusive1'}">
										  <ec:column property="ht_STATE" title="状态" width="20%" sortable="false">
													 完成
											</ec:column>
										</c:if>
										<c:if test="${record.ha_ACTIVITY_NAME=='exclusive2'}">
										  <ec:column property="ht_STATE" title="状态" width="20%" sortable="false">
													 完成
											</ec:column>
										</c:if>
										<c:if test="${record.ha_ACTIVITY_NAME!='exclusive1'}">
										   <c:if test="${record.ha_ACTIVITY_NAME!='exclusive2'}">
										   	<ec:column property="ht_STATE" title="状态" width="15%" sortable="false"/>
										  </c:if>
										</c:if>
										
											<ec:column property="ha_START" title="开始时间" width="20%" sortable="false">
													${fn:substring(record.ha_START,0,19) }
											</ec:column>
											<ec:column property="ha_END" title="结束时间" width="20%" sortable="false">
													${fn:substring(record.ha_END,0,19) }
											</ec:column>
										</ec:row>
									</ec:table>
								</td>
							</tr>
						</table>
				</td>
			</tr>
		</table>
		<div id="div_img" style="height:20px;overflow-x:auto; ">
		<center>
			<img onload="initPage();" style="border: solid;border-width:5px;border-color: #E2E2E2;" id="imgProcee" src="${pageContext.request.contextPath}/jbpmHistTask!getPng.action?execute=<s:property value="execute"/>" style="margin-top:10px; left:0px;top:0px">
			<c:if test="${not empty ac}">
				<div id="divFocus" style="position:absolute; border:1px solid red; left:${ac.x}px;top:${ac.y}px;width:${ac.width}px; height:${ac.height}px;"></div>
			</c:if>
		</center>
		</div>
		<div>
		&nbsp;
		</div>
		<script type="text/javascript">
			markUiElement();	
	
			function initPage(){
				<c:if test="${empty ac}">
					return;
				</c:if>
				var top = document.getElementById("div_img").offsetTop;
				var img_ = document.getElementById("imgProcee");
				var img_div = document.getElementById("divFocus");
				/*if(720-top>img_.height){
					if(img_.height>720-top-20){
						document.getElementById("div_img").style.overflowY="auto";	
					}else{
						document.getElementById("div_img").style.overflowY="hidden";
					}
					document.getElementById("div_img").style.height = img_.height;
				}else{
					if(img_.height<720-top-20){
						document.getElementById("div_img").style.overflowY="hidden";
					}else{
						document.getElementById("div_img").style.overflowY="auto";	
					}
					document.getElementById("div_img").style.height = img_.height;
				}
				*/
				document.getElementById("div_img").style.height = img_.height+20;
				img_div.style.top = (img_div.offsetTop + img_.offsetTop + 5)+ "px";
				img_div.style.left = (img_div.offsetLeft + img_.offsetLeft +5)+ "px";
			}
			
		</script>

	</body>
</html>
