<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>保存结果</title>
<base href="<%=basePath%>">
<meta name="description" content="overview & stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>

</head>
<body>
	<div id="zhongxin"></div>
	<script type="text/javascript">
		var msg = "${msg}";
		if(msg=="success" || msg==""|| msg=="OK"){
			document.getElementById('zhongxin').style.display = 'none';
			top.Dialog.close();
			 
			//工作流打开模型设计器
			var act = "${act}";
			if(act=="addModel"){
				var modelId = "${modelId}";
				
				var pathName = window.location.pathname.substring(1);	
	  			var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));			
	  			if (webName == "") {
	    			alert("无法打开模型设计器，请联系系统运维管理员");
	  			}
	  			else {
	   				 window.open(window.location.protocol + '//' + window.location.host + '/' + webName+"/act-process-editor/modeler.html?modelId=" + modelId);
	  			}						
			}else{
				top.Dialog.close();
			}
		}
	</script>
</body>
</html>