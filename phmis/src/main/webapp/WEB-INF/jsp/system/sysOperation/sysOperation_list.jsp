<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<!-- jsp文件头和头部 -->
<%@ include file="../index/top.jsp"%>
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
							
						<!-- 检索  -->
						<form action="sysOperation/list.do" method="post" name="sysOperationForm" id="sysOperationForm">
						 
						<!-- 检索  -->
						 		    	 
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">	
							<thead>
								<tr>								  
									<th class="center" style="width:50px;">序号</th>
									<th class="center">按钮名称</th>
									<th class="center">按钮key</th> 
									<th class="center">功能描述</th> 
									<th class="center">备注</th> 
									<th class="center">操作</th>
								</tr>
							</thead>
													
							<tbody>
							<!-- 开始循环 -->	
							<c:choose>
								<c:when test="${not empty sysOperationList}"> 
									<c:forEach items="${sysOperationList}" var="var" varStatus="vs">
										<tr>
											<td class='center' style="width: 30px;">${vs.index+1}</td>
									 		
									 		<td class='center'>${var.btnName}</td>  
											<td class='center'>${var.btnCode}</td> 
											<td class='center'>${var.depict}</td>  
											<td class='center'>${var.remark}</td> 
											<td class="center">											 
												<div class="hidden-sm hidden-xs btn-group"> 
													<a class="btn btn-xs btn-success" title="编辑" onclick="edit('${var.id}');">
														<i class="ace-icon fa fa-pencil-square-o bigger-120" title="编辑"></i>
													</a>  
													&nbsp;
													<a class="btn btn-xs btn-success" title="查看" onclick="view('${var.id}');">
														<i class="ace-icon fa fa-eye bigger-120" title="查看"></i>
													</a>  
													&nbsp;
													<a class="btn btn-xs btn-danger" onclick="del('${var.id}');">
														<i class="ace-icon fa fa-trash-o bigger-120" title="删除"></i>
													</a> 
												</div> 											  
											</td>
										</tr>
									
									</c:forEach>  
								</c:when>
								<c:otherwise>
									<tr class="main_info">
										<td colspan="100" class="center" >没有相关数据</td>
									</tr>
								</c:otherwise>
							</c:choose>
							</tbody>
						</table>
						<div class="page-header position-relative">
						<table style="width:100%;">
							<tr>
								<td style="vertical-align:top;">  
									<a class="btn btn-sm btn-success" onclick="add();">新增</a>   
								</td>
								<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
							</tr>
						</table>
						</div>
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


		<!-- 返回顶部 -->
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../index/foot.jsp"%>
	<!-- 删除时确认窗口 -->
	<script src="static/ace/js/bootbox.js"></script>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript">
		$(top.hangge());//关闭加载状态
		//检索
		function gsearch(){
			top.jzts();
			$("#sysOperationForm").submit();
		}
		
		//去此ID下子级列表
		function goSondict(DEPARTMENT_ID){ 			
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="查看";
			 diag.URL = '<%=basePath%>sysOperation/goView.do?id='+DEPARTMENT_ID;
			 diag.Width = 700;
			 diag.Height = 546;
			 diag.CancelEvent = function(){ //关闭事件
				 if('none' == diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display){
					 window.location.href="<%=basePath%>sysOperation/list.do?dnowPage=${page.currentPage}";
				}
				diag.close();
			 };
			 diag.show();
		};
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>sysOperation/goAdd/${entity.menuId}';
			 diag.Width = 700;
			 diag.Height = 350;
			 diag.CancelEvent = function(){ //关闭事件
				 if('none' == diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display){
				     var url="<%=basePath%>sysOperation/list.do?menuId=${entity.menuId}&dnowPage=${page.currentPage}";
				    // alert(url);
					 window.location.href=url;
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function del(Id){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>sysOperation/delete.do?id="+Id;
					$.get(url,function(data){
						if("success" == data.result){
							//nextPage(${page.currentPage});
							  var url="<%=basePath%>sysOperation/list.do?menuId=${entity.menuId}&dnowPage=${page.currentPage}";
						    // alert(url);
							 window.location.href=url;
						}else if("false" == data.result){
							top.hangge();
							bootbox.dialog({
								message: "<span class='bigger-110'>删除失败！</span>",
								buttons: 			
								{
									"button" :
									{
										"label" : "确定",
										"className" : "btn-sm btn-success"
									}
								}
							});
						}
					});
				}
			});
		}
		
		//修改
		function edit(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>sysOperation/goEdit/'+Id;
			 diag.Width = 700;
			 diag.Height = 250;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 window.location.href="<%=basePath%>sysOperation/list.do?menuId=${entity.menuId}&dnowPage=${page.currentPage}";
				}
				diag.close();
			 };
			 diag.show();
		}
		//修改
		function view(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="查看";
			 diag.URL = '<%=basePath%>sysOperation/goView/'+Id;
			 diag.Width = 700;
			 diag.Height = 250;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 window.location.href="<%=basePath%>sysOperation/list.do?dnowPage=${page.currentPage}";
				}
				diag.close();
			 };
			 diag.show();
		}
		

		
	</script>


</body>
</html>
