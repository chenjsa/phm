<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
<link type="text/css" rel="stylesheet" href="plugins/zTree/2.6/zTreeStyle.css"/>
<script type="text/javascript" src="plugins/zTree/2.6/jquery.ztree-2.6.min.js"></script> 
<!-- 下拉框 -->
<link rel="stylesheet" href="static/ace/css/chosen.css" />
<!-- jsp文件头和头部 -->
<%@ include file="../index/top.jsp"%>
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
						<form action="sysOperation/save.do" name="sysOperationForm" id="sysOperationForm" method="post">
							<input type="hidden" name="id" value="${entity.id}"> 
							<div id="zhongxin" style="padding-top: 13px;">
							<table id="table_report" class="table table-striped table-bordered table-hover"> 	
							 <tr>								
									<td style="width:89px;text-align: right;padding-top: 13px;">
										菜单：
									</td>
									<td class="controlcontent" >
										 ${menu.menuName}
								 	</td>									
								</tr>		  		
							    <tr>								
									<td style="width:89px;text-align: right;padding-top: 13px;">
										编码：
									</td>
									<td class="controlcontent" >
										<input type="text" id="btnCode" name="btnCode" onblur="vaild_btnCode();";  placeholder="按钮编码" title="btnCode" style="width:98%;" value="${entity.btnCode}"/>
								 	</td>									
								</tr>			 
							    <tr>								
									<td style="width:89px;text-align: right;padding-top: 13px;">
										名称：
									</td>
									<td class="controlcontent" >
										<input type="text" id="btnName" name="btnName" onblur="vaild_btnName();";  placeholder="按钮名称" title="btnName" style="width:98%;" value="${entity.btnName}"/>
								 	</td>									
								</tr>			
								  <tr>								
									<td style="width:89px;text-align: right;padding-top: 13px;">
										描述：
									</td>
									<td class="controlcontent" >
										<input type="text" id="depict" name="depict"    placeholder="描述" title="depict" style="width:98%;" value="${entity.depict}"/>
								 	</td>									
								</tr>			
								  <tr>								
									<td style="width:89px;text-align: right;padding-top: 13px;">
										备注：
									</td>
									<td class="controlcontent" >
										<input type="text" id="remark" name="remark"  placeholder="功能点名称" title="remark" style="width:98%;" value="${entity.remark}"/>
								 	</td>									
								</tr>			
								
										<input type="hidden" id="menuId" name="menuId" onblur="vaild_menuId();";  placeholder="输入menuId" title="menuId" style="width:98%;" value="${entity.menuId}"/>
								 
								<tr>
									<td class="center" colspan="6">
										<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
										<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
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
</body>		 
	<!-- /.main-container -->
	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../index/foot.jsp"%>
	<!-- ace scripts --> 
	<!-- inline scripts related to this page -->
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
<script type="text/javascript">
		$(top.hangge());
	 
		//保存
	function save(){ ;
	 	vaild_btnCode();
	 	vaild_btnName();
	 	vaild_menuId();

		$("#sysOperationForm").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	  /***
	    验证字段btnCode是否为空或别的验证逻辑都写在这里
	  ***/	
	  function vaild_btnCode(){
	     if($("#btnCode").val()==""){
			$("#btnCode").tips({
				side:3,
	            msg:'btn_code为必填项',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#btnCode").focus();
			return false;
		}
	  }
	  /***
	    验证字段btnName是否为空或别的验证逻辑都写在这里
	  ***/	
	  function vaild_btnName(){
	     if($("#btnName").val()==""){
			$("#btnName").tips({
				side:3,
	            msg:'btn_name为必填项',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#btnName").focus();
			return false;
		}
	  }
	  /***
	    验证字段menuId是否为空或别的验证逻辑都写在这里
	  ***/	
	  function vaild_menuId(){
	     if($("#menuId").val()==""){
			$("#menuId").tips({
				side:3,
	            msg:'menu_id为必填项',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#menuId").focus();
			return false;
		}
	  }

	
</SCRIPT>
</html>
