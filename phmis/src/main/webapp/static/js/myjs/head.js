var locat = (window.location+'').split('/'); 
$(function(){if('main'== locat[3]){locat =  locat[0]+'//'+locat[2];}else{locat =  locat[0]+'//'+locat[2]+'/'+locat[3];};});

var fmid = "fhindex";	//菜单点中状态
var mid = "fhindex";	//菜单点中状态
var fhsmsCount = 0;		//站内信总数
var USER_ID;			//用户ID
var user = "FH";		//用于即时通讯（ 当前登录用户）
var TFHsmsSound = '1';	//站内信提示音效
var websocket;			//websocket对象
var wimadress="";		//即时聊天服务器IP和端口
var oladress="";		//在线管理和站内信服务器IP和端口

function siMenu(id,fid,MENU_NAME,MENU_URL){
	if(id != mid){
		$("#"+mid).removeClass();
		mid = id;
	}
	if(fid != fmid){
		$("#"+fmid).removeClass();
		fmid = fid;
	}
	$("#"+fid).attr("class","active open");
	$("#"+id).attr("class","active");
	top.mainFrame.tabAddHandler('tab1_index1',MENU_NAME,MENU_URL);
	if(MENU_URL != "druid/index.html"){
		jzts();
	}
}

$(function(){
	getHeadMsg();	//初始页面最顶部信息
	
	getPasswordSetting();
});

//初始页面信息
function getHeadMsg(){
	/*$.ajax({
		type: "POST",
		url: locat+'/head/getList.do?tm='+new Date().getTime(),
    	data: encodeURI(""),
		dataType:'json',
		//beforeSend: validateData,
		cache: false,
		success: function(data){
			 $.each(data.list, function(i, list){
				 $("#user_info").html('<small>Welcome</small> '+list.NAME+'');//登陆者资料
				 user = list.USERNAME;
				 USER_ID = list.USER_ID;		//用户ID
//				 if(list.USERNAME != 'admin'){
//					 $("#systemset").hide();	//隐藏系统设置
//				 }
			 });
//			 fhsmsCount = Number(data.fhsmsCount);
//			 $("#fhsmsCount").html(Number(fhsmsCount));	//站内信未读总数
//			 TFHsmsSound = data.FHsmsSound;				//站内信提示音效
//			 wimadress = data.wimadress;				//即时聊天服务器IP和端口
//			 oladress = data.oladress;					//在线管理和站内信服务器IP和端口
//			 online();									//连接在线
		}
	});*/
}

//获取站内信未读总数(在站内信删除未读新信件时调用此函数更新未读数)
function getFhsmsCount(){
	$.ajax({
		type: "POST",
		url: locat+'/head/getFhsmsCount.do?tm='+new Date().getTime(),
    	data: encodeURI(""),
		dataType:'json',
		cache: false,
		success: function(data){
			 fhsmsCount = Number(data.fhsmsCount);
			 $("#fhsmsCount").html(Number(fhsmsCount));	//站内信未读总数
		}	
	});
}

//加入在线列表
function online(){
	if (window.WebSocket) {
		websocket = new WebSocket(encodeURI('ws://'+oladress)); //oladress在main.jsp页面定义
		websocket.onopen = function() {
			//连接成功
			websocket.send('[join]'+user);
		};
		websocket.onerror = function() {
			//连接失败
		};
		websocket.onclose = function() {
			//连接断开
		};
		//消息接收
		websocket.onmessage = function(message) {
			var message = JSON.parse(message.data);
			if(message.type == 'goOut'){
				$("body").html("");
				goOut("1");
			}else if(message.type == 'thegoout'){
				$("body").html("");
				goOut("2");
			}else if(message.type == 'senFhsms'){
				fhsmsCount = Number(fhsmsCount)+1;
				$("#fhsmsCount").html(Number(fhsmsCount));
				$("#fhsmsobj").html('<audio style="display: none;" id="fhsmstsy" src="static/sound/'+TFHsmsSound+'.mp3" autoplay controls></audio>');
				$("#fhsmstss").tips({
					side:3,
		            msg:'有新消息',
		            bg:'#AE81FF',
		            time:30
		        });
			}
		};
	}
}

//下线
function goOut(msg){
	window.location.href=locat+"/logout.do?msg="+msg;
}

//去通知收信人有站内信接收
function fhsmsmsg(USERNAME){
	var arrUSERNAME = USERNAME.split(';');
	for(var i=0;i<arrUSERNAME.length;i++){
		websocket.send('[fhsms]'+arrUSERNAME[i]);//发送通知
	}
}

//读取站内信时减少未读总数
function readFhsms(){
	fhsmsCount = Number(fhsmsCount)-1;
	$("#fhsmsCount").html(Number(fhsmsCount) <= 0 ?'0':fhsmsCount);
}

//修改个人资料
function editUserH(){
	 jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="个人资料";
	 diag.URL = locat+'/html/sys/user_update_pwd.html';
	 diag.Width = 469;
	 diag.Height = 469;
	 diag.CancelEvent = function(){ //关闭事件
		diag.close();
	 };
	 diag.show();
}

//系统设置
function editSys(){
	 jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="系统设置";
	 diag.URL = locat+'/head/goSystem.do';
	 diag.Width = 600;
	 diag.Height = 526;
	 diag.CancelEvent = function(){ //关闭事件
		diag.close();
	 };
	 diag.show();
}

//站内信
function fhsms(){
	console.log("站内信息");
	$("ul.submenu > li > a").each(function(i){
		if($(this).attr("onclick")!=undefined){
			var aOnclick=$(this).attr("onclick");
			if(aOnclick.includes("srsNotes_receiver_list.html")){
				$(this).trigger("click");
			}
		}
	 });
	/*	
	 jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="站内信";
	 diag.URL = locat+'/html/notes/srsNotes_receiver_list.html';
	 diag.Width = 900;
	 diag.Height = 500;
	 diag.CancelEvent = function(){ //关闭事件
		diag.close();
	 };
	 diag.show();
	 */
}

//是否启动“启用密码过期强制策略”，查看“密码即将到期提醒(天)：”
function getPasswordSetting(){
	/*
	$("#passwordSetting").html("系统安全策略");
	$("#passwordSetting").css("cursor","pointer ");
	
	$("#passwordSetting").click(function(){
		$("#sidebar li a").each(function (index, domEle){
			var onclickVar=$(this).attr("onclick");
			if(onclickVar!=null && onclickVar!=undefined && onclickVar.indexOf("account_security_setting.html")>-1){
				$(this).trigger("click");
			}
		});
	});
	*/
	$("#safeStra").css("display","none");
	/*$.ajax({
		type: "GET",
		url: '/SysStrategy/getSetting', 
		dataType:'json',
		cache: false,
		success: function(data){
			if(data && data!=""){
				if(data.length==0){
					$("#passwordSetting").html("系统安全策略没有初始化");
					$("#safeStra").css("display","block");
				}else if(data.length>1){
					$("#passwordSetting").html("系统安全策略条数："+data.length+"不唯一");
					$("#safeStra").css("display","block");
				}else{
					var isUsePassStrage=data[0].isUsePassStrage;//是否启用到期策略
					var passExpireRemindDays=data[0].passExpireRemindDays;//到期前多少天提醒
					console.log(isUsePassStrage+",,,"+data[0].isRemindUpdatePw);
					if(isUsePassStrage==1 && data[0].isRemindUpdatePw){
						$("#passwordSetting").html("密码即将到期请修改密码");
						$("#passwordSetting").css("cursor","pointer ");
						$("#passwordSetting").click(function(){
							$("#editUserH").trigger("click");
						});
						
						swal({
			                title: "密码即将到期",
			                text: "密码即将到期是否修改密码？",
			                type: "warning",
			                showCancelButton: true,
			                confirmButtonColor: "#DD6B55",
			                confirmButtonText: "是的，我要修改密码！",
			                cancelButtonText: "让我再考虑一下…",
			                closeOnConfirm: false,
			                closeOnCancel: false
			            }, function (isConfirm) {
			                if (isConfirm) {
			                	swal.close();
			                	$("#editUserH").trigger("click");
			                	
			                } else {
			                    //swal("已取消", "您取消了操作！", "error")
			                	swal.close();
			                }
			            })
			            $("#safeStra").css("display","block");
					}
				}
			}
		},
		error:function(err)
		{
			 alert("启用密码过期强制策略初始化数据失败");
		}
	});*/
}
//切换菜单
function changeMenus(){
	window.location.href=locat+'/main/yes';
}

//清除加载进度
function hangge(){
	$("#jzts").hide();
}

//显示加载进度
function jzts(){
	$("#jzts").show();
}