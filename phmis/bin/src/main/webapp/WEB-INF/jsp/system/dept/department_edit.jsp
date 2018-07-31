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
		<!-- jsp文件头和头部 -->
		<%@ include file="../../system/index/top.jsp"%>
		
	
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
					
					<form action="department/save.do" name="Form" id="Form" method="post">
						<input type="hidden" name="DEPARTMENT_ID" id="DEPARTMENT_ID" value="${pd.DEPARTMENT_ID}"/>
						<input type="hidden" name="PARENT_ID" id="PARENT_ID" value="${pd.PARENT_ID}"/>
						<div id="zhongxin">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:100px;text-align: right;padding-top: 13px;">上级:</td>
								<td>
									<div class="col-xs-4 label label-lg label-light arrowed-in arrowed-right">
										<b>${null == pds.deptName ?'(无) 此部门为顶级':pds.deptName}</b> 
										<input type="hidden" name="id" value="${pd.entity.id}"> 
									    <input type="hidden" name="casCode" value="${pd.entity.casCode}">
									    <input type="hidden" name="parentId" value="${pds.id}">
									    <input type="hidden" name="parentNo" value="${pd.entity.casCode}">
									</div>
								</td>
							</tr> 
							<tr>
								<td style="width:80px;text-align: right;padding-top: 13px;">编号:</td>
								<td><input type="text"  maxlength="5"  name="deptNo" id="deptNo" value="${pd.entity.deptNo}" maxlength="50"  maxlength="5"  placeholder="机构编号" title="机构编号" onblur="hasCode();" <c:if test="${null != pd.entity.deptNo}">readonly="readonly"</c:if> style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:80px;text-align: right;padding-top: 13px;">名称:</td>
								<td><input type="text" name="deptName" id="deptName" value="${pd.entity.deptName}" maxlength="100" placeholder="机构名称" title="编码" style="width:76%;" /></td>
							</tr> 
							<tr>
								<td style="width:80px;text-align: right;padding-top: 13px;">联系人:</td>
								<td><input type="text" name="deptContacts" id="deptContacts" value="${pd.entity.deptContacts}" maxlength="100" placeholder="" title="编码" style="width:76%;" /></td>
							</tr> 
							<tr>
								<td style="width:80px;text-align: right;padding-top: 13px;">联系电话:</td>
								<td><input type="text" name="deptContactsTel" id="deptContactsTel" value="${pd.entity.deptContactsTel}" maxlength="100" placeholder="" title="编码" style="width:76%;" /></td>
							</tr> 
							<tr>
								<td style="width:80px;text-align: right;padding-top: 13px;">地址:</td>
								<td><input type="text" name="deptAddr" id="deptAddr" value="${pd.entity.deptAddr}" maxlength="100" placeholder="机构名称" title="编码" style="width:76%;" /></td>
							</tr> 
							<tr>
								<td style="width:80px;text-align: right;padding-top: 13px;">其他:</td>
								<td><input type="text" name="deptOther" id="deptOther" value="${pd.entity.deptOther}" maxlength="100" placeholder="机构名称" title="编码" style="width:76%;" /></td>
							</tr> 
						  
							<tr>
								<td style="width:80px;text-align: right;padding-top: 13px;">描述:</td>
								<td>
									<textarea rows="3" cols="46" name="text" id="text" placeholder="这里输入备注"  maxlength="255" title="备注"  style="width:98%;">${pd.entity.text}</textarea>
								</td>
							</tr>
							<tr>
								<td class="center" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
								</td>
							</tr>
						</table>
						</div>
						
						<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
						
					</form>
	
					<div id="zhongxin2" class="center" style="display:none"><img src="static/images/jzx.gif" style="width: 50px;" /><br/><h4 class="lighter block green"></h4></div>
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
	<%@ include file="../../system/index/foot.jsp"%>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		//保存
		function save(){
if($("#deptNo").val()==""){
				$("#deptNo").tips({
					side:3,
		            msg:'机构编号不能为空',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#deptNo").focus();
			return false;
		}
			if($("#deptNo").val().length>5){
				$("#deptNo").tips({
					side:3,
		            msg:'机构编号超长最多为5位',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#deptNo").focus();
			return false;
		}
			if($("#deptName").val()==""){
				$("#deptName").tips({
					side:3,
		            msg:'请输入机构名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#deptName").focus();
			return false;
		}

if($("#deptName").val().length>100){
				$("#deptName").tips({
					side:3,
		            msg:'机构名称超长不能大于100个字符1个汉字为2个字符',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#deptName").focus();
			return false;
		}
			 
 
	if($("#text").val().length>300){
				$("#remark").tips({
					side:3,
		            msg:'电话超长最多输入300个字符，1个汉字2为2个字符',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#text").focus();
			return false;
		}
		hasCode();
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
			
		}
		
		//判断编码是否存在
		function hasCode(){
			var CODE = $.trim($("#deptNo").val()); 
			if("" == CODE)return;
			$.ajax({
				type: "POST",
				url: '<%=basePath%>department/hasCode.do',
		    	data: {CODE:CODE,tm:new Date().getTime()},
				dataType:'json',
				cache: false,
				success: function(data){
					 if("success" == data.result){
					 }else{
						$("#deptNo").tips({
							side:1,
				            msg:'编码'+CODE+'已存在,重新输入',
				            bg:'#AE81FF',
				            time:5
				        });
						$('#deptNo').val('');
						$('#deptNo').focus();
					 }
				}
			});
		}
		</script>
</body>
</html>