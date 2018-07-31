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
							
						<!-- 检索  -->
						<form action="user/list.do" method="post" name="Form" id="Form">
						<table style="margin-top:5px;">
							<tr>
								<td>
									<div class="nav-search">
									    <span class="input-icon"> 
											 <a  class="btn btn-info btn-sm">
										        <span class="glyphicon glyphicon-info-sign"></span>  ${pdept.deptName} ${radarUserInfo.stationName} 
										     </a> 
										</span>
										<span class="input-icon">
											用户号：
											<input type="text" placeholder="这里输入用户号"   id="no" name="no" autocomplete="off" value="${entitySearch.no}" />
										</span>
								 
										<span class="input-icon">
											用户名：
											<input type="text" placeholder="这里输入用户名"   id="name" name="name" autocomplete="off" value="${entitySearch.name}" />
										 	<input type="hidden" name='DEPARTMENT_ID' value="${DEPARTMENT_ID }">
										</span>
									</div>
								</td> 
								<td style="vertical-align:top;padding-left:2px"><a class="btn btn-light btn-xs" onclick="gsearch();"  title="检索"><i id="nav-search-icon" class="ace-icon fa fa-search bigger-110 nav-search-icon blue"></i></a></td>
				 				</tr>
						</table>
						<!-- 检索  -->
					
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">	
							<thead>
								<tr>
									<th class="center" style="width:50px;">序号</th>
									<th class="center">用户ID</th>
									<th class="center">姓名</th>
									<th class="center">用户类型</th>
									<th class="center">用户状态</th>
								    <th class="center">注册时间</th>
								    <th class="center">身份证号</th>
								    <th class="center">邮箱</th>
								    <th class="center">电话</th>
									<th class="center">操作</th>
								</tr>
							</thead>
													
							<tbody>
							<!-- 开始循环 -->	
							<c:choose>
								<c:when test="${not empty varList}"> 
									<c:forEach items="${varList}" var="var" varStatus="vs">
										<tr id="${var.id}">
											<td class='center' style="width: 30px;">${vs.index+1}</td>
											<td class='center'><i class="ace-icon fa fa-share bigger-100"></i>&nbsp;${var.no}</td>
											<td class='center'>${var.name}</td>
											<td class='center'>${var.userTypeInfo.userName}</td>
											<td class='center'>${var.userStateInfo.userStateName}</td>
											<td class='center'>${var.registrationDate}</td>
											<td class='center'>${var.idNumber}</td>
											<td class='center'>${var.email}</td>
											<td class='center'>${var.telephone}</td>
											<td class="center">  
												<div> 
												 
													<a class="btn btn-xs btn-success" title="编辑" onclick="edit('${var.id}','${radarId}');">
														<i class="ace-icon fa fa-pencil-square-o " title="编辑"></i>
													</a>  
													&nbsp; 
													  <a class="btn btn-xs btn-success" title="重置密码" onclick="resetPassword('${var.id}','${var.no }');">
														<i class="menu-icon fa fa-cogs white" title="重置密码"></i>
													</a>  
													<!-- &nbsp; 
													  <a class="btn btn-xs btn-success" title="解锁" onclick="resetLock('${var.id}','${var.no }');">
														<i class="ace-icon fa-leaf white" title="解锁"></i>
													</a>  
													 -->
													<c:if test="${var.userStateInfo.userStateName=='启用'}">
													&nbsp; 
														 <a class="btn btn-xs btn-success" title="弃用" onclick="giveUp('${var.id}','${var.no }');">
															<i class="ace-icon fa fa-eye-slash white" title="弃用"></i>
														</a>  
													</c:if>
													<c:if test="${var.userStateInfo.userStateName=='弃用'}">
													&nbsp; 
														 <a class="btn btn-xs btn-success" title="启用" onclick="startUp('${var.id}','${var.no }');">
															<i class="ace-icon fa fa-eye white" title="启用"></i>
														</a>  
													</c:if>
													<!-- &nbsp; 
													 <a class="btn btn-xs btn-success" title="更换机构" onclick="changeDept('${var.id}','${var.no }');"> 
														<i class="ace-icon fa fa-cogs white" title="更换机构"></i>
													</a>  
													 -->
													&nbsp; 
													<a class="btn btn-xs btn-danger" onclick="delUser('${var.id}','${var.name}');">
														<i class="ace-icon fa fa-trash-o white" title="删除"></i>
													</a> 
												</div> 
												 
											</td>
										</tr>
									
									</c:forEach> 
									<c:if test="${QX.cha == 0 }">
										<tr>
											<td colspan="100" class="center">您无权查看</td>
										</tr>
									</c:if>
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
								   <c:if test="${DEPARTMENT_ID!='0'}">
									<a class="btn btn-sm btn-success" onclick="add('${DEPARTMENT_ID}','${radarId}');">新增</a> 
									</c:if> 
									<!--<c:if test="${null != pd.id&& pd.id!= ''&&pdept.parentId!='0'}">
									<a class="btn btn-sm btn-success" onclick="goSondict('${pdept.parentId}');">返回</a>
									</c:if>-->
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
	<%@ include file="../../system/index/foot.jsp"%>
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
			$("#Form").submit();
		}
		
		//去此ID下子级列表
		function goSondict(DEPARTMENT_ID){
			top.jzts();
			window.location.href="<%=basePath%>user/list.do?DEPARTMENT_ID="+DEPARTMENT_ID;
		};
		
		//新增
		function add(DEPARTMENT_ID,radarId){ 
			  top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>user/toAdd.do?DEPARTMENT_ID='+DEPARTMENT_ID+"&radarId="+radarId;
			 diag.Width = $(window).width();
			 diag.Height = 600;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
				 //	alert(radarId);
				    if(radarId==""){
				        window.location.href="<%=basePath%>user/list.do?id=${DEPARTMENT_ID}&dnowPage=${page.currentPage}";
				    	
				    }else{
				       window.location.href="<%=basePath%>user/listRadar.do?radarId=${DEPARTMENT_ID}&dnowPage=${page.currentPage}";
				    	
				    }
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
					var url = "<%=basePath%>department/delete.do?DEPARTMENT_ID="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						if("success" == data.result){
							parent.location.href="<%=basePath%>user/listAllUser.do?DEPARTMENT_ID=${DEPARTMENT_ID}&dnowPage=${page.currentPage}";
						}else if("false" == data.result){
							top.hangge();
							bootbox.dialog({
								message: "<span class='bigger-110'>删除失败！请先删除子级部门.</span>",
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
		function edit(Id,radarId){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>user/toEdit.do?id='+Id;
			 diag.Width = $(window).width();
			 diag.Height = 600;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					///alert(radarId);
				    if(radarId==""){
				        window.location.href="<%=basePath%>user/list.do?id=${DEPARTMENT_ID}&dnowPage=${page.currentPage}";
				    	
				    }else{
				       window.location.href="<%=basePath%>user/listRadar.do?radarId=${radarId}&dnowPage=${page.currentPage}";
				    	
				    }				}
				diag.close();
			 };
			 diag.show();
		}
		function changeDept(Id){
		 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="更换机构";
			 diag.URL = '<%=basePath%>user/toMove.do?id='+Id;
			 diag.Width = 700;
			 diag.Height = 306;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 window.location.href="<%=basePath%>user/list.do?DEPARTMENT_ID=${DEPARTMENT_ID}&dnowPage=${page.currentPage}";
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function delUser(userId,msg){
			bootbox.confirm("确定要删除["+msg+"]吗?", function(result) {
				if(result) {
					top.jzts();
					
					
					$.ajax({
						type: "POST",
						url: '<%=basePath%>user/deleteU.do',
				    	data: {userId:userId,tm:new Date().getTime()},
						dataType:'json',
						cache: false,
						success: function(data){
							 if("0" == data.errCode){
							 	 nextPage(${page.currentPage}); 
							 }else{
							 	alert(data.errMsg);
							 	top.hangge();
							 }
						}
					});
				}
			});
		}
		
		
		
	//重置密码
	function resetPassword(id,no){ 
		$.ajax({
			type: "POST",
			url: '<%=basePath%>user/resetPassword.do',
	    	data: {userId:id,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" == data.result){
				 	 var trId=id;
					 $("#"+trId).tips({
							side:3,
				            msg:no+'密码已经重置',
				            bg:'#AE81FF',
				            time:3
				        }); 
				 }
			}
		});
	}
	
	//解锁
	function resetLock(id,no){ 
		$.ajax({
			type: "POST",
			url: '<%=basePath%>user/resetLock.do',
	    	data: {id:id,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" == data.result){
				 	 var trId=id;
					 $("#"+trId).tips({
							side:3,
				            msg:no+'解锁成功',
				            bg:'#AE81FF',
				            time:3
				        }); 
				 }
			}
		});
	}
	//启用
	function startUp(id,no){ 
		$.ajax({
			type: "POST",
			url: '<%=basePath%>user/startUp.do',
	    	data: {id:id,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" == data.result){
				 	 var trId=id;
					 $("#"+trId).tips({
							side:3,
				            msg:no+'启用成功',
				            bg:'#AE81FF',
				            time:3
				        }); 
				    window.location.reload() ;
				 }
			}
		});
	}
	//弃用
	function giveUp(id,no){ 
		$.ajax({
			type: "POST",
			url: '<%=basePath%>user/giveUp.do',
	    	data: {id:id,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" == data.result){
				 	 var trId=id;
					 $("#"+trId).tips({
							side:3,
				            msg:no+'弃用成功',
				            bg:'#AE81FF',
				            time:3
				        }); 
				  window.location.reload() ;
				 }else{
				 	bootbox.dialog({
								message: "<span class='bigger-110'>弃用失败！用户有未完成任务，请先删除任务才能弃用该用户.</span>",
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
			}
		});
	}
	</script>


</body>
</html>