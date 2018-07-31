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
	<style>
	#tabs-project .progress{
	background: url(/images/bg_state-1.jpg) no-repeat -157px 0px;
	height: 30px;
	width: 30px;
	float: left;
	position: relative;
}
	
#tabs-project .progress1 {
	background-position: 0 0;
	width: 157px;
}

#tabs-project .progress div.detial {
	position: relative;
	left: -40px;
	top: 40px;
	width: 137px;
	height: 80px;
	z-index: 1000;
	line-height: 18px;
}

#tabs-project .progress3 {
	background-position: 0 -58px;
	width: 157px;
}

#tabs-project .progress_cancel {
	background-position: -157px -90px;
}

#tabs-project .progress_unstart {
	background-position: -157px -60px;
} 
	 	#t_table td label {font-size:15px;}
	 	
	 	body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	font-size:12px;
	/* background-image: url(resources/fc/images/bj.jpg); */
}
	
	</style>
 </head>
 <body> 
	    	<div style="margin: 15px auto; height: 50px;" id="tabs-project">
	    	<table style="table-layout:fixed" >
	    	<tr> 
	    	 <c:forEach items="${varList}" var="var"   varStatus="stat">
	    	  <td> 
						     <div class="progress progress"  title="${var.taskNode}"></div>
					 
	    	    <div class="progress progress1">
		    			 
				    	 		
						    </div>  
						    
	    	  </td>
	    	  </c:forEach>
	    	  <td>  
				  <div class='progress progress_unstart'  ></div>   
	    	  </td>
	    	  
	    	</tr>
	    	<tr>
	    
	    	
	    	 <c:forEach items="${varList}" var="var"   varStatus="stat">
	    	 <td>
	    	<div class="detial">
							       <b>${var.taskNode}</b><br/>
							        [<span style="color:red;">处理时间:
							       ${var.handTime}</span>]<br/>
							       [<span>操作人：${var.handleUserName}]</span>
							    </div>
							    </td>
							   
	    	</c:forEach>
	    		  <td>  
				 
	    	  </td>
	    	 </tr>
	    	</table>
	    	  
    	 		<table id="t_table" class="table  table-bordered">
    	 		
    	 		 
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
		   
			<tr> 
			  <td class="value" id="trans">
			  	  <input type="hidden"  name="outCome" id="outCome" >
			  	  <input type="hidden"  name="outComeName" id="outComeName" >
			  	  <input type="hidden"  name="assigneeVariable" id="assigneeVariable"  >
			  	  <input type="hidden"  name="exclusiveGateway" id="exclusiveGateway" >
			     <c:forEach items="${modelList}" var="var" varStatus="vs">
			         <input type="radio" id="outRadio"  class="i-checks"    name="outRadio"
			          value="${var.transKey}@${var.assigneeVariable}@${var.isJointlySign}@${var.transName}@${var.candidateUsers}@${var.type}@${var.name}" 
			          onclick="changeModel(this);" />
			           
			        <label for="trans">${var.transName}</label> 
			        <c:if test="${not empty var.actUserTaskModels}"> 
			         	<span id="checkSelect" style="display:none">
			         	 <c:if test="${not empty var.name}"><font color=red>[${var.name}]</font></c:if>
						 <c:forEach items="${var.actUserTaskModels}" var="var1" varStatus="vs"> 
						 
						  <input  type="radio" id="xxx"    
						   name="ddd" value="${var1.transKey}@${var1.assigneeVariable}@${var1.isJointlySign}@${var1.transName}@${var1.candidateUsers}@${var1.type}@${var1.name}" 
			          onclick="changeModelTwo(this);" />
			             <label for="xxx">${var1.transName}</label>
			           
					  </c:forEach> 
					  ，</span>
					 </c:if>
			        
			     </c:forEach> 
			  </td>
		 	</tr> 
		 	<tr id="nextTaskAssignee" style="display:none"> 
			  <td class="value">
			 <div class="form-group" style="width:60%">  
   			 <div class="input-group col-xs-12">
          	
          	 <div class="input-group-btn">  
            <button name="type" id="nextNodeAssigenn" class="form-control btn " style="width: auto;">  
                指定下一步处理人
            </button>  
        </div>  
        <input type="text" name="assigneeName" readonly id="assigneeName" class="form-control" >  
        <span class="input-group-btn" style="align:left">  
            <button class="btn btn-success"  id="search_submit" onclick="selectUser();"  >	
         			    <span class="glyphicon glyphicon-user"></span>选择
		   </button>  
        </span>  
	    </div>  
	</div>  
			 
				 <input name="assignee" id="assignee" type="hidden"> 
					  
				 
				 
			 	  </td>
		 	</tr>
		 	<tr id="nextTaskJointlySign" style="display:none"> 
			  <td class="value">
			  <div class="form-group" style="width:60%">  
    <div class="input-group col-xs-12">   
          	
          	 <div class="input-group-btn">  
            <button id="nextNodeJoin"  name="type" class="form-control btn " style="width: auto;">  
                指定下一步处理人
            </button>  
        </div>  
        <input type="text" name="jointlySignerName" readonly  id="jointlySignerName" class="form-control" >  
        <span class="input-group-btn" style="align:left">  
            <button class="btn btn-success"  id="search_submit" onclick="selectUser();"  >	
         			    <span class="glyphicon glyphicon-user"></span>选择
		   </button>  
        </span>  
	    </div>  
	</div>  
			 
				 <input name="jointlySigner" id="jointlySigner" type="hidden"> 
					 
          
				 
			 	  </td>
		 	</tr>
		 	<tr>
			<td align="center" >
				 <button  type='button' onclick='completeTask();' class='btn btn-sm btn-info'><span class="glyphicon glyphicon-save"></span> 确认提交</button> 
			</td>
		</tr>  
		 </table>
     <script type="text/javascript">
	       $(document).ready(function () {
	         $(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green",})
	       });
 				/**
			 * 单分支模式/多分支模式切换
			 */
			 $("input:radio[name='outRadio']").on('ifChecked', function(event){
			      // alert($(this).val());
			       changeModel(this);
			});
			var isJointlySign="";
			var candidateUsers="";
			var isJointlySign="";
			var assigneeVariable="";
			var type="";
			var auditContent="";
			function changeModel(obj) {
		       $("input:radio[name='ddd']").attr("checked",false);
			    var radioValue=obj.value;
			    var arrayValue=radioValue.split("@");
			    var transKey=arrayValue[0];
			    assigneeVariable=arrayValue[1];
			    isJointlySign=arrayValue[2];
			    var transName=arrayValue[3];
			    candidateUsers=arrayValue[4];
			    type=arrayValue[5];
			    var name=arrayValue[6];
			    $("#outCome").val(transKey);
			    $("#outComeName").val(transName);	
			    $("#auditContent").val(transName);	
			    auditContent=transName;			   
			    $("#assigneeVariable").val(assigneeVariable);
			   // alert(type);
			   // debugger
				if (assigneeVariable.indexOf('candidateGroups')!=-1) {
					 $("#nextTaskAssignee").hide();
					$("#nextTaskJointlySign").hide();
				}else if (assigneeVariable == 'creater'){
					 $("#nextTaskAssignee").hide();
					 $("#nextTaskJointlySign").hide();				     
				}else if (assigneeVariable == 'assignee'){
					$("#nextTaskAssignee").show();
					$("#nextTaskJointlySign").hide();					
					$("#nextNodeAssigenn").text("指定["+name+"]处理人");		
				}else if (assigneeVariable == 'jointlySign'){
					$("#nextTaskAssignee").hide();
					$("#nextTaskJointlySign").show(); 
					$("#nextNodeJoin").text("指定["+name+"]处理人");		
				}
				if(type=='exclusiveGateway'){				   
				   $("#checkSelect").show(); 
				}else{ 
				   $("#checkSelect").hide(); 
				}
				

			}
			
			 function changeModelTwo(obj) {
			    // debugger;
			    var radioValue=obj.value;
			    var arrayValue=radioValue.split("@");
			    var transKey=arrayValue[0];
			    assigneeVariable=arrayValue[1];
			    isJointlySign=arrayValue[2];
			    var transName=arrayValue[3];
			    candidateUsers=arrayValue[4];
			    type=arrayValue[5];
			    var name=arrayValue[6];
			  //  alert(assigneeVariable);
			  //  $("#outCome").val(transKey);
			   // $("#outComeName").val(transName);					   
			     $("#assigneeVariable").val(assigneeVariable);
			   // alert(type);
			     $("#exclusiveGateway").val(transKey);		
			    
				 if (assigneeVariable.indexOf('candidateGroups')!=-1) {
					 $("#nextTaskAssignee").hide();
					$("#nextTaskJointlySign").hide();
				}else if (assigneeVariable == ''){
					 $("#nextTaskAssignee").hide();
					 $("#nextTaskJointlySign").hide();	
						     
				}
				else if (assigneeVariable == 'creater'){
					 $("#nextTaskAssignee").hide();
					 $("#nextTaskJointlySign").hide();				     
				}else if (assigneeVariable == 'assignee'){
					$("#nextTaskAssignee").show();
					$("#nextTaskJointlySign").hide();
					$("#nextNodeAssigenn").text("指定["+name+"]处理人");		
				}else if (assigneeVariable == 'jointlySign'){
					$("#nextTaskAssignee").hide();
					$("#nextTaskJointlySign").show(); 
					 $("#nextNodeJoin").text("指定["+name+"]处理人");	
				} 	
				$("#auditContent").val(auditContent+" 发往："+name);		
				 
				

			}
 			 function completeTask(){ 
 			    top.jzts();
 			    var radioChecked=$("#outCome").val();
				if(radioChecked==undefined||radioChecked==''){
					$("#trans").tips({
						side:3,
			            msg:'必须选择审批节点',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#trans").focus();
					 $(top.hangge());  
					return false;
				}else{
					 if (assigneeVariable == 'assignee'){
					    var assignee=$("#assigneeName").val();
					    if(assignee==undefined||assignee==''){
							$("#assigneeName").tips({
								side:3,
					            msg:'必须选择下一节点处理人',
					            bg:'#AE81FF',
					            time:2
					        });
							$("#assigneeName").focus();
							$(top.hangge());  
							return;
					   } 
					 }
					 else if (assigneeVariable == 'jointlySign'){
						var jointlySigner=$("#jointlySignerName").val();
					    if(jointlySigner==undefined||jointlySigner==''){
							$("#jointlySignerName").tips({
								side:3,
					            msg:'必须选择下一节点处理人',
					            bg:'#AE81FF',
					            time:2
					        });
							$("#jointlySignerName").focus();
							$(top.hangge());  
							return;
					  }  
					}
				}
				
				
                var url = "/actRuTask/taskHandle";
                $.ajax({
						type: "POST",
						url: url, 
						contentType: 'application/json', // 这句不加出现415错误:Unsupported Media Type
						data:JSON.stringify({   
							  'auditContent':$("#auditContent").val(),
							  'outComeName':$("#outComeName").val(),
							  'outCome':$("#outCome").val(),
							  'assigneeVariable':$("#assigneeVariable").val(),
							  'assignee':$("#assignee").val(),
							  'jointlySigner':$("#jointlySigner").val(),
							  'exclusiveGateway':$("#exclusiveGateway").val(),
							  'taskId':'${taskId}'
						  }),
						dataType:'json',
						cache: false,
						success: function(data){ 
							if(data && data!=""){ 
								if("success" == data.result){
								 $(top.hangge());  
								  //debugger
								  parent.parent.queryList(); 
		                          var index = parent.parent.layer.getFrameIndex(parent.window.name); //获取窗口索引
		                         
		      					  parent.parent.layer.close(index);
		      					  parent.parent.swal("任务处理成功！", "您已经成功处理了这笔任务。", "success");
								}
								else if("err" == data.result){ 
								   parent.parent.swal("任务处理失败！", data.msg, "err");	
								}						     
						    } 
						 }
				   }); 
			}
			
	    function selectUser(){ 
		 layer.open({
		 	type: 2, 
		 	closeBtn: 0,
		 	offset: 'auto',
		 	area: ['860px', '509px'],
		 	title:"<span class=\"glyphicon glyphicon-list\"></span>人员选择列表信息 ",
			content: '/html/task/actRuTask_assignee.html?roleCode='+candidateUsers+"&isJointlySign="+isJointlySign,
			btn: [' <span class="glyphicon glyphicon-user"></span>选择','<span class="glyphicon glyphicon-remove"></span>取消'],
			 
			 maxmin: true,
      			shadeClose: true, //点击遮罩关闭层
			yes: function(index, layero){
				var r=$(layero).find("iframe")[0].contentWindow.selectOne();
				if(r==undefined){ 
		            return;
			    }  
			    if(assigneeVariable=='jointlySign'){
			    	 $("#jointlySigner").val(r.no);  
			    	 $("#jointlySignerName").val(r.userName);  
			    }else{
			    	 $("#assignee").val(r.no);  
			    	 $("#assigneeName").val(r.userName);  
			    }
			   
			  //  alert(r.no);
			  //   alert(r.userName);
				layer.close(index);
			}, 
			no: function(index){
				//layer.open({content: '您选择了取消', time: 1});
				layer.close(index);
			}
		}); 
	}
					 
   </script>
   <!-- ace scripts --> 
	<!-- inline scripts related to this page -->
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="/static/js/jquery.tips.js"></script>
  <script type="text/javascript">
		$(top.hangge());
</script>	 
  
 </body>
</html>
