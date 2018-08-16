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
 
<script type="text/javascript" src="/static/ace/js/jquery.validate.js"></script> 

<link href="plugins/formValidator3.1/style/validator.css" type="text/css" rel="stylesheet"/>
<script src="plugins/formValidator3.1/formValidator.js" type="text/javascript"></script>
<script src="plugins/formValidator3.1/formValidatorRegex.js" type="text/javascript"></script>
<!-- 下拉框 -->
<link rel="stylesheet" href="static/ace/css/chosen.css" />
<!-- jsp文件头和头部 -->
<%@ include file="../index/top.jsp"%>
<!-- 日期框 -->
<link rel="stylesheet" href="static/ace/css/datepicker.css" />
		<link href="/static/ace/css/iCheck/custom.css" rel="stylesheet"> 
		<script src="/static/ace/js/icheck.min.js"></script>
			<script src="/static/js/util.js"></script>
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
						<form action="user/save.do" name="userForm" id="userForm" method="post">
							    <input type="hidden" name="fromQuery" value="${fromQuery}">
							    <input type="hidden" name="stationId" value="<c:if test="${entity.id==null}">${radarUserInfo.id}</c:if><c:if test="${fn:length(entity.id) gt 0}">${entity.stationId}</c:if>">
							    
								<input type="hidden" name="deptId" value="<c:if test="${entity.id==null}">${dept.id}</c:if><c:if test="${fn:length(entity.id) gt 0}">${entity.deptId}</c:if>">
								<input type="hidden" name="id" value="${entity.id}">  
								<input type="hidden" name="password" value="${entity.password}"> 
								<input type="hidden" name="isLock" value="<c:if test="${entity.id==null}">1</c:if><c:if test="${fn:length(entity.id) gt 0}">${entity.isLock}</c:if>">
								<input type="hidden" name="pwdEditDate" value="${entity.pwdEditDate}">
								<input type="hidden" name="lastLoginDate" value="${entity.lastLoginDate}">
								<input type="hidden" id="roleIds" name="roleIds" value="${entity.roleIds}">
								<input type="hidden" id="manageDeptIds" name="manageDeptIds" value="${entity.manageDeptIds}">  
							      <input type="hidden" name="registrationDate" value="${entity.registrationDate}"> 
						
							<table id="table_report" class="table table-striped table-bordered table-hover"> 
							
								 <tr>
								 	<td style="width:89px;text-align: right;padding-top: 13px;"><span style="color:#FF0000;">*</span>用户ID:</td>
									<td><input class="span10 date-picker" name="no" onblur="hasN();" id="noId" value="${entity.no}" type="text"   class="{required:true,minlength:5}"   style="width:98%;" />
										<span id="noTip"></span>
									</td>
									<td style="width:89px;text-align: right;padding-top: 13px;"><span style="color:#FF0000;">*</span>姓名:</td>
									<td><input class="span10 date-picker" name="name" id="name" value="${entity.name}" type="text"   class="{required:true,minlength:5}"   style="width:98%;" />
										<span id="nameTip"></span>
									</td> 
								</tr>
								
								 <tr>
								 <td style="width:79px;text-align: right;padding-top: 13px;">性别:</td>
									<td id="js">
									<select class="chosen-select form-control" name="sex" id=sexId data-placeholder="请选择性别" style="vertical-align:top;"  title="性别" style="width:98%;" >
									<option  <c:if test="${entity.sex =='1' }">selected</c:if>  value='1'>男</option>
									<option  <c:if test="${entity.sex =='2' }">selected</c:if>  value='2'>女</option>
									</select>
									</td>
								 	<td style="width:89px;text-align: right;padding-top: 13px;"><span style="color:#FF0000;">*</span>身份证:</td>
									<td><input class="span10 date-picker" name="idNumber"   id="idNumber" value="${entity.idNumber}" type="text" maxlength=18  class="{required:true,minlength:5}"   style="width:98%;" />
										<span id="idNumberTip"></span>
									</td> 
								</tr> 
								<tr>
									<td style="width:89px;text-align: right;padding-top: 13px;"><span style="color:#FF0000;">*</span>联系电话:</td>
									<td><input class="span10 date-picker" name="telephone" id="telephone" value="${entity.telephone}" type="text"   class="{required:true,minlength:5}"   style="width:98%;" />
										<span id="telephoneTip"></span>
									</td> 
									<td style="width:89px;text-align: right;padding-top: 13px;"><span style="color:#FF0000;">*</span>电子邮箱:</td>
									<td><input type="text" name="email" id="email"value="${entity.email}" placeholder="这里输入电子邮箱" title="电子邮箱" style="width:98%;" />
										<span id="emailTip"></span>
									</td> 
								</tr> 
								<tr>
									<td style="width:89px;text-align: right;padding-top: 13px;"><span style="color:#FF0000;">*</span>职务/类别:</td>
									<td>
										<select class="chosen-select form-control" name="postInfoId"  id="postInfoId" data-placeholder="请选择用户类型" style="vertical-align:top;"  title="用户类型" style="width:98%;" >
								  			<option  value=''>请选择</option>
								  			<c:forEach items="${postInfos}" var="var" varStatus="vs">
								  				<option <c:if test="${entity.postInfoId==var.id}">selected</c:if>  value='${var.id}'>${var.name}</option>
											</c:forEach>
										</select>
									 	<span id="typeIdTip"></span>
									</td> 
									<td style="width:89px;text-align: right;padding-top: 13px;"><span style="color:#FF0000;">*</span>健康状况:</td>
									<td>
									<select class="chosen-select form-control" name="physiclalStatusId" id="physiclalStatusId" data-placeholder="请选择用户类型" style="vertical-align:top;"  title="用户类型" style="width:98%;" >
								  			<option  value=''>请选择</option>
								  			<c:forEach items="${physiclalStatuses}" var="var" varStatus="vs">
								  				<option  <c:if test="${entity.physiclalStatusId==var.id}">selected</c:if>  value='${var.id}'>${var.description}</option>
											</c:forEach>
										</select>
										<span id="emailTip"></span>
									</td> 
								</tr> 
								
								<tr>
									<td style="width:89px;text-align: right;padding-top: 13px;"><span style="color:#FF0000;">*</span>专业领域:</td>
									<td>
										<select class="chosen-select form-control" name="professionalFieldId" id="professionalFieldId" data-placeholder="请选择用户类型" style="vertical-align:top;"  title="用户类型" style="width:98%;" >
								  			<option  value=''>请选择</option>
								  			<c:forEach items="${professionalFields}" var="var" varStatus="vs">
								  				<option  <c:if test="${entity.professionalFieldId==var.id}">selected</c:if>  value='${var.id}'>${var.radarTypeInfo.radarTypeName}-${var.subsystemInfo.subsystemName}-${var.supportPattern.name}</option>
											</c:forEach>
										</select>
									 	<span id="typeIdTip"></span>
									</td> 
									<td style="width:89px;text-align: right;padding-top: 13px;"><span style="color:#FF0000;">*</span>政治面貌:</td>
									<td>
									<input class="span10 date-picker" name="politicalStatus"   id="politicalStatus" value="${entity.politicalStatus}" type="text"   class="{required:true,minlength:5}"   style="width:98%;" />
										<span id="politicalStatusTip"></span> 
									</td> 
								</tr> 
								<tr> 
								    
									<td style="width:69px;text-align: right;padding-top: 13px;">联系方式:</td>
									<td colspan=3  >	
							<table id="table_report" class="table table-striped table-bordered table-hover"> 
									<tr>						
										<c:forEach items="${contactInfos}" var="var" varStatus="vs">
										
											<td  style="width:80px;text-align: right;padding-top: 13px;">
										 ${var.name}:		</td>
										 <td><input class="span10 date-picker"  name="userContactInfos[${vs.index }].no"     value="${entity.userContactInfos[vs.index].no}"    type="text"     style="width:98%;" />
											 <input type="hidden" name="userContactInfos[${vs.index }].contactId" value="${var.id}"  />
										  </td>
										  
									
										 </c:forEach>
										 	</tr>
										 </table>
									</td> 
								</tr>
								<tr> 
								    
									<td style="width:89px;text-align: right;padding-top: 13px;"><span style="color:#FF0000;">*</span>分配角色:</td>
									<td colspan=3>										
										 <div id="RoleTree" style="height:170px;border :1px solid Silver; overflow:auto;">
											<ul id="roleTree" class="tree"></ul>
										</div>
									</td> 
								</tr>
								<tr>
									<td class="center" colspan="6">
									 <button  type='button' onclick='save();' class='btn btn-sm btn-info'><span class="glyphicon glyphicon-save"></span> 保存</button>
											&nbsp;&nbsp;&nbsp;&nbsp;
		  								  <button  type='button' onclick='top.Dialog.close();' class='btn btn-xs btn-danger'><span class="glyphicon glyphicon-remove"></span> 取消</button>
	  
									</td>
								</tr>
							</table>
							</div>
								<div id="zhongxin" style="padding-top: 13px;">
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
		if(!validator('1')){		
		   return false;
		}
	     if(!vaild_all()){
			return false;
		 }
	
	 
		 var  roles=roleTree.getCheckedNodes(true);
         var roleStr="";  
		 for (var j = 0; j < roles.length; j++) {
       	     var node = roles[j];
			 roleStr=roleStr+ node.id+",";
		 }
		 if(roleStr == ''){//没有
			$("#RoleTree").tips({
				side:3,
	            msg:'请选择用户角色',
	            bg:'#AE81FF',
	            time:3
	        });
			$("#RoleTree").focus();
			return false;
		 }else{
		 	$("#roleIds").val($.trim(roleStr));
		 } 
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
 function vaild_all(){
	     if($("#name").val()==""){
			$("#name").tips({
				side:3,
	            msg:'姓名为必填项',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#name").focus();
			return false;
		}
	     if($("#userType1").val()==""){
			$("#userType1").tips({
				side:3,
	            msg:'组织机构职务为必填项',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#userType1").focus();
			return false;
		}
	     if($("#mobileId").val()==""){
			$("#mobileId").tips({
				side:3,
	            msg:'手机号为必填项',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#mobileId").focus();
			return false;
		}
	     if($("#email").val()==""){
			$("#email").tips({
				side:3,
	            msg:'邮箱为必填项',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#email").focus();
			return false;
		} 

		 return true;
	  }

	//判断手机号是否存在
	function hasUserByMobile(USERNAME){
		var mobile = $("#mobileId").val();
		if(mobile=="")
			return;
		$.ajax({
			type: "POST",
			url: '<%=basePath%>user/hasU.do',
	    	data: {mobile:mobile,USERNAME:USERNAME,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){ 
				 if("success" != data.result){
					 $("#mobileId").tips({
							side:3,
				            msg:'手机号'+mobile+'已存在',
				            bg:'#AE81FF',
				            time:3
				        });
					 $('#mobileId').val('');
					 $('#mobileId').focus();
				 }
			}
		});
	}
</SCRIPT>
<script> 
function info(){
	Ext.MessageBox.alert('消息', '验证未通过，请看错误提示!');
}
 $(document).ready(function(){
	$.formValidator.initConfig({formid:"userForm",onerror:function(msg){}}); 
    $("#no").formValidator({validatorgroup:"1",empty:false}).inputValidator({min:1,max:5,onerror:"姓名输入为1-5个字符,1个汉字为2个字符"});
    $("#name").formValidator({validatorgroup:"1",empty:false}).inputValidator({min:1,max:64,onerror:"姓名输入为1-64个字符,1个汉字为2个字符"});
   $("#mobileId").formValidator({empty:false,onfocus:"你要是输入了，必须输入正确",oncorrect:"谢谢你的合作",onempty:"你真的不想留手机号码啊？"}).inputValidator({min:11,max:11,onerror:"手机号码必须是11位的,请确认"}).regexValidator({regexp:"num1",datatype:"enum",onerror:"你输入的手机号码格式不正确"});;

	$("#email").formValidator({onshow:"请输入邮箱",onfocus:"邮箱至少6个字符,最多100个字符",oncorrect:"恭喜你,你输对了"}).inputValidator({min:6,max:100,onerror:"你输入的邮箱长度非法,请确认"}).regexValidator({regexp:"^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$",onerror:"你输入的邮箱格式不正确"});
 
 }); 				
    $(document).ready(function () {
        $(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green",})
    });
</script>
</html>