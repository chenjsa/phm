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
<!-- 下拉框 -->
<link rel="stylesheet" href="static/ace/css/chosen.css" />
<!-- jsp文件头和头部 --> 
<%@ include file="../../system/index/top.jsp"%>
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
							
						<!-- 检索  -->
						<form action="model/list.do" method="post" name="Form" id="Form">
						<table style="margin-top:5px;">
							<tr>
								<td>
									<div class="nav-search">
										<span class="input-icon">
											<input type="text" placeholder="这里输入关键词" class="nav-search-input" id="nav-search-input" autocomplete="off" name="keywords" value="${pd.keywords }" placeholder="这里输入关键词"/>
											<i class="ace-icon fa fa-search nav-search-icon"></i>
										</span>
									</div>
								</td>
								<td style="padding-left:2px;"><input class="span10 date-picker" name="lastStart" id="lastStart"  value="" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="开始日期" title="开始日期"/></td>
								<td style="padding-left:2px;"><input class="span10 date-picker" name="lastEnd" name="lastEnd"  value="" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="结束日期" title="结束日期"/></td>
								<td style="vertical-align:top;padding-left:2px;">
								 	<select class="chosen-select form-control" name="name" id="id" data-placeholder="请选择" style="vertical-align:top;width: 120px;">
									<option value=""></option>
									<option value="">全部</option>
									<option value="">1</option>
									<option value="">2</option>
								  	</select>
								</td>
								<c:if test="${QX.cha == 1 }">
								<td style="vertical-align:top;padding-left:2px"><a class="btn btn-light btn-xs" onclick="tosearch();"  title="检索"><i id="nav-search-icon" class="ace-icon fa fa-search bigger-110 nav-search-icon blue"></i></a></td>
								</c:if>
								<c:if test="${QX.toExcel == 1 }"><td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="ace-icon fa fa-download bigger-110 nav-search-icon blue"></i></a></td></c:if>
							</tr>
						</table>
						<!-- 检索  -->
					
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">	
							<thead>
								<tr>
									<th class="center" style="width:50px;">序号</th>
									<th class="center">KEY</th>
									<th class="center">名称</th>
									<th class="center">Version</th>
									<th class="center">创建时间</th>
									<th class="center">更新时间</th>
									<th class="center">元数据</th>
									<th class="center">部署状态</th>
									<th class="center">操作</th>
								</tr>
							</thead>
													
							<tbody>
							<!-- 开始循环 -->	
							<c:choose>
								<c:when test="${not empty varList}"> 
									<c:forEach items="${varList}" var="var" varStatus="vs">
										<tr>					
											<td class='center' style="width: 30px;">${vs.index+1}</td>
											<td class='center'>${var.KEY}</td>
											<td class='center'>${var.NAME}</td>
											<td class='center'>${var.VERSION}</td>
											<td class='center'>${var.CREATE_TIME}</td>
											<td class='center'>${var.LAST_UPDATE_TIME}</td>
											<td class='center'>${var.META_INFO}</td>
											<td class='center' id="STATUS${vs.index+1}">${var.STATUS == '1' ? '<h7 class="green">已部署</h7><img src="static/images/runing.gif" width="12px;" />' : '<h7 class="red">已停止</h7>'}</td>
											<td class="center">
												<c:if test="${QX.edit != 1 && QX.del != 1 }">
												<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
												</c:if>
												<div class="hidden-sm hidden-xs btn-group"> 
													<a class="btn btn-xs btn-success" title="编辑" onclick="edit('${var.ID}','${var.NAME}');">
														<i class="ace-icon fa fa-pencil-square-o bigger-120" title="编辑"></i>
													</a> 
													<a class="btn btn-xs btn-danger" onclick="del('${var.ID}','${var.NAME}','${var.STATUS}');">
														<i class="ace-icon fa fa-trash-o bigger-120" title="删除"></i>
													</a> 
													<a class="btn btn-info btn-xs" title="导出模型" onclick="exportModel('${var.ID}','${var.NAME}');">
														<i class="ace-icon glyphicon glyphicon-download-alt" title="导出模型"></i>
													</a>
													
													<a id="offing1${vs.index+1}"  class="btn btn-info btn-xs" title="部署" onclick="deploy('${var.ID}','${var.NAME}');">
                                                        <i class="ace-icon glyphicon glyphicon-play" title="部署"></i>
                                                    </a> 
												</div>
												<div class="hidden-md hidden-lg">
													<div class="inline pos-rel">
														<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
															<i class="ace-icon fa fa-cog icon-only bigger-110"></i>
														</button>
			
														<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
														 	<li>
																<a style="cursor:pointer;" onclick="edit('${var.MODEL_ID}');" class="tooltip-success" data-rel="tooltip" title="修改">
																	<span class="green">
																		<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																	</span>
																</a>
															</li> 
															<li>
																<a style="cursor:pointer;" onclick="del('${var.MODEL_ID}');" class="tooltip-error" data-rel="tooltip" title="删除">
																	<span class="red">
																		<i class="ace-icon fa fa-trash-o bigger-120"></i>
																	</span>
																</a>
															</li> 
														</ul>
													</div>
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
									<a class="btn btn-sm btn-success" onclick="add();">新增模型</a> 
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
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript">
		$(top.hangge());//关闭加载状态
		//检索
		function tosearch(){
			top.jzts();
			$("#Form").submit();
		}
		$(function() {
		
			//日期框
			$('.date-picker').datepicker({
				autoclose: true,
				todayHighlight: true
			});
			
			//下拉框
			if(!ace.vars['touch']) {
				$('.chosen-select').chosen({allow_single_deselect:true}); 
				$(window)
				.off('resize.chosen')
				.on('resize.chosen', function() {
					$('.chosen-select').each(function() {
						 var $this = $(this);
						 $this.next().css({'width': $this.parent().width()});
					});
				}).trigger('resize.chosen');
				$(document).on('settings.ace.chosen', function(e, event_name, event_val) {
					if(event_name != 'sidebar_collapsed') return;
					$('.chosen-select').each(function() {
						 var $this = $(this);
						 $this.next().css({'width': $this.parent().width()});
					});
				});
				$('#chosen-multiple-style .btn').on('click', function(e){
					var target = $(this).find('input[type=radio]');
					var which = parseInt(target.val());
					if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
					 else $('#form-field-select-4').removeClass('tag-input-style');
				});
			}
		});
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增模型";
			 diag.URL = '<%=basePath%>model/goAdd.do';
			 diag.Width = 450;
			 diag.Height = 255;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location=self.location",100);
					 }else{
						 nextPage(${page.currentPage});
					 }
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function del(Id,Name,Status){
		     bootbox.confirm("确定要删除【"+ Name+"】模型吗?", function(result) {
				if(result) {
				    top.jzts();
				    if(Status == 1){
                        alert("【"+ Name+"】该模型已部署，请停止后再删除！");
                        nextPage(${page.currentPage});
                        return false;
                    }
					var url = "<%=basePath%>model/delete.do?MODEL_ID="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						nextPage(${page.currentPage});
					});
				}
			});
		}
		
		//修改
		function edit(Id,Name){
			bootbox.confirm("确定要编辑【"+ Name+"】模型吗?", function(result) {
				if(result) {					 
					var pathName = window.location.pathname.substring(1);	
	  				var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));			
	  				if (webName == "") {
	    				alert("无法打开模型设计器，请联系系统运维管理员");
	  				}
	  				else {
	   					window.open(window.location.protocol + '//' + window.location.host +"/act-process-editor/modeler.html?modelId=" + Id);
	  				}	
				}
			});
		}
		
		//部署
		function deploy(Id,Name){
			bootbox.confirm("确定要部署【"+ Name+"】模型流程吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>model/deploy.do?MODEL_ID="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						if(data=="success"){
						  alert("部署成功！");
						}else{
						  alert("部署失败！");  
						}
						nextPage(${page.currentPage});
					});
				}
			});
		}
		
		//导出模型
		function exportModel(Id,Name){
			bootbox.confirm("确定要导出【"+ Name+"】模型吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>model/exportModel.do?MODEL_ID="+Id+"&tm="+new Date().getTime();
					window.open(url);
					nextPage(${page.currentPage});
				}
			});
		}
		
		//导出excel
		function toExcel(){
			window.location.href='<%=basePath%>model/excel.do';
		}
	</script>

</body>
</html>