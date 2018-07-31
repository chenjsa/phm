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
	<script type="text/javascript">
		//刷新ztree
		function parentReload(){
			if(null !='${MSG}' && 'change' == '${MSG}'){
				parent.location.href="<%=basePath%>menu/listAllMenu.do?MENU_ID=${MENU_ID}";
			}else{
				//什么也不干
			}
		}
		parentReload();
	</script>
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

							<table id="dynamic-table" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th class="center" style="width: 50px;">序号</th>
										<th class='center'>名称</th>
										<th class='center'>资源路径</th>
										<th class='center' style="width: 50px;">状态</th>
										<th class='center' style="width: 120px;">操作</th>
									</tr>
								</thead>

								<tbody>
								<c:choose>
									<c:when test="${not empty menuList}">
									<c:forEach items="${menuList}" var="menu" varStatus="vs">
									<tr>
										<td class='center'>${menu.serial }</td>
										<td class='center'><i class="${menu.menuIcon }">&nbsp;</i>
											<a href="javascript:goSonmenu('${menu.id }')">${menu.menuName }</a>
											&nbsp;
											<c:if test="${menu.parentId == '0' }">
											<c:if test="${menu.isUse == '1' }">
											<span class="label label-success arrowed">系统</span>
											</c:if>
											<c:if test="${menu.isUse != '1' }">
											<span class="label label-important arrowed-in">业务</span>
											</c:if>
											</c:if>
										</td>
										<td>${menu.actionPath == '#'? '': menu.actionPath}</td>
										<td class='center'><i class="ace-icon fa ${menu.isUse == 1? 'fa-eye': 'fa-eye-slash'}"></i></td>
										<td class='center'>
											 
											<div class="hidden-sm hidden-xs action-buttons">
												  <c:if test="${menu.isUse == '1' &&not empty menu.actionPath && '#' != menu.actionPath}">
													 
												 <a class="blue" href="javascript:editBtn('${menu.id }');"> 
													<i class="ace-icon glyphicon glyphicon-cog bigger-130" title="配置功能按钮"></i>
												</a> 
												 </c:if>
												<a class="blue" href="javascript:editTb('${menu.id }');"> 
													<i class="ace-icon glyphicon glyphicon-picture bigger-130" title="编辑图标"></i>
												</a> 
												<a class="green" href="javascript:editmenu('${menu.id }');">
													<i class="ace-icon fa fa-pencil-square-o bigger-130" title="修改"></i>
												</a>
											 
												<a class="red" href="javascript:delmenu('${menu.id }');">
													<i class="ace-icon fa fa-trash-o bigger-130" title="删除"></i>
												</a>
											 
											</div>
											<div class="hidden-md hidden-lg">
												<div class="inline pos-rel">
													<button
														class="btn btn-minier btn-yellow dropdown-toggle"
														data-toggle="dropdown" data-position="auto">
														<i
															class="ace-icon fa fa-caret-down icon-only bigger-120"></i>
													</button>

													<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
													    <c:if test="${menu.isUse == '1'&&not empty menu.actionPath && '#' != menu.actionPath }">
														  <li><a href="javascript:editBtn('${menu.id }');" class="tooltip-info" data-rel="tooltip" title="View">
															<span class="blue">
																<i class="ace-icon glyphicon glyphicon glyphicon-cog-120" title="配置功能按钮"></i>
															</span>
														</a></li>
														</c:if>
														<li><a href="javascript:editTb('${menu.id }');" class="tooltip-info" data-rel="tooltip" title="View">
															<span class="blue">
																<i class="ace-icon glyphicon glyphicon-picture bigger-120" title="编辑图标"></i>
															</span>
														</a></li>
														<li><a href="javascript:editmenu('${menu.id }');" class="tooltip-success" data-rel="tooltip" title="Edit">
															<span class="green">
																<i class="ace-icon fa fa-pencil-square-o bigger-120" title="修改"></i>
															</span>
														</a></li>
														 
														<li><a href="javascript:delmenu('${menu.id }');" class="tooltip-error" data-rel="tooltip" title="Delete">
															<span class="red"> <i class="ace-icon fa fa-trash-o bigger-120"  title="删除"></i>
															</span>
														</a></li>
														 
													</ul>
												</div>
											</div>
										</td>
									</tr>
									</c:forEach>
									</c:when>
										<c:otherwise>
											<tr>
											<td colspan="100" class='center'>没有相关数据</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
							
							<div>
								&nbsp;&nbsp;
								 <a class="btn btn-sm btn-success" onclick="addmenu('${MENU_ID}');">新增</a> 
								<c:if test="${null != MENU_ID && MENU_ID != '0'}">
									<a class="btn btn-sm btn-success" onclick="goSonmenu('${entity.parentId}');">返回</a>
								</c:if>
							</div>
							
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
		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
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

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		$(document).ready(function(){
			top.hangge();
		});	
		
		//去此ID下子菜单列表
		function goSonmenu(id){
			top.jzts();
			window.location.href="<%=basePath%>menu.do?MENU_ID="+id;
		};
		
		//新增菜单
		function addmenu(id){
			top.jzts();
			var url="<%=basePath%>menu/toAdd.do?MENU_ID="+id;
			//alert(url);
			window.location.href=url;
		};
		
		//编辑菜单
		function editmenu(id){
			top.jzts();
			var url="<%=basePath%>menu/toEdit.do?id="+id;
			window.location.href=url;
		};
		
		
		//删除
		function delmenu(id){
			bootbox.confirm("确定要删除此菜单吗?", function(result) {
				if(result) {
					var url = "<%=basePath%>menu/delete.do?MENU_ID="+id+"&guid="+new Date().getTime();
					top.jzts();
					$.get(url,function(data){
						if("success" == data.result){
							parent.location.href="<%=basePath%>menu/listAllMenu.do?MENU_ID=${MENU_ID}";
						}else if("false" == data.result){
							top.hangge();
							bootbox.dialog({
								message: "<span class='bigger-110'>删除失败,请先删除子菜单!</span>",
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
		
		//编辑菜单图标
		function editTb(id){ 
			 top.jzts();
		   	 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑图标";
			 var url='<%=basePath%>menu/toEditicon.do?id='+id;
			// alert(url);
			 diag.URL = url;
			 diag.Width = 800;
			 diag.Height = 380;
			 diag.CancelEvent = function(){ //关闭事件
				if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					top.jzts(); 
					setTimeout("location.reload()",100);
				}
				diag.close();
			 };
			 diag.show();
		}
		
		function editBtn(id){
			 top.jzts();
		   	 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑按钮";
			 var url='<%=basePath%>sysOperation/list.do?menuId='+id;
			 //alert(url);
			 diag.URL = url;
			 diag.Width = 1000;
			 diag.Height = 480;
			 diag.CancelEvent = function(){ //关闭事件				 
				diag.close();
			 };
			 diag.show();
		}
	</script>


</body>
</html>