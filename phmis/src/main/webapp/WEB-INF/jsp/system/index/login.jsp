<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>${pd.SYSNAME}</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<link rel="stylesheet" href="static/login/bootstrap.min.css" />
<link rel="stylesheet" href="static/login/css/camera.css" />
<link rel="stylesheet" href="static/login/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="static/login/matrix-login.css" />
<link href="static/login/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="static/login/js/jquery-1.5.1.min.js"></script>
 <style type="text/css">
    /*
   body{
    -webkit-transform: rotate(-3deg);
    -moz-transform: rotate(-3deg);
    -o-transform: rotate(-3deg);
	padding-top:20px;
    }
    */
  </style>
  <script>
  		//window.setTimeout(showfh,3000); 
  		var timer;
		function showfh(){
			fhi = 1;
			//关闭提示晃动屏幕，注释掉这句话即可
			//timer = setInterval(xzfh2, 10); 
		};
		var current = 0;
		function xzfh(){
			current = (current)%360;
			document.body.style.transform = 'rotate('+current+'deg)';
			current ++;
			if(current>360){current = 0;}
		};
		var fhi = 1;
		var current2 = 1;
		function xzfh2(){
			if(fhi>50){
				document.body.style.transform = 'rotate(0deg)';
				clearInterval(timer);
				return;
			}
			current = (current2)%360;
			document.body.style.transform = 'rotate('+current+'deg)';
			current ++;
			if(current2 == 1){current2 = -1;}else{current2 = 1;}
			fhi++;
		};
	</script>
	<style type="text/css">
		.myDiv{
			background-image: url('static/login/images/index_background.png');
			background-size:100% 100%;
			height:100%;
			width:100%;
		}
	</style>
</head>
<body>
	<div style="width:100%;text-align: center;margin: 0 auto;position: absolute;" class="myDiv">
		<div style="position: absolute;top:5%;left:2%;"><img src="" width="60%" height="60%"/></div>
		<div style="margin-top: 8%;color: #fff;margin-bottom: 0px;">
			<h3>
				<img alt="" src="static/login/images/LOGO.png">${pd.SYSNAME}
			</h3>
		</div>
		
		<div id="opacityDiv" style="position:relative;background-color: #eef3f9;width:25%; opacity:0.5;filter:alpha(opacity=50);margin-right: auto;margin-left: auto;text-align: center;"></div>
		<div id="loginbox" style="margin-top: 0px;position:absolute; left:34%; top:29%; ">
			<form action="" method="post" name="loginForm" 
				id="loginForm" style="margin: 0px;">
				
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg" style="background-color: #3280c8;">
							<i><img height="37" src="static/login/login_name.png" /></i>
							</span><input type="text" name="loginname" style="width:60%;" id="loginname" value="" placeholder="请输入用户名" />
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_ly" style="background-color: #ff6d00;padding-bottom: 8.1px;padding-top:9px;">
							<i><img height="37" src="static/login/login_pw.png" /></i>
							</span><input type="password" name="password" style="width:60%;" id="password" placeholder="请输入密码" value=""/>
						</div>
					</div>
				</div>
				<div style="float:right;padding-right:20%;display:none;" >
				    <div style="float: left;">
                        <input name="form-field-checkbox" id="sysUserChk" type="checkbox" checked="true"
                             style="padding-top:0px;" />
                    </div>
                    <div style="float: left;margin-top:3px;margin-right:2px;">
                        <font color="white">系统管理员</font>
                    </div>     
                </div>
				<div style="float:left;padding-left:20%;">
					<div style="float: right;margin-top:3px;margin-right:2px;">
						<font color="white">记住密码</font>
					</div>
					<div style="float: right;">
						<input name="form-field-checkbox" id="saveid" type="checkbox"
							onclick="savePaw();" style="padding-top:0px;" />
					</div>
				</div>
				<!-- 
				<div class="form-actions" style="border-top: 0px;">
					<div style="width:100%;padding-left:30%;">

						<div style="float: left;padding-top:2px;">
							<i><img src="static/login/yan.png" /></i>
						</div>
						<div style="float: left;" class="codediv">
							<input type="text" name="code" id="code" class="login_code"
								style="height:16px; padding-top:4px;" />
						</div>
						<div style="float: left;">
							<i><img style="height:22px;" id="codeImg" alt="点击更换"
								title="点击更换" src="" /></i>
						</div>
						
					</div>
				</div> -->
				
				<div style="padding-bottom:20px;">
					<span ><a onclick="severCheck();" class="flip-link btn btn-info" id="to-recover" style="width:20%;border-radius:10px;background: #ff7400;">登录</a></span>
				</div>
			</form>
			
		</div>
		
		<div style="position:absolute; bottom:0; width:100%; height:20px;">
					<font><span id="nameerr">Copyright ©龙控科技 2018年v1.0</span></font>
		</div>
	</div>

	<script type="text/javascript">
		//服务器校验
		function severCheck(){
			if(check()){
				var loginname = $("#loginname").val();
				var password = $("#password").val();
				//var code=$("#code").val();
				var sysUser = 0;
				if($("#sysUserChk").attr("checked")){
				    sysUser = 1;
				}
				var data2={"no":loginname,"password":password,"userStateId":sysUser};  
				$.ajax({
					type: "POST",
					url: 'login_login',
			    	data: JSON.stringify(data2),
					dataType:'json',
					contentType: "application/json",//不加此项就会出现415错误代码
					cache: false,
					async: false,
					success: function(data){ 
					    debugger
						if("success" == data.result){
							saveCookie();
							 $("#loginbox").tips({
								side : 1,
								msg : '正在登录 , 请稍后 ...',
								bg : '#68B500',
								time : 10
							});
							//alert("1212:"+data.result);
							
							var url="main/index";
							window.location.href=url;
						}else if("usererror" == data.result){
							$("#loginname").tips({
								side : 1,
								msg : "用户名或密码有误",
								bg : '#FF5080',
								time : 15
							});
							showfh();
							$("#loginname").focus();
						}
						else if("pwderr" == data.result){
							$("#loginname").tips({
								side : 1,
								msg : "密码有误",
								bg : '#FF5080',
								time : 5
							});
							showfh();
							$("#loginname").focus();
						}
						else if("usererr" == data.result){
							$("#loginname").tips({
								side : 1,
								msg : "用户名有误",
								bg : '#FF5080',
								time : 5
							});
							showfh();
							$("#loginname").focus();
						}
						else if("usergivup" == data.result){
							$("#loginname").tips({
								side : 1,
								msg : "用户未生效",
								bg : '#FF5080',
								time : 5
							});
							showfh();
							$("#loginname").focus();
						}
						else if("userlock" == data.result){
							$("#loginname").tips({
								side : 1,
								msg : "用户被锁定",
								bg : '#FF5080',
								time : 5
							});
							showfh();
							$("#loginname").focus();
						}
						
						else if("userqiyong" == data.result){
							$("#loginname").tips({
								side : 1,
								msg : "用户被弃用",
								bg : '#FF5080',
								time : 5
							});
							showfh();
							$("#loginname").focus();
						}
						
						else if("nouser" == data.result){
							$("#loginname").tips({
								side : 1,
								msg : "没用该用户",
								bg : '#FF5080',
								time : 5
							});
							showfh();
							$("#loginname").focus();
						}
						else if("codeerror" == data.result){
							$("#code").tips({
								side : 1,
								msg : "验证码输入有误",
								bg : '#FF5080',
								time : 5
							});
							showfh();
							$("#code").focus();
						}else{
							$("#loginname").tips({
								side : 1,
								msg : data.result,
								bg : '#FF5080',
								time : 5
							});
							showfh();
							$("#loginname").focus();
						}
					},
					
					error:function(XMLHttpRequest, textStatus, errorThrown) {  
                       alert(XMLHttpRequest.status);  
                       alert(XMLHttpRequest.readyState);  
                       alert(XMLHttpRequest.responseText);
                       alert(textStatus);  
                   }  
									
				});
			}
		}
	
		$(document).ready(function() {
			changeCode();
			///$("#codeImg").bind("click", changeCode);
			
			//alert($("#loginbox").height());height:45%;
			$("#opacityDiv").height($("#loginbox").height());
			//alert($("#opacityDiv").height());
		});

		$(document).keyup(function(event) {
			if (event.keyCode == 13) {
				$("#to-recover").trigger("click");
			}
		});

		function genTimestamp() {
			var time = new Date();
			return time.getTime();
		}

		function changeCode() {
			$("#codeImg").attr("src", "code.do?t=" + genTimestamp());
		}

		//客户端校验
		function check() {

			if ($("#loginname").val() == "") {

				$("#loginname").tips({
					side : 2,
					msg : '用户名不得为空',
					bg : '#AE81FF',
					time : 3
				});
				showfh();
				$("#loginname").focus();
				return false;
			} else {
				$("#loginname").val(jQuery.trim($('#loginname').val()));
			}

			if ($("#password").val() == "") {

				$("#password").tips({
					side : 2,
					msg : '密码不得为空',
					bg : '#AE81FF',
					time : 3
				});
				showfh();
				$("#password").focus();
				return false;
			}
			if ($("#code").val() == "") {

				$("#code").tips({
					side : 1,
					msg : '验证码不得为空',
					bg : '#AE81FF',
					time : 3
				});
				showfh();
				$("#code").focus();
				return false;
			}

			

			return true;
		}

		function savePaw() {
			if (!$("#saveid").attr("checked")) {
				$.cookie('loginname', '', {
					expires : -1
				});
				$.cookie('password', '', {
					expires : -1
				});
				$("#loginname").val('');
				$("#password").val('');
			}
		}

		function saveCookie() {
			if ($("#saveid").attr("checked")) {
				$.cookie('loginname', $("#loginname").val(), {
					expires : 7
				});
				$.cookie('password', $("#password").val(), {
					expires : 7
				});
			}
		}
		function quxiao() {
			$("#loginname").val('');
			$("#password").val('');
		}
		
		jQuery(function() {
			var loginname = $.cookie('loginname');
			var password = $.cookie('password');
			if (typeof(loginname) != "undefined"
					&& typeof(password) != "undefined") {
				$("#loginname").val(loginname);
				$("#password").val(password);
				//$("#saveid").attr("checked", true);
				$("#code").focus();
			}
		});
		
	</script>
	<script>
		//TOCMAT重启之后 点击左侧列表跳转登录首页 
		if (window != top) {
			top.location.href = location.href;
		}
	</script>
	<c:if test="${'1' == pd.msg}">
		<script type="text/javascript">
		$(tsMsg());
		function tsMsg(){
			alert('此用户在其它终端已经早于您登录,您暂时无法登录');
		}
		</script>
	</c:if>
	<c:if test="${'2' == pd.msg}">
		<script type="text/javascript">
			$(tsMsg());
			function tsMsg(){
				alert('您被系统管理员强制下线');
			}
		</script>
	</c:if>
	<script src="static/login/js/bootstrap.min.js"></script>
	<script src="static/js/jquery-1.7.2.js"></script>
	<script src="static/login/js/jquery.easing.1.3.js"></script>
	<script src="static/login/js/jquery.mobile.customized.min.js"></script>
	<script src="static/login/js/camera.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript" src="static/js/jquery.cookie.js"></script>
</body>

</html>