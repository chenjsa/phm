<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">
	<!-- 下拉框 -->
	<link rel="stylesheet" href="static/ace/css/chosen.css" />
	<!-- jsp文件头和头部 -->
	<%@ include file="../../../system/index/top.jsp"%>
	<!-- 日期框 -->
	<link rel="stylesheet" href="static/ace/css/datepicker.css" />
</head>
<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					
					<form action="leave/${msg}.do" name="Form" id="Form" method="post">
						<input type="hidden" name="LEAVE_ID" id="LEAVE_ID" value="${pd.LEAVE_ID}"/>
						<input type="hidden" name="APPLY_USER_ID" id="APPLY_USER_ID" value="${pd.APPLY_USER_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:110px;text-align: right;padding-top: 13px;">申请人:</td>					        
						        <td>
                                    <div class="col-xs-4 label label-lg label-light arrowed-in arrowed-right">
                                        <b>${null == pd.NAME ?'(无) ':pd.NAME}</b>
                                    </div>
                                </td>
                                <td style="width:110px;text-align: right;padding-top: 13px;">请假类型:</td>  
								<td id="leaveType">
                                    <select class="chosen-select form-control" name="LEAVE_TYPE" id="LEAVE_TYPE" data-placeholder="请选择请假类型" style="vertical-align:top;"  title="请假类型" style="width:98%;" >
                                    <option value=""></option>
                                    <c:forEach items="${dictionariesList}" var="dict">
                                        <option value="${dict.CODE }" <c:if test="${dict.CODE == pd.LEAVE_TYPE }">selected</c:if>>${dict.NAME }</option>
                                    </c:forEach>
                                    </select>
                                </td>   
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">请假开始时间:</td>
								<td><input class="span10 date-picker" name="START_DATE" id="START_DATE" value="${pd.START_DATE}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="请假开始时间" title="请假开始时间" style="width:98%;"/></td>
								<td style="width:75px;text-align: right;padding-top: 13px;">请假结束时间:</td>
								<td><input class="span10 date-picker" name="END_DATE" id="END_DATE" value="${pd.END_DATE}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="请假结束时间" title="请假结束时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">请假事由:</td>
							    <td colspan="3">
                                    <textarea rows="6" cols="50" name="REASON" id="REASON" placeholder="这里输入请假事由" title="请假事由"  style="width:98%;">${pd.REASON}</textarea>
                                </td>
							</tr>
							<%-- <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">主管审批人:</td>
								<td><input type="text" name="MANAGER_ID" id="MANAGER_ID" value="${pd.MANAGER_ID}" maxlength="100" placeholder="这里输入主管审批人" title="主管审批人" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">审批说明:</td>
								<td><input type="text" name="REMARKS1" id="REMARKS1" value="${pd.REMARKS1}" maxlength="100" placeholder="这里输入审批说明" title="审批说明" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">部门审批人:</td>
								<td><input type="text" name="LEADER_ID" id="LEADER_ID" value="${pd.LEADER_ID}" maxlength="100" placeholder="这里输入部门审批人" title="部门审批人" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">审批说明:</td>
								<td><input type="text" name="REMARKS2" id="REMARKS2" value="${pd.REMARKS2}" maxlength="100" placeholder="这里输入审批说明" title="审批说明" style="width:98%;"/></td>
							</tr> --%>
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
								</td>
							</tr>
						</table>
						  <table>
						      <tr>
                                            <td style="padding-left: 16px;" colspan="100">
                                                <font color="red" style="font-weight: bold;">
                                                    注意：<br/>
                                                      1. 请假申请人默认为本人，请不要代他人请假。<br/>
                                                      2. 部门领导审批过后，系统会自动核算销假（开发中……by zz）。
                                                </font>
                                            </td>
                               </tr>
						  </table>
						</div>
						<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
					</form>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->
</div>
<!-- /.main-container -->


	<!-- 页面底部js¨ -->
	<%@ include file="../../../system/index/foot.jsp"%>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		//保存
		function save(){
			if($("#APPLY_USER_ID").val()==""){
				$("#APPLY_USER_ID").tips({
					side:3,
		            msg:'请输入申请人ID',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#APPLY_USER_ID").focus();
			return false;
			}
			if($("#LEAVE_TYPE").val()==""){
				$("#LEAVE_TYPE").tips({
					side:3,
		            msg:'请输入请假类型',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#LEAVE_TYPE").focus();
			return false;
			}
			if($("#APPLY_DATE").val()==""){
				$("#APPLY_DATE").tips({
					side:3,
		            msg:'请输入申请时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#APPLY_DATE").focus();
			return false;
			}
			if($("#START_DATE").val()==""){
				$("#START_DATE").tips({
					side:3,
		            msg:'请输入请假开始时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#START_DATE").focus();
			return false;
			}
			if($("#END_DATE").val()==""){
				$("#END_DATE").tips({
					side:3,
		            msg:'请输入请假结束时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#END_DATE").focus();
			return false;
			}
			if($("#REASON").val()==""){
				$("#REASON").tips({
					side:3,
		            msg:'请输入请假事由',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#REASON").focus();
			return false;
			}
			if($("#MANAGER_ID").val()==""){
				$("#MANAGER_ID").tips({
					side:3,
		            msg:'请输入主管审批人',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#MANAGER_ID").focus();
			return false;
			}
			if($("#REMARKS1").val()==""){
				$("#REMARKS1").tips({
					side:3,
		            msg:'请输入审批说明',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#REMARKS1").focus();
			return false;
			}
			if($("#LEADER_ID").val()==""){
				$("#LEADER_ID").tips({
					side:3,
		            msg:'请输入部门审批人',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#LEADER_ID").focus();
			return false;
			}
			if($("#REMARKS2").val()==""){
				$("#REMARKS2").tips({
					side:3,
		            msg:'请输入审批说明',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#REMARKS2").focus();
			return false;
			}
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>