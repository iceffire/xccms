
$().ready(function() {
        // 字符验证
        $.validator.addMethod("stringCheck", function(value, element) {
            return this.optional(element) || chkChar(value);///^[\u0391-\uFFE5\w]+$/.test(value);
        }, "您的输入中含有非法字符<,\",%,>,~,&,?,',请重新输入!");
    
     // 手机号码验证
		$.validator.addMethod("isMobile", function(value,
				element) {
			var length = value.length;
			var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
			return this.optional(element)
					|| (length == 11 && mobile.test(value));
		}, "请正确填写您的手机号码");
		
		// 电话号码验证
		$.validator.addMethod("isTel",
				function(value, element) {
					var tel = /^\d{3,4}-?\d{7,8}$/; // 电话号码格式010-12345678
					return this.optional(element)
							|| (tel.test(value));
				}, "请正确填写您的电话号码,电话号码格式010-12345678");
		$.validator.addMethod("isIdentify",
				function(value, element) {
					var tel = /(^\d{15}$)|(^\d{17}(\d|X)$)/; //身份证号码校验
					return this.optional(element)
							|| (tel.test(value));
				}, "请正确填写身份证,身份证号码为15位或者18位");
		$.validator.addMethod("Fund",
				function(value, element) {
					if (parseFloat(value) <= 0){
						return false;
					}
					var tel = /^[+]?(([1-9]\d*[.]?)|(0.))(\d{0,2})?$/; // 金额正数
					return this.optional(element)
							|| (tel.test(value));
				}, "金额必须大于零且小数位不能超过2位!");
		$.validator.addMethod("icCardId",
				function(value, element) {
					var tel = /^(\d{1,14})?$/; // 会员卡
					return this.optional(element)
							|| (tel.test(value));
				}, "会员卡不能大于14位且必须是数字!");
		$.validator.addMethod("isBankacount",
				function(value, element) {
					var tel = /^(\d{1,20})?$/; // 会员卡
					return this.optional(element)
							|| (tel.test(value));
				}, "银行账户不能大于20位且必须是数字!");
		// 联系电话(手机/电话皆可)验证
		$.validator
				.addMethod(
						"isMobileOrTel",
						function(value, element) {
							var length = value.length;
							var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
							var tel = /^\d{3,4}-\d{7,8}$/;
							var tel2 = /^\d{7,8}$/;
							return this.optional(element)
									|| (tel.test(value)
											|| mobile.test(value) || tel2
											.test(value));

						}, "请正确填写您的联系电话");
		//判断是否为数字
		 $.validator.addMethod("isFloat", function(value, element) {
	            return this.optional(element) || isFloat(value,2);///^[\u0391-\uFFE5\w]+$/.test(value);
	        }, "请正确填写数值,注意整数及小数部分!");
		 //邮箱判断
			var isMail = function(value, element) {
				var email = value;
				var mess = "";

				var emailTest= /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
				if(!emailTest.test(email)){ 
					mess="请输入有效的邮箱!(例如：sumapay@126.com)";
				} 

				if (mess == "") {
					return this.optional(element) || true;
				} else {
					return this.optional(element) || false;
				}
			};
			$.validator.addMethod("isEmail", isMail,
					"请输入有效的邮箱!(例如：sumapay@126.com)");

			// 邮政编码验证
			$.validator.addMethod("isZipCode", function(value,
					element) {
				var tel = /^[0-9]{6}$/;
				return this.optional(element) || (tel.test(value));
			}, "请正确填写您的邮政编码");
    });


//带小数数值,小数点后n位
function isFloat(value,n) {
    var RefString = "0123456789.";
    if (value.length == 0) {
        return (false);
    }
    var countDot=0;
    var dotBackCount=0;
    var zeroFirst=false;
    for (Count = 0; Count < value.length; Count++) {
        TempChar = value.substring(Count, Count + 1);
        // 不等于（0--9 or 小数点）时返回错
        if (RefString.indexOf(TempChar, 0) == -1) {
            return (false);
        }else{
        	//小数点开头，视为错
        	if(Count==0 && TempChar=="."){
        		return (false);
        	}
        	//第一位数字：0 第二位数字不为小数点时，视为错
        	if(Count==1 && zeroFirst==true && TempChar!="."){return false;}
        	if(Count==0 && TempChar=="0"){zeroFirst=true;}
        	if(TempChar=="."){
        		countDot++;
        		if(countDot>=2){
        			return (false);
        		}
        	}else{
	        	if(countDot>0){
	        		dotBackCount++;
	        		if(dotBackCount>n){
	        			return (false);
	        		}
	        	}
        	}
        }
    }
    return (true);
}
//检查输入中的非法字符
function chkChar(InString) {
    var RefString = "<";
    var RefString2 = "%";
    var RefString3 = "\"";
    var RefString4 = ">";
    var RefString5 = "~";
    var RefString6 = "&";
    var RefString7 = "?";
    var RefString8 = "'";
    for (Count = 0; Count < InString.length; Count++) {
        TempChar = InString.substring(Count, Count + 1);
        if ((RefString.indexOf(TempChar, 0) == 0) || (RefString2.indexOf(TempChar, 0) == 0) || (RefString3.indexOf(TempChar, 0) == 0) || (RefString4.indexOf(TempChar, 0) == 0) || (RefString5.indexOf(TempChar, 0) == 0) || (RefString6.indexOf(TempChar, 0) == 0) || (RefString7.indexOf(TempChar, 0) == 0) || (RefString8.indexOf(TempChar, 0) == 0)) {
            return (false);
        }
    }
    return (true);
}

$.extend($.validator.messages, {
required: "必填字段",
remote: "请修正该字段",
email: "请输入正确格式的电子邮件",
ul: "请输入合法的网址",
date: "请输入合法的日期",
dateISO: "请输入合法的日期 (ISO).",
number: "请输入合法的数字",
digits: "只能输入整数",
creditcard: "请输入合法的信用卡号",
equalTo: "请再次输入相同的值",
accept: "请输入拥有合法后缀名的字符串",
maxlength: $.validator.format("请输入一个 长度最多是 {0} 的字符串"),
minlength: $.validator.format("请输入一个 长度最少是 {0} 的字符串"),
rangelength: $.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串"),
range: $.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
max: $.validator.format("请输入一个最大为{0} 的值"),
min: $.validator.format("请输入一个最小为{0} 的值")
});

