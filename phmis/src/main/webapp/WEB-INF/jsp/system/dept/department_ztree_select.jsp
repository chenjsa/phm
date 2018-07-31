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
	
	<link rel="stylesheet" href="static/ace/css/bootstrap.css" />
	
	<link rel="stylesheet" href="plugins/zTree/3.5/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="plugins/zTree/3.5/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="plugins/zTree/3.5/jquery.ztree.excheck-3.5.js"></script>
	
	<SCRIPT type="text/javascript">
		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeCheck: cancelOtherCheck
			}
		};
		
		function cancelOtherCheck(treeId, treeNode, clickFlag) {
			zTreeObject.checkAllNodes(false);
		    //alert(treeNode.checked);
			//return !treeNode.isParent;//当是父节点 返回false 不让选取
			//var nodes = zTreeObject.getCheckedNodes(true);
			//console.log("nodes:"+nodes);
			//$.each(nodes,function(i,item) {
			    //designIds.push(item.id);  
			    //item.checkedOld = item.checked;
			    //console.log("item.checkedOld:"+item.checkedOld);
			    //console.log("nodes[i].checked:"+nodes[i].checked);
			//    nodes[i].checked="false";
			//    zTreeObject.updateNode(nodes[i],true);
			    //zTreeObject.cancelSelectedNode(nodes[i]);
			    //zTreeObject.checkNode(nodes[i], true, true);
			//}) 
			//$('#'+treeId)
		};

		
		var zTreeObject;
		$(document).ready(function(){
			var custOrgTreeData=JSON.parse('${custOrgTreeData}');
			//console.log("custOrgTreeData:"+custOrgTreeData);
			zTreeObject=$.fn.zTree.init($("#treeDemo"), setting, custOrgTreeData);
			//setCheck();
			//$("#py").bind("change", setCheck);
			//$("#sy").bind("change", setCheck);
			//$("#pn").bind("change", setCheck);
			//$("#sn").bind("change", setCheck);
		});
		function save(){
			var nodes=zTreeObject.getCheckedNodes(true);
			if(nodes.length>1 || nodes.length<1){
				alert("请选择一个机构");
			}else{
				//alert(nodes[0].name); 
				//alert(window.parent.frames['${pd.pageFrameId}'].contentWindow.document.getElementById("${pd.showElementId}").value);
				//alert('${pd.pageFrameId}');
				window.parent.frames['${pd.pageFrameId}'].contentWindow.document.getElementById("${pd.showElementId}").value=nodes[0].name;
				window.parent.frames['${pd.pageFrameId}'].contentWindow.document.getElementById("${pd.hiddenElementId}").value=nodes[0].id;
				
				//$("#"+"${pd.showElementId}",window.parent.frames['${pd.pageFrameId}'].contentWindow).trigger("change");
				var call="${pd.callback}";
				if(call!="null"){
					window.parent.frames['${pd.pageFrameId}'].contentWindow.${pd.callback}();
				}
				//window.parent.frames["mainFrame"].document.getElementById('${pd.pageFrameId}').contentWindow.document.getElementById("${pd.showElementId}").value=nodes[0].name;
				//window.parent.frames["mainFrame"].document.getElementById('${pd.pageFrameId}').contentWindow.document.getElementById("${pd.hiddenElementId}").value=nodes[0].id;
				
				top.Dialog.close();//关闭窗口
				//$(window.frameElement).remove();
				//alert(111);
				//console.log("tree page find operer CUST_ORG_ID:"+$(window.frames.document).find('#CUST_ORG_ID').attr('id'))
				//window.operer.
			}
			
		}
		//-->
	</SCRIPT>
</head>
  
  <body style="background-color: #E6E6E6">
    <ul id="treeDemo" class="ztree"></ul>
    <div style="text-align: center;" >
    	<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
		<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
    </div>
  </body>
</html>
