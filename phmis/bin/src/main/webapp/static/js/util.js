 

function getParamer(paramer) {
	//alert(window.location.href);
	var url = window.location.href.split("?")[1]; /*获取url里"?"后面的值*/
	if(url!=undefined){
		if (url.indexOf("&") > 0) { /*判断是否是一个参数还是多个参数*/
			urlParamArry = url.split("&"); /*分开每个参数，并放到数组里*/
			for (var i = 0; i < urlParamArry.length; i++) {
				var paramerName = urlParamArry[i].split("="); /*把每个参数名和值分开，并放到数组里*/
				if (paramer == paramerName[0]) { /*匹配输入的参数和数组循环出来的参数是否一样*/
					return paramerName[1]; /*返回想要的参数值*/
				}
			}
		} else { /*判断只有个参数*/
			var paramerValue = url.split("=")[1];
			if (paramer == url.split("=")[0]) {
				return paramerValue
			} else {
				return "-9999";
			}
		}
	}else{
		//alert("");
		return "-9999";
	}
}
 
function pad(num, n) {  
    var len = num.toString().length;  
    while(len < n) {  
        num = "0" + num;  
        len++;  
    }  
    return num;  
}  
/** 
 * @param 页面日期控件取得的日期（yyyy-mm-dd）  
 * */  
function getMonDayAndSunDay(datevalue){  
    var dateValue = datevalue;  
    var arr = dateValue.split("-")  
    //月份-1 因为月份从0开始 构造一个Date对象  
    var date = new Date(arr[0],arr[1]-1,arr[2]);  
  
    var dateOfWeek = date.getDay();//返回当前日期的在当前周的某一天（0～6--周日到周一）  
  
    var dateOfWeekInt = parseInt(dateOfWeek,10);//转换为整型  
  
    if(dateOfWeekInt==0){//如果是周日  
        dateOfWeekInt=7;  
    }  
    var aa = 7-dateOfWeekInt;//当前于周末相差的天数  
  
    var temp2 = parseInt(arr[2],10);//按10进制转换，以免遇到08和09的时候转换成0  
    var sunDay = temp2+aa;//当前日期的周日的日期  
    var monDay = sunDay-6//当前日期的周一的日期  
  
    var startDate = new Date(arr[0],arr[1]-1,monDay);  
    var endDate = new Date(arr[0],arr[1]-1,sunDay);  
  
    var sm = parseInt(startDate.getMonth())+1;//月份+1 因为月份从0开始  
    var em = parseInt(endDate.getMonth())+1;  
  
//  alert("星期一的日期："+startDate.getYear()+"-"+sm+"-"+startDate.getDate());  
//  alert("星期日的日期："+endDate.getYear()+"-"+em+"-"+endDate.getDate());  
    var start = startDate.getYear()+"-"+sm+"-"+startDate.getDate();  
    var end = endDate.getFullYear()+"-"+em+"-"+endDate.getDate();  
    var result = new Array();  
    result.push(start);  
    result.push(end);   
    return new Date(end);  
}  


function getWeek(datevalue){  
    var dateValue = datevalue;  
    var arr = dateValue.split("-")  
    //月份-1 因为月份从0开始 构造一个Date对象  
    var date = new Date(arr[0],arr[1]-1,arr[2]);  
  
    var dateOfWeek = date.getDay();//返回当前日期的在当前周的某一天（0～6--周日到周一）  
    return dateOfWeek;
}  

function getMonth(datevalue){  
    var dateValue = datevalue;  
    var arr = dateValue.split("-")  
    //月份-1 因为月份从0开始 构造一个Date对象  
    var date = new Date(arr[0],arr[1]-1,arr[2]);  
  
    var dateOfWeek = date.getMonth()+1;//返回当前日期的在当前周的某一天（0～6--周日到周一）  
    var dd = date.getDate();//返回当前日期的在当前周的某一天（0～6--周日到周一）  
    return dateOfWeek+"月"+dd+"号";
}  

/**
 * 得到本季度开始的月份
 * @param month 需要计算的月份
 ***/ 
function getQuarterSeasonStartMonth(month){ 
    var quarterMonthStart=0; 
    var spring=0; //春  
    var summer=3; //夏  
    var fall=6;   //秋  
    var winter=9;//冬  
    //月份从0-11  
    if(month<3){ 
        return spring; 
    } 
     
    if(month<6){ 
        return summer; 
    } 
     
    if(month<9){ 
        return fall; 
    } 
     
    return winter; 
}; 
 
function getLastDay(datevalue){
	var dateValue = datevalue;  
    var arr = dateValue.split("-")  
     //月份-1 因为月份从0开始 构造一个Date对象  
    var date = new Date(arr[0],arr[1]-1,arr[2]); 
    var month=date.getMonth()+1;
    var year = date.getFullYear();//返回当前日期的在当前周的某一天（0～6--周日到周一）  
	var  day = new Date(year,month,0);   
	var lastdate = year + '-' + month + '-' + day.getDate();    //获取月份的最后一天
	 
	 return new Date(lastdate);  
}

/**
 * 获得该月的天数
 * @param year年份
 * @param month月份
 * */ 
function getMonthDays(year,month){ 
    //本月第一天 1-31  
    var relativeDate=new Date(year,month,1); 
    //获得当前月份0-11  
    var relativeMonth=relativeDate.getMonth(); 
    //获得当前年份4位年  
    var relativeYear=relativeDate.getFullYear(); 
     
    //当为12月的时候年份需要加1  
    //月份需要更新为0 也就是下一年的第一个月  
    if(relativeMonth==11){ 
        relativeYear++; 
        relativeMonth=0; 
    }else{ 
        //否则只是月份增加,以便求的下一月的第一天  
        relativeMonth++; 
    } 
    //一天的毫秒数  
    var millisecond=1000*60*60*24; 
    //下月的第一天  
    var nextMonthDayOne=new Date(relativeYear,relativeMonth,1); 
    //返回得到上月的最后一天,也就是本月总天数  
    return new Date(nextMonthDayOne.getTime()-millisecond).getDate(); 
}; 
 

 function  getbanYear(datevalue){   
	var dateValue = datevalue;  
    var arr = dateValue.split("-")   
    //获取当前时间  
    var currentDate= new Date(arr[0],arr[1]-1,arr[2]); 
    //获得当前月份0-11  
    var currentMonth=currentDate.getMonth(); 
    if(currentMonth<=5)
    	currentMonth=5;
    else
    	currentMonth=11;
    //获得当前年份4位年  
    var currentYear=currentDate.getFullYear();  
	var  day = new Date(currentYear,(currentMonth+1),0);    
	var lastdate = currentYear + '-' + (currentMonth+1) + '-' + day.getDate();    //获取月份的最后一天
	 
	 return new Date(lastdate);  
      
}; 

function  getquanYear(datevalue){   
	var dateValue = datevalue;  
    var arr = dateValue.split("-")   
   //获取当前时间  
   var currentDate= new Date(arr[0],arr[1]-1,arr[2]);  
   //获得当前年份4位年  
   var currentYear=currentDate.getFullYear();  
	var  day = new Date(currentYear,12,0);    
	var lastdate = currentYear + '-' + 12 + '-' + day.getDate();    //获取月份的最后一天
	 
	 return new Date(lastdate);  
     
}; 
 
function getQuarter(datevalue) {
	var dateValue = datevalue;  
    var arr = dateValue.split("-")   
    //获取当前时间  
    var currentDate= new Date(arr[0],arr[1]-1,arr[2]); 
    //获得当前月份0-11  
    var month=currentDate.getMonth(); 
    if(month  < 3) {
        return '1';
    }else if(month < 6) {
        return '2';
    }else if(month <9) {
        return '3';
    }else if(month <12) {
        return '4';
    }
};
function getBanYear(datevalue) {
	var dateValue = datevalue;  
    var arr = dateValue.split("-")   
    //获取当前时间  
    var currentDate= new Date(arr[0],arr[1]-1,arr[2]); 
    //获得当前月份0-11  
    var month=currentDate.getMonth(); 
    if(month  < 7) {
        return '1';
    }else if(month < 13) {
        return '2';
    } 
};
function getCurrentSeason(datevalue){
	
	 //起止日期数组  
    var startStop=new Array(); 
	var dateValue = datevalue;  
    var arr = dateValue.split("-")   
    //获取当前时间  
    var currentDate= new Date(arr[0],arr[1]-1,arr[2]); 
    //获得当前月份0-11  
    var currentMonth=currentDate.getMonth(); 
    //获得当前年份4位年  
    var currentYear=currentDate.getFullYear(); 
    //获得本季度开始月份  
    var quarterSeasonStartMonth=getQuarterSeasonStartMonth(currentMonth); 
    //获得本季度结束月份  
    var quarterSeasonEndMonth=quarterSeasonStartMonth+2; 
     
    //获得本季度开始的日期  
    var quarterSeasonStartDate=new Date(currentYear,quarterSeasonStartMonth,1); 
    //获得本季度结束的日期  
    var quarterSeasonEndDate=new Date(currentYear,quarterSeasonEndMonth,getMonthDays(currentYear, quarterSeasonEndMonth)); 
    //加入数组返回  
    startStop.push(quarterSeasonStartDate); 
    startStop.push(quarterSeasonEndDate); 
    //返回 
  //  alert(quarterSeasonEndDate);
    return  quarterSeasonEndDate; 
}

/**
 * 
 * 描述：js实现的map方法
 * 
 * @returns {Map}
 */
function Map() {
	var struct = function(key, value) {
		this.key = key;
		this.value = value;
	};
	// 添加map键值对
	var put = function(key, value) {
		for ( var i = 0; i < this.arr.length; i++) {
			if (this.arr[i].key === key) {
				this.arr[i].value = value;
				return;
			}
		}
		;
		this.arr[this.arr.length] = new struct(key, value);
	};
	// 根据key获取value
	var get = function(key) {
		for ( var i = 0; i < this.arr.length; i++) {
			if (this.arr[i].key === key) {
				return this.arr[i].value;
			}
		}
		return null;
	};
	// 根据key删除
	var remove = function(key) {
		var v;
		for ( var i = 0; i < this.arr.length; i++) {
			v = this.arr.pop();
			if (v.key === key) {
				continue;
			}
			this.arr.unshift(v);
		}
	};
	// 获取map键值对个数
	var size = function() {
		return this.arr.length;
	};
	// 判断map是否为空
	var isEmpty = function() {
		return this.arr.length <= 0;
	};
	this.arr = new Array();
	this.get = get;
	this.put = put;
	this.remove = remove;
	this.size = size;
	this.isEmpty = isEmpty;
}



/**
 * 验证
 * @param validatorgroupIds {需要验证的控件组ID} 
 */
function validator(validatorgroupIds){
	if(validatorgroupIds != undefined){
		var valigroupIds = validatorgroupIds.split(",");
		for(i=0;i<valigroupIds.length;i++){
			if(!(jQuery.formValidator.pageIsValid(valigroupIds[i]))){
				return false;
			}
		}
	}else if(validatorgroupIds == undefined){
		if(!(jQuery.formValidator.pageIsValid())){
			return false;
		}
	}
	return true;
}

/**
 * 设置验证需要验证和不需要验证的控件组
 * @param unvalIds {不需要验证的控件组ID} 
 * @param valIds {需要验证的控件组ID}
 */
function unvalidator(unvalIds, valIds){
	if(unvalIds!=undefined){
		var unIds = unvalIds.split(",");
		//不校验
		for(i=0;unIds!=undefined&&i<unIds.length;i++){
			jQuery("#"+unIds[i]).unFormValidator(true);
			//jQuery("#"+unIds[i]).attr("disabled",true).unFormValidator(true);
		}
	}
	if(valIds!=undefined){
		//校验
		var Ids = valIds.split(",");
		for(i=0;Ids!=undefined&&i<unIds.length;i++){
			jQuery("#"+valIds[i]).unFormValidator(false);
			//jQuery("#"+valIds[i]).attr("disabled",false).unFormValidator(false);
		}
	}
}

function closeWin(){
	 var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
 	 parent.layer.close(index);
}


/**
 * 
 * 描述：js实现的map方法
 * 
 * @returns {Map}
 */
function Map() {
	var struct = function(key, value) {
		this.key = key;
		this.value = value;
	};
	// 添加map键值对
	var put = function(key, value) {
		for ( var i = 0; i < this.arr.length; i++) {
			if (this.arr[i].key === key) {
				this.arr[i].value = value;
				return;
			}
		}
		;
		this.arr[this.arr.length] = new struct(key, value);
	};
	// 根据key获取value
	var get = function(key) {
		for ( var i = 0; i < this.arr.length; i++) {
			if (this.arr[i].key === key) {
				return this.arr[i].value;
			}
		}
		return null;
	};
	// 根据key删除
	var remove = function(key) {
		var v;
		for ( var i = 0; i < this.arr.length; i++) {
			v = this.arr.pop();
			if (v.key === key) {
				continue;
			}
			this.arr.unshift(v);
		}
	};
	// 获取map键值对个数
	var size = function() {
		return this.arr.length;
	};
	// 判断map是否为空
	var isEmpty = function() {
		return this.arr.length <= 0;
	};
	this.arr = new Array();
	this.get = get;
	this.put = put;
	this.remove = remove;
	this.size = size;
	this.isEmpty = isEmpty;
}


function isNumber(value) {         //验证是否为数字
    var patrn = /^(-)?\d+(\.\d+)?$/;
    if (patrn.exec(value) == null || value == "") {
        return false
    } else {
        return true
    }
}



function styleCheckbox(table) {
 
	/*$(table).find('input:checkbox').addClass('ace')
	.wrap('<label />')
	.after('<span class="lbl align-top" />')


	$('.ui-jqgrid-labels th[id*="_cb"]:first-child')
	.find('input.cbox[type=checkbox]').addClass('ace')
	.wrap('<label />').after('<span class="lbl align-top" />');*/
 
}


//unlike navButtons icons, action icons in rows seem to be hard-coded
//you can change them like this in here if you want
function updateActionIcons(table) {
	 
	var replacement = 
	{
		'ui-icon-pencil' : 'icon-pencil blue',
		'ui-icon-trash' : 'icon-trash red',
		'ui-icon-disk' : 'icon-ok green',
		'ui-icon-cancel' : 'icon-remove red'
	};
	$(table).find('.ui-pg-div span.ui-icon').each(function(){
		var icon = $(this);
		var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
		if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
	})
	 
}

//replace icons with FontAwesome icons like above
function updatePagerIcons(table) {
	var replacement = 
	{
		'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
		'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
		'ui-icon-seek-next' : 'icon-angle-right bigger-140',
		'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
	};
	$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
		var icon = $(this);
		var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
		
		if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
	})
}

function enableTooltips(table) {
	$('.navtable .ui-pg-button').tooltip({container:'body'});
	$(table).find('.ui-pg-div').tooltip({container:'body'});
}

function uuid(len, radix) {
    var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
    var uuid = [], i;
    radix = radix || chars.length;
 
    if (len) {
      // Compact form
      for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random()*radix];
    } else {
      // rfc4122, version 4 form
      var r;
 
      // rfc4122 requires these characters
      uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
      uuid[14] = '4';
 
      // Fill in random data.  At i==19 set the high bits of clock sequence as
      // per rfc4122, sec. 4.1.5
      for (i = 0; i < 36; i++) {
        if (!uuid[i]) {
          r = 0 | Math.random()*16;
          uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
        }
      }
    }
 
    return uuid.join('');
}
