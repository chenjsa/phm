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
						<form action="user/move.do" name="userForm" id="userForm" method="post">
						 	 <input type="hidden" name="id" value="${entity.id}">  
							<div id="zhongxin" style="padding-top: 13px;">
							<table id="table_report" class="table table-striped table-bordered table-hover">
							 
							 
								<tr>
									<td style="width:89px;text-align: right;padding-top: 13px;">用户名:</td>
									<td> ${entity.no }</td>
								</tr>	
								<tr>
									<td style="width:89px;text-align: right;padding-top: 13px;">姓名:</td>
									<td>${entity.name}</td>
								</tr>	
								<tr>
									<td style="width:89px;text-align: right;padding-top: 13px;">机构:</td>
									<td> 
									   <input type="hidden" name="deptId" id="deptId" value="${entity.deptId}"/>
									<input type="text" readonly name="sysCode" id="sysCode" value="${dept.sysCode }" maxlength="32" placeholder="这里输入机构" title="机构"  style="width:58%;" />
									
									 <button onClick="goSelectDept('${entity.id}');" type="button" class="btn btn-default btn-sm">
          <span class="glyphicon glyphicon-list-alt"></span>  
        </button>
								 		<span id="newDeptIdTip"></span>
										<span id="newDeptNameSpan">${dept.deptName}</span>
										<br>注意：更换机构后该用户将自动删除新机构所不具有的角色！
									</td>
							 	 </tr>
								 
								<tr>
									<td class="center" colspan="2">
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
		var zTree;
		var roleTree;
		$(document).ready(function(){
			var setting = {
			    showLine: true,
			    checkable: true
			};
			setting.checkType = {"Y":"", "N":""};
			var zn = '${zTreeNodes}'; 
			var zTreeNodes = eval(zn);
			zTree = $("#leftTree").zTree(setting, zTreeNodes);
			zTree.expandAll(true);
			//角色树
			var settingRole = {
			    showLine: true,
			    checkable: true
			};
			settingRole.checkType = {"Y":"", "N":""};
			var znRole = '${roleZTreeNodes}'; 
			var roleZTreeNodes = eval(znRole);
			roleTree = $("#roleTree").zTree(settingRole, roleZTreeNodes);
			roleTree.expandAll(true);
			
		});
	
		function treeFrameT(){
			var hmainT = document.getElementById("treeFrame");
			var bheightT = document.documentElement.clientHeight;
			hmainT .style.width = '100%';
			hmainT .style.height = (bheightT-26) + 'px';
		}
		treeFrameT();
		window.onresize=function(){  
			treeFrameT();
		};
		//保存
	function save(){ ;
		 
		$("#userForm").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
	
	//判断编码是否存在
	function hasN(USERNAME){
		var NUMBER = $("#noId").val();
		if(NUMBER=="")
			return;
		$.ajax({
			type: "POST",
			url: '<%=basePath%>user/hasU.do',
	    	data: {no:NUMBER,USERNAME:USERNAME,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" != data.result){
					 $("#noId").tips({
							side:3,
				            msg:'账号'+NUMBER+'已存在',
				            bg:'#AE81FF',
				            time:3
				        });
					 $('#noId').val('');
					 $('#noId').focus();
				 }
			}
		});
	}
	
	//选择机构
	function goSelectDept(Id){
	
	 top.jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="选择机构";
	 diag.URL = '<%=basePath%>user/toRadioTree.do?id='+Id;
	 diag.Width = 400;
	 diag.Height = 386;
	 diag.CancelEvent = function(){ //关闭事件
		 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
		   	 var checkNode=diag.innerFrame.contentWindow.document.getElementById('checkNode').value;
		   	 var checkName=diag.innerFrame.contentWindow.document.getElementById('checkName').value;
		   	 var deptId=diag.innerFrame.contentWindow.document.getElementById('checkId').value;
		  	// alert(checkNode);
		  	 $("#sysCode").val(checkNode);
		  	 $("#newDeptNameSpan").html(checkName);
		  	 $("#deptId").val(deptId);
			/// window.location.href="<%=basePath%>user/list.do?DEPARTMENT_ID=${DEPARTMENT_ID}&dnowPage=${page.currentPage}";
		}
		diag.close();
	 };
	 diag.show(); 
	}
</SCRIPT>
</html>