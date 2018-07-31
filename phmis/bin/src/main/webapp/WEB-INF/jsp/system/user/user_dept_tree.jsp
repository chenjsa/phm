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
	<script src="/static/ace/js/sweetalert/sweetalert.min.js"></script>
	<link href="/static/ace/js/sweetalert/sweetalert.css" rel="stylesheet">      
</head>
<body class="no-skin">
  
										<div id="ManageDeptTree"  class="panel panel-default" style="height:270px;border :1px solid Silver; overflow:auto;">
											<ul id="leftTree" class="tree"></ul>
										</div>
								 
									    <input type="hidden" name="checkNode" id="checkNode"/>
									    <input type="hidden" name="checkName" id="checkName"/>
									    <input type="hidden" name="checkId" id="checkId"/> 
									 
							 
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
	<link href="/static/ace/css/iCheck/custom.css" rel="stylesheet"> 
		<script src="/static/ace/js/icheck.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
<script type="text/javascript">
		$(top.hangge());
		var zTree;
		var roleTree;
		$(document).ready(function(){
			var setting = {
			    showLine: true,
			    checkable : true,
			    checkStyle : "checkbox",
			    checkRadioType : "all"
			};
			setting.chkStyle = "radio";
			setting.checkType = {"Y":"", "N":""};
			var zn = '${zTreeNodes}'; 
			var zTreeNodes = eval(zn);
			zTree = $("#leftTree").zTree(setting, zTreeNodes);
			zTree.expandAll(true); 
			
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
	function selectDept(){ ;
		 var  nodes=zTree.getCheckedNodes(true);
		 var deptName="";
         var deptStr="";  
         var deptId="";
		 for (var j = 0; j < nodes.length; j++) {
       	    
			 
			  var node = nodes[j];
			  if(j==nodes.length-1){
			 		deptStr=deptStr+ node.casCode;
				    deptName=deptName+node.name;
			 		deptId=deptId+node.id;
			  }else{
			 	 	deptStr=deptStr+ node.casCode+",";
			 		deptName=deptName+node.name+",";
			 		deptId=deptId+node.id+",";
			 }
		                       
		 }
		 if(deptStr == ''){//没有
			swal("", "请选择一个机构！", "error");
		 }else{
		 	$("#checkNode").val(deptStr);
		 	$("#checkName").val(deptName);
		 	$("#checkId").val(deptId);	
		 	return {deptId:deptId,deptName:deptName,casCode:deptStr};	 	
		 } 
		/// alert(deptName);
	//	 $("#zhongxin").hide();
	//	 $("#zhongxin2").show()
		/// top.$("#sysCode").val(deptStr);
		 top.Dialog.close();
		///$("#userForm").submit();
		//$("#zhongxin").hide();
		//$("#zhongxin2").show();
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
</SCRIPT>
</html>