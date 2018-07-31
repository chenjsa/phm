var chinessEnNum_ = {exp:/^[\u4e00-\u9fa5a-zA-Z0-9_]+$/,msg:"输入项只能由汉字、数字、英文、下划线组成"};

var chinessEnNum = {exp:/^[\u4e00-\u9fa5a-zA-Z0-9]+$/,msg:"输入项只能由汉字、数字、英文组成"};

function vilidateInfo(elId,expObject,maxLength){
	if(!expObject.exp.test($("#"+elId).val())){
		$("#"+elId).tips({
			side:3,
		    msg:expObject.msg,
		    bg:'#AE81FF',
		    time:5
		});
		$("#"+elId).focus();
		return false;
	}
	console.log("maxlength:"+maxLength);
	if(null!=maxLength && maxLength!=''){
		var calLength=getByteLen($("#"+elId).val());
		if(maxLength<calLength){
			$("#"+elId).tips({
				side:3,
			    msg:"字符数:"+calLength+",超过最大值"+maxLength,
			    bg:'#AE81FF',
			    time:3
			});
			$("#"+elId).focus();
			return false;
		}
	}
	return;
}

//获取字符串长度（汉字算两个字符，字母数字算一个）
    function getByteLen(val) {
      var len = 0;
      for (var i = 0; i < val.length; i++) {
        var a = val.charAt(i);
        if (a.match(/[^\x00-\xff]/ig) != null) {
          len += 2;
        }
        else {
          len += 1;
        }
      }
      return len;
    }
