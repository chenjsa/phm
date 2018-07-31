<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<!DOCTYPE html>
<html>
 <head>
  <title>任务处理</title> 
   
	  <style type="text/css">
   	#t_table td label {font-size:15px;}
   	.blueButton{
	  display: inline-block;
	  *display: inline;
	  padding: 4px 12px;
	  margin-bottom: 0;
	  *margin-left: .3em;
	  font-size: 14px;
	  line-height: 20px;
	  text-align: center;
	  vertical-align: middle;
	  cursor: pointer;
	  border: 1px solid #cccccc;
	  *border: 0;
	  border-bottom-color: #b3b3b3;
	  -webkit-border-radius: 4px;
	     -moz-border-radius: 4px;
	          border-radius: 4px;
	  *zoom: 1;
	  -webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
	     -moz-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
	          box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
	  color: #ffffff;
	  text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
	  background-color: #006dcc;
	  *background-color: #0044cc;
	  background-image: -moz-linear-gradient(top, #0088cc, #0044cc);
	  background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#0088cc), to(#0044cc));
	  background-image: -webkit-linear-gradient(top, #0088cc, #0044cc);
	  background-image: -o-linear-gradient(top, #0088cc, #0044cc);
	  background-image: linear-gradient(to bottom, #0088cc, #0044cc);
	  background-repeat: repeat-x;
	  border-color: #0044cc #0044cc #002a80;
	  border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
	  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff0088cc', endColorstr='#ff0044cc', GradientType=0);
	  filter: progid:DXImageTransform.Microsoft.gradient(enabled=false);
}

.blueButton:hover,
.blueButton:focus,
.blueButton:active,
.blueButton.active,
.blueButton.disabled,
.blueButton[disabled] {
  color: #ffffff;
  background-color: #0044cc;
  *background-color: #003bb3;
}
	
	.disabledButton{ 
		display: inline-block;
	  *display: inline;
	  padding: 4px 12px;
	  margin-bottom: 0;
	  *margin-left: .3em;
	  font-size: 14px;
	  line-height: 20px;
	  text-align: center;
	  vertical-align: middle;
	  cursor: pointer;
	  border: 1px solid #cccccc;
	  *border: 0;
	  border-bottom-color: #b3b3b3;
	  -webkit-border-radius: 4px;
	     -moz-border-radius: 4px;
	          border-radius: 4px;
	  *zoom: 1;
	  -webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
	     -moz-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
	          box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
	  color: #ffffff;
	  text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
	  background-color: #BDBEC0;
	  *background-color: #BDBEC0;
	  background-image: -moz-linear-gradient(top, #BDBEC0, #BDBEC0);
	  background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#BDBEC0), to(#0044cc));
	  background-image: -webkit-linear-gradient(top, #BDBEC0, #BDBEC0);
	  background-image: -o-linear-gradient(top, #BDBEC0, #BDBEC0);
	  background-image: linear-gradient(to bottom, #BDBEC0, #BDBEC0);
	  background-repeat: repeat-x;
	  border-color: #BDBEC0 #BDBEC0 #BDBEC0;
	  border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
	  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#BDBEC0', endColorstr='#BDBEC0', GradientType=0);
	  filter: progid:DXImageTransform.Microsoft.gradient(enabled=false);
	}
	
	body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	font-size:12px;
	/* background-image: url(resources/fc/images/bj.jpg); */
}
.formtable .inputxt{
    /*update-begin author:zhoujf date:20180412 for:注释格式不对导致样式不生效*/
	/* update-begin--Author:scott--- Date:20180327---for：shortcut风格下输入框蓝色边框---- */
	border:1px solid #54A5D5;
	/* update-end--Author:scott--- Date:20180327---for：shortcut风格下输入框蓝色边框---- */
	/*update-end author:zhoujf date:20180412 for:注释格式不对导致样式不生效*/
	width:150px;
	padding:3px 2px;
}
.formtable select{
	/* author:scott date:2017-08-09 for: ie8下 select无边框 */
	/*update-begin author:zhoujf date:20180412 for:注释格式不对导致样式不生效*/
	/* update-begin--Author:scott--- Date:20180327---for：shortcut风格下输入框蓝色边框---- */
	border:1px solid #54A5D5;
	/* update-end--Author:scott--- Date:20180327---for：shortcut风格下输入框蓝色边框---- */
	/*update-end author:zhoujf date:20180412 for:注释格式不对导致样式不生效*/
	padding:3px 2px;
	width:155px;
}
.Validform_label {
	font-size: 12px;
	font-weight: bold;
	color: #5E7595;
	padding: 5px;
	white-space:nowrap;
}
.formtable
{
	width:100%;
	background-color:#B8CCE2;
	align:right;
}
.main_table{margin:6px auto;  border-left:1px solid #d3d3d3; border-top:1px solid #d3d3d3}
.main_table td{line-height:26px; padding:2px; border-bottom:1px solid #d3d3d3; border-right:1px solid #d3d3d3}
.table_title{background:#f7f7f7; padding:6px; text-align:center; font-weight:bold}
.formtable tr 
{ 
	background-color:#F2F7FE;
	align:right;
}
.value
{
	background-color:#FFFFFF;
	padding:5px;
	align:left; 
	align:left; 
}

 
#tdid{
padding-top: 13px;border-top:1px dashed #00CCCC; font-size:15px;
}
	
  </style> 
    <link href="/static/ace/css/style.min.css" rel="stylesheet">
	<link href="/static/ace/css/bootstrap.min1.css" rel="stylesheet"> 		
	<link href="/static/ace/css/ui.jqgrid.css" rel="stylesheet">  
	<link href="/static/ace/js/sweetalert/sweetalert.css" rel="stylesheet">      
	<script src="/static/ace/js/ace-extra.js"></script>
	<script type="text/javascript" src="/static/ace/js/jquery.js"></script> 
	<script type="text/javascript" src="/static/ace/js/jqGrid/jquery.jqGrid.min.js"></script>
	<script src="/static/ace/js/jquery.peity.min.js"></script>
	<script type="text/javascript" src="/static/ace/js/jqGrid/i18n/grid.locale-cn.js"></script>
	<script src="/static/ace/js/bootstrap.js"></script> 
	<script src="/static/ace/js/sweetalert/sweetalert.min.js"></script>
	<link href="/static/ace/css/iCheck/custom.css" rel="stylesheet"> 
	<script src="/static/ace/js/icheck.min.js"></script>
	<script src="/static/js/util.js"></script>  
	<script type="text/javascript" src="/plugins/layer/3.0/layer.js"></script>
 </head>
 <body>
   
    	 		<table id="table_report" class="table  table-bordered">
	    	 <tr height="35"> 
	    	 	<td id="tdid"  >
	    	 			 <label class="Validform_label" style="font-size:16px;">意见信息</label>
	    	 	</td>
	    	 </tr>
	          
		     <c:forEach items="${varList}" var="var" varStatus="vs">
		     	 
		     	<tr height="35"> 
		     		<td   id="tdid" > 
		     	    <font  style="font-size:16px;">  
		     	     
		     	 <a href="#">    <span class="glyphicon glyphicon-tasks"></span><span style="color:blue"> [${var.taskNode}] </span> </a>
         	          ${var.outComeName}(${var.auditContent}) </font>
         	        		     			
		     	  <br>
		     			<font  style="font-size:16px;"> ${var.handTime}[操作人：${var.handleUserName}] </font>
		     		</td>
		     	</tr>
		       
		     	</c:forEach>
		          
		     		 
		     <tr height="35" >
		     	<td class="value" style="padding: 0px 5px;">
		     		 <label class="Validform_label" style="font-size:16px;">
				      	处理意见<p></p>
				     </label>
				     <textarea ID="auditContent" name="auditContent" class="form-control" rows="3"></textarea> 
		     		<span class="Validform_checktip"></span>
		     	</td>
		     </tr>
		   
		 
		 	</table>
     
  
 </body>
</html>
